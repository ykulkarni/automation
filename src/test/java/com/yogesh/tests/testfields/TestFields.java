package com.yogesh.tests.testfields;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.yogesh.core.BaseTest;
import com.yogesh.core.DataDriven;

@DataDriven
public class TestFields extends BaseTest {

	private String name;
	private List<String> phone;
	private Map<String, String> address;
	private int age;

	@Test
	public void test() {
		log.info("--- This is demo to print data read from data file ---");
		log.info("Name - " + name + " Age" + age);
		for (String e : phone) {
			log.info("Phone - " + e);
		}
		log.info("Address - " + address.get("street"));
		// Dummy assert statement.
		Assert.assertTrue(true);
	}
}
