package br.com.empresa.sgt.controller;

import java.util.List;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.exception.BusinessException;

public interface CrudController<T> {
	
	public String cadastrarGet();
	
	public String cadastrar() throws BusinessException;
	
	public String visualizar () throws BusinessException;
	
	public String alterarGet();
	
	public String alterar() throws BusinessException;
	
	public String remover() throws BusinessException;
	
	public String ativar() throws BusinessException;
	
	public String inativar() throws BusinessException;
	
	public String pesquisarGet();
	
	public void pesquisar() throws BusinessException;
	
	public CrudBusiness getBusinessClass();
	
	public T getModelo();
	
	public void setModelo(T obj);
	
	public void setResultadoPesquisa(List<T> resultado);

}
