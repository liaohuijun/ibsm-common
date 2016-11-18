package com.hm.common.util;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

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

	/** 背景色 */
	public int BACKGROUND_COLOR = 0xFF000000;
	/** 数据颜色 */
	public int DATA_COLOR = 0xFFFFFFFF;

	public BufferedImage getEncodeImage(Integer size, String content) throws Exception {
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
		return image;
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
		ImageIO.write(getEncodeImage(size, content), format, stream);
	}
}
