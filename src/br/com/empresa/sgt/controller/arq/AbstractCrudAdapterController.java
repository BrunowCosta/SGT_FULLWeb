package br.com.empresa.sgt.controller.arq;

import br.com.empresa.sgt.controller.CrudController;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;


@SuppressWarnings("serial")
public abstract class AbstractCrudAdapterController<T> extends AbstractController implements CrudController<T> {
	
	protected static String cadastrarUrl;
	protected static String visualizarUrl;
	protected static String alterarUrl;
	protected static String pesquisarUrl;
	
	@Override
	public String cadastrarGet() {
		return cadastrarUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String cadastrar() throws BusinessException {
		this.getBusinessClass().cadastrar(this.getModelo(), this.getUsuarioLogado());
		return this.visualizar();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String visualizar () throws BusinessException {
		this.setModelo(((T) this.getBusinessClass().visualizar(((Modelo) this.getModelo()).getId())));
		return visualizarUrl;
	}
	
	@Override
	public String alterarGet() {
		return alterarUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String alterar() throws BusinessException {
		this.getBusinessClass().alterar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String remover() throws BusinessException {
		this.getBusinessClass().remover(this.getModelo(), this.getUsuarioLogado());
		return pesquisarUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String ativar() throws BusinessException {
		this.getBusinessClass().ativar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String inativar() throws BusinessException {
		this.getBusinessClass().inativar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}
	
	@Override
	public String pesquisarGet() {
		return pesquisarUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() throws BusinessException {
		this.setResultadoPesquisa(this.getBusinessClass().pesquisar(this.getModelo(), this.getUsuarioLogado()));
	}

}
