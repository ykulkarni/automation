package com.yogesh.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private static Configuration instance = null;

	private Properties props;

	protected Configuration() {
		ClassLoader classLoader = getClass().getClassLoader();
		props = new Properties();
		File file = new File(classLoader.getResource("test.properties").getFile());

		try {
			props.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public String getBrowser() {
		return props.getProperty("browser");
	}

	public String getURL() {
		return props.getProperty("base.url");
	}
}
