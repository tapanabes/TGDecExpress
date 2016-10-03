package com.decexp.utils;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class ExcelDataProvider {
	public static <T>List<T> getExcelDataUsingPOI(String notation, 
					Class<T>cName, String fileName) 
					throws AutomationException, IOException{
		XSSFWorkbook workbk;
		List<T> listofObj = new ArrayList<T>();
		boolean validNotation = false;
		try{
			String filePath = System.getProperty("user.dir")+
					("//src//test//java//com//decexp//config//")+fileName;
			workbk = new XSSFWorkbook(new File(filePath));
			XSSFSheet sheet = workbk.getSheet(cName.getSimpleName());
			int sRow = sheet.getPhysicalNumberOfRows();
			int sCol = sheet.getRow(0).getLastCellNum();
			
			XSSFRow firstRow = sheet.getRow(0);
			
			List<HashMap> finalList = new ArrayList<HashMap>();
			for(int rowNum=1; rowNum<sRow; rowNum++){
				HashMap<String, String> innerHashMap = new HashMap<String, String>();
				XSSFRow rowNumber = sheet.getRow(rowNum);
				try{
					if(rowNumber.getCell(0).getStringCellValue().equals(notation)){
						validNotation = true;
						for(int colNum=1; colNum<sCol; colNum++){
							try{
								
								innerHashMap.put(firstRow.getCell(colNum).getStringCellValue(), 
									rowNumber.getCell(colNum).getStringCellValue());
							}catch(NullPointerException e){
								continue;
							}
						}
					}
				}catch(NullPointerException e){
					continue;
				}
				if(!innerHashMap.keySet().isEmpty()){
					finalList.add(innerHashMap);
				}
			}
			
			for(int listSize=0; listSize<finalList.size(); listSize++){
				T obj = cName.newInstance();
				java.lang.reflect.Field[] fields = cName.getDeclaredFields();
				for(java.lang.reflect.Field field : fields){
					field.setAccessible(true);
					if(finalList.get(listSize).containsKey(field.getName()))
						field.set(obj, 
								(finalList.get(listSize).get(field.getName())));
				}
				
				listofObj.add(obj);
			}
			
			if(!validNotation){
				System.out.println("No valid notation found in the specified field");
			}
			
		}catch(Exception e){
			throw new AutomationException("File Not Found");
			}
		
	return listofObj;
	}

}
