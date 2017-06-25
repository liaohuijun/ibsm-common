package com.hm.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shishun.wang
 * @date 上午10:01:11 2017年6月25日
 * @version 1.0
 * @describe
 */
public final class MoneyUtil {

	private final static Logger logger = LoggerFactory.getLogger(MoneyUtil.class);

	private MoneyUtil() {

	}

	/**
	 * 分转换为元.
	 *
	 * @param fen
	 *            分
	 * @return 元
	 */
	public static String fromFenToYuan(final String fen) {
		String yuan = "";
		final int MULTIPLIER = 100;
		Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
		Matcher matcher = pattern.matcher(fen);
		if (matcher.matches()) {
			yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
		}
		return yuan;
	}

	/**
	 * 元转换为分.
	 *
	 * @param yuan
	 *            元
	 * @return 分
	 */
	public static String fromYuanToFen(final String yuan) {
		String fen = "";
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");
		Matcher matcher = pattern.matcher(yuan);
		if (matcher.matches()) {
			try {
				NumberFormat format = NumberFormat.getInstance();
				Number number = format.parse(yuan);
				double temp = number.doubleValue() * 100.0;
				// 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
				format.setGroupingUsed(false);
				// 设置返回数的小数部分所允许的最大位数
				format.setMaximumFractionDigits(0);
				fen = format.format(temp);
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return fen;
	}

	public static String centToYuan(int cent) {
		if (cent <= 0) {
			return "0.00";
		} else {
			int yuan = cent / 100;
			int mod = cent % 100;
			int jiao = mod / 10;
			int fen = mod % 10;
			return yuan + "." + jiao + "" + fen;
		}
	}

	public static String centToYuan(Long cent) {
		if (cent != null && cent.longValue() > 0L) {
			long yuan = cent.longValue() / 100L;
			long mod = cent.longValue() % 100L;
			long jiao = mod / 10L;
			long fen = mod % 10L;
			return yuan + "." + jiao + "" + fen;
		} else {
			return "0.00";
		}
	}

	public static long yuanToCent(Double v) {
		BigDecimal bd = BigDecimal.valueOf(v.doubleValue());
		BigDecimal h = new BigDecimal(100);
		BigDecimal b = bd.multiply(h);
		return b.longValue();
	}

	public static long yuanToCent(String v) {
		if (v != null && v.length() > 0) {
			try {
				Double e = Double.valueOf(Double.parseDouble(v));
				return yuanToCent(e);
			} catch (Exception var2) {
				return 0L;
			}
		} else {
			return 0L;
		}
	}
}
