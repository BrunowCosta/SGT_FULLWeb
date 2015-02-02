package br.com.empresa.sgt.controller.arq;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.empresa.sgt.enumeration.MappedEnum;
import br.com.empresa.sgt.enumeration.MensagemEnum;
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
		super.addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(), 
								  objetoModelo.getClass().getSimpleName(), CrudAcaoEnum.CADASTRAR.getSucessoOperacao());
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
		addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(),
							objetoModelo.getClass().getSimpleName(), CrudAcaoEnum.ALTERAR.getSucessoOperacao());
		return visualizar(objetoModelo);
	}
	
	@Override
	public void remover() throws BusinessException {
		this.getBusinessClass().remover(objetoModelo.getId(), this.getUsuarioLogado());
		addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(), 
							objetoModelo.getClass().getSimpleName(), CrudAcaoEnum.REMOVER.getSucessoOperacao());
	}
	
	@Override
	public void ativarInativar(boolean ativo) throws BusinessException {
		this.getBusinessClass().ativarInativar(objetoModelo.getId(), ativo, super.getUsuarioLogado());
		String mensagem = ativo? CrudAcaoEnum.ATIVAR.getSucessoOperacao():CrudAcaoEnum.INATIVAR.getSucessoOperacao();
		addInterfaceMessage(FacesMessage.SEVERITY_INFO, MensagemEnum.SUCESSO_OPERACAO.getDescricao(), 
							objetoModelo.getClass().getSimpleName(), mensagem);
	}
	
	public String excultarAcao() throws BusinessException{
		if(acao == CrudAcaoEnum.CADASTRAR) {
			return cadastrar();
		} else if (acao == CrudAcaoEnum.ALTERAR) {
			return alterar();
		}
		return null;
	}
	
	public T getObjetoModelo() {
		return objetoModelo;
	}

	public void setObjetoModelo(T objetoModelo) {
		this.objetoModelo = objetoModelo;
	}

	public CrudAcaoEnum getAcao() {
		if(acao == null && super.getResquest().getParameter("formIncluirAlterar:acao") != null) {
			acao = CrudAcaoEnum.valueOf(super.getResquest().getParameter("formIncluirAlterar:acao"));
		}
		return acao;
	}

	public void setAcao(CrudAcaoEnum acao) {
		this.acao = acao;
	}

	public enum CrudAcaoEnum implements MappedEnum {
		CADASTRAR("view.acao.cadastrar", "msg.sucesso.cadastrado"), 
		ALTERAR("view.acao.alterar", "msg.sucesso.alterado"), 
		VISUALIZAR("view.acao.visualizar", "msg.sucesso.visualizado"),
		PESQUISAR("view.acao.pesquisar", "msg.sucesso.encontrado"),
		ATIVAR("view.acao.ativar", "msg.sucesso.ativado"),
		INATIVAR("view.acao.inativar", "msg.sucesso.inativado"),
		REMOVER("view.acao.remover", "msg.sucesso.removido");
		
		private String descricao;
		private String sucessoOperacao;
		
		CrudAcaoEnum(String descricao, String sucessoOperacao) {
			this.descricao = descricao;
			this.sucessoOperacao = sucessoOperacao;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getSucessoOperacao() {
			return sucessoOperacao;
		}

		public void setSucessoOperacao(String sucessoOperacao) {
			this.sucessoOperacao = sucessoOperacao;
		}
		
	}
	
	public boolean isCadastro() {
		return this.getAcao() == CrudAcaoEnum.CADASTRAR;
	}
	
	public boolean isAlteracao() {
		return this.getAcao() == CrudAcaoEnum.ALTERAR;
	}
	
	public boolean isVisualizacao() {
		return this.getAcao() == CrudAcaoEnum.VISUALIZAR;
	}
	
	public boolean isPesquisa() {
		return this.getAcao() == CrudAcaoEnum.PESQUISAR;
	}
	
}
