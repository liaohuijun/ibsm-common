package com.hm.common.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author shishun.wang
 * @date 下午2:09:08 2016年11月18日
 * @version 1.0
 * @describe
 */
public class QrCodeUtil {

	private Integer size = 350;

	private String format = "jpeg";

	private String coding = "UTF-8";

	/** 背景色 */
	public int BACKGROUND_COLOR = 0xFF000000;

	/** 数据颜色 */
	public int DATA_COLOR = 0xFFFFFFFF;

	private Map<EncodeHintType, Object> encodeHints() {
		Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();

		{// 生成二维码参数设置
			// 设置QR二维码的纠错级别（H为最高级别）具体级别信息,容错越高黑点越多
			encodeHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			encodeHints.put(EncodeHintType.CHARACTER_SET, coding);
			encodeHints.put(EncodeHintType.MARGIN, 0);
			encodeHints.put(EncodeHintType.MAX_SIZE, size);
			encodeHints.put(EncodeHintType.MIN_SIZE, size);
		}
		return encodeHints;
	}

	public BufferedImage getLogoEncodeImage(Integer size, Integer logoSize, String content, Color logoBorderColor, BufferedImage logo, int margin)
			throws Exception {
		BufferedImage image = getEncodeImage(size, content, margin);
		Graphics2D g = image.createGraphics();
		// 设置logo的大小为二维码图片的20%,因为过大会盖掉二维码
		int belongRatio = 2;
		int widthLogo = logo.getWidth(null) > image.getWidth() * belongRatio / 10 ? (image.getWidth() * belongRatio / 10) : logo.getWidth(null),
				heightLogo = logo.getHeight(null) > image.getHeight() * belongRatio / 10 ? (image.getHeight() * belongRatio / 10) : logo.getWidth(null);
		// 计算logo图片放置位置
		int x = (image.getWidth() - widthLogo) / 2;
		int y = (image.getHeight() - heightLogo) / 2;
		g.drawImage(logo, x, y, widthLogo, heightLogo, null);
		g.drawRoundRect(x, y, widthLogo, heightLogo, logoSize, logoSize);

		g.setStroke(new BasicStroke(margin));
		g.setColor(logoBorderColor);
		g.drawRect(x, y, widthLogo, heightLogo);

		g.dispose();
		logo.flush();
		image.flush();

		return image;
	}

	public BufferedImage getEncodeImage(Integer size, String content, int margin) throws Exception {
		Map<EncodeHintType, Object> encodeHints = encodeHints();

		if (null == size) {
			size = this.size;
		} else {// 重新设置二维码尺寸
			encodeHints.put(EncodeHintType.MAX_SIZE, this.size);
			encodeHints.put(EncodeHintType.MIN_SIZE, this.size);
		}

		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		// 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
		BitMatrix bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, encodeHints);

		int w = bm.getWidth();
		int h = bm.getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				image.setRGB(x, y, bm.get(x, y) ? BACKGROUND_COLOR : DATA_COLOR);
			}
		}

		if (margin < 0) {
			return image;
		}

		// 二维码尺寸处理
		int[] rec = bm.getEnclosingRectangle();
		int padding = rec[0];
		int realWidth = rec[3];
		if (realWidth < size && padding > 0 && (padding - margin > 0)) {// 图片截取(获取二维码图片真实尺寸)
			// 裁剪图片去除空白区域
			image = tailoringMargin(image, padding, realWidth, margin);
			// 图片放大，放大到指定尺寸大小
			return ImageUtil.resize(image, size);
		}
		return image;
	}

	/**
	 * 裁剪图片去除生成二维码边距
	 * 
	 * @param image
	 * @param padding
	 * @param size
	 * @return
	 * @throws Exception
	 */
	private BufferedImage tailoringMargin(BufferedImage image, int padding, int realSize, int margin) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, format, out);
		image.flush();

		InputStream source = new ByteArrayInputStream(out.toByteArray());

		// 读取图片流
		@SuppressWarnings("rawtypes")
		Iterator readers = ImageIO.getImageReadersByFormatName(format);
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream inputStream = ImageIO.createImageInputStream(source);
		reader.setInput(inputStream, true);
		// 图片参数
		ImageReadParam param = reader.getDefaultReadParam();
		// 100，200是左上起始位置，300就是取宽度为300的，就是从100开始取300宽，就是横向100~400，同理纵向200~350的区域就取高度150
		// Rectangle rect = new Rectangle(100, 200, 300, 150);//
		Rectangle rect = new Rectangle(padding - margin, padding - margin, realSize + 2 * margin, realSize + 2 * margin);
		param.setSourceRegion(rect);

		return reader.read(0, param);
	}

	/**
	 * 生成二维码
	 * 
	 * @param size
	 * @param content
	 * @param stream
	 * @throws Exception
	 */
	public void encode(Integer size, String content, OutputStream stream) throws Exception {
		ImageIO.write(getEncodeImage(size, content, -1), format, stream);
	}

	/**
	 * @param size
	 * @param logoSize
	 * @param content
	 * @param logo
	 * @param stream
	 * @throws Exception
	 */
	public void encodeLogo(Integer size, Integer logoSize, String content, BufferedImage logo, OutputStream stream) throws Exception {
		BufferedImage image = getLogoEncodeImage(350, logoSize, content, Color.white, logo, 2);
		ImageIO.write(image, format, stream);
	}

	public void encodeLogo(Integer size, Integer logoSize, int margin, String content, BufferedImage logo, OutputStream stream) throws Exception {
		BufferedImage image = getLogoEncodeImage(350, logoSize, content, Color.white, logo, margin);
		ImageIO.write(image, format, stream);
	}

	/**
	 * @param size
	 * @param content
	 * @param stream
	 * @param margin
	 *            二维码边框
	 * @throws Exception
	 */
	public void encode(Integer size, String content, OutputStream stream, int margin) throws Exception {
		ImageIO.write(getEncodeImage(size, content, margin), format, stream);
	}
}
