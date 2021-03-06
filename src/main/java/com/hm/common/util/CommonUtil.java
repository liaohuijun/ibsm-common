package com.hm.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author shishun.wang
 * @date 上午11:39:48 2016年11月18日
 * @version 1.0
 * @describe
 */
public abstract class CommonUtil {

	public static final boolean isDigit(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a double
			// value
			Double.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a long
			// value
			Long.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a Integer
			// value
			Integer.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isHexLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Long.parseUnsignedLong(str, 16);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isAnyEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isEmpty(Map<? extends Object, ? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isEmpty(Object value) {
		if (value != null && value instanceof String) {
			return isEmpty((String) value);
		} else if (value != null && value instanceof Collection) {
			return isEmpty((Collection<?>) value);
		} else if (value != null && value instanceof Map) {
			return isEmpty((Map<?, ?>) value);
		}
		return value == null;
	}

	public static final boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	public static final boolean isEmpty(Object[] value) {
		if (value == null || value.length < 1) {
			return true;
		}
		return false;
	}

	public static final boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static final boolean isAllEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (!isEmpty(item)) {
				return false;
			}
		}
		return true;
	}

	public static final boolean isAnyEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T of(T arg0) {
		if (isEmpty(arg0)) {
			return null;
		}
		if (arg0 instanceof String) {
			if (StringUtil.isBlank(arg0.toString())) {
				return null;
			}
			return (T) arg0.toString().trim();
		}
		return arg0;
	}

	/**
	 * 数字字符串转ASCII码字符串
	 * 
	 * @param String
	 *            字符串
	 * @return ASCII字符串
	 */
	public static String stringToAsciiString(String content) {
		String result = "";
		int max = content.length();
		for (int i = 0; i < max; i++) {
			char c = content.charAt(i);
			String b = Integer.toHexString(c);
			result = result + b;
		}
		return result;
	}

	/**
	 * 十六进制转字符串
	 * 
	 * @param hexString
	 *            十六进制字符串
	 * @param encodeType
	 *            编码类型4：Unicode，2：普通编码
	 * @return 字符串
	 */
	public static String hexStringToString(String hexString, int encodeType) {
		String result = "";
		int max = hexString.length() / encodeType;
		for (int i = 0; i < max; i++) {
			char c = (char) hexStringToAlgorism(hexString.substring(i * encodeType, (i + 1) * encodeType));
			result += c;
		}
		return result;
	}

