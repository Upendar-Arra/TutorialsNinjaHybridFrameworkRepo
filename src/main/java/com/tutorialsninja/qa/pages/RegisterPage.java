package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstnameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy (id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement YesNewsLetterOption;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyAgreeButton;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement ContinueButton;
	
	@FindBy(xpath="//div[contains(text(),'Warning: E-Mail Address is already registered!')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnameWarning;
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstnameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText) {
		
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordText) {
		
		passwordConfirmField.sendKeys(passwordText);
	}
	public void SelectYesNewsLetterOption() {
		YesNewsLetterOption.click();
	}
	
	public void selectPrivacyPolicy() {
		privacyPolicyAgreeButton.click();
		
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		
		ContinueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		
		String duplcateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplcateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning() {
		
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstnameWarning.getText();
		return firstNameWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
		
		firstnameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyAgreeButton.click();
		ContinueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
		
		firstnameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		YesNewsLetterOption.click();
		privacyPolicyAgreeButton.click();
		ContinueButton.click();
		return new AccountSuccessPage(driver);
		
	}
    
     public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning) {
    	 
    	 
    	 boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedFirstNameWarning);
    	
    	 boolean firstNameWarningStatus = firstnameWarning.getText().equals(expectedFirstNameWarning);
		return privacyPolicyWarningStatus && firstNameWarningStatus;
    	 
     }

}
