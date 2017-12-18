package com.yogesh.core.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Driver session manager to store current selenium session. You don't have to
 * pass driver around in page objects.
 * <p>
 * TO DO - Need to add ThreadLocal variable to store selenium session to support
 * testng multi-threading
 *
 * @author ykulk_000
 *
 */
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
