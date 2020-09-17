package event_management.model;

import event_management.util.CmnUtil;

public class CardErrorMsgs {

	private String cardNumberError, expDateError, pinError;
	private String errorMsg;

	public String getCardNumberError() {
		return cardNumberError;
	}

	public void setCardNumberError(String cardNumberError) {
		this.cardNumberError = cardNumberError;
	}

	public String getExpDateError() {
		return expDateError;
	}

	public void setExpDateError(String expDateError) {
		this.expDateError = expDateError;
	}

	public String getPinError() {
		return pinError;
	}

	public void setPinError(String pinError) {
		this.pinError = pinError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		if (!CmnUtil.isNullorEmpty(cardNumberError) || !CmnUtil.isNullorEmpty(expDateError)
				|| !CmnUtil.isNullorEmpty(pinError)) {
			this.errorMsg="please fix all errors";
		}
	}
}
