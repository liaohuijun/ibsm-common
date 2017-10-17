package util.test;

import java.io.File;

import com.hm.common.util.ZipUtil;

/**
 * @author shishun.wang
 * @date 下午4:24:34 2017年10月17日
 * @version 1.0
 * @describe 
 */
public class PackObd {

	public static void main(String[] args) {
		ZipUtil.zipFile("D:/var", new File("D:/pack_var/text.zip"));
	}
}
