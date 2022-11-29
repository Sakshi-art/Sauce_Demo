package com.saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseClass;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.PurchasePage;

public class SauceDemoTest extends BaseClass {
	LoginPage loginPage;
	PurchasePage purchasePage;

	@BeforeTest
	public void goToLoginPage() throws Exception {
		setBrowser("browser");
	}

	@Test(priority = 2)
	public void validVogin() throws Throwable {
		// WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		loginPage = new LoginPage();
		loginPage.provideUsername(prop.getProperty("username"));
		loginPage.providePassword(prop.getProperty("password"));
		loginPage.clickOnLogin();
		boolean loginSuccess = loginPage.loginSuccessful();
		Assert.assertTrue(loginSuccess);

	}

	@Test(priority = 1)
	public void invalidVogin() throws Throwable {
		loginPage = new LoginPage();
		boolean logo = loginPage.validateLogo();
		Assert.assertTrue(logo);
		loginPage.provideUsername(prop.getProperty("username"));
		loginPage.providePassword(prop.getProperty("incorrectpassword"));
		loginPage.clickOnLogin();
		boolean loginSuccess = loginPage.loginSuccessful();
		Assert.assertTrue(loginSuccess);
	}

	@Test(priority = 3, dependsOnMethods = {"validVogin"})
	public void selectProduct() throws Throwable {
		purchasePage = new PurchasePage();
		purchasePage.applyFIlter();
		purchasePage.bagAddToCart();
		purchasePage.goToShoppingCart();
		String item = purchasePage.validateItem();
		Assert.assertEquals("Sauce Labs Backpack", item);
	}

	@Test(priority = 4, dependsOnMethods = {"selectProduct"})
	public void placeTheOrder() throws Throwable {
		
		purchasePage.goToCheckoutPage();
		purchasePage.shippingDeatils(prop.getProperty("firstName"), prop.getProperty("lastName"));
		purchasePage.shippingDeatilsPostal(prop.getProperty("postalCode"));
		purchasePage.placeOrder();
	}
	@Test(priority = 5, dependsOnMethods = {"placeTheOrder"})
	public void reviewOrderDetails() throws Throwable{
		purchasePage.reviewOrder();
		boolean result = purchasePage.validateMessage();
		Assert.assertTrue(result);
	}

	@AfterTest
	public void closeBrowser() {
		getDriver().quit();
	}
}
