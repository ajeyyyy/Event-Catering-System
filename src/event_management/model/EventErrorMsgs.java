package event_management.model;

import event_management.util.CmnUtil;

public class EventErrorMsgs {
	private String errorMsg;
	private String idError, usernameError, dateError, startTimeError, hallNameError, estAttendeesError,
			nameError, foodTypeError, mealError, mealFormalityError, drinkTypeError, entertainmentItemsError,
			eventStatusError, successUpdate, estCost;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		if (!CmnUtil.isNullorEmpty(nameError) || !CmnUtil.isNullorEmpty(dateError)
				|| !CmnUtil.isNullorEmpty(startTimeError)
				|| !CmnUtil.isNullorEmpty(estAttendeesError))
			this.errorMsg = "please fix all errors";
	}

	public void setErrorMsgCustome(String errorMsg) {
		this.errorMsg = errorMsg;

	}

	public String getDateError() {
		return dateError;
	}

	public void setDateError(String dateError) {
		this.dateError = dateError;
	}

	public String getStartTimeError() {
		return startTimeError;
	}

	public void setStartTimeError(String startTimeError) {
		this.startTimeError = startTimeError;
	}

	public String getEstAttendeesError() {
		return estAttendeesError;
	}

	public void setEstAttendeesError(String estAttendeesError) {
		this.estAttendeesError = estAttendeesError;
	}

	public String getNameError() {
		return nameError;
	}

	public void setNameError(String nameError) {
		this.nameError = nameError;
	}

	public String getSuccessUpdate() {
		return successUpdate;
	}

	public void setSuccessUpdate(String successUpdate) {
		this.successUpdate = successUpdate;
	}

	public String getIdError() {
		return idError;
	}

	public void setIdError(String idError) {
		this.idError = idError;
	}

	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getHallNameError() {
		return hallNameError;
	}

	public void setHallNameError(String hallNameError) {
		this.hallNameError = hallNameError;
	}

	public String getFoodTypeError() {
		return foodTypeError;
	}

	public void setFoodTypeError(String foodTypeError) {
		this.foodTypeError = foodTypeError;
	}

	public String getMealError() {
		return mealError;
	}

	public void setMealError(String mealError) {
		this.mealError = mealError;
	}

	public String getMealFormalityError() {
		return mealFormalityError;
	}

	public void setMealFormalityError(String mealFormalityError) {
		this.mealFormalityError = mealFormalityError;
	}

	public String getDrinkTypeError() {
		return drinkTypeError;
	}

	public void setDrinkTypeError(String drinkTypeError) {
		this.drinkTypeError = drinkTypeError;
	}

	public String getEntertainmentItemsError() {
		return entertainmentItemsError;
	}

	public void setEntertainmentItemsError(String entertainmentItemsError) {
		this.entertainmentItemsError = entertainmentItemsError;
	}

	public String getEventStatusError() {
		return eventStatusError;
	}

	public void setEventStatusError(String eventStatusError) {
		this.eventStatusError = eventStatusError;
	}

	public String getEstCost() {
		return estCost;
	}

	public void setEstCost(String estCost) {
		this.estCost = estCost;
	}

}
