package br.com.empresa.sgt.controller.grupoPermissao;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.business.remote.GrupoPermissaoBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractPesquisarMB;
import br.com.empresa.sgt.controller.arq.CrudPesquisarMB;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.GrupoPermissao.GrupoPermissaoStatusEnum;

@Named
@ViewScoped
public class GrupoPermissaoPesquisarMB extends AbstractPesquisarMB<GrupoPermissao> implements CrudPesquisarMB<GrupoPermissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	@EJB private GrupoPermissaoBusinessRemote grupoPermissaoBusiness;
	
	private final List<GrupoPermissaoStatusEnum> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatusEnum.values());
	
	public GrupoPermissaoPesquisarMB() {
		pesquisarUrl = "/view/controleAcesso/grupoPermissoes/grupoPermissaoPesquisar";
	}

	public List<GrupoPermissaoStatusEnum> getGrupoPermissaoStatus() {
		return grupoPermissaoStatus;
	}

	@Override
	public CrudBusiness<GrupoPermissao> getBusinessClass() {
		return grupoPermissaoBusiness;
	}
	
}

