package com.decexp.fixtures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.decexp.beans.AddContactDetails;
import com.decexp.utils.AutomationException;
import com.decexp.utils.ConfigurationDataProvider;
import com.decexp.utils.ExcelDataProvider;

public class AddContactFixture {
	
	@DataProvider(name="AddContact")
	public static Iterator<Object []> addContact() throws AutomationException{
		List<AddContactDetails> contactDetails = null;
		Object [][] array = null;
		try{
		contactDetails = ExcelDataProvider.getExcelDataUsingPOI("createContact", AddContactDetails.class,
								AddContactFixture.getDataProviderFileName());
		array = new Object[contactDetails.size()][1];
		for(int i=0; i<array.length; i++){
			array[i][0] = contactDetails.get(i);
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
