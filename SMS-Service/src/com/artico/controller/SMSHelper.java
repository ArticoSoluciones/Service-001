/**
 * 
 */
package com.artico.controller;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
/**
 * @author asanchez
 *
 */
public class SMSHelper {
	final String RUTA_MODEM = "http://192.168.1.57";
	private String urlModem= RUTA_MODEM +"/default/en_US/sms_info.html";
	private static String line = "1"; // Esta variable se utiliza para indicar al modem con cual SIM enviara el sms, en caso de que tenga mas de 1
	final static String USER = "admin"; // Usuario del modem
	final static String PASSWORD = "admin"; // Password del modem
	final static String ACTION = "sms";
	final static String SEND = "send";
	
	
	
	public int enviaSMS(String numero, String mensaje) throws Exception {
		try {
		
		URLConnection urlConnection = autenticacionModem();
		if(urlConnection!=null){
		OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
	    wr.write(formaURL(numero,mensaje));
	    wr.flush();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	    /*String line;
	    while ((line = rd.readLine()) != null) {
	      System.out.println(line);
	    }*/
	    System.out.println("Enviado sms a: "+numero);
	    wr.close();
	    rd.close();
		}else{
			return -2; 
		}
	    
		/******************/
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
		return -1;
	} catch (IOException e) {
		e.printStackTrace();
		return -1;
	}
	return 0;
	}
	
	private URLConnection autenticacionModem(){
		try {
			String authString = USER + ":" + PASSWORD;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.getEncoder().encode((authString.getBytes()));
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);

			URL url = new URL(urlModem);
			
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			return urlConnection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	//Unifica el URL para hacer el post
	public String formaURL(String numero, String mensaje){
		String urlParameters = "line="+line + "&" + "smskey=" + generarRandom() + "&" 
				+ "action=" + ACTION + "&" + "telnum=" + numero  + "&" + 
				"smscontent="+mensaje +"&" + "send=" + SEND ;
		return urlParameters;
	}
	public String generarRandom(){	
		Random rand = new Random();
	    int randomNum = rand.nextInt(9999999);
	    return String.valueOf(randomNum);
	}
	
}
