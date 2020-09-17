package event_management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import event_management.data.EventDAO;
import event_management.data.UserDAO;
import event_management.model.Card;
import event_management.model.CardErrorMsgs;
import event_management.model.Event;
import event_management.model.EventErrorMsgs;
import event_management.model.User;
import event_management.model.UserErrorMsgs;
import event_management.util.CmnUtil;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String urlRedirect = "";
		boolean sendResponse = false;
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();

		if (action.equalsIgnoreCase(Event.EVENT_GET_PATH)) {
			Event event = new Event();
			setEventFromRequest(request, event);
			if (CmnUtil.isNullorEmpty(event.getDate())) {
				event.setDate(CmnUtil.getTodaysDate());
			}
			if (CmnUtil.isNullorEmpty(event.getStartTime())) {
				event.setStartTime(CmnUtil.getTodaysHrs());
			}
			List<Event> events = EventDAO.getUserEvents(CmnUtil.getLoggedInUser(session).getUsername(), event);
			request.setAttribute("EVENTS", events);
			request.setAttribute("event", event);
			urlRedirect = "/user/list_event.jsp";

		} else if (action.equalsIgnoreCase(Event.EVENT_STAFF_LIST_PATH)) {
			Event event = new Event();
			setEventFromRequest(request, event);
			if (CmnUtil.isNullorEmpty(event.getDate())) {
				event.setDate(CmnUtil.getTodaysDate());
			}
			if (CmnUtil.isNullorEmpty(event.getStartTime())) {
				event.setStartTime(CmnUtil.getTodaysHrs());
			}
			List<Event> events = EventDAO.getStaffEvents(CmnUtil.getLoggedInUser(session).getUsername(), event);
			request.setAttribute("EVENTS", events);
			request.setAttribute("event", event);
			urlRedirect = "/staff/staff_event.jsp";
		} else if (action.equalsIgnoreCase(Event.EVENT_MANAGER_PATH)) {
			Event event = new Event();
			setEventFromRequest(request, event);
			if (CmnUtil.isNullorEmpty(event.getDate())) {
				event.setDate(CmnUtil.getTodaysDate());
			}
			if (CmnUtil.isNullorEmpty(event.getStartTime())) {
				event.setStartTime(CmnUtil.getTodaysHrs());
			}
			List<Event> events = EventDAO.getManagerEvents(event);
			request.setAttribute("EVENTS", events);
			request.setAttribute("event", event);
			urlRedirect = "/manager/manager_event.jsp";
		} else if (action.equalsIgnoreCase(Event.EVENT_STAFF_SINGLE_PATH)) {
			List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("id"));
			request.setAttribute("event", events.get(0));
			urlRedirect = "/staff/viewStaffEvent.jsp";
		} else if (action.equalsIgnoreCase(Event.EVENT_MANAGER_SINGLE_PATH)) {

			List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("id"));
			// List<User> staffUsers = UserDAO.getAllStaff();
			List<User> staffUsers = UserDAO.getAllStaff(request.getParameter("id"));
			List<User> assignedStaff = UserDAO.getAssignedStaff(request.getParameter("id"));
			request.setAttribute("event", events.get(0));
			request.setAttribute("StaffUsers", staffUsers);
			request.setAttribute("AssignedStaff", assignedStaff);
			// urlRedirect = "/manager/view_manager_event.jsp";
			urlRedirect = "/manager/new.jsp";
