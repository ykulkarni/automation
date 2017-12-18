package com.yogesh.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.yogesh.core.page.AbstractPage;

public class LoginPage extends AbstractPage {

	@FindBy(id = "user_email")
	private WebElement userName;

	@FindBy(id = "user_password")
	private WebElement userPassword;

	@FindBy(id = "user_remember_me")
	private WebElement rememberMe;

	@FindBy(css = "input[name='submit']")
	private WebElement loginButton;

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getUserPassword() {
		return userPassword;
	}

	public WebElement getRememberMe() {
		return rememberMe;
	}

	public void login(String userName, String userPassword) {
		login(userName, userPassword, false);
	}

	public void login(String userName, String userPassword, boolean rememberMe) {
		getUserName().sendKeys(userName);
		getUserPassword().sendKeys(userPassword);
		if (rememberMe) {
			getRememberMe().click();
		}
		// getLoginButton().click();

	}
}
