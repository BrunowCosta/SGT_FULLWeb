package br.com.empresa.sgt.controller;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.business.remote.GrupoPermissaoBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractCrudMB;
import br.com.empresa.sgt.controller.arq.CrudMB;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.GrupoPermissao.GrupoPermissaoStatusEnum;
import br.com.empresa.sgt.model.acesso.Permissao.PermissaoValorEnum;

@Named
@RequestScoped
public class GrupoPermissaoMB extends AbstractCrudMB<GrupoPermissao> implements CrudMB<GrupoPermissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	@EJB private GrupoPermissaoBusinessRemote grupoPermissaoBusiness;
	
	private final List<GrupoPermissaoStatusEnum> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatusEnum.values());
	private final List<PermissaoValorEnum> permissaoValores = Arrays.asList(PermissaoValorEnum.values());
	
	public GrupoPermissaoMB() {
		super();
		cadastrarUrl = "/view/controleAcesso/grupoPermissoes/grupoPermissaoIncluirAlterar";
		alterarUrl = "/view/controleAcesso/grupoPermissoes/grupoPermissaoIncluirAlterar";
		visualizarUrl = "/view/controleAcesso/grupoPermissoes/grupoPermissaoVisualizar";
	}

	public List<GrupoPermissaoStatusEnum> getGrupoPermissaoStatus() {
		return grupoPermissaoStatus;
	}

	public List<PermissaoValorEnum> getPermissaoValores() {
		return permissaoValores;
	}

	@Override
	public CrudBusiness<GrupoPermissao> getBusinessClass() {
		return grupoPermissaoBusiness;
	}
	
}

