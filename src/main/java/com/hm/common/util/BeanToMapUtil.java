package com.hm.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shishun.wang
 * @date 2017年5月16日 下午2:12:50
 * @version 1.0
 * @describe
 */
public class BeanToMapUtil {

	private static Logger logger = LoggerFactory.getLogger(BeanToMapUtil.class);

	private BeanToMapUtil() {
	}

	/**
	 * @author shishun.wang
	 * @date 2017年5月16日 下午2:13:21
	 * @version 1.0
	 * @describe
	 */
	public static class Sample {

		private Sample() {
		}

		public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
			if (map == null) {
				return null;
			}

			try {
				Object obj = beanClass.newInstance();
				org.apache.commons.beanutils.BeanUtils.populate(obj, map);
				return obj;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}

		public static Map<?, ?> objectToMap(Object obj) {
			if (obj == null) {
				return null;
			}

			return new org.apache.commons.beanutils.BeanMap(obj);
		}
	}

	/**
	 * @author shishun.wang
	 * @date 2017年5月16日 下午2:13:15
	 * @version 1.0
	 * @describe
	 */
	public static class IntrospectorUtil {

		private IntrospectorUtil() {
		}

		public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
			if (map == null) {
				return null;
			}

			try {
				Object obj = beanClass.newInstance();

				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					Method setter = property.getWriteMethod();
					if (setter != null) {
						setter.invoke(obj, map.get(property.getName()));
					}
				}

				return obj;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			return null;
		}

		public static Map<String, Object> objectToMap(Object obj) {
			if (obj == null) {
				return null;
			}

			try {
				Map<String, Object> map = new HashMap<String, Object>();

				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					if (key.compareToIgnoreCase("class") == 0) {
						continue;
					}
					Method getter = property.getReadMethod();
					Object value = getter != null ? getter.invoke(obj) : null;
					map.put(key, value);
				}

				return map;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			return null;
		}
	}

	/**
	 * @author shishun.wang
	 * @date 2017年5月16日 下午2:13:06
	 * @version 1.0
	 * @describe
	 */
	public static class Reflect {

		private Reflect() {

		}

		public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
			if (map == null) {
				return null;
			}

			try {
				Object obj = beanClass.newInstance();

				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					int mod = field.getModifiers();
					if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
						continue;
					}

					field.setAccessible(true);
					field.set(obj, map.get(field.getName()));
				}

				return obj;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			return null;
		}

		public static Map<String, Object> objectToMap(Object obj) {
			if (obj == null) {
				return null;
			}

			try {
				Map<String, Object> map = new HashMap<String, Object>();

				Field[] declaredFields = obj.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					map.put(field.getName(), field.get(obj));
				}

				return map;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			return null;
		}
	}
}
