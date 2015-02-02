package br.com.empresa.sgt.controller.usuario;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.com.empresa.sgt.business.remote.UsuarioBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractCrudMB.CrudAcaoEnum;
import br.com.empresa.sgt.controller.arq.AbstractMB;
import br.com.empresa.sgt.enumeration.MensagemEnum;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.Usuario;

@Named
@ViewScoped
public class UsuarioCadastrarWizardMB extends AbstractMB {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;
	
	private final String cadastrarUrl = "/view/controleAcesso/usuario/usuarioIncluir";
	private final String visualizarUrl = "/view/controleAcesso/usuario/usuarioVisualizar";

	@EJB private UsuarioBusinessRemote usuarioBusiness;
	
	//TODO INJETAR
	Usuario novoUsuario = new Usuario();
	
	public UsuarioCadastrarWizardMB() {}
	
	public String cadastrar() throws BusinessException {
		usuarioBusiness.cadastrar(novoUsuario, super.getUsuarioLogado());
		super.addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(), 
								  Usuario.class.getSimpleName(), CrudAcaoEnum.CADASTRAR.getSucessoOperacao());
		
		super.getFlash().put("usuario", novoUsuario);
//		super.getFlash().put("mensagemCadastro", )
		return visualizarUrl + AbstractMB.REDIRECT_SUFIXO;
	}
	
	//Define o que o Wizard vai fazer ao clicar em proximo.
	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
    }
	
	public String goCadastrar() {
		return cadastrarUrl;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
	
}

