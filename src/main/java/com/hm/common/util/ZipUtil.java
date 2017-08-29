package com.hm.common.util;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author shishun.wang
 * @date 下午2:10:30 2016年11月18日
 * @version 1.0
 * @describe
 */
public class ZipUtil {

	private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

	private ZipUtil() {
	}

	/**
	 * 压缩
	 * 
	 * @param files
	 *            文件不支持文件夹
	 * @param zipFileName
	 * @param zipFilePath
	 */
	public static void compressFilesZip(ArrayList<File> files, String zipFilePath, String passwod) {
		if (CommonUtil.isAnyEmpty(files, zipFilePath)) {
			throw ServiceException.warning(ErrorCode.REQUIRED_PARAMETERS_MISSING);
		}
		try {
			ZipParameters zipParameters = new ZipParameters();
			zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if (StringUtil.isNotBlank(passwod)) {
				zipParameters.setEncryptFiles(true);
				zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				zipParameters.setPassword(passwod.toCharArray());
			}

			net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(zipFilePath);
			zipFile.addFiles(files, zipParameters);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 解压
	 * 
	 * @param unpackFilePath
	 * @param passwod
	 * @param compressFilePath
	 */
	public static void unpackFilesZip(String unpackFilePath, String passwod, String compressFilePath) {
		try {
			ZipFile zipFile = new ZipFile(compressFilePath);
			if (!zipFile.isValidZipFile()) {
				throw new ZipException(MessageFormat.format("压缩文件不合法，可能已经损坏{0}", compressFilePath));
			}

			File file = new File(unpackFilePath);
			if (file.isDirectory() && !file.exists()) {
				file.mkdirs();
			}

			if (StringUtil.isNotBlank(passwod) && zipFile.isEncrypted()) {
				zipFile.setPassword(passwod.toCharArray());
			}

			zipFile.extractAll(unpackFilePath);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
