package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.base.BaseClass;

public class PurchasePage extends BaseClass {
	
	public static void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	@FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select")
	private WebElement filter;

	@FindBy(xpath = "//option[contains(text(),'Price (low to high)')]")
	private WebElement lowToHigh;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement backpackAddToCart;

	@FindBy(className = "inventory_item_name")
	private WebElement addedItem;

	@FindBy(className = "shopping_cart_link")
	private WebElement shoppingCart;

	@FindBy(id = "checkout")
	private WebElement checkoutBtn;

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(className = "cart_button")
	private WebElement continueBtn;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[8]/button[2]")
	private WebElement finishBtn;

	@FindBy(xpath = "//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")
	private WebElement successMessage;

	public PurchasePage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void applyFIlter() {
		filter.click();
		wait.until(ExpectedConditions.elementToBeClickable(lowToHigh)); 
		lowToHigh.click();
	}

	public WebElement bagAddToCart() {
		scrollByVisibilityOfElement(getDriver(), backpackAddToCart);
		backpackAddToCart.click();
		return backpackAddToCart;
	}

	public void goToShoppingCart() {
		shoppingCart.click();
	}

	public String validateItem() {
		return addedItem.getText();
	}

	public void goToCheckoutPage() {
		checkoutBtn.click();
	}

	public void shippingDeatils(String firstname, String lastname) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
	}

	public void shippingDeatilsPostal(String postal) {
		postalCode.sendKeys(postal);
	}

	public void placeOrder() {
		continueBtn.click();
	}

	public void reviewOrder() {
		
		//scrollByVisibilityOfElement(getDriver(), finishBtn);
		wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();
	}

	public boolean validateMessage() {
		return successMessage.isDisplayed();
	}
}
