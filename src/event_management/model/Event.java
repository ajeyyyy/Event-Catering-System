package event_management.model;

import java.util.Calendar;
import java.util.Date;

import event_management.data.EventDAO;
import event_management.util.CmnUtil;

public class Event {
	public static final String EVENT_SAVE_PATH = "saveEvent";
	public static final String EVENT_GET_PATH = "getEvent";
	public static final String EVENT_GET_SINGLE_PATH = "getSingleEvent";
	public static final String EVENT_RESERVE_PATH = "reserveEvent";
	public static final String EVENT_RESERVE_PATH_GET = "reserveEventGet";
	public static final String EVENT_STAFF_SINGLE_PATH = "listSpecificStaffEvent";
	public static final String EVENT_STAFF_LIST_PATH = "listStaffEvent";
	public static final String EVENT_MANAGER_PATH = "listManagerEvent";
	public static final String EVENT_USER_SINGLE_PATH = "listSpecificUserEvent";
	public static final String EVENT_MANAGER_SINGLE_PATH = "listSpecificManagerEvent";
	public static final String EVENT_MANAGER_ASSIGN_EVENT = "assignEvents";
	public static final String EVENT_UPDATE_PATH = "updateEvent";
	public static final String EVENT_UPDATE_USER_PATH = "updateEventUser";
	public static final String EVENT_MANAGER_REMOVE_EVENT = "deleteEvents";

	public static final String EVENT_SUCCESS_UPDATE = "event updated successfully";

	private String id, username, date, startTime, duration, hallName, estAttendees, name, foodType, meal, mealFormality,
			drinkType, entertainmentItems, eventStatus, estCost;
	EventErrorMsgs errorMsgs;

	public static final String EVENT_ERROR_NAME_EMPTY = "Event Name must not be empty";
	public static final String EVENT_ERROR_NAME_CAPS = "Event name must start with a capital letter";
	public static final String EVENT_ERROR_NAME_SIZE = "Event name length must be >2 and <30";

	public static final String EVENT_ERROR_EST_ATTENDEE_EMPTY = "Estimated attendees must not be empty";
	public static final String EVENT_ERROR_EST_ATTENDEE_NUMBER = "Estimated attendees must be a number";
	public static final String EVENT_ERROR_EST_ATTENDEE_G_ZERO = "Estimated attendees must be greater than 0";
	public static final String EVENT_ERROR_EST_ATTENDEE_SIZE = "Estimated attendees must be <=100";

