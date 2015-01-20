package br.com.empresa.sgt.controller;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.business.remote.GrupoPermissaoBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractCrudAdapterController;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.GrupoPermissao.GrupoPermissaoStatusEnum;
import br.com.empresa.sgt.model.acesso.Permissao.PermissaoValorEnum;

@Named
@ViewScoped
public class GrupoPermissaoController extends AbstractCrudAdapterController<GrupoPermissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	@EJB private GrupoPermissaoBusinessRemote grupoPermissaoBusiness;
	
	private final List<GrupoPermissaoStatusEnum> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatusEnum.values());
	private final List<PermissaoValorEnum> permissaoValores = Arrays.asList(PermissaoValorEnum.values());
	
	public GrupoPermissaoController() {
		cadastrarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoIncluirAlterar.html";
		cadastrarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoIncluirAlterar.html";
		pesquisarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoPesquisar.html";
		visualizarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoVisualizar.html";
	}
	
	@Override
	public CrudBusiness<GrupoPermissao> getBusinessClass() {
		return this.grupoPermissaoBusiness;
	}

	public List<GrupoPermissaoStatusEnum> getGrupoPermissaoStatus() {
		return grupoPermissaoStatus;
	}

	public List<PermissaoValorEnum> getPermissaoValores() {
		return permissaoValores;
	}

}

