package br.com.fiap.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.User;



public class AuthorizationListener implements PhaseListener {

	private static final long seriaVersionUID = 1L;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String viewId = context.getViewRoot().getViewId();
		if(viewId.equals("/login.xhtml")) return;
		
		
		User user = (User) context
				.getExternalContext().getSessionMap().get("user");
		
		if(user != null) {
			return;
		}else {
			NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(context,"", "login?faces-redirect=true");
			
		}
		
	}


	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
