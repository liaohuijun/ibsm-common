package com.hm.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	 * 将多个文件打包到一个zip中
	 *
	 * @param sourceFolder
	 * @param zipFile
	 * @return
	 */
	public static boolean zipFile(String sourceFolder, File zipFile) {
		boolean isOk = true;
		File f = new File(sourceFolder);
		ZipOutputStream out = null;
		try {
			if (!f.exists()) {
				f.mkdirs();
			}
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			zip(out, f, "");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isOk;
	}

	/**
	 * 递归压缩文件
	 * 
	 * @param out
	 * @param f
	 * @param base
	 * @throws Exception
	 */
	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			logger.info(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
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
