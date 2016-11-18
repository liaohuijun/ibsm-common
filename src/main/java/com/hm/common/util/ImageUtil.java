package com.hm.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.ImageIcon;

/**
 * @author shishun.wang
 * @date 下午2:08:04 2016年11月18日
 * @version 1.0
 * @describe
 */
public class ImageUtil {

	/**
	 * 调整大小
	 * 
	 * @param image
	 *            目标图片
	 * @param size
	 *            将图片缩放到目标尺寸
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage resize(BufferedImage image, int size) throws Exception {
		Image resizedImage = null;
		int iWidth = image.getWidth(null);
		int iHeight = image.getHeight(null);
		if (iWidth > iHeight) {
			resizedImage = image.getScaledInstance(size, (size * iHeight) / iWidth, Image.SCALE_SMOOTH);
		} else {
			resizedImage = image.getScaledInstance((size * iWidth) / iHeight, size, Image.SCALE_SMOOTH);
		}
		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor,
				0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);

		return bufferedImage;
	}
}
