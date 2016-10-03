package com.decexp.utils;

import com.decexp.utils.AutomationException;

public class ConfigurationDataProvider {
	public static RunConfigurationReader configReader = null;
	
	public static RunConfigurationReader getReader() throws AutomationException{
		if(configReader==null){
			try{
				configReader = new RunConfigurationReader();
			}catch(Exception e){
				throw new AutomationException("Properties file is missing");
			}	
		}
		return configReader;
	}
	
	private ConfigurationDataProvider(){
		System.out.println("Object instance is not allowed");
	}

}
