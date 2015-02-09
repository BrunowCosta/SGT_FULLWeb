package br.com.empresa.sgt.controller.usuario;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.com.empresa.sgt.business.remote.GrupoPermissaoBusinessRemote;
import br.com.empresa.sgt.business.remote.UsuarioBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractCrudMB.CrudAcaoEnum;
import br.com.empresa.sgt.controller.arq.AbstractMB;
import br.com.empresa.sgt.enumeration.MensagemEnum;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.GrupoPermissao.GrupoPermissaoStatusEnum;
import br.com.empresa.sgt.model.acesso.Usuario;

@Named
@ViewScoped
public class UsuarioWizardMB extends AbstractMB {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;
	
	private final String cadastrarUrl = "/view/controleAcesso/usuario/usuarioIncluir";
	private final String visualizarUrl = "/view/controleAcesso/usuario/usuarioVisualizar";

	@EJB private UsuarioBusinessRemote usuarioBusiness;
	
	@EJB private GrupoPermissaoBusinessRemote grupoBussiness;
	
	private List<GrupoPermissao> gruposPermissoes;
	
	private String confirmacaoSenha;
	
	//TODO INJETAR
	Usuario usuario = new Usuario();
	
	public UsuarioWizardMB() {}
	
	public String cadastrar() throws BusinessException {
		usuarioBusiness.cadastrar(usuario, super.getUsuarioLogado());
		super.addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(), 
								  Usuario.class.getSimpleName(), CrudAcaoEnum.CADASTRAR.getSucessoOperacao());
		
		super.getFlash().put("usuario", usuario);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<GrupoPermissao> getGruposPermissoes() throws BusinessException {
		
		// Lazy
		if(gruposPermissoes == null) {
			GrupoPermissao example = new GrupoPermissao();
			example.setStatus(GrupoPermissaoStatusEnum.ATIVO);
			gruposPermissoes = grupoBussiness.pesquisar(example);
		}
		
		return gruposPermissoes;
	}

	public void setGruposPermissoes(List<GrupoPermissao> gruposPermissoes) {
		this.gruposPermissoes = gruposPermissoes;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
}

