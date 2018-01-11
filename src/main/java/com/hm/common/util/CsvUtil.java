package com.hm.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shishun.wang
 * @date 2017年12月29日 上午10:51:28
 * @version 1.0
 * @describe csv工具类
 */
@Slf4j
public final class CsvUtil {

	private CsvUtil() {

	}

	public static List<String> importCsv(File file) {
		List<String> dataList = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}

		return dataList;
	}

	public static byte[] exportCsv(List<String> dataList) {
		ByteArrayOutputStream byteArrayOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bufferedWriter.append(data).append("\r\n");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}

		return byteArrayOutputStream.toByteArray();
	}

	public static void download(HttpServletResponse response, String fileName, List<String> dataList) throws Exception {
		if (CommonUtil.isEmpty(fileName)) {
			log.error("下载csv文件名不能为空");
			return;
		}
		BufferedOutputStream outputStream = null;
		try {
			byte[] data = exportCsv(dataList);
			File file = File.createTempFile(System.nanoTime() + "", ".csv");
			file.deleteOnExit();

			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".csv", "UTF-8"));
			response.addHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");

			outputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/csv;charset=UTF-8");
			outputStream.write(data);
			outputStream.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
		}
	}

	public static void download(HttpServletResponse response, List<String> dataList) throws Exception {
		BufferedOutputStream outputStream = null;
		try {
			byte[] data = exportCsv(dataList);
			File file = File.createTempFile(System.nanoTime() + "", ".csv");
			file.deleteOnExit();

			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(System.nanoTime() + ".csv", "UTF-8"));
			response.addHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");

			outputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/csv;charset=UTF-8");
			outputStream.write(data);
			outputStream.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
		}
	}
}
