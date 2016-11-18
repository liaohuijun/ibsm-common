package com.hm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

/**
 * @author shishun.wang
 * @date 上午11:18:50 2016年11月18日
 * @version 1.0
 * @describe
 */
public class FileUtil extends CommonUtil{

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param url
	 * @param isMkdir
	 * @throws Exception
	 */
	public static boolean exists(String url, boolean isMkdir) throws Exception {
		File file = new File(url);
		if (!file.exists()) {
			if (!isMkdir) {
				return false;
			}
			file.mkdirs();
		}
		return true;
	}

	/**
	 * 文件写入
	 *
	 * @param path
	 * @param data
	 * @throws Exception
	 */
	public static void writer(String path, String data) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		} else {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.write(data);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bufferedWriter)
				bufferedWriter.close();
			if (null != outputStreamWriter)
				outputStreamWriter.close();
			if (null != fileOutputStream)
				fileOutputStream.close();
		}
	}

	public static String read(String path) throws Exception {
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;

		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			StringBuffer buffer = new StringBuffer("");
			String line = null;
			while (null != (line = bufferedReader.readLine())) {
				buffer.append(line).append(System.lineSeparator());
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bufferedReader)
				bufferedReader.close();
			if (null != inputStreamReader)
				inputStreamReader.close();
		}
		return null;
	}

	/**
	 * 拷贝文件
	 *
	 * @param path
	 * @param data
	 * @throws Exception
	 */
	public static void copyFile(String path, String data) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			writer(path, data);
		}
	}

	/**
	 * 删除文件
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFile(String path) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	/**
	 * 删除指定目录下所有的文件
	 *
	 * @param file
	 *            new File("e:/tmp/");
	 * @throws Exception
	 */
	public static void clearAllFile(File file) throws Exception {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (null != files) {
				for (File tmpFile : files) {
					clearAllFile(tmpFile);
				}
			}
		}
		file.delete();
	}

	/**
	 * 检查远程文件是否存在
	 *
	 * @param remotePath
	 * @return
	 * @throws Exception
	 */
	public static boolean remoteFileExists(String remotePath) throws Exception {
		try {
			new URL(remotePath).openConnection().getInputStream();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	public static void download(File file, String fileName, HttpServletResponse response) throws Exception {
		try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();

			// 设置response的Header
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + file.length());
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			// response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setContentType("binary/octet-stream");
			outputStream.write(buffer);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteFile(file.getPath());
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param current
	 * @param target
	 * @throws IOException
	 */
	public static void copy(File current, File target) throws IOException {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(current);
			outputStream = new FileOutputStream(target);
			byte buffer[] = new byte[1024 * 1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.flush();
			outputStream.close();
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
		}
	}

	/**
	 * 从指定路径读取文件到字节数组中,对于一些非文本格式的内容可以选用这个方法 457364578634785634534
	 * 
	 * @param path
	 *            文件路径,包含文件名
	 * @return byte[] 文件字节数组
	 * 
	 */
	public static byte[] getFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(path);
		int size = stream.available();
		byte data[] = new byte[size];
		stream.read(data);
		stream.close();
		stream = null;
		return data;
	}

	/**
	 * 把字节内容写入到对应的文件，对于一些非文本的文件可以采用这个方法。
	 * 
	 * @param data
	 *            将要写入到文件中的字节数据
	 * @param path
	 *            文件路径,包含文件名
	 * @return boolean isOK 当写入完毕时返回true;
	 * @throws Exception
	 */
	public static boolean toFile(byte data[], String path) throws Exception {
		FileOutputStream out = new FileOutputStream(path);
		out.write(data);
		out.flush();
		out.close();
		out = null;
		return true;
	}
}
