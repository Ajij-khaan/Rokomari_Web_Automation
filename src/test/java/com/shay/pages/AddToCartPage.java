package com.shay.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.shay.baseDriver.PageDriver;
import com.shay.utilities.GetScreenShot;

public class AddToCartPage {
ExtentTest test;
	
//	Actions action = new Actions(PageDriver.getCurrentDrieDriver());
//	JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDrieDriver();

	public  AddToCartPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDrieDriver(), this);
		this.test = test;
	}

	public void failedCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDrieDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDrieDriver().quit();
	}

	public void passedCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
	}

	public void passedCaseWithSc(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDrieDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}

	@FindBy(xpath = "//*[@id='cart-icon']")
	WebElement cartIcon;
	
	@FindBy(xpath = "//span[contains(text(), \" Place Order \")]") WebElement placeOrder;

	public void goToAddToCart() throws IOException {
		try {
			test.info("Click on cart icon");
			if (cartIcon.isDisplayed()) {
				cartIcon.click();
				passedCaseWithSc("cartIcon clicked", "cartIcon passed");
				
				try {
					test.info("Click on Place Order");
					if(placeOrder.isDisplayed()) {
						placeOrder.click();
						passedCaseWithSc("Place Order is clicked", "placeOrder Passed");
					}
				} catch (Exception e) {
					failedCase("placeOrder is not locateable", "placeOrder Faield");
				}
			}
		} catch (Exception e) {
			failedCase("cartIcon not locateable", "cartIcon passed");
		}
	}
}
