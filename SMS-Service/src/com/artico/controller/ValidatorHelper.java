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

	private String getSMSType(String sms) {
		if (!sms.matches(GSM_CHARACTERS_REGEX)) {
			return "GSM";
		} else {
			return "UNICODE";
		}

	}
	private int getSMSQuantity(String sms, String smsType){
		return 0;
		
	}
}
	