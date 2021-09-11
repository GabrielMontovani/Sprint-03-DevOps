package br.com.fiap.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String local;
	private Date data;
	private Date dataSaida;
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	private int adultos;
	private int criancas;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getAdultos() {
		return adultos;
	}
	public void setAdultos(int adultos) {
		this.adultos = adultos;
	}
	public int getCriancas() {
		return criancas;
	}
	public void setCriancas(int criancas) {
		this.criancas = criancas;
	}
	
	@Override
	public String toString() {
		return "Reserva [local=" + local + ", Data do Check-in= " + data + ", Data do Check-out= " + dataSaida + ", adultos=" + adultos + ", criancas=" + criancas + "]";
	}

	
}
