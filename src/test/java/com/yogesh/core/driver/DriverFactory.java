package com.yogesh.core.driver;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	private final static Logger log = Logger.getLogger(DriverFactory.class);

	/**
	 * Return an instance of webdriver based on configuration in test.properties.
	 *
	 * @param driverType
	 * @return
	 */
	public static WebDriver getWebDriver(String driverType) {

		WebDriver driver = null;

		DesiredCapabilities capabilities = null;

		if (driverType.contains("firefox")) {
			log.debug("Initializing Firefox driver");
			FirefoxProfile profile = new FirefoxProfile();
			File profileDir = null;
			profile = new FirefoxProfile(profileDir);

			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.useDownloadDir", true);

			driver = new FirefoxDriver();

		} else if (driverType.contains("chrome")) {
			log.debug("Initializing Chrome driver");
			URL pathTodriver = DriverFactory.class.getClassLoader().getResource("drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", pathTodriver.getFile());
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(options);

		}
		return driver;
	}

}
