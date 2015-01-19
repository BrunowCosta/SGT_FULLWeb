package br.com.empresa.sgt.controller;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
	
	// TODO ver como produzir isso de vera
	@Inject private GrupoPermissao grupoPermissao;
	
	private List<GrupoPermissao> resultadoPesquisa;
	
	private final List<GrupoPermissaoStatusEnum> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatusEnum.values());
	private final List<PermissaoValorEnum> permissaoValores = Arrays.asList(PermissaoValorEnum.values());
	
	public GrupoPermissaoController() {
		cadastrarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoIncluir.html";
		pesquisarUrl = "/resources/view/controleAcesso/grupoPermissoes/grupoPermissaoPesquisar.html";
	}
	
	// TODO Tipar isso aqui
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CrudBusiness getBusinessClass() {
		return this.grupoPermissaoBusiness;
	}

	@Override
	public GrupoPermissao getModelo() {
		return this.grupoPermissao;
	}

	@Override
	public void setModelo(GrupoPermissao obj) {
		this.grupoPermissao = obj;
	}
	
	public GrupoPermissao getGrupoPermissao() {
		return grupoPermissao;
	}

	public void setGrupoPermissao(GrupoPermissao grupoPermissao) {
		this.grupoPermissao = grupoPermissao;
	}

	public List<GrupoPermissaoStatusEnum> getGrupoPermissaoStatus() {
		return grupoPermissaoStatus;
	}

	public List<PermissaoValorEnum> getPermissaoValores() {
		return permissaoValores;
	}

	public List<GrupoPermissao> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	@Override
	public void setResultadoPesquisa(List<GrupoPermissao> resultado) {
		this.resultadoPesquisa = resultado;
	}

}

