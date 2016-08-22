package com.decexp.fixtures;

import com.decexp.beans.MSCRMLogin;
import com.decexp.utils.AutomationException;
import com.decexp.utils.ConfigurationDataProvider;
import com.decexp.utils.RunConfigurationReader;
import com.decexp.utils.ExcelDataProvider;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class DataProviderLogin {
	private static Properties prop = new Properties();
	
	@DataProvider(name="CRMloginPageDetails")
	public static Iterator<Object []> CRMLogin() throws AutomationException{   
	List<MSCRMLogin> loginDetails = null;
	Object[][] array = null;
	
	try{
		loginDetails = ExcelDataProvider.getExcelDataUsingPOI("valid", MSCRMLogin.class, 
				DataProviderLogin.getDataProviderFileName());
		array = new Object[loginDetails.size()][1];
		for(int i=0; i<loginDetails.size(); i++){
			array[i][0] = loginDetails.get(i);
		}		
	
	}catch(Exception e){
		throw new AutomationException("Data Provider is null");
	}
		
	return Arrays.asList(array).iterator();
	}

	public static String getDataProviderFileName() throws AutomationException{
		try{
			String fileName = ConfigurationDataProvider.getReader().getExcelFile();
			
			return fileName;
			}catch(Exception e){
				throw new AutomationException(e.getMessage());
		}
	}
}