	private void validateEstAttendee(String action) {
		if (CmnUtil.isNullorEmpty(estAttendees)) {
			errorMsgs.setEstAttendeesError(EVENT_ERROR_EST_ATTENDEE_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(estAttendees)) {
			errorMsgs.setEstAttendeesError(EVENT_ERROR_EST_ATTENDEE_NUMBER);
		} else if (Integer.parseInt(estAttendees) <= 0) {
			errorMsgs.setEstAttendeesError(EVENT_ERROR_EST_ATTENDEE_G_ZERO);
		} else if (Integer.parseInt(estAttendees) > 100) {
			errorMsgs.setEstAttendeesError(EVENT_ERROR_EST_ATTENDEE_SIZE);

		}

	}

	private void validateName(String action) {
		// TODO Auto-generated method stub
		if (CmnUtil.isNullorEmpty(name)) {
			errorMsgs.setNameError(EVENT_ERROR_NAME_EMPTY);
		} else if (!Character.isUpperCase(name.charAt(0))) {
			errorMsgs.setNameError(EVENT_ERROR_NAME_CAPS);
		} else if (!CmnUtil.stringSize(name, 3, 29)) {
			errorMsgs.setNameError(EVENT_ERROR_NAME_SIZE);
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getEstAttendees() {
		return estAttendees;
	}

	public void setEstAttendees(String estAttendees) {
		this.estAttendees = estAttendees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getMealFormality() {
		return mealFormality;
	}

	public void setMealFormality(String mealFormality) {
		this.mealFormality = mealFormality;
	}

	public String getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}

	public String getEntertainmentItems() {
		return entertainmentItems;
	}

	public void setEntertainmentItems(String entertainmentItems) {
		this.entertainmentItems = entertainmentItems;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public void setErrorMsgs(EventErrorMsgs eventErrorMsgs) {
		this.errorMsgs = eventErrorMsgs;

	}

	public void validate(String action) {
		// TODO Auto-generated method stub
		if (EVENT_SAVE_PATH.equals(action)) {
			validateName(EVENT_SAVE_PATH);
			validateEstAttendee(EVENT_SAVE_PATH);
			validateSameDay(EVENT_SAVE_PATH);
			validateFivePerWeek(EVENT_SAVE_PATH);
			validateTime(EVENT_SAVE_PATH);
		}
		errorMsgs.setErrorMsg("");

	}

	private void validateTime(String eventSavePath) {

		Calendar c = CmnUtil.dateToCalendar(CmnUtil.getDateTime(date, startTime));
		c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(duration));
		Date endTimeObj = c.getTime();
		Date startTimeObj = CmnUtil.getDateTime(date, startTime);
		Date currDate = new Date();
		if (startTimeObj.compareTo(currDate) < 0) {
			errorMsgs.setDateError("user must not select past date time");
			return;
		}
		Date openTimeObj, closingTimeObj;
		if (CmnUtil.checkIfSunday(date)) {
			openTimeObj = CmnUtil.getDateTime(date, "12:00");
		} else {
			openTimeObj = CmnUtil.getDateTime(date, "07:00");
		}
		if (CmnUtil.checkIfWeekend(date)) {
			Calendar cClose = CmnUtil.dateToCalendar(CmnUtil.getDateTime(date, "02:00"));
			cClose.add(Calendar.DAY_OF_MONTH, 1);
			closingTimeObj = cClose.getTime();
		} else {
			closingTimeObj = CmnUtil.getDateTime(date, "23:00");
		}

		if (CmnUtil.checkIfSunday(date) && startTimeObj.compareTo(openTimeObj) < 0) {
			errorMsgs.setStartTimeError("The halls opens at noon on sunday's");
		} else if (CmnUtil.checkIfWeekend(date) && endTimeObj.compareTo(closingTimeObj) > 0) {
			errorMsgs.setStartTimeError("The halls closes at 2 am on weekends");
		} else if (!CmnUtil.checkIfSunday(date) && startTimeObj.compareTo(openTimeObj) < 0) {
			errorMsgs.setStartTimeError("The halls opens at 7 am except on sunday's");
		} else if (!CmnUtil.checkIfSunday(date) && endTimeObj.compareTo(closingTimeObj) > 0) {
			errorMsgs.setStartTimeError("The halls closes at 11 pm on weekday's");
		}

	}

	private void validateFivePerWeek(String eventSavePath) {
		// TODO Auto-generated method stub
		int count = EventDAO.getWeekEventCount(username, this);
		if (count >= 5) {
			errorMsgs.setErrorMsgCustome("can not create more than 5 event a week");
		}

	}

	private void validateSameDay(String eventSavePath) {
		int count = EventDAO.getSingleDayEventCount(username, this);
		if (count >= 2) {
			errorMsgs.setErrorMsgCustome("can not create more than 2 event a day");

		}

	}

	public String getEstCost() {
		return estCost;
	}

	public void setEstCost(String estCost) {
		this.estCost = estCost;
	}

	public void setEvent(String id, String username, String date, String startTime, String duration, String hallName,
			String estAttendees, String name, String foodType, String meal, String mealFormality, String drinkType,
			String entertainmentItems, String eventStatus) {
		setId(id);
		setUsername(username);
		setDate(date);
		setStartTime(startTime);
		setDuration(duration);
		setHallName(hallName);
		setEstAttendees(estAttendees);
		setName(name);
		setFoodType(foodType);
		setMeal(meal);
		setMealFormality(mealFormality);
		setDrinkType(drinkType);
		setEntertainmentItems(entertainmentItems);
		setEventStatus(eventStatus);

	}

	public void setPrice(Event event) {
		double estCost = 0.0;
		int duration = Integer.parseInt(event.getDuration());
		int estAttendees = Integer.parseInt(event.getEstAttendees());
		if ("Maverick".equals(event.getHallName())) {
			estCost += 100 * duration;
		} else if ("KC".equals(event.getHallName()) || "Shard".equals(event.getHallName())) {
			estCost += 25 * duration;
		} else if ("Arlington".equals(event.getHallName())) {
			estCost += 50 * duration;
		} else {
			estCost += 75 * duration;
		}
		double mealCostMultiplier = 1.0;
		if ("formal".equals(event.getMealFormality())) {
			mealCostMultiplier = 1.5;
		}

		if ("breakfast".equals(event.getMeal())) {
			estCost += 8 * estAttendees * mealCostMultiplier;
		} else if ("lunch".equals(event.getMeal())) {
			estCost += 12 * estAttendees * mealCostMultiplier;
		} else {
			estCost += 18 * estAttendees * mealCostMultiplier;
		}

		if ("alcohol".equals(event.getDrinkType())) {
			estCost += 15 * estAttendees;
		}

		if ("music".equals(event.getEntertainmentItems())) {
			estCost += 50;
		}

		event.setEstCost(String.valueOf(estCost));

	}

}
