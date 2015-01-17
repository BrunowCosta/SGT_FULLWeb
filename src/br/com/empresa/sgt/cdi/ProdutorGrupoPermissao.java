package br.com.empresa.sgt.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;

import br.com.empresa.sgt.enumeration.TipoPermissao;
import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.Permissao;

public class ProdutorGrupoPermissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4213996421578257509L;

	@Produces
	public GrupoPermissao produzirGrupoPermissao() {
		GrupoPermissao grupo = new GrupoPermissao();
		grupo.setId(0);
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for(TipoPermissao tipo : TipoPermissao.values()) {
			Permissao permissao = new Permissao();
			permissao.setTipo(tipo.getCodigo());
			permissao.setTipoPermissao(tipo);
			permissoes.add(permissao);
		}
		grupo.setPermissoes(permissoes);
		return grupo;
	}

}
