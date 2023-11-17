package com.shay.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.dockerjava.api.model.Info;
import com.shay.baseDriver.PageDriver;
import com.shay.utilities.GetScreenShot;

public class WriterPage{
	ExtentTest test;
	
	Actions action = new Actions(PageDriver.getCurrentDrieDriver());
	JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDrieDriver();

	public  WriterPage(ExtentTest test) {
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
	
	
	//This alert is disssmised by rokomari.com
//	@FindBy(xpath ="//body/div[6]/div[2]/div[1]/button[1]/img[1]")
//	WebElement modalClose;
//	
//	public void closeModal() throws InterruptedException, IOException {
//		try {
//			if(modalClose.isDisplayed()) {
//				test.info("Close the modal.");
//				modalClose.click();
//				Thread.sleep(1000);
//				passedCaseWithSc("Modal closed.", "Modal close pass");
//			}
//		} catch (Exception e) {
//			failedCase("Modal close is not locatable.Please check the error.", "Modal close faield");
//		}
//	}
	

	@FindBy(xpath = "//a[@id='js--authors']") WebElement author;
	@FindBy(xpath = "//a[contains(text(),'ইমদাদুল হক মিলন')]") WebElement authorMilon;
	
	
	public void goToWriterPage() throws IOException, InterruptedException {
		
		try {
			if (author.isDisplayed()) {
				test.info("Hover to autor page");
				action.moveToElement(author).perform();
				Thread.sleep(500);
				passedCaseWithSc("Hover to autor page", "hoverAuthorPassed");
				try {
					if(authorMilon.isDisplayed()) {
						test.info("Go to Imdadul Milon author page");
						authorMilon.click();
						Thread.sleep(500);
						passedCaseWithSc("authorMilon Page", "authorMilonPassed");
					}
				} catch (Exception e) {
					failedCase("authorMilon located.", "authorMilonPassed");
				}
			}
		} catch (Exception e) {
			failedCase("author is not locateable.Please check error", "hoverAuthorFaield");
		}
		}
	
	@FindBy(xpath = "//body/div[6]/div[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[44]/div[1]/a[1]/div[1]/img[1]")
	WebElement vutureBookLocation;
	
	@FindBy(xpath = "//body/div[6]/div[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[44]/div[1]/a[1]/div[1]/div[2]/button[1]")
	WebElement vutureBookATC;

	
	public void bookAddToCart() throws IOException {
		try {
			if(vutureBookLocation.isDisplayed()) {
				 test.info("Scroll and Hover to Vuture Book");
			        js.executeScript("arguments[0].scrollIntoView(true)", vutureBookLocation);
			        Thread.sleep(1000);
			        action.moveToElement(vutureBookLocation).perform();
			        passedCaseWithSc("Scroll to Vuture Book", "vutureBookLocation Passed");

				try {
		            if (vutureBookATC.isDisplayed()) {
		                test.info("Click Add to Cart Vuture Book");
		                vutureBookATC.click();
		                passedCaseWithSc("ATC Vuture Book", "vutureBookATC Passed");
		                Thread.sleep(2000);
		            }
		        } catch (Exception e) {
		            failedCase("vutureBookATC is not locatable.", "vutureBookATC failed");
		        }
			}
		} catch (Exception e) {
			failedCase("vutureBookLocation is not locatalbe.", "vutureBookLocation Passed");
		}
	}
	

	
}
