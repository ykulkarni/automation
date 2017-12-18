package com.yogesh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.yogesh.core.page.AbstractPage;
import com.yogesh.pages.reports.ReportsPage;

public class BaseScoutPage extends AbstractPage {

	@FindBy(id = "location_search_query")
	private WebElement searchInput;

	@FindBy(css = "input[name='commit']")
	private WebElement scoutItButton;

	public WebElement getSearchInput() {
		return searchInput;
	}

	public WebElement getScoutItButton() {
		return scoutItButton;
	}

	/**
	 * Search for the Report based on location
	 * 
	 * @param searchString
	 * @return
	 */
	public ReportsPage scoutIt(String searchString) {
		getSearchInput().sendKeys(searchString);
		getScoutItButton().click();
		return new ReportsPage();
	}

}
