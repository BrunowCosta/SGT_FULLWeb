package br.com.empresa.sgt.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.com.empresa.sgt.utils.FacesMessageUtils;

public class CustomExceptionHandler extends ExceptionHandlerWrapper  {

	/**
	 * 
	 */
	private ExceptionHandler wrapped;
		  
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	private NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
		
	//Declara o construtor que recebe uma exceptio do tipo ExceptionHandler como parâmetro
	public CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}
		
	//Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}
	
	//Sobrescreve o método handle que é responsável por manipular as exceções do JSF
	@Override
	public void handle() throws FacesException {
	  
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();
			try {
				if (t instanceof ViewExpiredException) {
					this.trataErroViewExpired(t);
				} else if(this.isCausedByBusinessException(t)) {
					this.trataErroNegocio(t);
				} else {
					this.trataErroGenerico(t);
				}
			} finally {
	          i.remove();
	        }
		}
		getWrapped().handle();
	}
  
	private boolean isCausedByBusinessException (Throwable t) {
		if(t instanceof FacesException) {
			FacesException excep = (FacesException) t;
			if (excep.getMessage() != null && excep.getMessage().contains(BusinessException.class.getSimpleName())) {
				return true;
			}
		}
		
		return false;
	}
	  
	// Trata exceções de negocio
	private void trataErroNegocio(Throwable t) {
		Throwable cause = t.getCause();
		while (cause != null && !(cause instanceof BusinessException)) {
			cause = cause.getCause();
		}
		BusinessException businessException = (BusinessException) cause;
		FacesMessageUtils.getInstance().addInterfaceMessage(businessException);
	}
  
	// Trata qualquer exceção gerenericamente
	private void trataErroGenerico(Throwable t) {
		t.printStackTrace();
		requestMap.put("stackTrace", t.getMessage());
		nav.handleNavigation(facesContext, null, "erroPage");
	}
  
	// Trata view expired 
	private void trataErroViewExpired(Throwable t) {
		ViewExpiredException vee = (ViewExpiredException) t;
		// Passa a viewExpired para a requisição, será exibida na viewExpiredPage.xhtml.
		requestMap.put("viewExpired", vee.getViewId());
		nav.handleNavigation(facesContext, null, "viewExpiredPage");
		facesContext.renderResponse();
	}

}