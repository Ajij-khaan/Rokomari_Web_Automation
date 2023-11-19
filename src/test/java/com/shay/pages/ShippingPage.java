package com.shay.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.shay.baseDriver.PageDriver;
import com.shay.utilities.GetScreenShot;

public class ShippingPage {
	ExtentTest test;
	
	JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDrieDriver();

	public ShippingPage(ExtentTest test) {
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

	@FindBy(xpath = "//div[@class='shipping-form-container']//input[@id='name']") WebElement name;
	@FindBy(xpath = "//fieldset[@class='group']//input[@id='phone']") WebElement phone;
	@FindBy(xpath = "//select[@id='js--country']") WebElement country;
	@FindBy(xpath = "//select[@id='js--city']") WebElement city;
	@FindBy(xpath = "//select[@id='js--area']") WebElement area;
	@FindBy(xpath = "//fieldset[@class='mb-0']//textarea[@id='address']") WebElement address;
	
	@FindBy(xpath = "//body/div[@id='shipping-payment']/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/label[1]") WebElement bKash;
//	@FindBy(xpath = "//label[@for=\"js--terms-checkbox\"]") WebElement termsCondition;

	@FindBy(xpath = "//body/div[@id='shipping-payment']/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[3]/div[2]/label[1]") WebElement termsCondition;
	public void shippingDetails() throws IOException, InterruptedException {
		try {
			test.info("Enter Name");
			if (name.isDisplayed()) {
				name.sendKeys("AR Rahaman");
				passedCaseWithSc("Name is entered", "Name Passed");
			}
		} catch (Exception e) {
			failedCase("Name is not locateable", "name faield");
		}
		
		try {
			test.info("Enter Phone number");
			if (phone.isDisplayed()) {
				phone.sendKeys("01998245769");
				passedCaseWithSc("Phone is entered", "Phone Passed");
			}
		} catch (Exception e) {
			failedCase("Phone is not locateable", "Phone faield");
		}
		
		try {
			test.info("Enter Country");
			if (country.isDisplayed()) {
				Select select = new Select(country);
				select.selectByVisibleText("Bangladesh");
				Thread.sleep(1500);
				passedCaseWithSc("Country is selected", "Country Passed");
			}
		} catch (Exception e) {
			failedCase("Country is not locateable", "Country faield");
		}
		
		try {
			test.info("Enter city");
			if (city.isDisplayed()) {
				Select select = new Select(city);
				select.selectByVisibleText("ঢাকা");
				Thread.sleep(1500);
				passedCaseWithSc("City is selected", "city Passed");
			}
		} catch (Exception e) {
			failedCase("City is not locateable", "city faield");
		}
		
		try {
			test.info("Enter area");
			if (area.isDisplayed()) {
				Select select = new Select(area);
				select.selectByVisibleText("আদাবর");				
				passedCaseWithSc("Area is selected", "area Passed");
				js.executeScript("arguments[0].scrollIntoView(true)", area);
			}
		} catch (Exception e) {
			failedCase("Area is not locateable", "area faield");
		}
		
		try {
			test.info("Enter Address");
			if (address.isDisplayed()) {
				address.sendKeys("33/D Adabar");
				passedCaseWithSc("Address is entered", "Address Passed");
			}
		} catch (Exception e) {
			failedCase("Address is not locateable", "Address faield");
		}
		
		try {
			test.info("Enter Payment Type");
			if (bKash.isDisplayed()) {
				bKash.click();
				passedCaseWithSc("bKash is selected", "Bkash Passed");
			}
		} catch (Exception e) {
			failedCase("Bkash is not locateable", "Bkash faield");
		}
		
		try {
			test.info("Agree Terms & Condition");
			if (termsCondition.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true)", termsCondition);
				Thread.sleep(1000);
				termsCondition.click();
				passedCaseWithSc("termsCondition is checked", "termsCondition Passed");
			}
		} catch (Exception e) {
			failedCase("termsCondition is not locateable", "termsCondition faield");
		}
		
		Thread.sleep(50000);
	}
}
