package br.com.empresa.sgt.controller.arq;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

public interface CrudMB<T extends Modelo> {
	
	public String goCadastrar();
	
	public String cadastrar() throws BusinessException;
	
	public String visualizar(T objetoModelo) throws BusinessException;
	
	public String goAlterar(T objetoModelo);
	
	public String alterar() throws BusinessException;
	
	public void remover() throws BusinessException;
	
	public void ativarInativar(boolean ativo) throws BusinessException;
	
	public CrudBusiness<T> getBusinessClass();

}
