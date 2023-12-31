package com.shay.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.shay.baseDriver.PageDriver;
import com.shay.utilities.GetScreenShot;

public class LoginPage {
	ExtentTest test;
	public  LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDrieDriver(), this);
		this.test = test;
	}
	
	public void failedCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDrieDriver(), ""+ scName +"" );
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDrieDriver().quit();
	}
	
	public void passedCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
	}
	
	public void passedCaseWithSc(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDrieDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	
	
	
	@FindBys({
		@FindBy(xpath = "//input[@placeholder='Email or phone']"),
		@FindBy(xpath = "//input[@name=\"username\"]")
	})
	WebElement email;
	
	@FindBy(xpath = "//button[@id='js--btn-next']")
	WebElement nextBtn;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passField;
	
	@FindBy(xpath = "//form[@id='login-form']//button[@type='submit'][normalize-space()='Login']")
	WebElement loginBtn;
	
	
	public void login() throws IOException {
		
		try {
			if(email.isDisplayed()) {
				test.info("Please enter email");
				email.sendKeys("wijovi2107@bikedid.com");
				Thread.sleep(500);
				passedCaseWithSc("Email Entered", "Email enterd");
				
				try {
					if(nextBtn.isDisplayed()) {
						test.info("Please click next");
						nextBtn.click();
						Thread.sleep(500);
						passedCaseWithSc("Next button clicked", "Next button");
						
						try {
							if (passField.isDisplayed()) {
								test.info("Please enter password");
								passField.sendKeys("pass1234");
								Thread.sleep(500);
								passedCaseWithSc("Password Enterd","Password entered");
								
								try {
									if (loginBtn.isDisplayed()) {
										test.info("Please click login button.");
										loginBtn.click();
										passedCaseWithSc("Login Button clicked", "loginbtn clicked");
									}
								} catch (Exception e) {
									failedCase("Login button is not locateable.Please check the error.", "loginBtn error");
								}
							}
						} catch (Exception e) {
							failedCase("Password field is not locateable.Please check the error.", "passfield error");
						}
					}
				} catch (Exception e) {
					failedCase("Next button is not locateable.Please check the error.", "Next buttons error");
				}
			}
			
		} catch (Exception e) {
			failedCase("Email is not locateable.Please check the error.", "email field error");
		}
	}
}
