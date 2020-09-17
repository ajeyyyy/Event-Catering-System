package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import event_management.model.Card;
import event_management.model.CardErrorMsgs;
import event_management.util.CmnUtil;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CardTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@FileParameters("resources/card_number.csv")
	public void testCardNumnber(String cardNumber, String expDate, String pin, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		Card card = new Card();
		CardErrorMsgs errorMsgs = new CardErrorMsgs();
		card.setErrorMsgs(errorMsgs);
		setCardTest(card, cardNumber, expDate, pin);

		card.validate(null);
		if(error==null) {
			assertTrue(CmnUtil.isNullorEmpty(errorMsgs.getErrorMsg()));
		}
		assertEquals(error, errorMsgs.getCardNumberError());

	}

	@Test
	@FileParameters("resources/exp_date.csv")
	public void testExpDate(String cardNumber, String expDate, String pin, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		Card card = new Card();
		CardErrorMsgs errorMsgs = new CardErrorMsgs();
		card.setErrorMsgs(errorMsgs);
		setCardTest(card, cardNumber, expDate, pin);

		card.validate(null);
		if(error==null) {
			assertTrue(CmnUtil.isNullorEmpty(errorMsgs.getErrorMsg()));
		}
		assertEquals(error, errorMsgs.getExpDateError());

	}

	@Test
	@FileParameters("resources/pin.csv")
	public void testPin(String cardNumber, String expDate, String pin, String error) {
		if (CmnUtil.isNullorEmpty(error)) {
			error = null;
		}
		Card card = new Card();
		CardErrorMsgs errorMsgs = new CardErrorMsgs();
		card.setErrorMsgs(errorMsgs);
		setCardTest(card, cardNumber, expDate, pin);

		card.validate(null);
		if(error==null) {
			assertTrue(CmnUtil.isNullorEmpty(errorMsgs.getErrorMsg()));
		}
		assertEquals(error, errorMsgs.getPinError());

	}

	private void setCardTest(Card card, String cardNumber, String expDate, String pin) {
		// TODO Auto-generated method stub
		card.setCardNumber(cardNumber);
		card.setPin(pin);
		card.setExpDate(expDate);

	}

}
