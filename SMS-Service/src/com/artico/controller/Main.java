package com.artico.controller;
import com.artico.DAO.RecadosManager;

public class Main {
	public static void main(String[] args) throws Exception {
		//SMSHelper helper = new SMSHelper();
		//helper.enviaSMS("84136195", "TEST");
		new Thread( new RecadosManager(1)).start();
	}
}
