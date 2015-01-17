package br.com.empresa.sgt.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.empresa.sgt.exception.BusinessException;

public class FacesMessageUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -62224673944887934L;

	private MessageBundleUtils messageBundleUtils = MessageBundleUtils.getInstance();

	private FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private static FacesMessageUtils instance;
	
	public static FacesMessageUtils getInstance(){
		if (instance == null)
			instance = new FacesMessageUtils();
		return instance;
	}
	
	// Metódos utilizados para exibir um mensagem amigavel na interface.
	public void addInterfaceMessage(BusinessException e) {
		
		String mensagem = messageBundleUtils.traduzirMensagemMultipla(e.getMessage());
		String prefixo = "";
		if(e.getPrefixo() != null || e.getPrefixo() != "") {
			prefixo = messageBundleUtils.getMensagem(e.getPrefixo());
		}
		facesContext.addMessage(null, new FacesMessage(this.getServerity(e.getSeverity()), prefixo , mensagem));
	}
	
	public void addInterfaceMessage(String mensagem, String prefixo, String severity) {
		mensagem = messageBundleUtils.getMensagem(mensagem);
		prefixo = messageBundleUtils.getMensagem(prefixo);
		facesContext.addMessage(null, new FacesMessage(this.getServerity(severity), prefixo, mensagem));
	}
	
	public void addGenericErroMessage() {
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
									messageBundleUtils.getMensagem("sistema.erroPrefixo.generico"), 
									messageBundleUtils.getMensagem("sistema.erro.generico")));
	}
	
	private Severity getServerity(String codigo) {
		
		if(codigo == null) {
			return FacesMessage.SEVERITY_ERROR;
		}
		
		switch (codigo) {
		case BusinessException.SEVERITY_ERROR:
			return FacesMessage.SEVERITY_ERROR;
		case BusinessException.SEVERITY_FATAL:
			return FacesMessage.SEVERITY_FATAL;
		case BusinessException.SEVERITY_INFO:
			return FacesMessage.SEVERITY_INFO;
		case BusinessException.SEVERITY_WARNNING:
			return FacesMessage.SEVERITY_WARN;
		default:
			return FacesMessage.SEVERITY_ERROR;
		}
	}

	public MessageBundleUtils getMessageBundleUtils() {
		return messageBundleUtils;
	}

	public void setMessageBundleUtils(MessageBundleUtils messageBundleUtils) {
		this.messageBundleUtils = messageBundleUtils;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
}