package br.com.empresa.sgt.cdi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;

import br.com.empresa.sgt.model.acesso.GrupoPermissao;
import br.com.empresa.sgt.model.acesso.Permissao;
import br.com.empresa.sgt.model.acesso.Permissao.PermissaoTipoEnum;

public class ProdutorGrupoPermissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4213996421578257509L;

	@Produces
	public GrupoPermissao produzirGrupoPermissao() {
		GrupoPermissao grupo = new GrupoPermissao();
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for(PermissaoTipoEnum tipo : PermissaoTipoEnum.values()) {
			Permissao permissao = new Permissao();
			permissao.setTipo(tipo);
			permissoes.add(permissao);
		}
		grupo.setPermissoes(permissoes);
		return grupo;
	}

}
