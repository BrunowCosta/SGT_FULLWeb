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
import br.com.empresa.sgt.enumeration.GrupoPermissaoStatus;
import br.com.empresa.sgt.enumeration.PermissaoValor;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.arq.Modelo;

@Named
@ViewScoped
public class GrupoPermissaoController extends AbstractCrudAdapterController<GrupoPermissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	@EJB private GrupoPermissaoBusinessRemote grupoPermissaoBusiness;
	
	// TODO ver como produzir isso de vera
	@Inject private GrupoPermissao grupoPermissao;
	
	private List<GrupoPermissao> resultadoPesquisa;
	
	private GrupoPermissao grupoPermStatusEnum;
	
	private final List<GrupoPermissaoStatus> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatus.values());
	private final List<PermissaoValor> permissaoValores = Arrays.asList(PermissaoValor.values());
	
	public GrupoPermissaoController() {
		cadastrarUrl = "/resources/view/controleAcesso/grupoPermissoes/incluirGrupoPermissoes.html";
		pesquisarUrl = "/resources/view/controleAcesso/grupoPermissoes/pesquisarGrupoPermissoes.html";
	}
	
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

	public List<GrupoPermissaoStatus> getGrupoPermissaoStatus() {
		return grupoPermissaoStatus;
	}

	public List<PermissaoValor> getPermissaoValores() {
		return permissaoValores;
	}

	public List<GrupoPermissao> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	@Override
	public void setResultadoPesquisa(List<GrupoPermissao> resultado) {
		this.resultadoPesquisa = resultado;
		
	}

	public GrupoPermissao getGrupoPermStatusEnum() {
		return grupoPermStatusEnum;
	}

	public void setGrupoPermStatusEnum(GrupoPermissao grupoPermStatusEnum) {
		this.grupoPermStatusEnum = grupoPermStatusEnum;
	}

}

