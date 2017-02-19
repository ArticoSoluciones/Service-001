/**
 * 
 */
package com.artico.controller;

/**
 * @author asanchez
 *
 */
public class ValidatorHelper {
	public static final String GSM_CHARACTERS_REGEX = "^[A-Za-z0-9 \\r\\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!\"#$%&amp;'()*+,\\-./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}\\\\\\[~\\]|\u20AC]*$";

	public String getSMSType(String sms) {
		if (sms.matches(GSM_CHARACTERS_REGEX)) {
			return "GSM";
		} else {
			return "UNICODE";
		}

	}

	public int getSMSQuantity(String sms, String smsType) {
		int characters = 0;
		characters = sms.length();
		if (smsType.equals("GSM")) {
			if (characters <= 160) {
				return 1;
			} else if (characters > 160 && characters <= 306) {
				return 2;
			} else if (characters > 306 && characters <= 459) {
				return 3;
			} else if (characters > 459 && characters <= 612) {
				return 4;
			} else if (characters > 612 && characters <= 765) {
				return 5;
			} else if (characters > 765 && characters <= 918) {
				return 6;
			} else {
				return 6;
			}
		} else if (smsType.equals("UNICODE")) {
			if (characters <= 67) {
				return 1;
			} else if (characters > 67 && characters <= 134) {
				return 2;
			} else if (characters > 134 && characters <= 201) {
				return 3;
			} else if (characters > 201 && characters <= 268) {
				return 4;
			} else if (characters > 268 && characters <= 335) {
				return 5;
			} else if (characters > 335 && characters <= 402) {
				return 6;
			} else {
				return 6;
			}

		}
		return -1;

	}
}
