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

import com.decexp.beans.NewMembershipWorkflow;

public class DataProviderCreateNewMember {
	private static Properties prop = new Properties();
	
	@DataProvider(name="CreateNewMember")
	public static Iterator<Object []> createNewUser() throws AutomationException{
		List<NewMembershipWorkflow> newUserDetails = null;
		Object[][] array = null;
		try{
			newUserDetails = ExcelDataProvider.getExcelDataUsingPOI("valid", NewMembershipWorkflow.class, 
					DataProviderCreateNewMember.getDataProviderFileName());
			array = new Object[newUserDetails.size()][1];
			for(int i=0; i<newUserDetails.size(); i++){
				array[i][0] = newUserDetails.get(i);
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


