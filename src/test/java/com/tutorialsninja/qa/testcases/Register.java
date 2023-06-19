package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base{
	
        public  Register() { //HybridFrame part 3 time 33:35
		
		    super();
	}
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setup() {
		
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
        
		
		
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not Displayed");
        
        
	}
	
	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        
        
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not Displayed");
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithAlreadyExistingEmailAddress() {
	 	
		
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
	
    
        Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message is not displayed");
           
	}
	
	@Test(priority = 4) // all warning related xpath from selenium part5 time 01:07
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
		
		
		registerPage.clickOnContinueButton();
       
		/*
		 * Assert.assertTrue(registerPage.displayStatusOfWarningMessages(
		 * dataProp.getProperty("privacyPolicyWarning"),
		 * dataProp.getProperty("firstNameWarning")),
		 * "Warning Messages are not Displayed");
		 */

       Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy warning message is not getting displayed"); 
       Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),"First Name warning message is not displayed");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

}
