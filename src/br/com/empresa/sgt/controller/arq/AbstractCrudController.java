package br.com.empresa.sgt.controller.arq;

import br.com.empresa.sgt.controller.CrudController;
import br.com.empresa.sgt.exception.BusinessException;


@SuppressWarnings("serial")
public abstract class AbstractCrudController<Modelo> extends AbstractController implements CrudController {
	
	protected static String cadastrarUrl;
	protected static String visualizarUrl;
	protected static String alterarUrl;
	protected static String pesquisarUrl;
	
	public String cadastrarGet() {
		return cadastrarUrl;
	}
	
	public String cadastrar() throws BusinessException {
		this.getBusinessClass().cadastrar(this.getModelo(), this.getUsuarioLogado());
		return this.visualizar();
	}
	
	public String visualizar () {
//		this.setModelo(this.getBusinessClass().visualizar(this.getModelo().getId()));
		return visualizarUrl;
	}
	
	public String alterarGet() {
		return alterarUrl;
	}
	
	public String alterar() throws BusinessException {
		this.getBusinessClass().alterar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}
	
	public String remover() throws BusinessException {
		this.getBusinessClass().remover(this.getModelo(), this.getUsuarioLogado());
		return pesquisarUrl;
	}
	
	public String ativar() throws BusinessException {
		this.getBusinessClass().ativar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}
	
	public String inativar() throws BusinessException {
		this.getBusinessClass().inativar(this.getModelo(), this.getUsuarioLogado());
		return visualizar();
	}

}
