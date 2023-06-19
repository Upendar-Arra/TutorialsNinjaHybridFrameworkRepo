package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base {
	
	public Search() {
		
		super();
	}

	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup() {
		
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		
	}
	
	@Test(priority = 1)
	public void verifySearchFunctionalityWithValidProduct() {
		
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		
	
		Assert.assertTrue(searchPage.displayStatusOfHPProduct(),"Valid Product is not Displayed");
		
		
	}
	
	@Test(priority = 2)
	public void verifySearchFunctionalityWithInvalidProduct() {
		
		searchPage =homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
		
		System.out.println(searchPage.retrieveNoProductMessageText());
		
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("NoProductTextInSearchResults"),"Searched product is not available");
	}
	
	@Test(priority = 3)
	public void verifySearchFunctionalityWithoutProvidingAnyProductName() {
		
		
		searchPage = homePage.clickOnSearchButton();
	
		System.out.println(searchPage.retrieveNoProductMessageText());
		
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("NoProductTextInSearchResults"),"Searched product is not available");
	}
		
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

}
