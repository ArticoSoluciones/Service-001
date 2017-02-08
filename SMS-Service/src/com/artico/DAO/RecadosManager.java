package com.artico.DAO;

import java.sql.*;

import javax.sound.midi.ShortMessage;

import com.artico.controller.SMSHelper;
import com.artico.model.Recados;
import oracle.jdbc.OracleTypes;

public class RecadosManager implements Runnable {

	private int noThread = 0;

	public RecadosManager(int n) {
		noThread = n;
	}

	public void run() {
		System.out.println("Starting Thread " + noThread);
		while (true) {
			try {
				if(Recados.getInstancia().getEstadoProceso() == 'L' ){
					obtieneSMSPendiente();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Sleep... " + noThread);
				try {
					Thread.sleep(30000);
				} catch (Exception e) {
				}
			}
		}
	}

	private static void obtieneSMSPendiente() throws SQLException {
		Connection dbConnection = ConectionHelper.getConnection("env " + 1);
		SMSHelper sms = new SMSHelper();
		dbConnection.setAutoCommit(false);
		CallableStatement callableStatement = null;
		ResultSet rs = null;

		String getDBUSERCursorSql = "{call PKG_RECADOS.OBTIENESMSPENDIENTE(?)}";

		try {
			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

			// execute getDBUSERCursor store procedure
			callableStatement.executeUpdate();

			// get cursor and cast it to ResultSet
			rs = (ResultSet) callableStatement.getObject(1);
			Recados.getInstancia().setEstadoProceso('P');
			while (rs.next()) {
				int id = rs.getInt("ID");
				String telefono = rs.getString("TELEFONO");
				String asunto = rs.getString("ASUNTO");
				String mensaje = rs.getString("MENSAJE");
				sms.enviaSMS(telefono, mensaje);
				System.out.println("id : " + id);
				System.out.println("telefono : " + telefono);
				System.out.println("asunto : " + asunto);
				System.out.println("mensaje : " + mensaje);
				cambiaEstadoSMS(id);
				Thread.sleep(30000);
			}
			Recados.getInstancia().setEstadoProceso('L');

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				rs.close();
			}

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
public static void cambiaEstadoSMS(int idSMS) throws SQLException{
	Connection dbConnection = ConectionHelper.getConnection("env " + 2);
	dbConnection.setAutoCommit(false);
	CallableStatement callableStatement = null;
	ResultSet rs = null;
	String error = null;
	String sql = "{? = call PKG_RECADOS.ENVIADO_TEXTO(?,?,?,?,?)}";

	try {
		callableStatement = dbConnection.prepareCall(sql);
		callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
		
		callableStatement.setInt(2, idSMS);
		callableStatement.setInt(3, 1000);
		callableStatement.setString(4, "GSM");
		callableStatement.setInt(5, 1);
		callableStatement.setString(6, error);
		

		// execute getDBUSERCursor store procedure
		callableStatement.executeUpdate();
		
		// get cursor and cast it to ResultSet
		System.out.println("update = " + callableStatement.getInt(1));
	} catch (SQLException e) {

		System.out.println(e.getMessage());

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {

		if (rs != null) {
			rs.close();
		}

		if (callableStatement != null) {
			callableStatement.close();
		}

		if (dbConnection != null) {
			dbConnection.close();
		}

	}	
	
}	
	

}