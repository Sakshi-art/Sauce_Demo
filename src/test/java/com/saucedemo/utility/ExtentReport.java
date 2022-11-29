package com.saucedemo.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	
	public static void setExtent() {
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"ExtentReport.html");
		htmlReporter.config().setDocumentTitle("SauceDome Test Report");
		htmlReporter.config().setReportName("SauceDome Test Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("ProjectName", "SauceDemo");
	}
	public static void closeReport() {
		report.flush();
	}
}