package br.com.empresa.sgt.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;

import br.com.empresa.sgt.controller.arq.AbstractMB;

@Named
@Singleton
public class AutoCompleteUtilMB extends AbstractMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366030532084589240L;
	
	private List<Locale> locales = new ArrayList<>();
	
	public AutoCompleteUtilMB() {
		this.carregarPaises();
	}
	
	public synchronized List<String> completePais(String query) {
		Locale localidadeSistema = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        List<String> paises = new ArrayList<String>();
        
        for (Locale locale : this.locales) {
        	String pais = locale.getDisplayCountry(localidadeSistema);
        	if(pais.matches("^(?i)(" + query + ").*")) {
        		paises.add(pais);
        	}
        }
         
        return paises;
    }
	
	private void carregarPaises() {
		List<String> codigoPaises = Arrays.asList(Locale.getISOCountries());
		for (String codigoPais : codigoPaises) {
		    Locale locale = new Locale("", codigoPais);
		    this.locales.add(locale);
		 }
	}
}
