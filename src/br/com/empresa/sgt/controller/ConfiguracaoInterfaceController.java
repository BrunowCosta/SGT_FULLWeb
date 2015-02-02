package br.com.empresa.sgt.controller;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import br.com.empresa.sgt.controller.arq.AbstractMB;

@Named
@ApplicationScoped
public class ConfiguracaoInterfaceController extends AbstractMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912557368586720814L;
	
	public static final String[] POSSIVEIS_TEMAS = { 
						"afterdark", "afternoon", "afterwork", "aristo",
						"black-tie", "blitzer", "bluesky", "casablanca",
						"cruze", "cupertino", "dark-hive", "dot-luv",
						"eggplant", "excite-bike", "flick", "glass-x",
						"home", "hot-sneaks", "humanity", "le-frog",
						"midnight", "mint-choc", "overcast", "pepper-grinder",
						"redmond", "rocket", "sam", "smoothness",
						"south-street", "start", "sunny", "swanky-purse",
						"trontastic", "twitter bootstrap", "ui-darkness",
						"ui-lightness", "vader","clean" };
	
	public String[] getTemas() {
		return(POSSIVEIS_TEMAS);
	}
	
	public String nomeAplicacao = "";
	public String corBackground = "";
	public String corConteudo = "";
	public Integer tamanhoFonte = 0;
	public Integer arredondamentoComponentes = 0;
	public Boolean habDesabSombra;
	
	public ConfiguracaoInterfaceController() {}

	public String getNomeAplicacao() {
		return nomeAplicacao;
	}

	public void setNomeAplicacao(String nomeAplicacao) {
		this.nomeAplicacao = nomeAplicacao;
	}

	public String getCorBackground() {
		return corBackground;
	}

	public void setCorBackground(String corBackground) {
		this.corBackground = corBackground;
	}

	public String getCorConteudo() {
		return corConteudo;
	}

	public void setCorConteudo(String corConteudo) {
		this.corConteudo = corConteudo;
	}

	public Integer getTamanhoFonte() {
		return tamanhoFonte;
	}

	public void setTamanhoFonte(Integer tamanhoFonte) {
		this.tamanhoFonte = tamanhoFonte;
	}

	public Integer getArredondamentoComponentes() {
		return arredondamentoComponentes;
	}

	public void setArredondamentoComponentes(Integer arredondamentoComponentes) {
		this.arredondamentoComponentes = arredondamentoComponentes;
	}

	public Boolean getHabDesabSombra() {
		return habDesabSombra;
	}

	public void setHabDesabSombra(Boolean habDesabSombra) {
		this.habDesabSombra = habDesabSombra;
	}

}
