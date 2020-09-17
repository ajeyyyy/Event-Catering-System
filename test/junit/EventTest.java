package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import event_management.data.EventDAO;
import event_management.model.Event;
import event_management.model.EventErrorMsgs;
import event_management.model.User;
import event_management.util.CmnUtil;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class EventTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void zDestroy() {
		EventDAO.deleteEvents();

	}

	@Test
	@FileParameters("resources/event_name.csv")
	public void testEventName(String id, String username, String date, String startTime, String duration,
			String hallName, String estAttendees, String name, String foodType, String meal, String mealFormality,
			String drinkType, String entertainmentItems, String eventStatus, String estCost, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}

		Event event = new Event();
		EventErrorMsgs errorMsgs = new EventErrorMsgs();
		event.setErrorMsgs(errorMsgs);
		setEvent(event, id, username, date, startTime, duration, hallName, estAttendees, name, foodType, meal,
				mealFormality, drinkType, entertainmentItems, eventStatus);

		event.validate(Event.EVENT_SAVE_PATH);
		assertEquals(error, errorMsgs.getNameError());
		event.validate(Event.EVENT_UPDATE_PATH);
		assertEquals(error, errorMsgs.getNameError());

	}

	@Test
	@FileParameters("resources/event_attendees.csv")
	public void testEventAttendees(String id, String username, String date, String startTime, String duration,
			String hallName, String estAttendees, String name, String foodType, String meal, String mealFormality,
			String drinkType, String entertainmentItems, String eventStatus, String estCost, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		Event event = new Event();
		EventErrorMsgs errorMsgs = new EventErrorMsgs();
		event.setErrorMsgs(errorMsgs);
		setEvent(event, id, username, date, startTime, duration, hallName, estAttendees, name, foodType, meal,
				mealFormality, drinkType, entertainmentItems, eventStatus);

		event.validate(Event.EVENT_SAVE_PATH);
		assertEquals(error, errorMsgs.getEstAttendeesError());
		event.validate(Event.EVENT_UPDATE_PATH);
		assertEquals(error, errorMsgs.getEstAttendeesError());

	}

	@Test
	@FileParameters("resources/event_date_time.csv")
	public void testdate(String id, String username, String date, String startTime, String duration, String hallName,
			String estAttendees, String name, String foodType, String meal, String mealFormality, String drinkType,
			String entertainmentItems, String eventStatus, String estCost, String timeError,String dateError) {
		if (CmnUtil.isNullorEmpty(timeError)) {
			timeError = null;
		}
		if (CmnUtil.isNullorEmpty(dateError)) {
			dateError = null;
		}
		Event event = new Event();
		EventErrorMsgs errorMsgs = new EventErrorMsgs();
		event.setErrorMsgs(errorMsgs);
		setEvent(event, id, username, date, startTime, duration, hallName, estAttendees, name, foodType, meal,
				mealFormality, drinkType, entertainmentItems, eventStatus);

		event.validate(Event.EVENT_SAVE_PATH);
		assertEquals(timeError, errorMsgs.getStartTimeError());
		assertEquals(dateError, errorMsgs.getDateError());
		

	}

	@Test
	@FileParameters("resources/event_datecount.csv")
	public void testdatecount(String id, String username, String date, String startTime, String duration,
			String hallName, String estAttendees, String name, String foodType, String meal, String mealFormality,
			String drinkType, String entertainmentItems, String eventStatus, String estCost, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		Event event = new Event();
		EventErrorMsgs errorMsgs = new EventErrorMsgs();
		event.setErrorMsgs(errorMsgs);
		event.setEvent(id, username, date, startTime, duration, hallName, estAttendees, name, foodType, meal,
				mealFormality, drinkType, entertainmentItems, eventStatus);

		event.validate(Event.EVENT_SAVE_PATH);
		if (CmnUtil.isNullorEmpty(errorMsgs.getErrorMsg())) {
			EventDAO.saveEvent(event);
		}
		assertEquals(error, errorMsgs.getErrorMsg());

	}

	private void setEvent(Event event, String id, String username, String date, String startTime, String duration,
			String hallName, String estAttendees, String name, String foodType, String meal, String mealFormality,
			String drinkType, String entertainmentItems, String eventStatus) {
		event.setId(id);
		event.setUsername(username);
		event.setDate(date);
		event.setStartTime(startTime);
		event.setDuration(duration);
		event.setHallName(hallName);
		event.setEstAttendees(estAttendees);
		event.setName(name);
		event.setFoodType(foodType);
		event.setMeal(meal);
		event.setMealFormality(mealFormality);
		event.setDrinkType(drinkType);
		event.setEntertainmentItems(entertainmentItems);
		event.setEventStatus(eventStatus);

	}

	@Test
	@FileParameters("resources/set_price.csv")
	public void testPrice(String duration, String hallName, String estAttend, String meal, String mealFormality,
			String drinkType, String entertainmentItem, String estCost) {
		// TODO Auto-generated method stub
		Event event = new Event();
		event.setEvent("", "", "", "", duration, hallName, estAttend, "", "", meal, mealFormality, drinkType,
				entertainmentItem, "");
		event.setPrice(event);
		assertEquals(Double.parseDouble(estCost), Double.parseDouble(event.getEstCost()), 2);
	}

	@Test
	public void testEventDao() {
		EventDAO.deleteEvents();
		Event event=new Event();
		event.setEvent("", "junituser", "2020-12-20", "13:00", "2", "Maverick", "100", "Junit",
				"Indian", "breakfast", "formal", "non-alcohol", "music", "reserved");
		
		assertEquals(1, EventDAO.saveEvent(event));
		Event dbEvent = EventDAO.getUserEvents("junituser", event).get(0);
		
		event.setId(dbEvent.getId());
		event.setFoodType("American");
		assertEquals(1, EventDAO.updateEvent(event));
		event.setPrice(event);
		assertEquals(1, EventDAO.updateEventReserve(event));
		
		Event dbEventUpdate = EventDAO.getUserEvents("junituser", event).get(0);
		assertNotNull(dbEventUpdate.getDate());
		assertNotNull(dbEventUpdate.getDrinkType());
		assertNotNull(dbEventUpdate.getDuration());
		assertNotNull(dbEventUpdate.getEntertainmentItems());
		assertNotNull(dbEventUpdate.getEstAttendees());
		assertNotNull(dbEventUpdate.getEstCost());
		assertNotNull(dbEventUpdate.getEventStatus());
		assertNotNull(dbEventUpdate.getFoodType());
		assertNotNull(dbEventUpdate.getHallName());
		assertNotNull(dbEventUpdate.getId());
		assertNotNull(dbEventUpdate.getMeal());
		assertNotNull(dbEventUpdate.getMealFormality());
		assertNotNull(dbEventUpdate.getName());
		assertNotNull(dbEventUpdate.getStartTime());
		
		User staff=new User();
		staff.setUsername("junitstaff");
		assertEquals(1, EventDAO.assignEvent(event, staff));
		assertTrue(EventDAO.getStaffEvents("junitstaff", dbEvent).size()>0);
		assertTrue(EventDAO.getManagerEvents(dbEvent).size()>0);
		assertTrue(EventDAO.getSingleUserEvents(dbEvent.getId()).size()>0);
		assertTrue(EventDAO.deleteAssignedStaffEvents("junitstaff"));
		assertTrue(EventDAO.getStaffEvents("junitstaff", dbEvent).size()==0);

		EventDAO.deleteEvents();
		
		

	}

}