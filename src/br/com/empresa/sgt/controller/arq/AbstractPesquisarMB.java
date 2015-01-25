package br.com.empresa.sgt.controller.arq;

import java.util.List;

import javax.inject.Inject;

import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

@SuppressWarnings("serial")
public abstract class AbstractPesquisarMB<T extends Modelo> extends AbstractMB implements CrudPesquisarMB<T>{
	
	protected static String pesquisarUrl;
	
	@Inject protected T objetoPesquisa;
	
	protected List<T> resultadoPesquisa;
	
	public String goPesquisar() {
		return pesquisarUrl;
	}
	
	public void pesquisar() throws BusinessException {
		resultadoPesquisa = this.getBusinessClass().pesquisar(objetoPesquisa);
	}

	public T getObjetoPesquisa() {
		return objetoPesquisa;
	}

	public void setObjetoPesquisa(T objetoPesquisa) {
		this.objetoPesquisa = objetoPesquisa;
	}

	public List<T> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(List<T> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
