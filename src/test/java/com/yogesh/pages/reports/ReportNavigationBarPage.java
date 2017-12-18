package com.yogesh.pages.reports;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yogesh.core.page.AbstractPage;
import com.yogesh.pages.reports.overview.OverviewTabPage;
import com.yogesh.pages.reports.schools.SchoolTabPage;

@FindBy(css = "ul[class*='neighborhood-nav']")
public class ReportNavigationBarPage extends AbstractPage {

	@FindBy(css = "a[href='#overview']")
	private WebElement overviewTab;

	@FindBy(xpath = ".//a[(normalize-space(@class)='nav-link') and (@href='#real-estate')]")
	private WebElement realEstateTab;

	@FindBy(xpath = ".//a[(normalize-space(@class)='nav-link') and (@href='#schools')]")
	private WebElement schoolTab;

	public void realEstate() {
		ExpectedConditions.visibilityOf(realEstateTab);
		realEstateTab.click();
	}

	public SchoolTabPage schools() {
		ExpectedConditions.visibilityOf(schoolTab);
		schoolTab.click();
		return new SchoolTabPage();
	}

	public OverviewTabPage overview() {
		ExpectedConditions.visibilityOf(overviewTab);
		overviewTab.click();
		return new OverviewTabPage();
	}
}
