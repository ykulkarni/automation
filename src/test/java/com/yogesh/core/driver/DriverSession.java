package com.yogesh.core.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverSession {

	private static final Logger log = Logger.getLogger(DriverSession.class.getName());

	private static WebDriver driver = null;

	/**
	 * Retrieve the current Selenium session
	 *
	 * @return
	 */
	public static WebDriver get() {
		log.debug("Returning Driver ===");
		return driver;
	}

	/**
	 * Set the current Selenium Session
	 *
	 * @param instance
	 */
	public static void set(WebDriver instance) {
		driver = instance;
	}
}
