package event_management.model;

import event_management.util.CmnUtil;

/**
 * @author hsingh
 *
 */
/**
 * @author hsingh
 *
 */
public class UserErrorMsgs {

	private String utaIdError;
	private String usernameError;
	private String passwordError;
	private String firstNameError;
	private String lastNameError;
	private String emailError;
	private String phoneError;
	private String zipCodeError;
	private String streetNameError;
	private String streetNumberError;
	private String stateError;
	private String roleError;
	private String cityError;
	private String errorMsg;
	private String userDeleteSuccess;

	public String getUtaIdError() {
		return utaIdError;
	}

	public void setUtaIdError(String utaIdError) {
		this.utaIdError = utaIdError;
	}

	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getFirstNameError() {
		return firstNameError;
	}

	public void setFirstNameError(String firstNameError) {
		this.firstNameError = firstNameError;
	}

	public String getLastNameError() {
		return lastNameError;
	}

	public void setLastNameError(String lastNameError) {
		this.lastNameError = lastNameError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getPhoneError() {
		return phoneError;
	}

	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}

	public String getZipCodeError() {
		return zipCodeError;
	}

	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}

	public String getStreetNameError() {
		return streetNameError;
	}

	public void setStreetNameError(String streetNameError) {
		this.streetNameError = streetNameError;
	}

	public String getStreetNumberError() {
		return streetNumberError;
	}

	public void setStreetNumberError(String streetNumberError) {
		this.streetNumberError = streetNumberError;
	}

	public String getStateError() {
		return stateError;
	}

	public void setStateError(String stateError) {
		this.stateError = stateError;
	}

	public String getRoleError() {
		return roleError;
	}

	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}

	public String getCityError() {
		return cityError;
	}

	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {

		if (!CmnUtil.isNullorEmpty(utaIdError) || !CmnUtil.isNullorEmpty(usernameError)
				|| !CmnUtil.isNullorEmpty(passwordError) || !CmnUtil.isNullorEmpty(firstNameError)
				|| !CmnUtil.isNullorEmpty(lastNameError) || !CmnUtil.isNullorEmpty(emailError)
				|| !CmnUtil.isNullorEmpty(phoneError) || !CmnUtil.isNullorEmpty(zipCodeError)
				|| !CmnUtil.isNullorEmpty(streetNameError) || !CmnUtil.isNullorEmpty(streetNumberError)
				|| !CmnUtil.isNullorEmpty(stateError) || !CmnUtil.isNullorEmpty(roleError)
				|| !CmnUtil.isNullorEmpty(cityError))
			this.errorMsg = "please fix all errors";
	}

	public void setErrorMsgCustome(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getUserDeleteSuccess() {
		return userDeleteSuccess;
	}

	public void setUserDeleteSuccess(String userDeleteSuccess) {
		this.userDeleteSuccess = userDeleteSuccess;
	}

}
