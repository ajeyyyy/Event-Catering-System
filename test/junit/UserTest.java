package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import event_management.model.User;
import event_management.model.UserErrorMsgs;
import event_management.util.CmnUtil;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@FileParameters("resources/first_name.csv")
	public void testFirstName(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getFirstNameError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getFirstNameError());

	}

	@Test
	@FileParameters("resources/last_name.csv")
	public void testLastName(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getLastNameError());
		user.validate(User.USER_SEARCH_PATH);
		assertEquals(error, errorMsgs.getLastNameError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getLastNameError());

	}
	
	@Test
	@FileParameters("resources/password.csv")
	public void testPassword(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getPasswordError());
		user.validate(User.USER_LOGIN_PATH);
		assertEquals(error, errorMsgs.getPasswordError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getPasswordError());
		

	}

	@Test
	@FileParameters("resources/email.csv")
	public void testEmail(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getEmailError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getEmailError());
		
		

	}
	
	@Test
	@FileParameters("resources/phone_number.csv")
	public void testPhoneNo(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getPhoneError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getPhoneError());
		
		

	}
	
	@Test
	@FileParameters("resources/zip_code.csv")
	public void testZipCode(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getZipCodeError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getZipCodeError());
		
		

	}
	
	@Test
	@FileParameters("resources/st_name.csv")
	public void testStreetName(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getStreetNameError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getStreetNameError());
		
		

	}
	
	@Test
	@FileParameters("resources/street.csv")
	public void testStreetNo(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getStreetNumberError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getStreetNumberError());
		
		

	}
	
	@Test
	@FileParameters("resources/state.csv")
	public void testState(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getStateError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getStateError());
		
		

	}
	
	@Test
	@FileParameters("resources/city.csv")
	public void testCity(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getCityError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getCityError());
		
		

	}
	
	@Test
	@FileParameters("resources/uta_id.csv")
	public void testUtaId(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getUtaIdError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getUtaIdError());
		
		

	}
	
	@Test
	@FileParameters("resources/uname.csv")
	public void testUserName(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		setUser(user, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber,
				state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getUsernameError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getUsernameError());
		
		

	}
	
	@Test
	@FileParameters("resources/role.csv")
	public void testRole(String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		user.setErrorMsgs(errorMsgs);
		user.setUser(utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, state, role, city);

		user.validate(User.USER_SAVE_PATH);
		assertEquals(error, errorMsgs.getRoleError());
		user.validate(User.USER_UPDATE_PATH);
		assertEquals(error, errorMsgs.getRoleError());
		
		

	}


	 
	private void setUser(User user, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city) {
		user.setUtaId(utaId);
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setZipCode(zipCode);
		user.setStreetName(streetName);
		user.setStreetNumber(streetNumber);
		user.setState(state);
		user.setRole(role);
		user.setCity(city);
	}

}
