package com.yogesh.tests.reports;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.yogesh.core.BaseTest;
import com.yogesh.core.DataDriven;
import com.yogesh.core.services.NavigationService;
import com.yogesh.pages.home.HomePage;
import com.yogesh.pages.reports.ReportsPage;
import com.yogesh.pages.reports.overview.OverviewTabPage;
import com.yogesh.pages.reports.overview.OverviewTabPage.Category;

/**
 * Use case - Login to you account<br>
 * Search for a address<br>
 * Navigate overview tab to general information<br>
 * Navigate to Schools tab and validate the schools listed. <br>
 *
 * @author Yogesh Kulkarni
 *
 */

@DataDriven
public class TestReport extends BaseTest {

	private List<String> schoolList;
	private String expectedPropertyLocation01545;
	private String expectedPropertyLocation01752;

	private ReportsPage reports;
	private SoftAssert softAssert;

	@Test
	public void schoolTest() {
		softAssert = new SoftAssert();
		HomePage homePage = NavigationService.homePage();
		reports = homePage.sampleReport();

		String propertyLocation = reports.getPropertyLocation();
		log.info("Sample Report Property Location : " + propertyLocation);
		softAssert.assertEquals(propertyLocation, expectedPropertyLocation01545,
				"Property Location info in header is not correct");

		log.info("School information ===");

		List<String> schools = reports.schools().getListedSchools();
		schools.stream().forEach(it -> {
			log.info("School Info : " + it);
			log.info("-----------------------");
			softAssert.assertTrue(schoolList.contains(it), it + " not listed");
		});
		log.info("======================");
		softAssert.assertAll();
	}

	@Test(dependsOnMethods = "schoolTest")
	public void overviewTabTest() {
		softAssert = new SoftAssert();
		OverviewTabPage overviewTab = reports.overview();
		String realEstateAlerts = overviewTab.getAlerts(Category.REAL_ESTATE);
		log.info("Real Estate alerts - " + realEstateAlerts);
		softAssert.assertNotNull(realEstateAlerts, "No Alerts for real estate");
		softAssert.assertAll();
	}

	@Test(dependsOnMethods = "overviewTabTest")
	public void scoutItTest() {
		reports.scoutIt("45 Lakeside Avenue, Marlborough, MA");
		String propertyLocation = reports.getPropertyLocation();
		log.info("Marlborough Property Location : " + propertyLocation);
		Assert.assertEquals(propertyLocation, expectedPropertyLocation01752,
				"Property Location info in header is not correct");

	}
}
