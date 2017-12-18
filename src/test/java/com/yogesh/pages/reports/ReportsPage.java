package com.yogesh.pages.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.yogesh.pages.BaseScoutPage;
import com.yogesh.pages.reports.overview.OverviewTabPage;
import com.yogesh.pages.reports.schools.SchoolTabPage;

public class ReportsPage extends BaseScoutPage {

	public ReportsPage() {
		super();
	}

	@FindBy(css = "ul[class*='neighborhood-nav']")
	private WebElement reportNavBar;

	@FindBy(css = "span[property='name']")
	private WebElement propertyNameElement;

	public String getPropertyLocation() {
		return propertyNameElement.getText().trim();
	}

	public OverviewTabPage overview() {
		return new ReportNavigationBarPage().overview();
	}

	public SchoolTabPage schools() {
		return new ReportNavigationBarPage().schools();
	}
}