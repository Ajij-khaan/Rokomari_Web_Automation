package com.shay.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.shay.baseDriver.BaseDriver;
import com.shay.pages.LoginPage;
import com.shay.utilities.ExtentFactory;

public class LoginTest extends BaseDriver{
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void start() throws InterruptedException {
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>Login Page</b></p>").assignAuthor("QA TEAM").assignDevice("Windows");
	}
	
	@Test
	public void Signin() throws IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Login Credential</b></p>");
		LoginPage login = new LoginPage(childTest);
		login.login();
	}
	
	@AfterClass
	public void report() {
		report.flush();
	}
}
