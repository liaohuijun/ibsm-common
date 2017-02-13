package util.test;

import java.io.File;

import org.junit.Test;

import com.hm.common.util.ImageUtil;

/**
 * @author shishun.wang
 * @date 2017年2月13日 下午4:19:01
 * @version 1.0
 * @describe 
 */
public class TestImage {

	@Test
	public void testBase64(){
		System.out.println(ImageUtil.toBase64(new File("d:/13.png")));
	}
}
