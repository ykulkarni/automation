package com.yogesh.pages.reports.overview;

import org.openqa.selenium.By;

import com.yogesh.pages.BaseScoutPage;

public class OverviewTabPage extends BaseScoutPage {

	public enum Category {
		REAL_ESTATE("real-estate"), SCHOOLS("schools"), DEMOGRAPHICS("demographics"), CRIMES("crimes");

		private String id;

		private Category(String value) {
			id = value;
		}

		public String getID() {
			return id;
		}
	}

	public static String xpath_stats = ".//header[@class='%s']/parent::div//h2";

	public static String xpath_alerts = ".//header[@class='%s']/parent::div//h3";

	public String getAlerts(Category category) {
		return driver.findElement(By.xpath(String.format(xpath_alerts, category.getID()))).getText();
	}

	public String getStatistics(Category category) {
		return driver.findElement(By.xpath(String.format(xpath_stats, category.getID()))).getText();
	}

}
