package com.hm.akka.demo;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shishun.wang
 * @date 下午5:05:01 2017年6月19日
 * @version 1.0
 * @describe
 */
public class Test {

	public static void main(String[] args) {
		// 100A080F2821 平台当前时间 2016-10-8 15:47:33
		Date date = new Date(100100801521l);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
		BigInteger srch = new BigInteger("100A080F2821", 16);
		System.out
				.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(Long.parseLong(srch.toString()))));

		DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		format.format(date);

		// System.out.println(new
		// String(StringUtil.parseHexStr2Byte("100A080F2821")));

		// byte[] bytes = HexString2Bytes("17635270928417l");
		// System.out.println(bcd2Str(bytes));

		// char ch = (char) Integer.parseInt("100A080F2821", 16);
		// String str = Integer.toHexString(0xffff & ch);
		// System.out.println(str);

		// System.out.println(String.valueOf());
		test("323031362D30392D3138");
		// test("E7010200804D201608011129AB85484C4F4D32303056313030303138323031362D30392D313805D8FF34343554474306653453494D434F4D5F53494D3830304C00383631343435303334343330333335343630303031343234383735323633383938363030393832323136396465633532363300000000000000000000000000000000000000000C299C000135022EE7");
	}

	private static void test(String input) {
		String regex = "(.{2})";
		input = input.replaceAll(regex, "$1 ");
		// input = input.replaceAll (regex, "AABBCC");
		// System.out.println(input);
		String val = "";
		for (String s : input.split(" ")) {
			val = val + parse((char) Integer.parseInt(s, 16));
			//Integer.parseInt(s, 16);
			 System.out.println(parse((char) Integer.parseInt(s, 16)));
		}
		System.out.println(val);
		// int b = Integer.parseInt(s.replaceAll("^0[x|X]", ""), 16);
		// System.out.println((char)b);

	}

	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	private final static byte[] hex = "100A080F2821".getBytes();

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	// 从字节数组到十六进制字符串转换
	public static String Bytes2HexString(byte[] b) {
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];
		}
		return new String(buff);
	}

	// 从十六进制字符串到字节数组转换
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
}
