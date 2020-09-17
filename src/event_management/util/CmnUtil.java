package event_management.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import event_management.model.User;

public class CmnUtil {

	public static boolean isTextAnInteger(String str) {

		boolean result;
		try {
			Long.parseLong(str);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;

	}

	public static boolean stringSize(String string, int min, int max) {
		return string.length() >= min && string.length() <= max;
	}

	public static String validateEmail(String email) {
		String result = "", extension = "";
		List<String> ext=Arrays.asList(".org",".edu",".com",".net",".gov",".mil");
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else if (!stringSize(email, 7, 45))
			result = "Email address must be between 7 and 45 characters long";
		else {
			extension = email.substring(email.length() - 4, email.length());
			if (!ext.contains(extension))
				result = "Invalid domain name";
		}
		return result;
	}

	public static String validatePhone(String phone) {
		String result = "";
		if (phone.length() != 10)
			result = "Phone number must be 10 digits in length";
		else if (!isTextAnInteger(phone))
			result = "Phone number must be a number";
		return result;
	}

	public static boolean isNullorEmpty(String str) {
		return str == null || str.isEmpty();

	}

	public static boolean containsNumber(String strng) {
		String str = "1234567890";
		for (int i = 0; i < strng.length(); i++) {
			if (str.indexOf(strng.charAt(i)) != -1)
				return true;
		}
		return false;

	}

	public static boolean containsSpecialChar(String strng) {
		String str = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
		for (int i = 0; i < strng.length(); i++) {
			if (str.indexOf(strng.charAt(i)) != -1)
				return true;
		}
		return false;

	}

	public static boolean containsUpperCase(String strng) {
		String str = "QWERTYUIOPLKJHGFDSAZXCVBNM";
		for (int i = 0; i < strng.length(); i++) {
			if (str.indexOf(strng.charAt(i)) != -1)
				return true;
		}
		return false;

	}

	public static boolean containsLowerCase(String strng) {
		String str = "qwertyuioplkjhgfdsazxcvbnm";
		for (int i = 0; i < strng.length(); i++) {
			if (str.indexOf(strng.charAt(i)) != -1)
				return true;
		}
		return false;

	}

	public static User getLoggedInUser(HttpSession session) {
		return (User) session.getAttribute("LOGIN_USER");

	}

	public static String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getTodaysHrs() {
		DateFormat dateFormat = new SimpleDateFormat("HH");
		Date date = new Date();
		return dateFormat.format(date) + ":00";
	}

	public static Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	public static String getFirstDateOfWeek(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateJava = null;
		try {
			dateJava = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = dateToCalendar(dateJava);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMinimum(Calendar.DAY_OF_WEEK));
		return sdf.format(c.getTime());

	}

	public static String getLastDateOfWeek(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateJava = null;
		try {
			dateJava = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = dateToCalendar(dateJava);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMinimum(Calendar.DAY_OF_WEEK));
		c.add(Calendar.DAY_OF_MONTH, 7);
		return sdf.format(c.getTime());

	}

	public static boolean checkIfSunday(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateJava = null;
		try {
			dateJava = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = dateToCalendar(dateJava);
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	public static boolean checkIfWeekend(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateJava = null;
		try {
			dateJava = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = dateToCalendar(dateJava);
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public static Time getTime(String time) {
		java.sql.Time ppstime = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			java.util.Date d1 = (java.util.Date) format.parse(time);
			ppstime = new java.sql.Time(d1.getTime());

		} catch (Exception e) {
		}
		return ppstime;
	}

	public static Date getDateTime(String date, String time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateObj = new Date();
		try {
			dateObj = dateFormat.parse(date + " " + time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateObj;
	}

	public static void main(String[] args) {
		Calendar c = CmnUtil.dateToCalendar(CmnUtil.getDateTime("2020-03-08", "22:00"));
		System.err.println(c.getTime());
		c.add(Calendar.HOUR_OF_DAY, 2);
		Date endTimeObj = c.getTime();
		System.out.println(endTimeObj);
		System.out.println(getTime("12:00"));
	}

	public static boolean isValidState(String st) {
		String[] str = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
				"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
				"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };
		for (int i = 0; i < str.length; i++) {
			if (str[i].equalsIgnoreCase(st))
				return true;
		}
		return false;

	}

}
