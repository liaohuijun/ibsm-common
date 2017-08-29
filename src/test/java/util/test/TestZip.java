package util.test;

import java.io.File;
import java.util.ArrayList;

import com.hm.common.util.ZipUtil;

/**
 * @author shishun.wang
 * @date 下午6:05:09 2017年8月25日
 * @version 1.0
 * @describe
 */
public class TestZip {

	public static void main(String[] args) {
		try {

			ArrayList<File> list = new ArrayList<File>();
			list.add(new File("D:/tmp/mysql/2017-08-25.sql"));
			ZipUtil.compressFilesZip(list, "D:/tmp/mysql/123.zip", "nihao123.");
			
			ZipUtil.unpackFilesZip("D:/tmp/mysql/unpack", "nihao123.", "D:/tmp/mysql/123.zip");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
