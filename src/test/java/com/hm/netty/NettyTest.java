package com.hm.netty;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author shishun.wang
 * @date 下午3:55:11 2017年6月27日
 * @version 1.0
 * @describe
 */
public class NettyTest {

	@Test
	public void testServer() throws Exception {
		// NettyFactory.SampleServer.start("localhost", 10000, new
		// SampleServerHandler());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		System.out.println(Integer.toHexString(calendar.get(Calendar.YEAR) - 2000));
		System.out.println(Integer.toHexString(calendar.get(Calendar.MONTH)));
		System.out.println(Integer.toHexString(calendar.get(Calendar.DATE)));
		System.out.println(Integer.toHexString(calendar.get(Calendar.HOUR)));
		System.out.println(Integer.toHexString(calendar.get(Calendar.MINUTE)));
		System.out.println(Integer.toHexString(calendar.get(Calendar.SECOND)));

		StringBuffer buffer = new StringBuffer();
		
		String year = Integer.toHexString(calendar.get(Calendar.YEAR));
		buffer.append(year.length() < 2 ? "0" + year : year);
		
		String month = Integer.toHexString(calendar.get(Calendar.MONTH));
		buffer.append(month.length() < 2 ? "0" + month : month);
		
		String day = Integer.toHexString(calendar.get(Calendar.DATE));
		buffer.append(day.length() < 2 ? "0" + day : day);
		
		String hour = Integer.toHexString(calendar.get(Calendar.HOUR));
		buffer.append(hour.length() < 2 ? "0" + hour : hour);
		
		String minute = Integer.toHexString(calendar.get(Calendar.MINUTE));
		buffer.append(minute.length() < 2 ? "0" + minute : minute);
		
		String second = Integer.toHexString(calendar.get(Calendar.SECOND));
		buffer.append(second.length() < 2 ? "0" + second : second);
		
		System.out.println(buffer.toString());
	}
}
