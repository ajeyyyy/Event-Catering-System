package event_management.model;

import java.util.List;

import event_management.data.UserDAO;
import event_management.util.CmnUtil;

/**
 * @author hsingh
 *
 */
public class User {

	public static final String USER_SAVE_PATH = "saveUser";
	public static final String USER_LOGIN_PATH = "login";
	public static final String USER_SEARCH_PATH = "searchUser";
	public static final String USER_GET_PATH = "getUser";
	public static final String USER_GET_PATH_UPDATE = "getUserUpdate";
	public static final String USER_UPDATE_PATH = "updateUser";
	public static final String USER_LOGOUT_PATH = "logout";
	public static final String USER_DELETE_PATH = "deleteUser";

	public static final String USER_SUCCESS_DELETE = "user Deleted Successfully";

	public static final String USER_ERROR_UTA_ID_EMPTY = "UTA Id can not be empty";
	public static final String USER_ERROR_UTA_ID_INT = "UTA Id must be numeric";
	public static final String USER_ERROR_UTA_ID_SIZE = "UTA Id must have a length of 10";

	public static final String USER_ERROR_FNAME_EMPTY = "First name must not be empty";
	public static final String USER_ERROR_FNAME_CAPS = "First name must start with a capital letter";
	public static final String USER_ERROR_FNAME_SIZE = "First name length must be >2 and <30";
	public static final String USER_ERROR_FNAME_NUM = "First name cannot contains number";
	public static final String USER_ERROR_FNAME_SPE_CHAR = "First name cannot contain special characters";

	public static final String USER_ERROR_LNAME_EMPTY = "Last name must not be empty";
	public static final String USER_ERROR_LNAME_CAPS = "Last name must start with a capital letter";
	public static final String USER_ERROR_LNAME_SIZE = "Last name length must be >2 and <30";
	public static final String USER_ERROR_LNAME_NUM = "Last name cannot contains number";
	public static final String USER_ERROR_LNAME_SPE_CHAR = "Last name cannot contain special characters";

	public static final String USER_ERROR_PASS_EMPTY = "Password must not be empty";
	public static final String USER_ERROR_PASS_M_UPPER = "Password must contain an upper case letter";
	public static final String USER_ERROR_PASS_M_LOWER = "Password must contain a lower case letter";
	public static final String USER_ERROR_PASS_SIZE = "Password length must be >7 and <30";
	public static final String USER_ERROR_PASS_M_NUMBER = "Password must contain a number";
	public static final String USER_ERROR_PASS_M_SPECIAL_CHAR = "Password must contain a special character";

	public static final String USER_ERROR_UNAME_EMPTY = "Username must not be empty";
	public static final String USER_ERROR_UNAME_M_LETTER = "Username must start with a letter";
	public static final String USER_ERROR_UNAME_SIZE = "Username length must be >4 and <=20";
	public static final String USER_ERROR_UNAME_SPECIAL_CHAR = "Username cannot contain special characters";

	public static final String USER_ERROR_PHONE_EMPTY = "Phone number must not be empty";
	public static final String USER_ERROR_PHONE_NUMERIC = "Phone number must be numeric";
	public static final String USER_ERROR_PHONE_SIZE = "Phone number must have 10 digits";

	public static final String USER_ERROR_ST_NAME_EMPTY = "Street name must not be empty";
	public static final String USER_ERROR_ST_NAME_NUMERIC = "Street name be non-numeric";
	public static final String USER_ERROR_ST_NAME_SIZE = "Street name length must be less than 40";

	public static final String USER_ERROR_STATE_EMPTY = "State must not be empty";
	public static final String USER_ERROR_STATE_M_NON_NUMERIC = "State must be non-numeric";
	public static final String USER_ERROR_STATE_SIZE = "State must be a 2 letter abbreviation";
	public static final String USER_ERROR_STATE_ABBRIVATION = "State abbreviation not found";
	// public static final String USER_ERROR_ST_NAME_EMPTY = "State abbreviation not
	// found";
	public static final String USER_ERROR_STREET_EMPTY = "Street must not be empty";
	public static final String USER_ERROR_STREET_NUM_EMPTY = "Street number must be numeric";
	public static final String USER_ERROR_STREE_NUM_SIZE = "Street number length must be >0 and <7";

