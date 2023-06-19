package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy (id="input-email")
	private WebElement emailAddressFiled;
	
	@FindBy (id="input-password")
	private WebElement passwordFiled;
	
	@FindBy (xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy (xpath="//div[text()='Warning: No match for E-Mail Address and/or Password.']")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterEmailAddress(String emailText) {
		
		emailAddressFiled.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		
		passwordFiled.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton() {
		
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String emailText, String passwordText) {
		
		emailAddressFiled.sendKeys(emailText);
		passwordFiled.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public String retrieveEmailPassordNotMactchingWarningMessageText() {
		
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
		
		
	}

}
