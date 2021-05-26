package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UserDao;
import br.com.fiap.model.User;

@Named
@RequestScoped
public class UserBean {
	
	private User user = new User();
	
	public void save() {
			
		new UserDao().save(this.user);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("usuario cadastrado com sucesso"));
		System.out.println("Salvando o setup:\n" + this.user);
	}
	
	public String login() {
		boolean exist = new UserDao().exist(user);
		//if de uma linha sem a necessidade de um bloco e else opcional
		FacesContext context = FacesContext.getCurrentInstance();
		if(exist) {
			context
				.getExternalContext().getSessionMap().put("user", user);
			return "index?faces-redirect=true";
		
		
		}
		
		//para duas requisições 
		context.getExternalContext()
			.getFlash().setKeepMessages(true);
		
		context
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido", "Erro"));
		return "login?faces-redirect=true";
	
		
	}
	
	
	public String logout() {
		FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}
	 
	
	public List<User> getUsers() {
		return new UserDao().getAll();
	}
	
	//public List<User> getUsersLogin(Long id) {
		//return new UserDao().equals(id);
	//}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	


}
