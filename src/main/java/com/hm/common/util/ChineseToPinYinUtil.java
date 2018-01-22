package com.hm.common.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author shishun.wang
 * @date 2018年1月19日 下午12:01:29
 * @version 1.0
 * @describe
 */
@Slf4j
public final class ChineseToPinYinUtil extends CommonUtil {

	private ChineseToPinYinUtil() {
	}

	/**
	 * 中文转拼音
	 * 
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {
		char[] charts = null;
		charts = src.toCharArray();
		String[] strs = new String[charts.length];
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			String pinyin = "";
			int chartsLength = charts.length;
			for (int i = 0; i < chartsLength; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(charts[i]).matches("[\\u4E00-\\u9FA5]+")) {
					strs = PinyinHelper.toHanyuPinyinStringArray(charts[i], format);
					pinyin += strs[0];
				} else
					pinyin += java.lang.Character.toString(charts[i]);
			}
			return pinyin;
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 返回中文的首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer buffer = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			buffer.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getPingYin("点心/蛋糕".replace("/", "_or_")).toUpperCase());
	}
}
