package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.model.Reserva;

@Named
@RequestScoped
public class ReservaBean {
	
	private Reserva reserva = new Reserva();

	public void save() {
		new ReservaDAO().save(this.reserva);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Reserva cadastrada com sucesso"));
		System.out.println(this.reserva);
	}
	
	public List<Reserva> getReservas(){
		return new ReservaDAO().getAll();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
}
