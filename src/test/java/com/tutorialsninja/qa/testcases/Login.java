package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base {

	AccountPage accountPage;
	LoginPage loginPage;
	public WebDriver driver;

	public Login() {
		super();
	}

	@BeforeMethod
	public void setup() {
		
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();

	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier") // For Data driven Testing
	public void verifyLoginWithValidCredentials(String email, String password) {

		accountPage = loginPage.login(email, password);

		 Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
		 "Edit your account information option is not displayed");
	}

	// For Data driven Testing
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
		//Assert.assertTrue(loginPage.retrieveEmailPassordNotMactchingWarningMessageText()
				//.contains(dataProp.getProperty("emailPasswordNotMatchingWarning")), "Expected Warning Message is not Displayed");
		
		String actualWarningMessage ="//div[text()='Warning: No match for E-Mail Address and/or Password.']";
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not Displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidUsernameValidPassword() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));

		/*
		 * Assert.assertTrue(loginPage.
		 * retrieveEmailPassordNotMactchingWarningMessageText()
		 * .contains(dataProp.getProperty("emailPasswordNotMatchingWarning")),
		 * "Expected Warning Message is not Displayed");
		 */
		String actualWarningMessage ="//div[text()='Warning: No match for E-Mail Address and/or Password.']";
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not Displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidUsernameInvalidPassword() {

		loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));

		/*
		 * Assert.assertTrue(loginPage.
		 * retrieveEmailPassordNotMactchingWarningMessageText()
		 * .contains(dataProp.getProperty("emailPasswordNotMatchingWarning")),
		 * "Expected Message is not Displayed");
		 */
		
		String actualWarningMessage ="//div[text()='Warning: No match for E-Mail Address and/or Password.']";
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not Displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingAnyCredentials() {

		loginPage.clickOnLoginButton();

		/*
		 * Assert.assertTrue(loginPage.
		 * retrieveEmailPassordNotMactchingWarningMessageText().contains(dataProp.
		 * getProperty("emailPasswordNotMatchingWarning")),
		 * "Expected Message is not Displayed");
		 */
		
		String actualWarningMessage ="//div[text()='Warning: No match for E-Mail Address and/or Password.']";
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not Displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
