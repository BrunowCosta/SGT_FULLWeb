package br.com.empresa.sgt.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.empresa.sgt.enumeration.ErroNegocioEnum;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.exception.BusinessException.ErroNegocioPrefixoEnum;
import br.com.empresa.sgt.exception.BusinessException.ErroNegocioServidadeEnum;

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
		String prefixo = messageBundleUtils.getMensagem(e.getPrefixo().getDescricao());
		facesContext.addMessage(null, new FacesMessage(this.getServerity(e.getSeveridade()), prefixo , mensagem));
	}
	
	public void addInterfaceMessage(String mensagem, String prefixo, Severity severity) {
		mensagem = messageBundleUtils.getMensagem(mensagem);
		prefixo = messageBundleUtils.getMensagem(prefixo);
		facesContext.addMessage(null, new FacesMessage(severity, prefixo, mensagem));
	}
	
	public void addGenericErroMessage() {
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
													   messageBundleUtils.getMensagem(ErroNegocioPrefixoEnum.GENERICO.getDescricao()), 
													   messageBundleUtils.getMensagem(ErroNegocioEnum.ERRO_GENERICO.getDescricao())));
	}
	
	private Severity getServerity(ErroNegocioServidadeEnum severidade) {
		if(severidade == null) {
			return FacesMessage.SEVERITY_ERROR;
		}
		
		switch (severidade) {
		case ERRO:
			return FacesMessage.SEVERITY_ERROR;
		case FATAL:
			return FacesMessage.SEVERITY_FATAL;
		case INFO:
			return FacesMessage.SEVERITY_INFO;
		case AVISO:
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