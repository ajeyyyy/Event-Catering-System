package event_management.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import event_management.model.Event;
import event_management.model.User;
import event_management.util.CmnUtil;
import event_management.util.SQLConnection;

public class EventDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static int updateEventReserve(Event event) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "UPDATE `company_management`.`event`\n" + "SET\n"
					+ " `event_status` = ?,`est_cost` = ? \n" + "WHERE `id` = ?";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);

			stmt.setString(1, event.getEventStatus());
			stmt.setString(2, event.getEstCost());
			stmt.setString(3, event.getId());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	public static int updateEvent(Event event) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "UPDATE `company_management`.`event`\n" + "SET\n" + "`username` = ?,\n" + "`date` =?,\n"
					+ "`start_time` = ?,\n" + "`duration` = ?,\n" + "`hall_name` = ?,\n" + "`est_attendees` = ?,\n"
					+ "`name` = ?,\n" + "`food_type` = ?,\n" + "`meal` =?,\n" + "`meal_formality` =?,\n"
					+ "`drink_type` = ?,\n" + "`entertainment_items` = ?,\n" + "`est_cost` =?\n" + "WHERE `id` = ?;";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);

			stmt.setString(1, event.getUsername());
			stmt.setString(2, event.getDate());
			stmt.setString(3, event.getStartTime());
			stmt.setString(4, event.getDuration());
			stmt.setString(5, event.getHallName());
			stmt.setString(6, event.getEstAttendees());
			stmt.setString(7, event.getName());
			stmt.setString(8, event.getFoodType());
			stmt.setString(9, event.getMeal());
			stmt.setString(10, event.getMealFormality());
			stmt.setString(11, event.getDrinkType());
			stmt.setString(12, event.getEntertainmentItems());
			stmt.setString(13, event.getEstCost());
			stmt.setString(14, event.getId());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	public static int assignEvent(Event event, User user) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "INSERT INTO `company_management`.`event_staff`(`event_id`,`staff_username`) VALUES (?,?)";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);

			stmt.setString(1, event.getId());
			stmt.setString(2, user.getUsername());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	public static int saveEvent(Event event) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "INSERT INTO `company_management`.`event`\n" + "(`username`,\n" + "`date`,\n"
					+ "`start_time`,\n" + "`duration`,\n" + "`hall_name`,\n" + "`est_attendees`,\n" + "`name`,\n"
					+ "`food_type`,\n" + "`meal`,\n" + "`meal_formality`,\n" + "`drink_type`,\n"
					+ "`entertainment_items`,\n" + "`event_status`)\n" + "VALUES\n" + "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);
			stmt.setString(1, event.getUsername());
			stmt.setString(2, event.getDate());
			stmt.setString(3, event.getStartTime());
			stmt.setString(4, event.getDuration());
			stmt.setString(5, event.getHallName());
			stmt.setString(6, event.getEstAttendees());
			stmt.setString(7, event.getName());
			stmt.setString(8, event.getFoodType());
			stmt.setString(9, event.getMeal());
			stmt.setString(10, event.getMealFormality());
			stmt.setString(11, event.getDrinkType());
			stmt.setString(12, event.getEntertainmentItems());
			stmt.setString(13, event.getEventStatus());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

