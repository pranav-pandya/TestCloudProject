package com.ge.billing.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @version 1.0
 * @author Manoj Mangane(#SSO) The DateFormatter class is responsible for all
 *         date related.
 */

public final class StringUtils {

	/**
	 * @param str
	 * @return
	 */
	public static String checkNullString(String str) {
		try {
			if (null != str && str.trim().length() > 0) {
				str = str.replaceAll("\'", "`");
				str = str.replaceAll("&", " and ");
			} else {
				str = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			str = "";
		}
		return str;
	}

	/**
	 * @param str
	 * @return
	 */
	public static String formatInt(String str) {
		if (null != str) {
			str = str.replaceAll("\'", "`");
			str = str.replaceAll("&", " and ");
		} else
			str = "0";
		return str;
	}

	/**
	 * @param str
	 * @return
	 */
	public static String formatDate(String str) {
		if (str.equals("") || null == str)
			str = "00/00/0000";

		return str;
	}

	/**
	 * @param strOrg
	 * @param strReplace
	 * @return
	 */
	public static String formatString(String strOrg, String strReplace) {
		String strReturn = "";
		try {
			if (null != strOrg && strOrg.trim().length() > 0) {
				strReturn = strOrg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			strReturn = strReplace;
		}
		return strReturn;
	}

	/**
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parseDate(String date, String format) {
		DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param str
	 * @return
	 */
	public static String replaceAllWhiteSpaces(String str) {
		if (null != str) {
			str = str.replaceAll(" ","");
		}
		return str;
	}
	
}
