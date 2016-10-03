package com.decexp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;

public class RunConfigurationReader {
	
	Properties prop;
	
	public RunConfigurationReader(){
		
		try {
			//System.out.println("Current Directory is--->"+System.getProperty("user.dir"));
			File src = new File(System.getProperty("user.dir")
						+"//src//test//java//com//decexp//config//Configuration.properties");
			
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		}
	
	public List<String> getBrowser(){
		String browserNames = prop.getProperty("browsers");
		String[] browserArray = browserNames.split(",");
		List<String> browserList = new ArrayList<String>();
		for(int i=0; i<browserArray.length; i++){
			browserList.add(browserArray[i]);
		}
		return browserList;
	}
	
	public String getTestURL(){
		return prop.getProperty("testURL");	
		
	}
	
	public String getExcelFile(){
		return prop.getProperty("dataProviderName");	
		
	}
	
	public String getIEDriver(){
		return prop.getProperty("IEDriverPath");
	}
	
	public String getChromeDriver(){
		return prop.getProperty("ChromeDriverPath");
	}
}