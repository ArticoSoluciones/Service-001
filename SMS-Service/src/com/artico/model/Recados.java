/**
 * 
 */
package com.artico.model;

/**
 * @author asanchez
 * 
 */
public class Recados {
	private int id;
	private String destino;
	private String mensaje;
	private String estado;
	private String tipoTexto;
	private int cantMensajes;
	private String MensajeError;
	private char estadoProceso = 'L';
	private static Recados instancia = null;
	
	public static Recados getInstancia() {
		if(instancia == null) {
			instancia = new Recados();
	      }
	      return instancia;
	}
	public static void setInstancia(Recados instancia) {
		Recados.instancia = instancia;
	}
	public char getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(char estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoTexto() {
		return tipoTexto;
	}
	public void setTipoTexto(String tipoTexto) {
		this.tipoTexto = tipoTexto;
	}
	public int getCantMensajes() {
		return cantMensajes;
	}
	public void setCantMensajes(int cantMensajes) {
		this.cantMensajes = cantMensajes;
	}
	public String getMensajeError() {
		return MensajeError;
	}
	public void setMensajeError(String mensajeError) {
		MensajeError = mensajeError;
	}
	
}