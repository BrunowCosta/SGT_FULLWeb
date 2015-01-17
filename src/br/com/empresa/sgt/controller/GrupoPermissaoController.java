package br.com.empresa.sgt.controller;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.empresa.sgt.business.remote.CrudBusiness;
import br.com.empresa.sgt.business.remote.GrupoPermissaoBusinessRemote;
import br.com.empresa.sgt.controller.arq.AbstractController;
import br.com.empresa.sgt.controller.arq.AbstractCrudController;
import br.com.empresa.sgt.enumeration.GrupoPermissaoStatus;
import br.com.empresa.sgt.enumeration.PermissaoValor;
import br.com.empresa.sgt.exception.BusinessException;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.arq.Modelo;

@Named
@ViewScoped
public class GrupoPermissaoController extends AbstractCrudController<GrupoPermissao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8481910644134112660L;

	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	@EJB private GrupoPermissaoBusinessRemote grupoPermissaoBusiness;
	
	// Campos da tela
	// TODO ver como produzir isso de vera
	@Inject private GrupoPermissao grupoPermissao;
	
	private List<GrupoPermissao> resultadoPesquisa;
	
	private GrupoPermissao grupoPermStatusEnum;
	
	private final List<GrupoPermissaoStatus> grupoPermissaoStatus = Arrays.asList(GrupoPermissaoStatus.values());
	private final List<PermissaoValor> permissaoValores = Arrays.asList(PermissaoValor.values());
	
	public GrupoPermissaoController() {}
	
//	public String cadastrar() throws BusinessException {
//		grupoPermissaoBusiness.cadastrar(this.getGrupoPermissao(), this.getUsuarioLogado());
//		return null;
//	}
//	
//	public String pesquisar() throws BusinessException {
//		this.resultadoPesquisa = grupoPermissaoBusiness.pesquisar(this.getGrupoPermissao(), this.getUsuarioLogado());
//		return null;
//	}
	
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

	public void setResultadoPesquisa(List<GrupoPermissao> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	public GrupoPermissao getGrupoPermStatusEnum() {
		return grupoPermStatusEnum;
	}

	public void setGrupoPermStatusEnum(GrupoPermissao grupoPermStatusEnum) {
		this.grupoPermStatusEnum = grupoPermStatusEnum;
	}

	@Override
	public CrudBusiness getBusinessClass() {
		return this.getBusinessClass();
	}

	@Override
	public Modelo getModelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modelo setModelo(Modelo obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

