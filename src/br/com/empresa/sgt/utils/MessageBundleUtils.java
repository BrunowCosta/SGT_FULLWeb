package br.com.empresa.sgt.utils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import br.com.empresa.sgt.exception.BusinessException;

public class MessageBundleUtils implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -614274541669541010L;

	private final String MESSAGEBUDDLE_PATH = "br.com.empresa.sgt.mensagens.mensagens";
	
	private static MessageBundleUtils instance;
	
	public static MessageBundleUtils getInstance(){
		if (instance == null)
			instance = new MessageBundleUtils();
		return instance;
	}
	
	private ResourceBundle getResourceBundle(Locale locale) {
		if (locale == null) {
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		}
		return  ResourceBundle.getBundle(MESSAGEBUDDLE_PATH, locale);
	}
	
	public String getMensagem(String key, Object...parametros) {
		return this.getMensagem(null, key, parametros);
	}
	
	public String getMensagem(BusinessException e) {
		return this.getMensagem(e.getErro().getDescricao(), e.getParametros());
	}
	
	public String getMensagem(Locale locale, String key, Object...parametros) {
		String mensagemTraduzida = "";
		try {
			mensagemTraduzida = this.getResourceBundle(locale).getString(key);
			
			if(parametros != null && parametros.length > 0) {
				mensagemTraduzida = MessageFormat.format(mensagemTraduzida, this.traduzirParametros(parametros));
			}
		} catch(Exception e) {
			mensagemTraduzida = "???" + key + "???";
		}
		return mensagemTraduzida; 
	}
	
	private Object[] traduzirParametros(Object...parametros) {
		
		List<Object> parametrosTraduzidos = new ArrayList<>(parametros.length);
		
		if(parametros != null) {
			for(Object parametro: parametros) {
				if(parametro instanceof String) {
					parametrosTraduzidos.add(this.getMensagem((String) parametro));
				} else {
					parametrosTraduzidos.add(parametro);
				}
			}
		}
		
		return parametrosTraduzidos.toArray();
	}
	
}