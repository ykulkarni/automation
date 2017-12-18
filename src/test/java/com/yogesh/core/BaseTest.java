package com.yogesh.core;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.yogesh.core.config.Configuration;
import com.yogesh.core.driver.DriverFactory;
import com.yogesh.core.driver.DriverSession;
import com.yogesh.core.page.AbstractPage;
import com.yogesh.utils.ClassUtils;

public abstract class BaseTest {

	protected final Logger log = Logger.getLogger(this.getClass());
	protected WebDriver driver;

	@BeforeClass
	public void init() {
		String browser = Configuration.getInstance().getBrowser();
		log.info("Browser Type - " + browser);
		driver = DriverFactory.getWebDriver(browser);
		DriverSession.set(driver);
	}

	@BeforeClass(dependsOnMethods = "init")
	public void setup() {
		if (driver == null) {
			log.info("=== Setting the session ====");
			driver = DriverSession.get();
		}
		driver.get(Configuration.getInstance().getURL());
		driver.manage().window().maximize();
		AbstractPage.getWindowHandlesMap().put("home", driver.getWindowHandle());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	public BaseTest() {

	}

	/**
	 * This initializes the test data fields to data from data file.
	 *
	 * @param map
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Factory(dataProvider = "yamlProvider")
	public Object[] initData(Map<String, Object> map) throws IllegalArgumentException, IllegalAccessException {
		log.debug("Class Name - " + getClass().getName());
		List<Object> objectList = new ArrayList<>();

		Field[] fields = this.getClass().getDeclaredFields();

		Class<? extends BaseTest> testClass = this.getClass();
		Object inst = ClassUtils.createTestInstance(testClass);

		for (Field field : fields) {
			log.debug("Field Name - " + field.getName());
			Object it = map.get(field.getName());
			if (it != null)
				if (field.getType().equals(Map.class) || field.getType().equals(List.class)) {
					log.debug(field.getType().getName());
					ClassUtils.setFieldCollection(inst, field, it);
				} else {
					log.debug(field.getType().getName());
					ClassUtils.setFieldValue(inst, field, (String) it);
				}
		}
		objectList.add(inst);
		return objectList.toArray();
	}

	/**
	 * This assumes that the data file is in the same folder as the *.java test
	 * file. and has the same name as the test class.
	 *
	 * @param context
	 * @return
	 * @throws YamlException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	@DataProvider
	public Iterator<Object[]> yamlProvider(ITestContext context) throws YamlException, FileNotFoundException {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		YamlReader reader = new YamlReader(getFileInputStream());
		while (true) {
			Map record = (Map) reader.read();
			if (record == null)
				break;
			else {
				dataToBeReturned.add(new Object[] { record });
			}
		}
		return dataToBeReturned.iterator();
	}

	public InputStreamReader getFileInputStream() {
		String className = this.getClass().getName();
		String filePath = className.replace(".", "/") + ".yml";
		log.info("Test Data File Name - " + filePath);
		return new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filePath));
	}
}
