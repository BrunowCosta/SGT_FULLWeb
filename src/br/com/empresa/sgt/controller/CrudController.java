package br.com.empresa.sgt.controller;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

public interface CrudController<T extends Modelo> {
	
	public String cadastrarGet() throws BusinessException;
	
	public String cadastrar() throws BusinessException;
	
	public String visualizar(Integer id) throws BusinessException;
	
	public String alterarGet(Integer id) throws BusinessException;
	
	public String alterar() throws BusinessException;
	
	public String remover(Integer id) throws BusinessException;
	
	public String ativarInativar(Integer id, boolean ativo) throws BusinessException;
	
	public String pesquisarGet() throws BusinessException;
	
	public void pesquisar() throws BusinessException;
	
	public CrudBusiness<T> getBusinessClass();

}
