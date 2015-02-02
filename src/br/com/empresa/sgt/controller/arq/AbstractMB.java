package br.com.empresa.sgt.controller.arq;

import java.io.Serializable;

import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.empresa.sgt.controller.AcessoMB;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.Usuario;
import br.com.empresa.sgt.utils.FacesMessageUtils;


@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable  {
	
	protected static final String REDIRECT_SUFIXO = "?faces-redirect=true";
	
	@Inject protected FacesMessageUtils facesMasseUtils;
	
	protected Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario userLogged = (Usuario) session.getAttribute(AcessoMB.USUARIO_LOGADO);
		return userLogged;
	}
	
	protected HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	protected HttpServletRequest getResquest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	protected void addInterfaceMessage(BusinessException e) {
		facesMasseUtils.addInterfaceMessage(e);
	}
	
	protected void addInterfaceMessage(Severity severity, String mensagem, Object...parametros) {
		facesMasseUtils.addInterfaceMessage(severity, mensagem, parametros);
	}
	
	protected Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

}
