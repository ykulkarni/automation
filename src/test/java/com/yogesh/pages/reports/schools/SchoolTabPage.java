package com.yogesh.pages.reports.schools;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.yogesh.core.page.AbstractPage;

@FindBy(id = "schools")
public class SchoolTabPage extends AbstractPage {

	@FindBy(css = "table[class='table school']")
	private WebElement schoolTable;

	@FindBy(css = "tr address")
	private List<WebElement> schoolRow;

	public List<String> getListedSchools() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", schoolTable);
		List<String> schoolNames = schoolRow.stream().map(e -> e.getText().replaceAll("\n", ",").trim())
				.collect(Collectors.toList());
		return schoolNames;
	}

}
