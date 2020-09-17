package event_management.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import event_management.util.CmnUtil;

public class Card {

	public static final String CARD_ERROR_CNO_EMPTY = "Card number must not be empty";
	public static final String CARD_ERROR_CNO_SIZE = "Credit card number must be 16 digits";
	public static final String CARD_ERROR_CNO_M_NUM = "Credit card number must be a number";

	public static final String CARD_ERROR_EXP_EMPTY = "Credit card exp date must not be empty";
	public static final String CARD_ERROR_EXP_SIZE = "Credit card exp date must be 4 digits";
	public static final String CARD_ERROR_EXP_M_NUM = "Credit card exp date must be a number";
	public static final String CARD_ERROR_EXP_FUT = "Credit card exp date must be a future date";

	public static final String CARD_ERROR_PIN_EMPTY = "Credit card pin must not be empty";
	public static final String CARD_ERROR_PIN_SIZE = "Credit card pin must be 4 digits";
	public static final String CARD_ERROR_PIN_M_NUM = "Credit card pin must be a number";

	String cardNumber, expDate, pin;
	private CardErrorMsgs errorMsgs;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void validate(CardErrorMsgs cardErrorMsgs) {
		validateCardNumber();
		validateExpDate();
		validatePin();
		errorMsgs.setErrorMsg("");

	}

	private void validatePin() {
		if (CmnUtil.isNullorEmpty(pin)) {
			errorMsgs.setPinError(CARD_ERROR_PIN_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(pin)) {
			errorMsgs.setPinError(CARD_ERROR_PIN_M_NUM);
		} else if (!CmnUtil.stringSize(pin, 4, 4)) {
			errorMsgs.setPinError(CARD_ERROR_PIN_SIZE);
		}

	}

	private void validateCardNumber() {
		// TODO Auto-generated method stub
		if (CmnUtil.isNullorEmpty(cardNumber)) {
			errorMsgs.setCardNumberError(CARD_ERROR_CNO_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(cardNumber)) {
			errorMsgs.setCardNumberError(CARD_ERROR_CNO_M_NUM);
		} else if (!CmnUtil.stringSize(cardNumber, 16, 16)) {
			errorMsgs.setCardNumberError(CARD_ERROR_CNO_SIZE);
		}

	}

	private void validateExpDate() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyy");
		Date dateForm=new Date();
		try {
			dateForm = dateFormat.parse("31"+expDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateSys=new Date();

		if (CmnUtil.isNullorEmpty(expDate)) {
			errorMsgs.setExpDateError(CARD_ERROR_EXP_EMPTY);
		} else if (!CmnUtil.isTextAnInteger(expDate)) {
			errorMsgs.setExpDateError(CARD_ERROR_EXP_M_NUM);
		} else if (!CmnUtil.stringSize(expDate, 4, 4)) {
			errorMsgs.setExpDateError(CARD_ERROR_EXP_SIZE);
		}else if(dateSys.compareTo(dateForm)>0) {
			errorMsgs.setExpDateError(CARD_ERROR_EXP_FUT);
		}
	}

	
	public void setErrorMsgs(CardErrorMsgs errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

}
