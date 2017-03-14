package util.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.hm.common.util.QrCodeUtil;

/**
 * @author shishun.wang
 * @date 2017年3月14日 下午3:06:12
 * @version 1.0
 * @describe
 */
public class QrCodeTest {

	@Test
	public void test() throws Exception {

		QrCodeUtil qrCodeUtil = new QrCodeUtil();
		BufferedImage image = qrCodeUtil.getEncodeImage(350, "http://www.oschina.net/question/315_181184", 5);
		ImageIO.write(image, "PNG", new FileOutputStream("d:/test11.png"));
	}

	@Test
	public void testLogo() throws Exception {
		QrCodeUtil qrCodeUtil = new QrCodeUtil();

		FileInputStream inputStream = new FileInputStream("d:/logo.png");
		BufferedImage logo = ImageIO.read(inputStream);

		BufferedImage image = qrCodeUtil.getLogoEncodeImage(350, 16, "http://www.oschina.net/question/315_181184", Color.white, logo, 2);
		ImageIO.write(image, "PNG", new FileOutputStream("d:/test12.png"));
	}
}
