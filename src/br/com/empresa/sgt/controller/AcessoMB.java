package br.com.empresa.sgt.controller;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.errors.EncryptionException;

import br.com.empresa.sgt.business.remote.AcessoBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractMB;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.RegistroAcesso;
import br.com.empresa.sgt.model.acesso.Usuario;
import eu.bitwalker.useragentutils.UserAgent;

@Named
@SessionScoped
public class AcessoMB extends AbstractMB {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	@EJB private AcessoBusinessRemote acessoBusiness;
	
	// Campos da tela
	private String login = "";
	private String senha = "";
	
	public AcessoMB() {}
	
	public String logon() throws EncryptionException, BusinessException {
//		Usuario usuario = acessoBusiness.autenticar(login, senha, this.criarRegistroAcesso(this.getResquest()));
//		this.getSession().setAttribute(AcessoMB.USUARIO_LOGADO, usuario);
	
		// TODO Ver um maneira legal de botar o caminho das telas.
		// Provavelmente vai ser utilizado no prettyface.
		this.limparCampos();
		return "/view/controleAcesso/usuario/usuarioIncluir";
	}
	
	private RegistroAcesso criarRegistroAcesso(HttpServletRequest req){
		UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
		RegistroAcesso registro = new RegistroAcesso();
		registro.setBrowser(userAgent.getBrowser().getName());
		registro.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		registro.setSistemaOperacional(userAgent.getOperatingSystem().getName());
		registro.setDispositivo(userAgent.getOperatingSystem().getDeviceType().getName());
		registro.setIp(this.getEnderecoIpRemoto(req));
		return registro;
	}
	
	private String getEnderecoIpRemoto(HttpServletRequest req){
		String ipAddress = req.getHeader("x-forwarded-for");
		if (ipAddress == null) {
		    ipAddress = req.getHeader("X_FORWARDED_FOR");
		    if (ipAddress == null){
		        ipAddress = req.getRemoteAddr();
		    }
		}
		return ipAddress;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/view/index";
	}
	
	private void limparCampos() {
		this.login = "";
		this.senha = "";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
