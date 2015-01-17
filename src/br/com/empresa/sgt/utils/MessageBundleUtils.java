package br.com.empresa.sgt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

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
	
	public String getMensagem(String key) {
		return this.getMensagem(key, null); 
	}
	
	public String getMensagem(String key, Locale locale) {
		String mensagemTraduzida = "";
		try {
			mensagemTraduzida = this.getResourceBundle(locale).getString(key);
		} catch(Exception e) {
			mensagemTraduzida = key;
		}
		return mensagemTraduzida; 
	}
	
	public String traduzirMensagemMultipla(String mensagemMultipla) {
		List<String> mensagens = new ArrayList<String>();
		mensagens.toArray(mensagemMultipla.split(" "));
		
		if(mensagens.isEmpty()) {
			return this.getMensagem(mensagemMultipla);
		}
		
		String mensagemTraduzida = "";
		for (String mensagem: mensagens) {
			if(mensagemTraduzida != "") {
				mensagemTraduzida += " ";
			}
			
			try {
				mensagemTraduzida = this.getMensagem(mensagem);
			} catch(Exception e) {
				mensagemTraduzida = mensagem;
			}
		}
		
		return mensagemTraduzida;
	}
	
}