	/**
	 * 十六进制字符串装十进制
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return 十进制数值
	 */
	public static int hexStringToAlgorism(String hex) {
		hex = hex.toUpperCase();
		int max = hex.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = hex.charAt(i - 1);
			int algorism = 0;
			if (c >= '0' && c <= '9') {
				algorism = c - '0';
			} else {
				algorism = c - 55;
			}
			result += Math.pow(16, max - i) * algorism;
		}
		return result;
	}

	/**
	 * 十六转二进制
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return 二进制字符串
	 */
	public static String hexStringToBinary(String hex) {
		hex = hex.toUpperCase();
		String result = "";
		int max = hex.length();
		for (int i = 0; i < max; i++) {
			char c = hex.charAt(i);
			switch (c) {
			case '0':
				result += "0000";
				break;
			case '1':
				result += "0001";
				break;
			case '2':
				result += "0010";
				break;
			case '3':
				result += "0011";
				break;
			case '4':
				result += "0100";
				break;
			case '5':
				result += "0101";
				break;
			case '6':
				result += "0110";
				break;
			case '7':
				result += "0111";
				break;
			case '8':
				result += "1000";
				break;
			case '9':
				result += "1001";
				break;
			case 'A':
				result += "1010";
				break;
			case 'B':
				result += "1011";
				break;
			case 'C':
				result += "1100";
				break;
			case 'D':
				result += "1101";
				break;
			case 'E':
				result += "1110";
				break;
			case 'F':
				result += "1111";
				break;
			}
		}
		return result;
	}

	/**
	 * ASCII码字符串转数字字符串
	 * 
	 * @param String
	 *            ASCII字符串
	 * @return 字符串
	 */
	public static String asciiStringToString(String content) {
		String result = "";
		int length = content.length() / 2;
		for (int i = 0; i < length; i++) {
			String c = content.substring(i * 2, i * 2 + 2);
			int a = hexStringToAlgorism(c);
			char b = (char) a;
			String d = String.valueOf(b);
			result += d;
		}
		return result;
	}

	/**
	 * 将十进制转换为指定长度的十六进制字符串
	 * 
	 * @param algorism
	 *            int 十进制数字
	 * @param maxLength
	 *            int 转换后的十六进制字符串长度
	 * @return String 转换后的十六进制字符串
	 */
	public static String algorismToHEXString(int algorism, int maxLength) {
		String result = "";
		result = Integer.toHexString(algorism);

		if (result.length() % 2 == 1) {
			result = "0" + result;
		}
		return patchHexString(result.toUpperCase(), maxLength);
	}

	/**
	 * 字节数组转为普通字符串（ASCII对应的字符）
	 * 
	 * @param bytearray
	 *            byte[]
	 * @return String
	 */
	public static String bytetoString(byte[] bytearray) {
		String result = "";
		char temp;

		int length = bytearray.length;
		for (int i = 0; i < length; i++) {
			temp = (char) bytearray[i];
			result += temp;
		}
		return result;
	}

	/**
	 * 二进制字符串转十进制
	 * 
	 * @param binary
	 *            二进制字符串
	 * @return 十进制数值
	 */
	public static int binaryToAlgorism(String binary) {
		int max = binary.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = binary.charAt(i - 1);
			int algorism = c - '0';
			result += Math.pow(2, max - i) * algorism;
		}
		return result;
	}

	/**
	 * 十进制转换为十六进制字符串
	 * 
	 * @param algorism
	 *            int 十进制的数字
	 * @return String 对应的十六进制字符串
	 */
	public static String algorismToHEXString(int algorism) {
		String result = "";
		result = Integer.toHexString(algorism);

		if (result.length() % 2 == 1) {
			result = "0" + result;

		}
		result = result.toUpperCase();

		return result;
	}

	/**
	 * HEX字符串前补0，主要用于长度位数不足。
	 * 
	 * @param str
	 *            String 需要补充长度的十六进制字符串
	 * @param maxLength
	 *            int 补充后十六进制字符串的长度
	 * @return 补充结果
	 */
	static public String patchHexString(String str, int maxLength) {
		String temp = "";
		for (int i = 0; i < maxLength - str.length(); i++) {
			temp = "0" + temp;
		}
		str = (temp + str).substring(0, maxLength);
		return str;
	}

	/**
	 * 将一个字符串转换为int
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @param defaultInt
	 *            int 如果出现异常,默认返回的数字
	 * @param radix
	 *            int 要转换的字符串是什么进制的,如16 8 10.
	 * @return int 转换后的数字
	 */
	public static int parseToInt(String s, int defaultInt, int radix) {
		int i = 0;
		try {
			i = Integer.parseInt(s, radix);
		} catch (NumberFormatException ex) {
			i = defaultInt;
		}
		return i;
	}

	/**
	 * 将一个十进制形式的数字字符串转换为int
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @param defaultInt
	 *            int 如果出现异常,默认返回的数字
	 * @return int 转换后的数字
	 */
	public static int parseToInt(String s, int defaultInt) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = defaultInt;
		}
		return i;
	}

	/**
	 * 十六进制字符串转为Byte数组,每两个十六进制字符转为一个Byte
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return byte 转换结果
	 */
	public static byte[] hexStringToByte(String hex) {
		int max = hex.length() / 2;
		byte[] bytes = new byte[max];
		String binarys = hexStringToBinary(hex);
		for (int i = 0; i < max; i++) {
			bytes[i] = (byte) binaryToAlgorism(binarys.substring(i * 8 + 1, (i + 1) * 8));
			if (binarys.charAt(8 * i) == '1') {
				bytes[i] = (byte) (0 - bytes[i]);
			}
		}
		return bytes;
	}

	/**
	 * 十六进制串转化为byte数组
	 * 
	 * @return the array of byte
	 */
	public static final byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	/**
	 * 字节数组转换为十六进制字符串
	 * 
	 * @param b
	 *            byte[] 需要转换的字节数组
	 * @return String 十六进制字符串
	 */
	public static final String byte2hex(byte b[]) {
		if (b == null) {
			throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
		}
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 16进制转ASCII
	 * 
	 * @param hex
	 * @return
	 */
	public static final String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}
		return sb.toString();
	}

	/**
	 * 16进制转10进制数组
	 * 
	 * @param u16
	 * @return
	 */
	public static int[] convertHexToArray(String u16) {
		String regex = "(.{2})";
		u16 = u16.replaceAll(regex, "$1 ");
		String[] u16s = u16.split("\\s+");
		int[] content = new int[u16s.length];
		for (int i = 0; i < u16s.length; i++) {
			content[i] = Integer.parseInt(u16s[i], 16);
		}
		return content;
	}

	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param String
	 *            str 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */
	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;

		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param String
	 *            str Byte字符串(Byte之间无分隔符 如:[616C6B])
	 * @return String 对应的字符串
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param byte[]
	 *            b byte数组
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
		}
		return sb.toString().toUpperCase().trim();
	}

	/**
	 * bytes字符串转换为Byte值
	 * 
	 * @param String
	 *            src Byte字符串，每个Byte之间没有分隔符
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		System.out.println(l);
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
		}
		return ret;
	}

	/**
	 * String的字符串转换成unicode的String
	 * 
	 * @param String
	 *            strText 全角字符串
	 * @return String 每个unicode之间无分隔符
	 * @throws Exception
	 */
	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u" + strHex);
			else // 低位在前面补00
				str.append("\\u00" + strHex);
		}
		return str.toString();
	}

	/**
	 * unicode的String转换成String的字符串
	 * 
	 * @param String
	 *            hex 16进制值字符串 （一个unicode为2byte）
	 * @return String 全角字符串
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 16进制字符串异或计算
	 * @param content
	 * @return
	 */
	public static String hexToYiHuo(String content) {
		content = hexToYiHuoChange(content);
		String[] b = content.split(" ");
		int a = 0;
		for (int i = 0; i < b.length; i++) {
			a = a ^ Integer.parseInt(b[i], 16);
		}
		if (a < 10) {
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			sb.append(a);
			return sb.toString();
		}

		String value = Integer.toHexString(a);

		return (value.length() != 2) ? ("0" + value) : value;
	}

	private static String hexToYiHuoChange(String content) {
		String str = "";
		for (int i = 0; i < content.length(); i++) {
			if (i % 2 == 0) {
				str += " " + content.substring(i, i + 1);
			} else {
				str += content.substring(i, i + 1);
			}
		}
		return str.trim();
	}
}
