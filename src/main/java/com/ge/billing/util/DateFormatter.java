package com.ge.billing.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author 502692964
 * The DateFormatter class is responsible for all date related operations.
 */

public final class DateFormatter {

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getDay(Date d2) {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getTodaysDateWithStringFormat(String strDateFormat) {
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String addOrSubstractDaysFromDate(Date aDate, int noOfDays,
			String dateFormat) {
		Calendar calendar = Calendar.getInstance(); // get the calendar instance
		calendar.setTime(aDate);// set it to today
		calendar.add(Calendar.DATE, noOfDays);
		Date d1 = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		String dater = formatter.format(d1);
		return dater;
	}

	public static int getDifferenceDays(String strDate1, String strDate2,
			String dateFormat) {
		SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);
		int days = 0;
		try {
			Date date1 = myFormat.parse(strDate1);
			Date date2 = myFormat.parse(strDate2);
			long diff = date1.getTime() - date2.getTime();
			days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			days = 0;
			e.printStackTrace();
		}
		return days;
	}

	public static Timestamp convertStringDateToTimestamp(String strDate,
			String dateFormat) throws ParseException {
		java.sql.Timestamp timeStampDate = null;
		if (null != strDate) {
			DateFormat formatter;
			formatter = new SimpleDateFormat(dateFormat);
			Date date = (Date) formatter.parse(strDate);
			date = removeTimeFromDate(date); //Manoj added this for removing time 1205
			timeStampDate = new Timestamp(date.getTime());
		}
		return timeStampDate;
	}

	public static String chkForValidateDate(String strDate, String dateValue)
			throws ParseException {
		if (null != strDate && strDate.equals(dateValue)) {
			return null;
		}
		return strDate;
	}

	public static java.sql.Date getSqlDate(String dateInString,
			String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try {
			Date date = formatter.parse(dateInString);
			date = removeTimeFromDate(date); //Manoj added this for removing time(hr:min:sec.milliseconds) 1205
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static int getDayFromTimestampDateFormat(Timestamp timestampDate) {
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		return cal.get(Calendar.DATE);
	}
	
	public static Date removeTimeFromDate(Date date) {
		Date returnDate = date;
		try {
			if(null!=date){
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(date);
				gc.set(Calendar.HOUR_OF_DAY, 0);
				gc.set(Calendar.MINUTE, 0);
				gc.set(Calendar.SECOND, 0);
				gc.set(Calendar.MILLISECOND, 0);
				SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
				String strDate = dmyFormat.format(gc.getTime());
				returnDate = dmyFormat.parse(strDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnDate;
	}
	
	public static long getDifferenceInMonthFromTwoDates(Date startDate, Date endDate) {
		long diffInMonths =  0;
		
		try {
			if(null!=startDate && null!=endDate){
				LocalDate lstartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate lendDate = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    diffInMonths = ChronoUnit.MONTHS.between(lstartDate, lendDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return diffInMonths;
	}
	
	
}
