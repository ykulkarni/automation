package com.yogesh.core;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.yogesh.utils.ClassUtils;

public class BaseTest {

	protected final Logger log = Logger.getLogger(this.getClass());

	public BaseTest() {

	}

	@Factory(dataProvider = "yamlProvider")
	public Object[] initData(Map<String, Object> map) throws IllegalArgumentException, IllegalAccessException {
		log.debug("Class Name - " + getClass().getName());
		List<Object> objectList = new ArrayList<>();

		Field[] fields = this.getClass().getDeclaredFields();

		Class<? extends BaseTest> testClass = this.getClass();
		Object inst = ClassUtils.createTestInstance(testClass);

		for (Field field : fields) {
			log.debug("Field Name - " + field.getName());
			if (field.getType().equals(Map.class) || field.getType().equals(List.class)) {
				log.debug(field.getType().getName());
				ClassUtils.setFieldCollection(inst, field, map.get(field.getName()));
			} else {
				log.debug(field.getType().getName());
				ClassUtils.setFieldValue(inst, field, (String) map.get(field.getName()));
			}
		}
		objectList.add(inst);
		return objectList.toArray();
	}

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
		log.debug("Test Data File Name - " + filePath);
		return new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filePath));
	}

}
