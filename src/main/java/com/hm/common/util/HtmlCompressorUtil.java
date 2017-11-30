package com.hm.common.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.googlecode.htmlcompressor.compressor.XmlCompressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;
import com.googlecode.htmlcompressor.compressor.YuiJavaScriptCompressor;

/**
 * @author shishun.wang
 * @date 2017年11月30日 下午6:12:35
 * @version 1.0
 * @describe 静态文件压缩
 */
public final class HtmlCompressorUtil extends CommonUtil {

	private static Logger log = LoggerFactory.getLogger(HtmlCompressorUtil.class);

	private HtmlCompressorUtil() {
	}

	public static String html(String filePath) {
		try {
			HtmlCompressor compressor = new HtmlCompressor();
			compressor.setCompressCss(true);
			compressor.setCompressJavaScript(true);
			return compressor.compress(FileUtil.read(filePath));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static String css(String filePath) {
		try {
			YuiCssCompressor compressor = new YuiCssCompressor();
			return compressor.compress(FileUtil.read(filePath));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static String xml(String filePath) {
		try {
			XmlCompressor compressor = new XmlCompressor();
			compressor.isRemoveComments();
			return compressor.compress(FileUtil.read(filePath));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static String javascript(String filePath) {
		try {
			YuiJavaScriptCompressor compressor = new YuiJavaScriptCompressor();
			return compressor.compress(FileUtil.read(filePath));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
