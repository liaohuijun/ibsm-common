package com.hm.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.util.IOUtils;

/**
 * @author shishun.wang
 * @date 下午2:10:30 2016年11月18日
 * @version 1.0
 * @describe
 */
public class ZipUtil {

	public ZipUtil(){}
	
	/**
	 * zip 文件打包适用于所有
	 * 
	 * @param files
	 * @param filename
	 * @return
	 */
	public static File compressFilesZip(List<File> files, String filename) {
		File zipfile = new File(filename);
		ZipArchiveOutputStream zipOutput = null;
		try {
			zipOutput = (ZipArchiveOutputStream) new ArchiveStreamFactory()
					.createArchiveOutputStream(ArchiveStreamFactory.ZIP, new FileOutputStream(zipfile));
			zipOutput.setEncoding("UTF-8");
			zipOutput.setUseZip64(Zip64Mode.AsNeeded);
			for (File file : files) {
				InputStream in = null;
				try {
					in = new FileInputStream(file);
					ZipArchiveEntry entry = new ZipArchiveEntry(file, file.getName());// zipOutput.createArchiveEntry(logFile,
																						// logFile.getName());
					zipOutput.putArchiveEntry(entry);
					IOUtils.copy(in, zipOutput);
					zipOutput.closeArchiveEntry();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception e) {
						}
					}
				}
			}
			zipOutput.finish();
			zipOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zipOutput != null) {
				try {
					zipOutput.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		}
		return zipfile;
	}

	/**
	 * 该压缩只支持jdk1.7及以上
	 * 
	 * @param files
	 * @param filename
	 * @return
	 */
	public static File zip(List<File> files, String filename) {
		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			// compress the files
			for (int i = 0; i < files.size(); i++) {
				FileInputStream in = new FileInputStream(files.get(i).getCanonicalPath());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				// transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// complete the entry
				out.closeEntry();
				in.close();
			}
			// complete the ZIP file
			out.close();
			return zipfile;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 解压缩文件
	 * 
	 * @param srcName
	 *            源文件名称
	 * @param targetName
	 *            目标文件名称
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void decompressionZip(String srcName, String targetName) throws IOException {
		System.out.println("=============解压开始=============");
		ZipFile zipFile = new ZipFile(srcName);// 创建一个ZIPfile对象
		Enumeration e = zipFile.entries();
		ZipEntry zipEntry = null;
		File dest = new File(targetName);// 要创建的目录
		dest.mkdirs();// 创建目录（该目录为根目录）
		while (e.hasMoreElements()) {
			zipEntry = (ZipEntry) e.nextElement();// 转为ZipEnrty对象
			unRealCompress(zipEntry, targetName, zipFile);
		}
		System.out.println("=============解压结束=============");
	}

	private static void unRealCompress(ZipEntry zipEntry, String targetName, ZipFile zipFile)
			throws ZipException, IOException {
		String entryName = zipEntry.getName();// 获取文件名称
		if (zipEntry.isDirectory()) {
			entryName = entryName.substring(0, entryName.length() - 1);
			File f = new File(targetName + File.separator + entryName);// 根目录+"/"+文件名
			f.mkdirs();// 创建该文件路径
		} else {
			int index = entryName.lastIndexOf("\\");
			if (index != -1) {
				File df = new File(targetName + File.separator + entryName.substring(0, index));
				df.mkdirs();
			}
			index = entryName.lastIndexOf("/");
			if (index != -1) {
				File df = new File(targetName + File.separator + entryName.substring(0, index));
				df.mkdirs();
			}
			File f = new File(targetName + File.separator + zipEntry.getName());

			InputStream in = zipFile.getInputStream(zipEntry);
			OutputStream out = new FileOutputStream(f);

			int c;
			byte[] by = new byte[1024];

			while ((c = in.read(by)) != -1) {
				out.write(by, 0, c);
			}
			out.flush();
			System.out.println("解压：" + f.getAbsolutePath());
			in.close();
			out.close();
		}
	}
}
