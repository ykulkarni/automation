package com.yogesh.pages.home;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yogesh.core.page.AbstractPage;
import com.yogesh.pages.login.LoginPage;
import com.yogesh.pages.reports.ReportsPage;

public class HomePage extends AbstractPage {

	@FindBy(css = "a[class*='btn-subscribe']")
	private WebElement subscribeButton;

	@FindBy(css = "a[href='/users/sign_in']")
	private WebElement loginButton;

	@FindBy(css = "a#sample")
	private WebElement sampleLink;

	public WebElement getSubscribeButton() {
		return subscribeButton;
	}

	public void setSubscribeButton(WebElement subscribeButton) {
		this.subscribeButton = subscribeButton;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(WebElement loginButton) {
		this.loginButton = loginButton;
	}

	public WebElement getSampleLink() {
		return sampleLink;
	}

	public void setSampleLink(WebElement sampleLink) {
		this.sampleLink = sampleLink;
	}

	public LoginPage login() {
		getLoginButton().click();
		return new LoginPage();
	}

	public ReportsPage sampleReport() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getSampleLink());
		ExpectedConditions.visibilityOf(sampleLink);
		getSampleLink().click();
		switchWindow("reports");
		return new ReportsPage();
	}

}
