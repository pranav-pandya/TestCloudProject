package com.ge.billing.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author mm837463
 *
 */
public final class ReadPropertyData {

	/**
	 * @param filename
	 * @param strKeyName
	 * @return
	 * @throws IOException
	 */
	public static String readFromPropertyFile(String filename,String strKeyName) throws IOException{
		String strValue = "";
		if(null!=filename && null!=strKeyName){
			String strPropertyFilePath = "C:\\Users\\mm837463\\TempWorkSpace\\bfe-scheduler\\src\\main\\resources\\";
			String strPropertyFileAbsoultePath = strPropertyFilePath+filename.trim();
			FileInputStream file = new FileInputStream(strPropertyFileAbsoultePath);
			Properties props=new Properties();
			props.load(file);
			strValue = props.getProperty(strKeyName);
		}
		return strValue;
	}
	
}
