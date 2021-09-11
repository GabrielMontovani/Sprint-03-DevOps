package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.CadastroProdutosDAO;
import br.com.fiap.model.CadastroProdutos;


@Named
@RequestScoped
public class CadastroProdutosBean {

	private CadastroProdutos cadastroProdutos = new CadastroProdutos();

	public void save() {
		new CadastroProdutosDAO().save(this.cadastroProdutos);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Cadastro do produto cadastrado com sucesso"));
		System.out.println(this.cadastroProdutos);
	}
	
	public List<CadastroProdutos> getCadastroProdutoss(){
		return new CadastroProdutosDAO().getAll();
	}

	public CadastroProdutos getCadastroProdutos() {
		return cadastroProdutos;
	}

	public void setCadastroProdutos(CadastroProdutos cadastroProdutos) {
		this.cadastroProdutos = cadastroProdutos;
	}
	
}
