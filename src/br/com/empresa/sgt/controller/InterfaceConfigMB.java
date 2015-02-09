package br.com.empresa.sgt.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.empresa.sgt.controller.arq.AbstractMB;

@Named
@ApplicationScoped
public class InterfaceConfigMB extends AbstractMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912557368586720814L;
	
	private List<TemaEnum> temas = Arrays.asList(TemaEnum.values());
	private TemaEnum tema;
	private String nomeAplicacao = "";
	private String corBackground = "";
	private String corConteudo = "";
	private Integer tamanhoFonte = 0;
	private Integer arredondamentoComponentes = 0;
	private Boolean habDesabSombra = true;
	
	@PostConstruct
	public void init() {
		tema = TemaEnum.CLEAN;
	}
	
	public InterfaceConfigMB() {}
	
	public String salvar() {
		return null;
	}
	
	public List<TemaEnum> getTemas() {
		return temas;
	}

	public void setTemas(List<TemaEnum> temas) {
		this.temas = temas;
	}

	public TemaEnum getTema() {
		return tema;
	}

	public void setTema(TemaEnum tema) {
		this.tema = tema;
	}
	
	public String getDisplayTema() {
		return tema.toString();
	}
	
	public void setDisplayTema(String displayTema) {
		
		//Evita ter que criar uma lista ao inves de enum para os tema da aplicação.
		//Não é possivel sobreescrever o converter default para enum do jsf.
		//Trata-se de uma solução alternativa para evitar o uso de lista.
		if(displayTema != null && displayTema != "") {
			TemaEnum[] temas = TemaEnum.values();
			for(TemaEnum tema : temas) {
				if(tema.getNome().equals(displayTema)) {
					this.setTema(tema);
				}
			}
		}
		
	}
	
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

	public enum TemaEnum {
		CLEAN("clean"),
		BOOTSTRAP("bootstrap"),
		BLITZER("blitzer"),
		SOUTHSTRET("south-street");
		
		private String nome;
		
		TemaEnum(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
	}

}