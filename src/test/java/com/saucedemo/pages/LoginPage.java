package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.base.BaseClass;

public class LoginPage extends BaseClass {
	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
	

	@FindBy(id = "user-name")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordBox;

	@FindBy(id = "login-button")
	private WebElement loginBtn;

	@FindBy(className = "login_logo")
	private WebElement logo;

	@FindBy(id = "menu_button_container")
	private WebElement loginSuccess;

	@FindBy(className = "error-message-container")
	private WebElement invalidLoginError;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public String getCurrentPageUrl() {
		return getDriver().getCurrentUrl();
	}

	public boolean validateLogo() {
		return logo.isDisplayed();
	}

	public boolean loginSuccessful() {
		return loginSuccess.isDisplayed();
	}

	public void provideUsername(String user) throws Throwable {
		userName.clear();
		userName.sendKeys(user);
	}

	public void providePassword(String pwd) throws Throwable {

		passwordBox.clear();
		passwordBox.sendKeys(pwd);
	}

	public void clickOnLogin() throws Throwable {
		loginBtn.click();
	}

	public void provideIncorrectPassword(String wrongpwd) throws Throwable {
		passwordBox.clear();
		passwordBox.sendKeys(wrongpwd);
	}

	public boolean validateInvalidLoginError() {
		wait.until(ExpectedConditions.visibilityOf(invalidLoginError));
		return invalidLoginError.isDisplayed();
	}

	public boolean errorMessageExist(String errorMessage) {
		if (invalidLoginError.getText().contains(errorMessage)) {
			return true;
		}
		return false;
	}
}
