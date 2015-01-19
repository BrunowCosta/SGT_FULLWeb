package br.com.empresa.sgt.controller.arq;

import java.io.Serializable;

import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.empresa.sgt.controller.AcessoController;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.Usuario;
import br.com.empresa.sgt.utils.FacesMessageUtils;


@SuppressWarnings("serial")
public abstract class AbstractController implements Serializable  {
	
	@Inject protected FacesMessageUtils facesMasseUtils;
	
	protected Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario userLogged = (Usuario) session.getAttribute(AcessoController.USUARIO_LOGADO);
		return userLogged;
	}
	
	protected HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	protected HttpServletRequest getResquest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected void addInterfaceMessage(BusinessException e) {
		facesMasseUtils.addInterfaceMessage(e);
	}
	
	protected void addInterfaceMessage(String mensagem, String prefixo, Severity severity) {
		facesMasseUtils.addInterfaceMessage(mensagem, prefixo, severity);
	}

}
