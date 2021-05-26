package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDao;
import br.com.fiap.model.Setup;

@Named
@RequestScoped
public class SetupBean {
	
	private Setup setup = new Setup();
	
	public void save() {
			
		new SetupDao().save(this.setup);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Setup cadastrado com sucesso"));
		System.out.println("Salvando o setup:\n" + this.setup);
	}
	
	public List<Setup> getSetups() {
		return new SetupDao().getAll();
	}
	
	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}


}
