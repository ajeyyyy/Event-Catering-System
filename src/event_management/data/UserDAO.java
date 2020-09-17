package event_management.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import event_management.model.User;
import event_management.util.SQLConnection;

public class UserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	// UPDATE `company_management`.`user` SET`uta_id` = ?,`username` = ?,`password`
	// = ?,`first_name` = ?,`last_name` = ?,`email` = ?,`phone` = ?,`zip_code` =
	// ?,`street_name` = ?,`street_number` = ?,`state` = ?,`role` = ?,`city` = ?
	// WHERE `uta_id` = ?;

	public static int updateUser(User user) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "UPDATE `company_management`.`user` SET`uta_id` = ?,"
					+ "`password` = ?,`first_name` = ?,`last_name` = ?,`email` = ?,"
					+ "`phone` = ?,`zip_code` = ?,`street_name` = ?,`street_number` = ?,"
					+ "`state` = ?,`role` = ?,`city` = ? WHERE `username` = ?;";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);

			stmt.setString(1, user.getUtaId());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getPhone());
			stmt.setString(7, user.getZipCode());
			stmt.setString(8, user.getStreetName());
			stmt.setString(9, user.getStreetNumber());
			stmt.setString(10, user.getState());
			stmt.setString(11, user.getRole());
			stmt.setString(12, user.getCity());
			stmt.setString(13, user.getUsername());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	public static int saveUser(User user) {

		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			String insertUser = "INSERT INTO company_management.user(uta_id,username,password,first_name,last_name,email,phone,zip_code,street_name,street_number,state,role,city)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = (PreparedStatement) conn.prepareStatement(insertUser);

			stmt.setString(1, user.getUtaId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getPhone());
			stmt.setString(8, user.getZipCode());
			stmt.setString(9, user.getStreetName());
			stmt.setString(10, user.getStreetNumber());
			stmt.setString(11, user.getState());
			stmt.setString(12, user.getRole());
			stmt.setString(13, user.getCity());

			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	private static ArrayList<User> returnMatchingUserList(String queryString) {
		ArrayList<User> userListInDB = new ArrayList<User>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				User user = new User();
				user.setUtaId(userList.getString("uta_id"));
				user.setCity(userList.getString("city"));
				user.setFirstName(userList.getString("first_name"));
				user.setLastName(userList.getString("last_name"));
				user.setRole(userList.getString("role"));
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setState(userList.getString("state"));
				user.setEmail(userList.getString("email"));
				user.setPhone(userList.getString("phone"));
				user.setStreetName(userList.getString("street_name"));
				user.setZipCode(userList.getString("zip_code"));
				user.setStreetNumber(userList.getString("street_number"));
				userListInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return userListInDB;
	}
	// ;

	public static User getUser(User user) {
		List<User> users = returnMatchingUserList("select * from user where username='" + user.getUsername()
				+ "' and password='" + user.getPassword() + "'");
		if (users.size() == 0)
			return null;
		return users.get(0);

	}

	public static List<User> getUserByLastName(User user) {
		return returnMatchingUserList(
				"select * from user where last_name like '%" + user.getLastName() + "%' order by last_name");

	}

	public static List<User> getUserByUserName(User user) {
		return returnMatchingUserList("select * from user where username='" + user.getUsername() + "' ");

	}

	public static List<User> getUserByRole(User user) {
		return returnMatchingUserList("select * from user where role='" + user.getRole() + "' ");
	}

	public static List<User> getUserByUtaId(User user) {
		return returnMatchingUserList("select * from user where uta_id='" + user.getUtaId() + "' ");
	}

	public static List<User> getUserByUsername(User user) {
		return returnMatchingUserList("select * from user where username='" + user.getUsername() + "' ");
	}

	public static List<User> getAllStaff() {
		return returnMatchingUserList("select * from user where role='staff' order by last_name;");
	}
	//test this with event insert
	public static List<User> getAllStaff(String eventId) {
		return returnMatchingUserList(
				"select * from user where role='staff' and username NOT IN (select staff_username from event_staff where event_id = "
						+ eventId + " ) order by last_name;");
	}

	public static List<User> getAssignedStaff(String staffId) {
		return returnMatchingUserList(
				"select u.* from event_staff e inner join user u where e.staff_username= u.username and e.event_id="
						+ staffId);
	}
	public static int getAFirstlastNameCount(User user) {
		return returnMatchingUserList("select * from user where first_name='"+user.getFirstName()+"' and last_name='"+user.getLastName()+"' and role='"+user.getRole()+"';").size();
	}

	public static Boolean deleteUser(User user) {
		String query = "Delete from user where username='" + user.getUsername() + "'";
		String query2 = "Delete from event_staff where staff_username='" + user.getUsername() + "'";
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;

		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt1 = (PreparedStatement) conn.prepareStatement(query);
			stmt1.execute();
			stmt2 = (PreparedStatement) conn.prepareStatement(query2);
			stmt2.execute();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
