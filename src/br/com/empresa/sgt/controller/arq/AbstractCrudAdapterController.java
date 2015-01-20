package br.com.empresa.sgt.controller.arq;

import java.util.List;

import javax.inject.Inject;

import br.com.empresa.sgt.controller.CrudController;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

@SuppressWarnings("serial")
public abstract class AbstractCrudAdapterController<T extends Modelo> extends AbstractController implements CrudController<T> {
	
	protected static String cadastrarUrl;
	protected static String visualizarUrl;
	protected static String alterarUrl;
	protected static String pesquisarUrl;
	
	@Inject protected T objetoModelo;
	
	protected CrudAcaoEnum acao;
	
	protected List<T> resultadoPesquisa;
	
	@Override
	public String cadastrarGet() {
		acao = CrudAcaoEnum.CADASTRAR;
		return cadastrarUrl;
	}
	
	@Override
	public String cadastrar() throws BusinessException {
		this.getBusinessClass().cadastrar(objetoModelo, super.getUsuarioLogado());
		return this.visualizar(objetoModelo.getId());
	}

	@Override
	public String visualizar(Integer id) throws BusinessException {
		acao = CrudAcaoEnum.VISUALIZAR;
		this.setObjetoModelo(this.getBusinessClass().encontrar(id));
		return visualizarUrl;
	}
	
	@Override
	public String alterarGet(Integer id) throws BusinessException {
		acao = CrudAcaoEnum.ALTERAR;
		this.setObjetoModelo(this.getBusinessClass().encontrar(id));
		return alterarUrl;
	}
	
	@Override
	public String alterar() throws BusinessException {
		this.getBusinessClass().alterar(objetoModelo, super.getUsuarioLogado());
		return visualizar(objetoModelo.getId());
	}
	
	@Override
	public String remover(Integer id) throws BusinessException {
		this.getBusinessClass().remover(id, this.getUsuarioLogado());
		return pesquisarUrl;
	}
	
	@Override
	public String ativarInativar(Integer id, boolean ativo) throws BusinessException {
		this.getBusinessClass().ativarInativar(id, ativo, super.getUsuarioLogado());
		return visualizar(objetoModelo.getId());
	}
	
	@Override
	public String pesquisarGet() {
		return pesquisarUrl;
	}
	
	@Override
	public void pesquisar() throws BusinessException {
		this.setResultadoPesquisa(this.getBusinessClass().pesquisar(objetoModelo));
	}

	public T getObjetoModelo() {
		return objetoModelo;
	}

	public void setObjetoModelo(T objetoModelo) {
		this.objetoModelo = objetoModelo;
	}

	public List<T> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(List<T> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}
	
	public enum CrudAcaoEnum {
		CADASTRAR, ALTERAR, VISUALIZAR, PESQUISAR;
		
		CrudAcaoEnum() {}
	}

}
