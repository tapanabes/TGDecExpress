package com.decexp.utils;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		//Iterating over each suite included in the test
		for(ISuite suite : suites){
			//following code get the suite name
			String suiteName = suite.getName();
			//Getting the result for the said suite
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for(ISuiteResult sr : suiteResults.values()){
				ITestContext tc = sr.getTestContext();
				System.out.println(
						"Passed Tests for suites '"+suiteName+"' is:"+tc.getPassedTests().getAllResults().size());
				System.out.println(
						"Failed Tests for suites '"+suiteName+"' is:"+tc.getFailedTests().getAllResults().size());
				System.out.println(
						"Skipped Tests for suites '"+suiteName+"' is:"+tc.getSkippedTests().getAllResults().size());
			}
		}
		
	}

}