//			List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("id"));
//			List<User> staffUsers = UserDAO.getAllStaff();
//			List<User> assignedStaff = UserDAO.getAssignedStaff(request.getParameter("id"));
//			request.setAttribute("event", events.get(0));
//			request.setAttribute("StaffUsers", staffUsers);
//			request.setAttribute("AssignedStaff", assignedStaff);
//			urlRedirect = "/manager/view_manager_event.jsp";
		} else if (action.equalsIgnoreCase(Event.EVENT_USER_SINGLE_PATH)) {

			List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("id"));
			request.setAttribute("event", events.get(0));
			urlRedirect = "/user/user_event_edit.jsp";

		} else if (action.equalsIgnoreCase(Event.EVENT_GET_SINGLE_PATH)) {
			List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("id"));
			request.setAttribute("event", events.get(0));
			urlRedirect = "/user/reserve_event.jsp";

		} else if (action.equals(Event.EVENT_RESERVE_PATH_GET)) {
			Event event = new Event();
			setEventFromRequest(request, event);
			if (CmnUtil.isNullorEmpty(event.getDate())) {
				event.setDate(CmnUtil.getTodaysDate());
			}
			if (CmnUtil.isNullorEmpty(event.getStartTime())) {
				event.setStartTime(CmnUtil.getTodaysHrs());
			}
			request.setAttribute("event", event);
			urlRedirect = "/user/form_event.jsp";
		} else if (action.equalsIgnoreCase(Event.EVENT_MANAGER_REMOVE_EVENT)) {
			String userName = request.getParameter("username");
			Boolean result = EventDAO.deleteAssignedStaffEvents(userName);
			if (result == true) {
				List<Event> events = EventDAO.getSingleUserEvents(request.getParameter("eventId"));

				List<User> staffUsers = UserDAO.getAllStaff(request.getParameter("eventId"));
				List<User> assignedStaff = UserDAO.getAssignedStaff(request.getParameter("eventId"));
				userErrorMsgs.setUserDeleteSuccess(userName + " " + User.USER_SUCCESS_DELETE);
				request.setAttribute("event", events.get(0));
				request.setAttribute("StaffUsers", staffUsers);
				request.setAttribute("AssignedStaff", assignedStaff);
				request.setAttribute("userErrorMsgs", userErrorMsgs);
				urlRedirect = "/manager/new.jsp";
				// urlRedirect="/EventController?action=listSpecificManagerEvent&id="+request.getParameter("id");
			}

		}
		if (sendResponse) {
			response.sendRedirect(urlRedirect);
		} else {
			request.getRequestDispatcher(urlRedirect).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Event event = new Event();
		EventErrorMsgs eventErrorMsgs = new EventErrorMsgs();
		String urlRedirect = "";
		boolean sendResponse = false;

		if (action.equalsIgnoreCase(Event.EVENT_SAVE_PATH)) {
			setEventFromRequest(request, event);
			System.out.println(event.toString());
			event.setUsername(CmnUtil.getLoggedInUser(session).getUsername());
			event.setErrorMsgs(eventErrorMsgs);
			event.validate(Event.EVENT_SAVE_PATH);
			System.out.println(event.toString());
			if (!CmnUtil.isNullorEmpty(eventErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", eventErrorMsgs);
				request.setAttribute("event", event);
				urlRedirect = "/user/form_event.jsp";

			} else {
				EventDAO.saveEvent(event);
				sendResponse = true;
				urlRedirect = "user/home_page_user.jsp";
			}

		} else if (action.equalsIgnoreCase(Event.EVENT_RESERVE_PATH)) {
			Card card = new Card();
			CardErrorMsgs cardErrorMsgs = new CardErrorMsgs();
			setEventFromRequest(request, event);
			setCardFromRequest(request, card);
			card.setErrorMsgs(cardErrorMsgs);
			card.validate(cardErrorMsgs);
			event = EventDAO.getSingleUserEvents(event.getId()).get(0);

			if (!CmnUtil.isNullorEmpty(cardErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", cardErrorMsgs);
				request.setAttribute("event", event);
				request.setAttribute("card", card);
				urlRedirect = "/user/reserve_event.jsp";
			} else {
				event.setEventStatus("reserved");
				event.setPrice(event);
				System.out.println(event.toString());
				EventDAO.updateEventReserve(event);
				sendResponse = true;
				System.out.println(request.getHeader("referer"));
				urlRedirect = "EventController?startTime=" + event.getStartTime() + "&date=" + event.getDate()
						+ "&action=" + Event.EVENT_GET_PATH;
			}

		} else if (action.equalsIgnoreCase(Event.EVENT_MANAGER_ASSIGN_EVENT)) {
			event.setId(request.getParameter("id"));
			event.setEventStatus("reserved");
			User user = new User();
			user.setUsername(request.getParameter("username"));
			EventDAO.assignEvent(event, user);
			sendResponse = true;
			urlRedirect = "EventController?action=listSpecificManagerEvent&id=" + event.getId();

		} else if (action.equalsIgnoreCase(Event.EVENT_UPDATE_PATH)) {
			setEventFromRequest(request, event);
			System.out.println(event.toString());
			event.setEventStatus("reserved");
			// event.setUsername(CmnUtil.getLoggedInUser(session).getUsername());
			event.setErrorMsgs(eventErrorMsgs);
			event.validate(Event.EVENT_SAVE_PATH);
			// System.out.println(event.toString());
			if (!CmnUtil.isNullorEmpty(eventErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", eventErrorMsgs);
				request.setAttribute("event", event);
				List<User> staffUsers = UserDAO.getAllStaff(event.getId());
				List<User> assignedStaff = UserDAO.getAssignedStaff(event.getId());
				request.setAttribute("StaffUsers", staffUsers);
				request.setAttribute("AssignedStaff", assignedStaff);

				urlRedirect = "/manager/new.jsp";

			} else {
				event.setPrice(event);
				EventDAO.updateEvent(event);

				List<User> staffUsers = UserDAO.getAllStaff(event.getId());
				List<User> assignedStaff = UserDAO.getAssignedStaff(event.getId());

				eventErrorMsgs.setSuccessUpdate(event.getName() + " " + Event.EVENT_SUCCESS_UPDATE);
				request.setAttribute("event", event);
				request.setAttribute("StaffUsers", staffUsers);
				request.setAttribute("AssignedStaff", assignedStaff);
				request.setAttribute("errorMsgs", eventErrorMsgs);
				// urlRedirect = "/manager/view_manager_event.jsp";
				urlRedirect = "/manager/new.jsp";

				// urlRedirect = "EventController?action=listSpecificManagerEvent&id=" +
				// event.getId();

			}
		} else if (action.equalsIgnoreCase(Event.EVENT_UPDATE_USER_PATH)) {
			setEventFromRequest(request, event);
			System.out.println(event.toString());
			event.setErrorMsgs(eventErrorMsgs);
			event.validate(Event.EVENT_SAVE_PATH);
			if (!CmnUtil.isNullorEmpty(eventErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", eventErrorMsgs);
				request.setAttribute("event", event);
				urlRedirect = "/user/user_event_edit.jsp";

			} else {
				event.setPrice(event);
				EventDAO.updateEvent(event);
				eventErrorMsgs.setSuccessUpdate(event.getName() + " " + Event.EVENT_SUCCESS_UPDATE);
				request.setAttribute("event", event);
				request.setAttribute("errorMsgs", eventErrorMsgs);
				urlRedirect = "/user/user_event_edit.jsp";
			}
		}
		if (sendResponse) {
			response.sendRedirect(urlRedirect);
		} else {
			request.getRequestDispatcher(urlRedirect).forward(request, response);
		}
	}

	private void setCardFromRequest(HttpServletRequest request, Card card) {
		// TODO Auto-generated method stub
		card.setCardNumber(request.getParameter("cardNumber"));
		card.setExpDate(request.getParameter("expDate"));
		card.setPin(request.getParameter("pin"));

	}

	

	private void setEventFromRequest(HttpServletRequest request, Event event) {
		event.setId(request.getParameter("id"));
		event.setDate(request.getParameter("date"));
		event.setDrinkType(request.getParameter("drinkType"));
		event.setDuration(request.getParameter("duration"));
		event.setEntertainmentItems(request.getParameter("entertainmentItems"));
		event.setEstAttendees(request.getParameter("estAttendees"));
		event.setEventStatus("saved");
		event.setFoodType(request.getParameter("foodType"));
		event.setHallName(request.getParameter("hallName"));
		event.setMeal(request.getParameter("meal"));
		event.setMealFormality(request.getParameter("mealFormality"));
		event.setName(request.getParameter("name"));
		event.setStartTime(request.getParameter("startTime"));
		event.setEstCost("700");

	}

}
