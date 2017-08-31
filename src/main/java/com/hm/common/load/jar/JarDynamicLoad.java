package com.hm.common.load.jar;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 下午4:44:37 2017年8月31日
 * @version 1.0
 * @describe
 */
public class JarDynamicLoad {

	private static Logger logger = LoggerFactory.getLogger(JarDynamicLoad.class);

	public void loads(List<File> files, String methodName, Class<?>... parameterTypes) {
		if (CommonUtil.isEmpty(files)) {
			throw ServiceException.warning(ErrorCode.PARAMETERS_MISSING);
		}

		Method method = null;
		try {
			method = URLClassLoader.class.getDeclaredMethod(methodName, parameterTypes);
			boolean accessible = method.isAccessible(); // 获取方法的访问权限
			if (!accessible) {
				method.setAccessible(true); // 设置方法的访问权限
			}
			// 获取系统类加载器
			URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			for (File file : files) {
				URL url = file.toURI().toURL();
				try {
					method.invoke(classLoader, url);
					logger.debug("读取jar文件[name={}]", file.getName());
				} catch (Exception e) {
					logger.error("读取jar文件[name={}]失败", file.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.setAccessible(false);
		}
	}

	public void load(File file, String methodName, Class<?>... parameterTypes) {
		if (CommonUtil.isEmpty(file)) {
			throw ServiceException.warning(ErrorCode.PARAMETERS_MISSING);
		}

		Method method = null;
		try {
			method = URLClassLoader.class.getDeclaredMethod(methodName, parameterTypes);
			boolean accessible = method.isAccessible(); // 获取方法的访问权限
			if (!accessible) {
				method.setAccessible(true); // 设置方法的访问权限
			}
			// 获取系统类加载器
			URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			URL url = file.toURI().toURL();
			try {
				method.invoke(classLoader, url);
				logger.debug("读取jar文件[name={}]", file.getName());
			} catch (Exception e) {
				logger.error("读取jar文件[name={}]失败", file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.setAccessible(false);
		}
	}
}
