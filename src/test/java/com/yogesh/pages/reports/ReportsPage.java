package com.yogesh.pages.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.yogesh.pages.BaseScoutPage;

public class ReportsPage extends BaseScoutPage {

	public ReportsPage() {
		super();
	}

	@FindBy(css = "ul[class*='neighborhood-nav']")
	private WebElement reportNavBar;

	@FindBy(css = "span[property='name']")
	private WebElement propertyNameElement;

	private ReportNavigationBarPage navBar;

	public ReportNavigationBarPage getNavBar() {
		navBar = new ReportNavigationBarPage();
		return navBar;
	}

	public String getPropertyLocation() {
		return propertyNameElement.getText().trim();
	}
}