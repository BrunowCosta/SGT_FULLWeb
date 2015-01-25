package br.com.empresa.sgt.controller.arq;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.empresa.sgt.enumeration.EnumMapped;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;


/**
 * 
 * @author Bruno Costa
 *
 * @param <T> implementa Modelo
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractCrudMB<T extends Modelo> extends AbstractMB implements CrudMB<T> {
	
	protected static String cadastrarUrl;
	protected static String visualizarUrl;
	protected static String alterarUrl;
	
	@Inject protected T objetoModelo;
	
	protected CrudAcaoEnum acao;
	
	public AbstractCrudMB() {
		if (super.getFlash().containsKey("acao")) {
			acao = (CrudAcaoEnum) super.getFlash().get("acao");
		}
	}
	
	@Override
	public String goCadastrar() {
		super.getFlash().put("acao", CrudAcaoEnum.CADASTRAR);
		super.getFlash().setKeepMessages(true);
		return cadastrarUrl + AbstractMB.REDIRECT_SUFIXO;
	}
	
	@Override
	public String cadastrar() throws BusinessException {
		this.getBusinessClass().cadastrar(objetoModelo, super.getUsuarioLogado());
		addInterfaceMessage(objetoModelo.getClass().getSimpleName() + " msg.sucesso.cadastro" , null, FacesMessage.SEVERITY_INFO);
		return this.visualizar(objetoModelo);
	}

	@Override
	public String visualizar(T objetoModelo) throws BusinessException {
		this.objetoModelo = objetoModelo;
		acao = CrudAcaoEnum.VISUALIZAR;
		return visualizarUrl;
	}
	
	@Override
	public String goAlterar(T objetoModelo) {
		this.objetoModelo = objetoModelo;
		acao = CrudAcaoEnum.ALTERAR;
		return alterarUrl;
	}
	
	@Override
	public String alterar() throws BusinessException {
		this.getBusinessClass().alterar(objetoModelo, super.getUsuarioLogado());
		addInterfaceMessage(objetoModelo.getClass().getSimpleName() + " msg.sucesso.alteracao" , null, FacesMessage.SEVERITY_INFO);
		return visualizar(objetoModelo);
	}
	
	@Override
	public void remover() throws BusinessException {
		this.getBusinessClass().remover(objetoModelo.getId(), this.getUsuarioLogado());
		addInterfaceMessage(objetoModelo.getClass().getSimpleName() + " msg.sucesso.remocao" , null, FacesMessage.SEVERITY_INFO);
	}
	
	@Override
	public void ativarInativar(boolean ativo) throws BusinessException {
		this.getBusinessClass().ativarInativar(objetoModelo.getId(), ativo, super.getUsuarioLogado());
		String mensagem = ativo? "msg.sucesso.ativacao":"msg.sucesso.inativacao";
		addInterfaceMessage(objetoModelo.getClass().getSimpleName() + " " + mensagem , null, FacesMessage.SEVERITY_INFO);
	}
	
	public T getObjetoModelo() {
		return objetoModelo;
	}

	public void setObjetoModelo(T objetoModelo) {
		this.objetoModelo = objetoModelo;
	}

	public CrudAcaoEnum getAcao() {
		return acao;
	}

	public void setAcao(CrudAcaoEnum acao) {
		this.acao = acao;
	}

	public enum CrudAcaoEnum implements EnumMapped {
		CADASTRAR("view.acao.cadastrar"), 
		ALTERAR("view.acao.alterar"), 
		VISUALIZAR("view.acao.visualizar"),
		PESQUISAR("view.acao.pesquisar");
		
		private String descricao;
		
		CrudAcaoEnum(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
	}
	
	public boolean isCadastro() {
		return acao == CrudAcaoEnum.CADASTRAR;
	}
	
	public boolean isAlteracao() {
		return acao == CrudAcaoEnum.ALTERAR;
	}
	
	public boolean isVisualizacao() {
		return acao == CrudAcaoEnum.VISUALIZAR;
	}
	
	public boolean isPesquisa() {
		return acao == CrudAcaoEnum.PESQUISAR;
	}
	
}
