package com.hm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hm.common.def.DateUtilCompareDef;

/**
 * @author shishun.wang
 * @date 上午11:47:00 2016年11月18日
 * @version 1.0
 * @describe
 */
public class DateUtil extends CommonUtil {

	private DateUtil(){}
	
	public static String now4yyyyMMddhhmm(){
		return yyyyMMddhhmm(new Date());
	}
	
	public static String now4yyyyMMdd(){
		return yyyyMMdd(new Date());
	}
	
	public static String yyyyMMddhhmm(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	}

	public static String yyyyMMdd(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static Long data2long(Date date) {
		return date.getTime();
	}

	public static Date long2date(long time) {
		return new Date(time);
	}

	/**
	 * 指定日期，在指定日期之间状态判定
	 * 
	 * @param source
	 *            待比较日期
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * 
	 * @return
	 */
	public static DateUtilCompareDef compare(Date source, Date startTime, Date endTime) {
		if (CommonUtil.isAnyEmpty(source, startTime, endTime)) {
			return null;
		}
		if (startTime.getTime() == endTime.getTime()) {
			return DateUtilCompareDef.START_EQUAL_END_TIME;
		}
		if (startTime.getTime() == source.getTime()) {
			return DateUtilCompareDef.EQUAL_START_TIME;
		}
		if (endTime.getTime() == source.getTime()) {
			return DateUtilCompareDef.EQUAL_END_TIME;
		}
		if (source.getTime() < startTime.getTime()) {
			return DateUtilCompareDef.LESS_START_TIME;
		}
		if (endTime.getTime() < source.getTime()) {
			return DateUtilCompareDef.GREATER_END_TIME;
		}
		return DateUtilCompareDef.MIDDLE_TIME;
	}

	/**
	 * 小于
	 * 
	 * @param source
	 * @param date
	 * @return
	 */
	public static boolean less(Date source, Date date) {
		return source.getTime() < date.getTime();
	}

	/**
	 * 等于
	 * 
	 * @param source
	 * @param date
	 * @return
	 */
	public static boolean equal(Date source, Date date) {
		return source.getTime() == date.getTime();
	}

	/**
	 * 大于
	 * 
	 * @param source
	 * @param date
	 * @return
	 */
	public static boolean greater(Date source, Date date) {
		return source.getTime() > date.getTime();
	}

	/**
	 * 比较日期
	 * 
	 * @param source
	 * @param date
	 * @return
	 */
	public static DateUtilCompareDef compare(Date source, Date date) {
		if (less(source, date)) {
			return DateUtilCompareDef.LESS;
		}
		if (greater(source, date)) {
			return DateUtilCompareDef.GREATER;
		}
		return DateUtilCompareDef.EQUAL;
	}

	/**
	 * 判断两个日期相差几天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getBetweenDay(Date date1, Date date2) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(date1);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(date2);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算从source开始之后day天后，日期是
	 * 
	 * @param source
	 * @param day
	 * @return
	 */
	public static Date plusDay(Date source, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 转换日期
	 * 
	 * @param localDate
	 * @param isStart
	 * @return
	 */
	public static Date changeDate(LocalDate localDate, boolean isStart) {
		try {
			String dateStr = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();
			dateStr += isStart ? " 00:00:00" : " 23:59:59";
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (Exception e) {
			return new Date();
		}
	}

	public static Date now() {
		return new Date();
	}

	/**
	 * 获取指定日期前一天
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getCurrenBeforeDay(Date currentDate) {
		return getCurrentCustomerDay(currentDate, -1);
	}

	/**
	 * 获取指定日期后一天
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getCurrenAfterDay(Date currentDate) {
		return getCurrentCustomerDay(currentDate, 1);
	}

	/**
	 * 获取指定日期,前后几天
	 * 
	 * @param currentDate
	 * @param days
	 * @return
	 */
	public static Date getCurrentCustomerDay(Date currentDate, Integer days) {
		if (CommonUtil.isEmpty(currentDate)) {
			currentDate = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 获取某一天
	 * 
	 * @param currentDate
	 * @return
	 * @throws ParseException
	 */
	public static OneDay oneDay(Date currentDate) {
		OneDay oneDay = new OneDay();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			oneDay.setStartTime(dateFormat.parse(format.format(currentDate) + " 00:00:00"));
			oneDay.setEndTime(dateFormat.parse(format.format(currentDate) + " 23:59:59"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return oneDay;
	}

	/**
	 * 获取某一天
	 * 
	 * @param currentDate
	 * @return
	 * @throws ParseException
	 */
	public static OneLocalDate oneLocalDate(Date currentDate) {
		OneDay oneDay = oneDay(currentDate);
		OneLocalDate oneLocalDate = new OneLocalDate();
		{
			ZoneId zone = ZoneId.systemDefault();
			Date startTime = oneDay.getStartTime();
			{
				Instant instant = startTime.toInstant();
				LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
				oneLocalDate.setStartTime(localDateTime.toLocalDate());
			}
			Date endTime = oneDay.getEndTime();
			{
				Instant instant = endTime.toInstant();
				LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
				oneLocalDate.setEndTime(localDateTime.toLocalDate());
			}
		}

		return oneLocalDate;
	}

	public static LocalDate date2LocalDate(Date currentDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = currentDate.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}

	public static Date localdate2date(LocalDate currentDate) {

		return null;
	}

	public static class OneLocalDate {

		private LocalDate startTime;

		private LocalDate endTime;

		public LocalDate getStartTime() {
			return startTime;
		}

		public void setStartTime(LocalDate startTime) {
			this.startTime = startTime;
		}

		public LocalDate getEndTime() {
			return endTime;
		}

		public void setEndTime(LocalDate endTime) {
			this.endTime = endTime;
		}

	}

	public static class OneDay {

		private Date startTime;

		private Date endTime;

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

	}
}