//	private static ArrayList<Event> returnMatchingEventList(String queryString) {
//		ArrayList<Event> eventListInDB = new ArrayList<Event>();
//
//		Statement stmt = null;
//		Connection conn = SQLConnection.getDBConnection();
//		try {
//			stmt = conn.createStatement();
//			ResultSet eventList = stmt.executeQuery(queryString);
//			while (eventList.next()) {
//				Event event = new Event();
//				event.setId(eventList.getString("id"));
//				event.setName(eventList.getString("name"));
//				event.setStartTime(eventList.getString("start_time"));
//				event.setDuration(eventList.getString("duration"));
//				event.setHallName(eventList.getString("hall_name"));
//				event.setEstCost(eventList.getString("est_cost"));
//				event.setDate(eventList.getString("date"));
//				event.setEventStatus(eventList.getString("event_status"));
//				event.setMeal(eventList.getString("meal"));
//				event.setFoodType(eventList.getString("food_type"));
//				eventListInDB.add(event);
//			}
//		} catch (SQLException e) {
//		}
//		return eventListInDB;
//	}

	private static ArrayList<Event> returnMatchingEventList(String queryString) {
		System.out.println(queryString);
		ArrayList<Event> eventListInDB = new ArrayList<Event>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Event event = new Event();
				try {

					event.setId(eventList.getString("id"));
					event.setName(eventList.getString("name"));
					event.setStartTime(eventList.getString("start_time"));
					event.setDuration(eventList.getString("duration"));
					event.setHallName(eventList.getString("hall_name"));
					event.setEstCost(eventList.getString("est_cost"));
					event.setDate(eventList.getString("date"));
					event.setEventStatus(eventList.getString("event_status"));
					event.setEstAttendees(eventList.getString("est_attendees"));
					event.setFoodType(eventList.getString("food_type"));
					event.setMeal(eventList.getString("meal"));
					event.setMealFormality(eventList.getString("meal_formality"));
					event.setDrinkType(eventList.getString("drink_type"));
					event.setEntertainmentItems(eventList.getString("entertainment_items"));
				} catch (Exception e) {

				}

				eventListInDB.add(event);
			}
		} catch (SQLException e) {
		}
		return eventListInDB;
	}

	public static List<Event> getStaffEvents(String staffUsername, Event event) {
		return returnMatchingEventList("select  * from event e inner join event_staff es "
				+ "on e.id = es.event_id where " + "TIME_FORMAT(e.start_time, '%H:%i')>=TIME_FORMAT('"
				+ event.getStartTime() + "', '%H:%i') " + "and DATE_FORMAT(e.`date`, '%Y-%M-%d')=DATE_FORMAT('"
				+ event.getDate() + "', '%Y-%M-%d') " + "and es.staff_username='" + staffUsername
				+ "' order by TIME_FORMAT(e.start_time, '%H:%i'), e.name;");
	}

	public static List<Event> getManagerEvents(Event event) {
		return returnMatchingEventList("select  * from event "
				+ "where event_status='reserved' and TIME_FORMAT(start_time, '%H:%i')>=TIME_FORMAT('"
				+ event.getStartTime() + "', '%H:%i') " + "and DATE_FORMAT(`date`, '%Y-%M-%d')=DATE_FORMAT('"
				+ event.getDate() + "', '%Y-%M-%d')  " + "order by TIME_FORMAT(start_time, '%H:%i'),name;");
	}

	public static List<Event> getUserEvents(String username, Event event) {
		return returnMatchingEventList("select * from event where username='" + username + "' and date='"
				+ event.getDate() + "' and start_time>='" + event.getStartTime()
				+ "' order by TIME_FORMAT(start_time, '%H:%i'),name;");
	}

	public static List<Event> getSingleUserEvents(String id) {
		return returnMatchingEventList("select * from event where id='" + id + "'");
	}

	public static int getSingleDayEventCount(String username, Event event) {
		return Integer.parseInt(returnMatchingEventList(
				"select count(*) as id from event where username='" + username + "' and date='" + event.getDate() + "'")
						.get(0).getId());
	}

	public static int getWeekEventCount(String username, Event event) {
		String startOfTheWeek = CmnUtil.getFirstDateOfWeek(event.getDate());
		String endOfTheWeek = CmnUtil.getLastDateOfWeek(event.getDate());
		return Integer.parseInt(returnMatchingEventList("select count(e.username) as id from event e where username='"
				+ username + "' and STR_TO_DATE(e.`date`, '%Y-%m-%d')>=STR_TO_DATE('" + startOfTheWeek
				+ "', '%Y-%m-%d') and STR_TO_DATE(e.`date`, '%Y-%m-%d')<=STR_TO_DATE('" + endOfTheWeek
				+ "', '%Y-%m-%d');").get(0).getId());
	}

	public static Boolean deleteAssignedStaffEvents(String userName) {
		// return returnMatchingEventList("select * from event where id='" + id + "'");
		String query = "Delete from `company_management`.`event_staff` where staff_username='" + userName + "'";
		PreparedStatement stmt = null;

		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.execute();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	public static Boolean deleteEvents() {
		// return returnMatchingEventList("select * from event where id='" + id + "'");
		String query = "Delete from `company_management`.`event` where username='Hazel' or username='junituser'";
		PreparedStatement stmt = null;
		
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.execute();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
