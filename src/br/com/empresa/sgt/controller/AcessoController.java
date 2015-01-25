package br.com.empresa.sgt.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.errors.EncryptionException;

import br.com.empresa.sgt.business.remote.UsuarioBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractMB;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.RegistroAcesso;
import eu.bitwalker.useragentutils.UserAgent;

@Named
@RequestScoped
public class AcessoController extends AbstractMB {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	@EJB private UsuarioBusinessRemote usuarioBusiness;
	
	// Campos da tela
	private String login = "";
	private String senha = "";
	
	public AcessoController() {}
	
	public String logon() throws EncryptionException, BusinessException {
//		Usuario usuario = usuarioBusiness.autenticar(login, senha, this.criarRegistroAcesso(this.getResquest()));
//		this.getSession().setAttribute(AcessoController.USUARIO_LOGADO, usuario);
	
		// TODO Ver por que as vezes da pau no firefox
		// TODO Ver um maneira legal de botar o caminho das telas.
		// Provavelmente vai ser utilizado no prettyface.
		return "/view/controleAcesso/usuario/usuarioIncluir.html";
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
		return "pagina_inicio";
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
