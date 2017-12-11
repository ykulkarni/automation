package com.yogesh.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.yogesh.core.BaseTest;

public class ClassUtils {

	/**
	 * Creates a test instance
	 *
	 * @param clazz
	 * @return
	 */
	public static Object createTestInstance(Class<? extends BaseTest> clazz) {
		Object inst = null;
		try {
			Constructor<? extends BaseTest> cons = clazz.getConstructor();
			inst = cons.newInstance();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inst;
	}

	/**
	 * Set the field value to appropriate type
	 *
	 * @param inst
	 * @param field
	 * @param value
	 */
	public static void setFieldValue(Object inst, Field field, String value) {
		field.setAccessible(true);
		Class<?> type = field.getType();
		try {
			Object convertedValue = null;

			if (type == String.class) {

				convertedValue = value;

			} else if (type == Integer.TYPE) {

				convertedValue = value.length() == 0 ? 0 : Integer.decode(value);

			} else if (type == Integer.class) {

				convertedValue = value.length() == 0 ? null : Integer.decode(value);

			} else if (type == Boolean.TYPE) {

				convertedValue = value.length() == 0 ? false : Boolean.valueOf(value);

			} else if (type == Boolean.class) {

				convertedValue = value.length() == 0 ? null : Boolean.valueOf(value);

			} else if (type == Float.TYPE) {

				convertedValue = value.length() == 0 ? 0 : Float.valueOf(value);

			} else if (type == Float.class) {

				convertedValue = value.length() == 0 ? null : Float.valueOf(value);

			} else if (type == Double.TYPE) {

				convertedValue = value.length() == 0 ? 0 : Double.valueOf(value);

			} else if (type == Double.class) {

				convertedValue = value.length() == 0 ? null : Double.valueOf(value);

			} else if (type == Long.TYPE) {

				convertedValue = value.length() == 0 ? 0 : Long.decode(value);

			} else if (type == Long.class) {

				convertedValue = value.length() == 0 ? null : Long.decode(value);

			} else if (type == Short.TYPE) {

				convertedValue = value.length() == 0 ? 0 : Short.decode(value);

			} else if (type == Short.class) {

				convertedValue = value.length() == 0 ? null : Short.decode(value);
			}

			field.set(inst, convertedValue);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the value of field if the type is Map or List
	 * 
	 * @param inst
	 * @param field
	 * @param ref
	 */
	public static void setFieldCollection(Object inst, Field field, Object ref) {
		field.setAccessible(true);
		try {
			field.set(inst, ref);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