	public static final String USER_ERROR_ZIPCODE_EMPTY = "Zipcode must not be empty";
	public static final String USER_ERROR_ZIPCODE_M_NUMERIC = "Zipcode must be numeric";
	public static final String USER_ERROR_ZIPCODE_SIZE = "Zipcode must have a length of 5";

	public static final String USER_ERROR_CITY_EMPTY = "City must not be empty";
	public static final String USER_ERROR_CITY_CAPS = "City must start with a capital letter";
	public static final String USER_ERROR_CITY_SIZE = "City length must be >2 and <30";
	public static final String USER_ERROR_CITY_NUMERIC = "City cannot be a number";
	public static final String USER_ERROR_CITY_SPECIAL_CHAR = "City cannot contain special characters";

	public void validateCity() {
		if (CmnUtil.isNullorEmpty(city)) {
			errorMsgs.setCityError(USER_ERROR_CITY_EMPTY);
		} else if (CmnUtil.containsNumber(city)) {
			errorMsgs.setCityError(USER_ERROR_CITY_NUMERIC);
		} else if (CmnUtil.containsSpecialChar(city)) {
			errorMsgs.setCityError(USER_ERROR_CITY_SPECIAL_CHAR);
		} else if (!Character.isUpperCase(city.charAt(0))) {
			errorMsgs.setCityError(USER_ERROR_CITY_CAPS);
		} else if (!CmnUtil.stringSize(city, 3, 29)) {
			errorMsgs.setCityError(USER_ERROR_CITY_SIZE);
		}

	}

