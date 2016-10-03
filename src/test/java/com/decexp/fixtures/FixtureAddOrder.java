package com.decexp.fixtures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.decexp.beans.AddOrderDetails;
import com.decexp.utils.AutomationException;
import com.decexp.utils.ConfigurationDataProvider;
import com.decexp.utils.ExcelDataProvider;

public class FixtureAddOrder {
	
	@DataProvider(name="AddOrder")
	public static Iterator<Object []> addOrder() throws AutomationException{
		
		List<AddOrderDetails> newOrder = null;
		Object[][] array =null;
		try{
			newOrder = ExcelDataProvider.getExcelDataUsingPOI("createOrder", AddOrderDetails.class, 
							FixtureAddOrder.getDataProviderFileName());
			array = new Object[newOrder.size()][1];
			for(int i=0; i<newOrder.size(); i++){
				array[i][0] = newOrder.get(i);
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
			throw new AutomationException("Data Provider file is not found");
		}
	}

}
