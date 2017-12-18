package com.yogesh.core.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.yogesh.core.driver.DriverSession;

/**
 * Base Page class for all Page objects.<br>
 * Initializes Webelement fields
 *
 * @author Yogesh Kulkarni
 *
 */
public abstract class AbstractPage {

	protected WebDriver driver = DriverSession.get();

	private static Map<String, String> windowHandlesMap = new HashMap<>();

	public static Map<String, String> getWindowHandlesMap() {
		return windowHandlesMap;
	}

	public AbstractPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Switch Windows for multi window appication
	 * 
	 * @param windowName
	 */
	public void switchWindow(String windowName) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!windowHandlesMap.containsValue(handle)) {
				driver.switchTo().window(handle);
				windowHandlesMap.put(windowName, handle);
			}
		}

	}

}
