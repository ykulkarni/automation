package com.yogesh.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.yogesh.core.BaseTest;
import com.yogesh.core.DataDriven;
import com.yogesh.core.services.NavigationService;
import com.yogesh.pages.home.HomePage;
import com.yogesh.pages.login.LoginPage;

/**
 * Simple Login test
 *
 * @author Yogesh.Kulkarni
 *
 */
@DataDriven
public class LoginTest extends BaseTest {

	private String userName;
	private String userPassword;

	@Test
	public void loginTest() {
		HomePage homePage = NavigationService.homePage();
		LoginPage loginPage = homePage.login();
		log.info("Logging as : " + userName + "/" + userPassword);
		loginPage.login(userName, userPassword);
		Assert.assertTrue(loginPage.getUserName().isDisplayed(), "Username field is not displayed");
	}
}
