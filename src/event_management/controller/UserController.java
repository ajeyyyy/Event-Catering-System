package event_management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import event_management.data.UserDAO;
import event_management.model.User;
import event_management.model.UserErrorMsgs;
import event_management.util.CmnUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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
		HttpSession session = request.getSession(false);
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		boolean sendResponse = false;

		User user = new User();
		String urlRedirect = "";
		if (User.USER_GET_PATH.equals(action)) {
			setUserFromRequest(request, user);

			List<User> userList = UserDAO.getUserByUserName(user);
			if (userList.size() == 0) {
				urlRedirect = "/index.jsp";
			} else {
				request.setAttribute("user", userList.get(0));
				urlRedirect = "/admin/form_user_edit.jsp";
			}

		} else if (User.USER_GET_PATH_UPDATE.equals(action)) {
			User sessionUser = (User) session.getAttribute("LOGIN_USER");
			List<User> userList = UserDAO.getUserByUserName(sessionUser);
			if (userList.size() == 0) {
				urlRedirect = "/index.jsp";
			} else {
				request.setAttribute("user", userList.get(0));
				urlRedirect = "/user/form_user_edit.jsp";
			}

		} else if (User.USER_SEARCH_PATH.equals(action)) {
			setUserFromRequest(request, user);
			user.setErrorMsgs(userErrorMsgs);
			user.validate(User.USER_SEARCH_PATH);
			if (!CmnUtil.isNullorEmpty(userErrorMsgs.getErrorMsg())) {
				userErrorMsgs.setErrorMsgCustome(userErrorMsgs.getLastNameError());
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("user", user);
				urlRedirect = "/admin/search_user.jsp";
			} else {
				List<User> userList = UserDAO.getUserByLastName(user);
				if (userList.size() == 0) {
					userErrorMsgs.setErrorMsgCustome("No Records found");
				}
				request.setAttribute("user", user);
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("Users", userList);
				urlRedirect = "/admin/search_user.jsp";
			}

		} else if (User.USER_LOGOUT_PATH.equals(action)) {
			if (session != null)
				session.invalidate();
			sendResponse = true;
			urlRedirect = "index.jsp";

		} else if (User.USER_DELETE_PATH.equals(action)) {
			setUserFromRequest(request, user);
			Boolean result = UserDAO.deleteUser(user);
			if (result == true) {
				List<User> userList = UserDAO.getUserByLastName(user);
				if (userList.size() == 0) {
					userErrorMsgs.setErrorMsgCustome("No Records found");
				}
				request.setAttribute("user", user);
				userErrorMsgs.setUserDeleteSuccess(user.getUsername() + " " + User.USER_SUCCESS_DELETE);
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("Users", userList);

				urlRedirect = "/admin/search_user.jsp";
			}

		} else {
			urlRedirect = "index.jsp";
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
		User user = new User();
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();
		String urlRedirect = "";
		boolean sendResponse = false;
 
		if (action.equalsIgnoreCase(User.USER_SAVE_PATH)) {
			setUserFromRequest(request, user);
			user.setErrorMsgs(userErrorMsgs);
			user.validate(User.USER_SAVE_PATH);
			System.out.println(user.toString());
			if (!CmnUtil.isNullorEmpty(userErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("user", user);
				urlRedirect = "/user/form_user.jsp";

			} else {
				UserDAO.saveUser(user);
				urlRedirect = "/index.jsp";
			}

		} else if (action.equalsIgnoreCase(User.USER_UPDATE_PATH)) {
			setUserFromRequest(request, user);
			user.setErrorMsgs(userErrorMsgs);
			user.validate(User.USER_UPDATE_PATH);
			System.out.println(user.toString());
			if (!CmnUtil.isNullorEmpty(userErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("user", user);
				if ("user".equals(CmnUtil.getLoggedInUser(session).getRole())) {
					urlRedirect = "/user/form_user_edit.jsp";
				} else {
					urlRedirect = "/admin/form_user_edit.jsp";
				}

			} else {
				sendResponse = true;
				UserDAO.updateUser(user);
				if ("user".equals(CmnUtil.getLoggedInUser(session).getRole())) {
					urlRedirect = "user/home_page_user.jsp";
				} else {
					request.setAttribute("action", User.USER_SEARCH_PATH);
					request.setAttribute("lastName", request.getParameter("queryStr"));
					urlRedirect = "UserController?lastName=" + request.getParameter("queryStr") + "&action=searchUser";
				}

				// urlRedirect = "/user/search_user.jsp";
			}

		} else if (User.USER_LOGIN_PATH.equals(action)) {
			setUserFromRequest(request, user);
			user.setErrorMsgs(userErrorMsgs);
			user.validate(User.USER_LOGIN_PATH);
			User databaseUser = null;
			if (!CmnUtil.isNullorEmpty(userErrorMsgs.getErrorMsg())) {
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("user", user);
				urlRedirect = "/user/login.jsp";
			} else if ((databaseUser = UserDAO.getUser(user)) == null) {
				userErrorMsgs.setUsernameError("user name or password does not match");
				request.setAttribute("errorMsgs", userErrorMsgs);
				request.setAttribute("user", user);
				urlRedirect = "/user/login.jsp";
			} else {
				System.out.println(databaseUser.toString());
				session.setAttribute("LOGIN_USER", databaseUser);
				if ("manager".equals(databaseUser.getRole()))
					urlRedirect = "/manager/home_page_manager.jsp";
				else if ("user".equals(databaseUser.getRole()))
					urlRedirect = "/user/home_page_user.jsp";
				else if ("admin".equals(databaseUser.getRole()))
					urlRedirect = "/admin/home_page_admin.jsp";
				else if ("staff".equals(databaseUser.getRole()))
					urlRedirect = "/staff/home_page_staff.jsp";
				else
					urlRedirect = "/home_page.jsp";
			}
		}

		if (sendResponse) {
			response.sendRedirect(urlRedirect);
		} else {
			request.getRequestDispatcher(urlRedirect).forward(request, response);
		}

	}

	private void setUserFromRequest(HttpServletRequest request, User user) {

		user.setUser(request.getParameter("utaId"), request.getParameter("username"), request.getParameter("password"),
				request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"),
				request.getParameter("phone"), request.getParameter("zipCode"), request.getParameter("streetName"),
				request.getParameter("streetNumber"), request.getParameter("state"), request.getParameter("role"),
				request.getParameter("city"));

	}

}