	public void validateZipCode() {
		if (CmnUtil.isNullorEmpty(zipCode)) {
			errorMsgs.setZipCodeError(USER_ERROR_ZIPCODE_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(zipCode)) {
			errorMsgs.setZipCodeError(USER_ERROR_ZIPCODE_M_NUMERIC);
		} else if (!CmnUtil.stringSize(zipCode, 5, 5)) {
			errorMsgs.setZipCodeError(USER_ERROR_ZIPCODE_SIZE);
		}
	}

	public void validateStreetNumber() {
		if (CmnUtil.isNullorEmpty(streetNumber)) {
			errorMsgs.setStreetNumberError(USER_ERROR_STREET_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(streetNumber)) {
			errorMsgs.setStreetNumberError(USER_ERROR_STREET_NUM_EMPTY);
		} else if (!CmnUtil.stringSize(streetNumber, 1, 6)) {
			errorMsgs.setStreetNumberError(USER_ERROR_STREE_NUM_SIZE);
		}
	}

	public void validateState() {
		if (CmnUtil.isNullorEmpty(state)) {
			errorMsgs.setStateError(USER_ERROR_STATE_EMPTY);
		} else if (CmnUtil.containsNumber(state)) {
			errorMsgs.setStateError(USER_ERROR_STATE_M_NON_NUMERIC);
		} else if (!CmnUtil.stringSize(state, 2, 2)) {
			errorMsgs.setStateError(USER_ERROR_STATE_SIZE);
		} else if (!CmnUtil.isValidState(state)) {
			errorMsgs.setStateError(USER_ERROR_STATE_ABBRIVATION);
		}
	}

	private String utaId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String zipCode;
	private String streetName;
	private String streetNumber;
	private String state;
	private String role;
	private String city;
	private UserErrorMsgs errorMsgs;

	public String getUtaId() {
		return utaId;
	}

	public void validateUtaId() {
		if (CmnUtil.isNullorEmpty(utaId)) {
			errorMsgs.setUtaIdError(USER_ERROR_UTA_ID_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(utaId)) {
			errorMsgs.setUtaIdError(USER_ERROR_UTA_ID_INT);
		} else if (!CmnUtil.stringSize(utaId, 10, 10)) {
			errorMsgs.setUtaIdError(USER_ERROR_UTA_ID_SIZE);
		}

	}

	public void validateFirstName() {

		if (CmnUtil.isNullorEmpty(firstName)) {
			errorMsgs.setFirstNameError(USER_ERROR_FNAME_EMPTY);
		} else if (CmnUtil.containsNumber(firstName)) {
			errorMsgs.setFirstNameError(USER_ERROR_FNAME_NUM);
		} else if (CmnUtil.containsSpecialChar(firstName)) {
			errorMsgs.setFirstNameError(USER_ERROR_FNAME_SPE_CHAR);
		} else if (!Character.isUpperCase(firstName.charAt(0))) {
			errorMsgs.setFirstNameError(USER_ERROR_FNAME_CAPS);
		} else if (!CmnUtil.stringSize(firstName, 3, 29)) {
			errorMsgs.setFirstNameError(USER_ERROR_FNAME_SIZE);
		}

	}

	public void validateLastName(String action) {

		if (CmnUtil.isNullorEmpty(lastName)) {
			errorMsgs.setLastNameError(USER_ERROR_LNAME_EMPTY);
			return;
		}
		if (USER_SAVE_PATH.equals(action)) {
			if (CmnUtil.containsNumber(lastName)) {
				errorMsgs.setLastNameError(USER_ERROR_LNAME_NUM);
			} else if (CmnUtil.containsSpecialChar(lastName)) {
				errorMsgs.setLastNameError(USER_ERROR_LNAME_SPE_CHAR);
			} else if (!Character.isUpperCase(lastName.charAt(0))) {
				errorMsgs.setLastNameError(USER_ERROR_LNAME_CAPS);
			} else if (!CmnUtil.stringSize(lastName, 3, 29)) {
				errorMsgs.setLastNameError(USER_ERROR_LNAME_SIZE);
			}
		}

	}

	public void validatePassword(String action) {

		if (CmnUtil.isNullorEmpty(password)) {
			errorMsgs.setPasswordError(USER_ERROR_PASS_EMPTY);
			return;
		}
		if (USER_SAVE_PATH.equals(action)) {
			if (!CmnUtil.containsUpperCase(password)) {
				errorMsgs.setPasswordError(USER_ERROR_PASS_M_UPPER);
			} else if (!CmnUtil.containsLowerCase(password)) {
				errorMsgs.setPasswordError(USER_ERROR_PASS_M_LOWER);
			} else if (!CmnUtil.containsSpecialChar(password)) {
				errorMsgs.setPasswordError(USER_ERROR_PASS_M_SPECIAL_CHAR);
			} else if (!CmnUtil.containsNumber(password)) {
				errorMsgs.setPasswordError(USER_ERROR_PASS_M_NUMBER);
			} else if (!CmnUtil.stringSize(password, 8, 29)) {
				errorMsgs.setPasswordError(USER_ERROR_PASS_SIZE);
			}
		}

	}

	public void validateUserName(String action) {
		if (CmnUtil.isNullorEmpty(username)) {
			errorMsgs.setUsernameError(USER_ERROR_UNAME_EMPTY);
			return;
		}

		if (USER_SAVE_PATH.equals(action)) {
			if (!Character.isLetter(username.charAt(0))) {
				errorMsgs.setUsernameError(USER_ERROR_UNAME_M_LETTER);
			} else if (!CmnUtil.stringSize(username, 5, 20)) {
				errorMsgs.setUsernameError(USER_ERROR_UNAME_SIZE);
			} else if (CmnUtil.containsSpecialChar(username)) {
				errorMsgs.setUsernameError(USER_ERROR_UNAME_SPECIAL_CHAR);
			}
		}

	}

	public void validatePhone() {
		if (CmnUtil.isNullorEmpty(phone)) {
			errorMsgs.setPhoneError(USER_ERROR_PHONE_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(phone)) {
			errorMsgs.setPhoneError(USER_ERROR_PHONE_NUMERIC);
		} else if (!CmnUtil.stringSize(phone, 10, 10)) {
			errorMsgs.setPhoneError(USER_ERROR_PHONE_SIZE);
		}
	}

	public void validateStreetName() {
		if (CmnUtil.isNullorEmpty(streetName)) {
			errorMsgs.setStreetNameError(USER_ERROR_ST_NAME_EMPTY);
		} else if (CmnUtil.containsNumber(streetName)) {
			errorMsgs.setStreetNameError(USER_ERROR_ST_NAME_NUMERIC);
		} else if (!CmnUtil.stringSize(streetName, 1, 39)) {
			errorMsgs.setStreetNameError(USER_ERROR_ST_NAME_SIZE);
		}
	}

	public void setUser(String utaId, String username, String password, String firstName, String lastName, String email,
			String phone, String zipCode, String streetName, String streetNumber, String state, String role,
			String city) {

		setUtaId(utaId);
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhone(phone);
		setZipCode(zipCode);
		setStreetName(streetName);
		setStreetNumber(streetNumber);
		setState(state);
		setRole(role);
		setCity(city);

	}

	public void setUtaId(String uta_id) {
		this.utaId = uta_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void validate(String action) {

		if (USER_SAVE_PATH.equals(action)) {
			validateUtaId();
			validateEmail();
			validateFirstName();
			validateLastName(USER_SAVE_PATH);
			validatePassword(USER_SAVE_PATH);
			validateUserName(USER_SAVE_PATH);
			validatePhone();
			validateStreetName();
			validateState();
			validateStreetNumber();
			validateZipCode();
			validateCity();
			validateUsernameDB();
			validateUtaIdDb();
			validateFirstLastNameDB();
			if ("admin".equals(role) || "manager".equals(role))
				validateSingleUser();

		} else if (USER_LOGIN_PATH.equals(action)) {
			validatePassword(USER_LOGIN_PATH);
			validateUserName(USER_LOGIN_PATH);
		} else if (USER_SEARCH_PATH.equals(action)) {
			validateLastName(USER_SEARCH_PATH);

		} else if (USER_UPDATE_PATH.equals(action)) {
			validateUtaId();
			validateFirstName();
			validateEmail();
			validateLastName(USER_SAVE_PATH);
			validatePassword(USER_SAVE_PATH);
			validateUserName(USER_SAVE_PATH);
			validatePhone();
			validateStreetName();
			validateState();
			validateStreetNumber();
			validateZipCode();
			validateCity();
		}
		errorMsgs.setErrorMsg("");

	}

	private void validateFirstLastNameDB() {
		if (UserDAO.getAFirstlastNameCount(this) > 0) {
			errorMsgs.setFirstNameError("last name first name combination for given role exists");
		}

	}

	private void validateEmail() {
		String res = CmnUtil.validateEmail(email);
		if (!CmnUtil.isNullorEmpty(res)) {
			errorMsgs.setEmailError(res);
		}

	}

	private void validateUtaIdDb() {
		List<User> userByRole = UserDAO.getUserByUtaId(this);
		if (userByRole.size() > 0) {
			errorMsgs.setUtaIdError("Only one Uta Id can exist in the system");
		}

	}

	private void validateUsernameDB() {
		List<User> userByRole = UserDAO.getUserByUsername(this);
		if (userByRole.size() > 0) {
			errorMsgs.setUsernameError("Only one Username can exist in the system");
		}

	}

	private void validateSingleUser() {
		List<User> userByRole = UserDAO.getUserByRole(this);
		if (userByRole.size() > 0) {
			errorMsgs.setRoleError("Only one " + role + " can exist in the system");
		}

	}

	public void setErrorMsgs(UserErrorMsgs errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

}
