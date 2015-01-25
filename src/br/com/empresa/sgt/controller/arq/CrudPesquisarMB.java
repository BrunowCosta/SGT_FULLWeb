package br.com.empresa.sgt.controller.arq;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.arq.Modelo;

public interface CrudPesquisarMB<T extends Modelo> {
	
	public String goPesquisar();
	
	public void pesquisar() throws BusinessException;
	
	public CrudBusiness<T> getBusinessClass();

}
