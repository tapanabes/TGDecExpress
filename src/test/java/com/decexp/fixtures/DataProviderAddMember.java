package com.decexp.fixtures;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.decexp.utils.AutomationException;
import com.decexp.utils.ConfigurationDataProvider;
import com.decexp.utils.ExcelDataProvider;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import com.decexp.beans.AddMemberDetails;

public class DataProviderAddMember {
	private static Properties prop = new Properties();
	
	@DataProvider(name="AddProspect")
	public static Iterator<Object []> addProspect() throws AutomationException{
		List<AddMemberDetails> addMemberDetails = null;
		Object[][] array = null;
		try{
			addMemberDetails = ExcelDataProvider.getExcelDataUsingPOI("createProspect", AddMemberDetails.class, 
					DataProviderAddMember.getDataProviderFileName());
			array = new Object[addMemberDetails.size()][1];
			for(int i=0; i<addMemberDetails.size(); i++){
				array[i][0] = addMemberDetails.get(i);
			}		
		
		}catch(Exception e){
			throw new AutomationException("Data Provider is null");
		}
			return Arrays.asList(array).iterator();
	}

		
		@DataProvider(name="AddMember")
		public static Iterator<Object []> addMember() throws AutomationException{
			List<AddMemberDetails> addMemberDetails = null;
			Object[][] array = null;
			try{
				addMemberDetails = ExcelDataProvider.getExcelDataUsingPOI("createMember", AddMemberDetails.class, 
						DataProviderAddMember.getDataProviderFileName());
				array = new Object[addMemberDetails.size()][1];
				for(int i=0; i<addMemberDetails.size(); i++){
					array[i][0] = addMemberDetails.get(i);
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
					throw new AutomationException("Data Provider File Name is missing");
			}
		}

	
	}


