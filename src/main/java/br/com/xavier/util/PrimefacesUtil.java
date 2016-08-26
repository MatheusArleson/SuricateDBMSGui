package br.com.xavier.util;

import org.primefaces.context.RequestContext;

public class PrimefacesUtil {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	private PrimefacesUtil() {}
	
	//XXX METHODS
	
	public static void showByWidgetVar(String widgetVar){
		executeJavascript("PF('" + widgetVar + "').show();");
	}
	
	public static void hideByWidgetVar(String widgetVar){
		executeJavascript("PF('" + widgetVar + "').hide();");
	}
	
	public static void executeJavascript(String script){
		if(StringUtils.isNullOrEmpty(script)){
			return;
		}
		
		RequestContext.getCurrentInstance().execute(script);
	}
	
}
