package br.com.empresa.sgt.controller;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

public interface CrudController {
	
	public String cadastrarGet();
	
	public String cadastrar() throws BusinessException;
	
	public String visualizar ();
	
	public String alterarGet();
	
	public String alterar() throws BusinessException;
	
	public String remover() throws BusinessException;
	
	public String ativar() throws BusinessException;
	
	public String inativar() throws BusinessException;
	
	public CrudBusiness getBusinessClass();
	
	public Modelo getModelo();
	
	public Modelo setModelo(Modelo obj);

}
