package com.ge.billing.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.ge.billing.dto.ContractDTO;
import com.ge.billing.dto.InvoiceDTO;
/**
 * Billing Utility for BFE 
 * @author VL823275
 *
 */

public final class BillingUtility {
	
	/**
	 * Method to read the data from the Properties file
	 * @param filename - It is smax.properties filename
	 * @param strKeyName - Input to the smax.properties Like 1stofMonth,1,...etc
	 * @return - It will return the corresponding value.
	 * @throws IOException
	 */
	public static String readFromPropertyFile(String filename,String strKeyName) throws IOException{
		String str = "";
		if(null!=filename && null!=strKeyName){
			String strPropertyFileName = filename.trim();
			FileInputStream file = new FileInputStream(strPropertyFileName);
			Properties props=new Properties();
			props.load(file);
			str = props.getProperty(strKeyName);	
			//System.out.println("Reading from propertyfile::"+str);
		}
		return str;
	}
	
	/**
	 * Method to get the value from the properties file
	 * @param invoiceCycleStart - InvoiceCycleStart Like::1stofMonth,5thofQuarter,...etc
	 * @param readingFromApplication - smax.properties file
	 * @return - It will return the corresponding value
	 */
	public static int getDay(String invoiceCycleStart, String readingFromApplication){	
		int day = 0;
		invoiceCycleStart= StringUtils.replaceAllWhiteSpaces(invoiceCycleStart);
		if(invoiceCycleStart!=null && readingFromApplication!=null){
			try{
				String strPropertyValue = readFromPropertyFile(readingFromApplication,invoiceCycleStart);
				if(null!=strPropertyValue){
					day=Integer.parseInt(strPropertyValue);
				}
			}
			catch(Exception e){
				System.out.println("exception"+e);
			}
		}
		return day;
	}	
	
	/**
	 * Method to get the proper cycle start date
	 * @param agreementStartDate - It can be any Timestamp. 
	 * @param invoiceCycleStart - Like 1stofMonth,5thofQuarter...etc
	 * @param readingFromApplication - smax.properties File
	 * @return - It will retrun the proper cycle start date from the Agreement start date.
	 */
	public static Timestamp getProperCycleStartDate(Timestamp agreementStartDate, String invoiceCycleStart, String readingFromApplication){
		int day = getDay(invoiceCycleStart, readingFromApplication);
		String strDateFormat = "yyyy-MM-dd 00:00:00.0"; //1205 added
		Timestamp properCycleStartDate=null;
		if(agreementStartDate!=null){
			SimpleDateFormat formatter = new SimpleDateFormat(strDateFormat);
			try{	
				int agreementStartDateDay = 0;
				agreementStartDateDay = getDayFromTimestampDateFormat(agreementStartDate);
				if(day==agreementStartDateDay){ //Proper dates formats for example agsd = 10/10/2016 getDay : 10
					properCycleStartDate = agreementStartDate;
				}else if(agreementStartDateDay>day){ //For exmale agreementStartDate: 10/10/2016: 15 and day : 5
					//1205 removing hrs SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
					Date agreementDate = formatter.parse(""+agreementStartDate);
					properCycleStartDate = convertStringDateToTimestamp(addOrSubstractDaysFromDate(agreementDate,-(agreementStartDateDay-day),"MM/dd/yyyy"),"MM/dd/yyyy");
				}else if(agreementStartDateDay<day){//For exmale agreementStartDate: 10/02/2016: 2 and day : 5
					//1205 removing hrs SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
					Date d1 = formatter.parse(""+agreementStartDate);
					properCycleStartDate = convertStringDateToTimestamp(addOrSubstractDaysFromDate(d1, day-agreementStartDateDay,"MM/dd/yyyy"),"MM/dd/yyyy");
				}
			}catch(Exception e){
				System.out.println("agreementStartDateDay exception"+e);
			}
		}
		return properCycleStartDate;		
	}
	
	/**
	 * Method to get the Day from the timestampDate
	 * @param - timestampDate - It can be any Timestamp 
	 * @return - It will return the Day from the Timestamp.
	 */
	public static int getDayFromTimestampDateFormat(Timestamp timestampDate){
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * Method to convert from StringDate to Timestamp
	 * @param strDate - Date which we need to convert
	 * @param dateFormat - Format can be Like::"yyyy-MM-dd hh:mm:ss.sss"
	 * @return - It will return the timestamp
	 * @throws ParseException
	 */
	public static Timestamp convertStringDateToTimestamp(String strDate, String dateFormat) throws ParseException{
		DateFormat formatter;
		formatter = new SimpleDateFormat(dateFormat);
		Date date = (Date) formatter.parse(strDate);
		java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
		return timeStampDate;
	}
	
	/**
	 * Method to add or subtract days from the Date
	 * @param aDate - Input as a DATE
	 * @param noOfDays - Number of days to be added to the Date
	 * @param dateFormat - Dateformat can be "MM/dd/yyyy"
	 * @return - It will return Date.
	 */
	public static String addOrSubstractDaysFromDate(Date aDate, int noOfDays, String dateFormat) {
		Calendar calendar = Calendar.getInstance(); // get the calendar instance
		calendar.setTime(aDate);// set it to today
		// start:Manoj Added for removing time: 1205
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//END: Manoj Added for removing time: 1205
		calendar.add(Calendar.DATE, noOfDays);
		Date d1 = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		String dater = formatter.format(d1);
		return dater;
	}
	
	/**
	 * Method to get the timestamp based on the days 
	 * @param days - It can be 1 to 31 Eg:- 15
	 * @param timestamp - Timestamp //2016-12-01 00:00:00.000"
	 * @return - 2016-12-15 00:00:00.000
	 */
	public static Timestamp getReqDate(int days,Timestamp timestamp) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(timestamp);
		// start:Manoj Added for removing time: 1205
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//END: Manoj Added for removing time: 1205
		calendar.set(Calendar.DATE, days);
		Date dateToLookBackAfter = calendar.getTime();
		return new Timestamp(dateToLookBackAfter.getTime());
	} 

		/**
		 * Method to get the Timestamp based on the Month 
		 * @param nextInvoiceDate - Eg: 2016-12-01 00:00:00.000
		 * @param month - It can be 1 to 12 Eg:-05
		 * @return - 2016-05-01 00:00:00.000
		 */
		public static Timestamp getReqMonth(Timestamp nextInvoiceDate, int month) {	
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(nextInvoiceDate);
			calendar.add(Calendar.MONTH, month);
			Date dateToLookBackAfter = calendar.getTime();
			return new Timestamp(dateToLookBackAfter.getTime());
	} 
		
		/**
		 * Method to get the Timestamp based on the Day
		 * @param nextInvoiceDate - It can be "2016-12-01 00:00:00.000"
		 * @param day - Day can be 1 to 31;
		 * @return - it will set the day and return.
		 */
		public static Timestamp setReqDay(Timestamp nextInvoiceDate, int day) {	
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(nextInvoiceDate);
			//calendar.set(Calendar.DATE, days); //TODO
			// start:Manoj Added for removing time: 1205
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			//END: Manoj Added for removing time: 1205
			calendar.add(Calendar.DATE, day);
			Date dateToLookBackAfter = calendar.getTime();
			return new Timestamp(dateToLookBackAfter.getTime());
		} 
	
	/**
	 * Method to get the month from the BillingFrequence
	 * @param billingFrequency - It can be like Monthly,Quarterly,SemiAnnual etc..
	 * @param readingFromApplication - smax.properties
	 * @return - It can return either 1 or 3 or 6 or 12
	 */
	public static int getMonth(String billingFrequency, String readingFromApplication){
		int getMonthValue=0;
		if(billingFrequency!=null && readingFromApplication!=null){
			try{
				getMonthValue = getDay(billingFrequency, readingFromApplication);
			}
			catch(Exception e){
				System.out.println("exception"+e);		
			}
		}
		return getMonthValue;
	}
	/**
	 * Method to get days from the differences between two dates
	 * @param strDate1 - String Date1
	 * @param strDate2 - String Date2
	 * @param dateFormat - yyyy-MM-dd
	 * @return - It will return number of Days.
	 */
	public static int getDifferenceDays(String strDate1, String strDate2, String dateFormat) {
		SimpleDateFormat myFormat = new SimpleDateFormat(dateFormat);
		int days = 0;
		try {
		    Date date1 = myFormat.parse(strDate1);
		    Date date2 = myFormat.parse(strDate2);
		    long diff = date1.getTime() - date2.getTime();
		    days = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    //System.out.println ("getDifferenceDays: " +days);
		} catch (ParseException e) {
			days = 0;
		    e.printStackTrace();
		}
		return days;
	}

    /**
   * Method to get the month from the Timestamp
   * @param timestampDate - Timestamp
   * @return- it will return month based on the Timestamp
   */
  public static int getMonthFromTimestamp(Timestamp timestampDate){
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);		
		return cal.get(Calendar.MONTH)+1;//for months need to be added +1(bcoz its starts with 0);
	}
	   
	/**
	 * Method to get the endday of the month based on the TimestampDate
	 * @param timestampDate - Timestamp
	 * @return - it will return lastday of the month
	 */
	public static Timestamp getEndDayofMonth(Timestamp timestampDate){
	//java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2016-09-16 10:10:10.0");
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));	 
		Date getTime= cal.getTime();
	 return new Timestamp(getTime.getTime());
	}

	/**
	 * Method to get the first day of the month based on the timestampDate
	 * @param timestampDate - Timestamp
	 * @return - It will return first day of the month
	 */
	public static Timestamp getFirstDayofMonth(Timestamp timestampDate){
		//java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2016-09-16 10:10:10.0");
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date getTime=cal.getTime();
		return new Timestamp(getTime.getTime());
	}

	/**
	 * method to get the rounded value of the amount
	 * @param value - Double value
	 * @param places - to specify how many digits need to be there
	 * @return 
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/**
	 * @param str
	 * @param maxStringLength
	 * @return
	 */
	public static String generateInvoiceNumber(String str, int maxStringLength) {
		String temp = "";
		if(null!=str){
			if(str.length()<maxStringLength){
				for(int i=0; i<maxStringLength-str.length(); i++){
					 temp=temp+"0";
				}
				str = temp+str;
			}
		}
		return str;
	}	
/**
* Method to subtract the date from the days
* @param date - Timestamp
* @param day - it can be any day
* @return - Timestamp
*/

	/**
	 * Method to get the newNextInvoiceDate list 
	 * @param agreementStartDate -  String Date
	 * @param nextInvoiceDate - Timestamp
	 * @param agreementEndDate - String Date
	 * @param invoiceCycleStart - It can be Like 1stofMonth,5thofMonth..etc
	 * @param billingFrequency - It can be like Monthly,Quarterly... etc
	 * @param readingFromApplication - smax.properties
	 * @return - List<String> - newNextInvoiceDates
	 * @throws ParseException
	 */
	public static List<String> getNewNextInvoiceDateList(String agreementStartDate, Timestamp nextInvoiceDate, String agreementEndDate, 
		String invoiceCycleStart, String billingFrequency, String readingFromApplication ) throws ParseException{
		List<String> newNextInvoiceList=new ArrayList<String>();
		String strDateFormatWithOutTime = "yyyy-MM-dd 00:00:00.0"; //1205 added
		if(getDifferenceDays(agreementEndDate,agreementStartDate,"yyyy-MM-dd")>0){
			int day = getDay(invoiceCycleStart, readingFromApplication);
			int month = getMonth(billingFrequency, readingFromApplication);
			System.out.println("From getNewNextInvoiceDateList method Day::"+day);
			System.out.println("Month::"+month);
			if(null!=nextInvoiceDate){			
				nextInvoiceDate = getReqDate(day,nextInvoiceDate);
				newNextInvoiceList = getNewNextInvoiceDate(nextInvoiceDate, month);			
				Date startDate = nextInvoiceDate;		
				
				//MAnoj Commented on 1205: Replacing with generic code
				/*Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(startDate);						
				Date currentDate = new Date();
				Calendar currentCalendar = new GregorianCalendar();
				currentCalendar.setTime(currentDate);
				
				LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				//1205 Manoj Commented Below LOC to remove hrs
			    //LocalDate currntDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate currntDate = Instant.ofEpochMilli(DateFormatter.removeTimeFromDate(new Date()).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currntDate);*/
				
				long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"));
				System.out.println("difference b/w two dates:"+diffInMonths);
				
				Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
    			String getReqMonth = null;
				//1205 for(int i=0;i<=diffInMonths;i++){ <---------------Needs to think
    			for(int i=0;i<=diffInMonths;i++){
					try {										
						Timestamp getReqMonth1 = getReqMonth(getProperCyclestartDate, month);
						//getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1); //1205
						getReqMonth = new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth1);
						getProperCyclestartDate = getReqMonth1;					
						newNextInvoiceList.add(getReqMonth);									
					} catch (Exception e) {
						System.out.println("exception" + e);
					}
				}
				//END: MAnoj added
			}		
			 else {// nextInvoiceDate == null
					// nextInvoiceDate = agreementStartDate;
					// getNewNextInvoiceDate(agreementStartDate);
				if(null==nextInvoiceDate){
					//nextInvoiceDate = convertStringDateToTimestamp(agreementStartDate,"yyyy-MM-dd hh:mm:ss.sss"); //1205
					nextInvoiceDate = convertStringDateToTimestamp(agreementStartDate, strDateFormatWithOutTime);
					nextInvoiceDate=getReqDate(day,nextInvoiceDate);
					if (nextInvoiceDate != null) {
						
						if (month == 1) {   //for Monthly Biling freqency 							
							Date startDate = nextInvoiceDate;													  
							long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"));
							System.out.println("difference b/w two dates:"+diffInMonths);
							Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
							String getReqMonth = null;
						
							for(int i=0;i<=diffInMonths;i++){
								try {										
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									//getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1); //1205
									getReqMonth = new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;					
									newNextInvoiceList.add(getReqMonth);									
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						}
						 else if (month == 3) {//for querterly Biling freqency	
						   Date startDate = nextInvoiceDate;
						   long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"));
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/3;i++){
								try {
									Timestamp getReqMonth1 = getReqMonth(getProperCyclestartDate, month);
									//getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1); //1205
									getReqMonth = new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;																	
									newNextInvoiceList.add(getReqMonth);									
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						} else if (month == 6) {//for semiannual Billing freqency	
						   Date startDate = nextInvoiceDate;	
						   long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"));
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/6;i++){
								try {
									Timestamp getReqMonth1 = getReqMonth(getProperCyclestartDate, month);
									//getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1); //1205
									getReqMonth = new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;									
									newNextInvoiceList.add(getReqMonth);													
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						}else {//for annual Billing freqency	
						   Date startDate = nextInvoiceDate;					
					   long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"));
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/12;i++){
								try {
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									//getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1); //1205
									getReqMonth = new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth1);									
									getProperCyclestartDate = getReqMonth1;									
									newNextInvoiceList.add(getReqMonth);									
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							 }
						}	 
			        }
		         }
		    }
		}
		return newNextInvoiceList;
	}		

	/**
	 * Method to get the newNextInvoiceDate based on the Month
	 * @param nextInvoiceDate - Timestamp
	 * @param month - it can be 1 to 12.
	 * @return - Timestamp
	 */
	public static ArrayList<String> getNewNextInvoiceDate(Timestamp nextInvoiceDate,int month){
		ArrayList<String> list1 = new ArrayList<String>();
		String newNextInvoiceDate=null;
		try{
			//newNextInvoiceDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth(nextInvoiceDate, month));
			//1205: Manoj remove above hrs, mins and sec, millisec from above loc and in below added to 00 respectively.
			newNextInvoiceDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(getReqMonth(nextInvoiceDate, month));
			list1.add(newNextInvoiceDate);
			//System.out.println("getMonthBillingFreq"+newNextInvoiceDate);
		}
		catch(Exception e){
			System.out.println("exception"+e);		
		}
		return list1;
	}
	
	/**
	 * This is the Generic method to set the values in header level for each line item
	 * @param contractDTO - ContractDTO attributes
	 * @param generateInvoiceDTO -InvoiceDTO attributes
	 */
	public static void setInvoiceHeaderData(ContractDTO contractDTO, InvoiceDTO generateInvoiceDTO){
		try {
			generateInvoiceDTO.setAgreementNum(contractDTO.getAgreementNum());     			
			generateInvoiceDTO.setAgreementType(contractDTO.getAgreementType());
			generateInvoiceDTO.setAgreementItem(contractDTO.getAgreementName());
			generateInvoiceDTO.setBillingSchedule(contractDTO.getBillingSchedule());
			generateInvoiceDTO.setBillingFrequence(contractDTO.getBillingFrequency());     		
		//	generateInvoiceDTO.setInvoiceDate(contractDTO.getStartDt());
		//	generateInvoiceDTO.setTransactionType(contractDTO.get);    			
			generateInvoiceDTO.setBillToAccountName(contractDTO.getBillToAcct());
			generateInvoiceDTO.setBillToCustomerNum(contractDTO.getBillToAcctNum());
			generateInvoiceDTO.setBillToSiteNum(contractDTO.getBillToSiteNum());
			generateInvoiceDTO.setShipToAccountName(contractDTO.getShipToAcct());
			generateInvoiceDTO.setShipToCustomerNum(contractDTO.getShipToAcctNum());;
			generateInvoiceDTO.setShipToSiteNum(contractDTO.getShipToSiteNum());;
			generateInvoiceDTO.setPoNum(contractDTO.getPoNum());
			generateInvoiceDTO.setPoExpirationDate(contractDTO.getPoExpirationDt());
			generateInvoiceDTO.setPoRequired(contractDTO.getPoRequired());
			generateInvoiceDTO.setAssetNum(contractDTO.getAssetNum());
			//added on 11/18/2016
			generateInvoiceDTO.setAgreementLineItemid(contractDTO.getLineItemId());
			generateInvoiceDTO.setRcAmount(contractDTO.getRcAmount());
			generateInvoiceDTO.setMrcAmount(contractDTO.getMrcAmount());
			//added on 11/19/2016
			generateInvoiceDTO.setInvoiceCycleStart(contractDTO.getInvoiceCycleStart());
			generateInvoiceDTO.setCurrency(contractDTO.getCurrency());
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to generate the list of invoice line items
	 * @param contractDTO - ContractDTO class 
	 * @return - List<InvoiceDTO>
	 * @throws ParseException
	 */
	public static List<InvoiceDTO> getInvoiceList(ContractDTO contractDTO, String readingFromApplication) throws ParseException{
	  	List<InvoiceDTO> invoiceDtoList=new ArrayList<InvoiceDTO>();
	  	try {
			invoiceDtoList = getInvoiceLineItemDateList(contractDTO, readingFromApplication);
		} catch (Exception e) {
			// TODO: handle exception
		}
      return invoiceDtoList;
	}
	
	/**
* Method to subtract the date from the days
* @param date - Timestamp
* @param day - it can be any day
* @return - Timestamp
*/			
	public static Timestamp subtractDays(Timestamp date, int day) {
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		// start:Manoj Added for removing time: 12005
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//END: Manoj Added for removing time: 1205
		cal.add(Calendar.DATE, -day);	
		Date subDate = cal.getTime();
		return new Timestamp(subDate.getTime()); 
	}
	
		/**
	 * Method to get the newNextInvoiceDate based on proper cycle start date(nextInvoiceDate)
	 * @param nextInvoiceDate - TimeStamp
	 * @param month - 1 to 12
	 * @return - Timestamp	 */
	 
	public static Timestamp getNewNextInvoiceDateString(Timestamp nextInvoiceDate,int month){
		String newNextInvoiceDate=null;
		Timestamp newNextInvDate = null;
		String strDateFormatWithOutTime = "yyyy-MM-dd 00:00:00.0"; //1205 added
		try{
			//newNextInvoiceDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth(nextInvoiceDate, month)); //1205
			newNextInvoiceDate= new SimpleDateFormat(strDateFormatWithOutTime).format(getReqMonth(nextInvoiceDate, month));
			newNextInvDate = convertStringDateToTimestamp(newNextInvoiceDate, strDateFormatWithOutTime);
		}
		catch(Exception e){
			System.out.println("exception"+e);		
		}
		return newNextInvDate;
	}
	
	/**
	 * Method to get the list of line items
	 * @param contractDTO -ContractDTO type
	 * @param strReadingFromApplication - smax.properties
	 * @return - List<InvoiceDTO>
	 * @throws ParseException
	 */
	public static List<InvoiceDTO> getInvoiceLineItemDateList(ContractDTO contractDTO, String readingFromApplication) throws ParseException{
		  
	    List<InvoiceDTO> invoiceLineItmList = new ArrayList<InvoiceDTO>(); 
	  
	    Timestamp rcStartDate = contractDTO.getRcStartDt();		       
	    String chargeEndDate = contractDTO.getChargeEndDt().toString();
  		String invoiceCycleStart = StringUtils.replaceAllWhiteSpaces(contractDTO.getInvoiceCycleStart());
  		String billingFrequency = StringUtils.replaceAllWhiteSpaces(contractDTO.getBillingFrequency());
  		String strDateFormatWithOutTime = "yyyy-MM-dd 00:00:00.0"; //1205 added
  		//String rcStrtDate=(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(rcStartDate));//1201 ??????? hrs added  in thisssss???2016-11-01 12:00:00.000
  		String rcStrtDate = (new SimpleDateFormat(strDateFormatWithOutTime).format(rcStartDate));//1201
  		//String rcStrtDate = ""+rcStartDate;
  		
		if(getDifferenceDays(chargeEndDate,rcStrtDate,"yyyy-MM-dd")>=0 ){
	
	  		int day = getDay(invoiceCycleStart, readingFromApplication);
	  		int month = getMonth(billingFrequency, readingFromApplication);
	  		
	  		//check if rcStartDate is null, then set to lineitemstartdate
	  		if(rcStartDate==null)
	  		{
	  			rcStartDate = contractDTO.getLineStartDt();
	  		}
	  		if(null!=rcStartDate){     		
	  			
	  			InvoiceDTO invDTO = new InvoiceDTO();
	  		    setInvoiceHeaderData(contractDTO, invDTO);	  			     	  		  			
	  			Timestamp propCycleStDate = getReqDate(day,rcStartDate);//it will give the propercyclestartdate
	  			System.out.println("propCycleStDate"+propCycleStDate);
	  				Date startDate = rcStartDate;                    
	  				Calendar startCalendar = new GregorianCalendar();
	  				startCalendar.setTime(startDate);	  				
	  				LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  				LocalDate currntDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  				
	  				long diffInMonths = DateFormatter.getDifferenceInMonthFromTwoDates(startDate, convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0")); //1205
	  				System.out.println("difference b/w two dates::::"+diffInMonths);
	  				
	  				//Timestamp currDate = new Timestamp(System.currentTimeMillis()); //1201 taking hrs as well
	  				Timestamp currDate = convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0"); //1205 replacing with this
	  				//Timestamp currDate = convertStringDateToTimestamp(com.ge.billing.util.DateFormatter.getTodaysDateWithStringFormat("yyyy-MM-dd")+ " 00:00:00.0","yyyy-MM-dd hh:mm:ss.sss");  				
	  				//System.out.println("MANOJ CHECK HERERERE E %%%%%%%%%%%%%%%%%%%%%%% currDate ..." + currDate);
	  				
	  				Timestamp properCyclestartDate = null;
	  				properCyclestartDate = getProperCycleStartDate(rcStartDate, invoiceCycleStart,readingFromApplication);//not req
	  				System.out.println("properCyclestartDate ..." + properCyclestartDate);
	  				
	  				Timestamp newNextInvDate = getNewNextInvoiceDateString(properCyclestartDate,month);
	  				System.out.println("newNextInvDate ..." + newNextInvDate);
	
	  				//Timestamp chargEndDate = convertStringDateToTimestamp(chargeEndDate,"yyyy-MM-dd hh:mm:ss.sss"); //1205 
	  				Timestamp chargEndDate = convertStringDateToTimestamp(chargeEndDate, strDateFormatWithOutTime);
	  				System.out.println("chargEndDate ..." + chargEndDate);
	  				
	  				//int dayDiff =  getDifferenceDays(chargeEndDate+"", rcStartDate+"", "yyyy-MM-dd hh:mm:ss.sss"); //1205
	  				int dayDiff =  getDifferenceDays(chargeEndDate+"", rcStartDate+"", strDateFormatWithOutTime);
	  				System.out.println("dayDiff b/w two dates::::"+dayDiff);
	  				
	  				boolean flag = false;     				
	  				long newNextInvDatelng = newNextInvDate.getTime();
	  				//System.out.println("newNextInvDatelng----" + newNextInvDatelng);
	  				
	  				long currDatelng = currDate.getTime();
	  				//System.out.println("currDatelng----" + currDatelng);
	  				
	  				long chgEndDatelng = chargEndDate.getTime();
	  				//System.out.println("chgEndDatelng----" + chgEndDatelng);
	  				
	  				long prCyDatelng = properCyclestartDate.getTime();
	  				//System.out.println("prCyDatelng----" + prCyDatelng);
	  				
	  				long rcStDatelng = rcStartDate.getTime();
	  				//System.out.println("rcStDatelng----" + rcStDatelng);
	  				
	  				//Manoj Added
	  				if(rcStDatelng>currDatelng){ //rcStartDate : 12/01/2016
	  					properCyclestartDate = getProperCycleStartDate(currDate, invoiceCycleStart,readingFromApplication);
	  					System.out.println("MAnoj properCyclestartDate:"+properCyclestartDate);
	  					prCyDatelng = properCyclestartDate.getTime(); //1201	  					
		  				newNextInvDate = getNewNextInvoiceDateString(properCyclestartDate,month);
		  				newNextInvDatelng = newNextInvDate.getTime();
		  				System.out.println("MAnoj newNextInvDate ..." + newNextInvDate);
		  				
	  				}
	  				
	  				
	  				Timestamp invEndDate=null;
	  				Timestamp invStDate=null;
	  				
	  				//InvoiceDTO invDTO = new InvoiceDTO();
	  				setInvoiceHeaderData(contractDTO, invDTO);
	  				InvoiceDTO invDTO1 = new InvoiceDTO();
	  				
	  				boolean sameDateFlag = false;
	  				long sameDatelng;
	  				long invstdatelng;
		  			long invenddatelng;
		  			String fullCycleFlag="N";
		  			boolean startDateFlag = false;  //2911
		  				
		  			try{
		  				
		  				if( newNextInvDatelng >= currDatelng ){	
		  					
			  				if(rcStDatelng >= prCyDatelng){     //  pcdate:10/1/2016  ---   rcdate:10/5/2016   1stofMonth, monthly
			  					invStDate = rcStartDate;
			  				    invDTO.setInvoiceStartDate(invStDate);
			  				    
			  				    fullCycleFlag="N";
			  				    
		  						if (rcStDatelng == prCyDatelng){
		  							//System.out.println("*******************************"+rcStDatelng+"****"+prCyDatelng);
				  				    fullCycleFlag="Y";
				  				    startDateFlag = true; //2911
		  						}		  				
			  				}
			  				else   //  pcdate:10/5/2016  ---   rcdate:10/1/2016   5thofMonth, monthly
			  				{
				  				System.out.println("*** Entered diff scenario********");
			  					invStDate = rcStartDate;
			  					invEndDate = subtractDays(properCyclestartDate, 1);
			  					
			  					long invEndDatelng = invEndDate.getTime();		
			  					fullCycleFlag="N";
			  					
			  					if (chgEndDatelng <= invEndDatelng)
			  					{	
			  						invEndDate = chargEndDate;
			  						//if (chgEndDatelng == invEndDatelng)
				  						//fullCycleFlag="Y";
			  					}
			  						  					
			  					invDTO1 = new InvoiceDTO();			  					
			  					setInvoiceHeaderData(contractDTO, invDTO1);
			  				    invDTO1.setInvoiceStartDate(invStDate);
				  				invDTO1.setInvoiceEndDate(invEndDate);
				  				invDTO1.setFullCycleFlag(fullCycleFlag);
				  			    invstdatelng = invStDate.getTime();
				  			    invenddatelng = invEndDate.getTime();

				  			    if (invstdatelng <= invenddatelng){
				  			    	invoiceLineItmList.add(invDTO1);
				  			    }
					  			    
				  				System.out.println("***InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
				  				
				  				//Manoj 
				  				if(prCyDatelng<=currDatelng){
				  				//if(prCyDatelng<newNextInvDatelng){
					  				invStDate = properCyclestartDate;
					  				sameDatelng = invStDate.getTime();
					  				
					  				invDTO = new InvoiceDTO();
					  				setInvoiceHeaderData(contractDTO, invDTO);
					  				invDTO.setInvoiceStartDate(invStDate);
					  				fullCycleFlag="Y";
					  				startDateFlag = true; //2911
					  				
		    		  				newNextInvDate = getNewNextInvoiceDateString(invStDate,month);
		    		  				newNextInvDatelng = newNextInvDate.getTime();
		    		  				
		    		  				//System.out.println("extra invStDate:" + invStDate + "       " + "newNextInvDate:" + newNextInvDate);
					  				System.out.println("***sameDatelng:" + sameDatelng + "       " + "chgEndDatelng:" + chgEndDatelng + "    " + "sameDateFlag:" + sameDateFlag);
				  				
				  				}
	    		  				//11/23/2016: if proeprcyclestrtdt <= now() or currentdt
			  				}
			  				
		  					if (chgEndDatelng >= newNextInvDatelng){	  		
		  						System.out.println("MAnoj if loop 1201============================>"+fullCycleFlag);
		  						invEndDate = subtractDays(newNextInvDate, 1);
				  				//System.out.println("invEndDate----" + invEndDate);
		  					}else{	  		
		  						System.out.println("MAnoj ellse loop 1201============================>"+fullCycleFlag);
		  						fullCycleFlag="N";
		  						invEndDate = chargEndDate;
				  				//System.out.println("invEndDate----" + invEndDate);
		  						
		  						Timestamp tempEndDate = subtractDays(newNextInvDate,1);
				  				long tempEndDatelong = tempEndDate.getTime();
				  				
				  				if(chgEndDatelng == tempEndDatelong && startDateFlag){ //2911
				  				//if(chgEndDatelng == tempEndDatelong){
			  						fullCycleFlag="Y";
				  				}
				  				
		  					}
		  					
		  					System.out.println("MAnoj 1201============================>"+fullCycleFlag);
		  					 invDTO.setFullCycleFlag(fullCycleFlag);
			  				 invDTO.setInvoiceEndDate(invEndDate);
			  				 invstdatelng = invStDate.getTime();
				  			 invenddatelng = invEndDate.getTime();
				  			 
				  			 System.out.println(invStDate+"$$$invstdatelng:" + invstdatelng + "       " + "invenddatelng:" + invenddatelng+"::invEndDate:"+invEndDate);			  			 
				  			 if (invstdatelng <= invenddatelng){
	                            System.out.println("INSIDE IFF MANOJ");
				  				 if(null!=invDTO.getInvoiceStartDate() && null!=invDTO.getInvoiceEndDate()){
	                            	invoiceLineItmList.add(invDTO);
				  			     }	  					
	                        }
			  				//System.out.println("-----Invoice Start and End dates----");
			  				System.out.println("Regular --- InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate +":fullCycleFlag:"+fullCycleFlag);
		  				}else{
		  					
			  				System.out.println("else loop for BACKDATED newNextInvDate ..." + newNextInvDate);
		  					flag = true;
	
		  					//invStDate = rcStartDate;
	    					//System.out.println("else invStDate ..." + invStDate);
			  				newNextInvDate = getNewNextInvoiceDateString(properCyclestartDate,month);
			  				//System.out.println("else newNextInvDate ..." + newNextInvDate);
			  				
			  				if(rcStDatelng >= prCyDatelng){    //  pcdate:05/1/2016  ---   rcdate:05/5/2016   1stofMonth, monthly
			  					invStDate = rcStartDate;
			  					invDTO.setInvoiceStartDate(invStDate);
			  					
			  					fullCycleFlag="N";
		  						
		  						if (rcStDatelng == prCyDatelng){
				  					fullCycleFlag="Y";
		  							startDateFlag = true; //2911
		  						}
			  				}
			  				else    //  pcdate:05/5/2016  ---   rcdate:05/1/2016   5thofMonth, monthly
			  				{
				  				System.out.println("*** Entered diff scenario********");
			  					invStDate = rcStartDate;
			  					invEndDate = subtractDays(properCyclestartDate, 1);
			  					
			  					long invEndDatelng = invEndDate.getTime();
			  					fullCycleFlag="N";
			  					
			  					if(chgEndDatelng <= invEndDatelng)
			  					{	
			  						invEndDate = chargEndDate;
			  						//if (chgEndDatelng == invEndDatelng)
					  					//fullCycleFlag="Y";
			  					}
			  					
				  				System.out.println("***InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
				  				
				  				invDTO1 = new InvoiceDTO();
				  				setInvoiceHeaderData(contractDTO, invDTO1);			  				
			  				    invDTO1.setInvoiceStartDate(invStDate);
				  				invDTO1.setInvoiceEndDate(invEndDate);
				  				invDTO1.setFullCycleFlag(fullCycleFlag);
				  				invstdatelng = invStDate.getTime();
					  			invenddatelng = invEndDate.getTime();
					  			if(invstdatelng <= invenddatelng){
					  				invoiceLineItmList.add(invDTO1);
					  			 }
					  								  				
				  				invStDate = properCyclestartDate;
				  				sameDatelng = invStDate.getTime();
				  				
				  				invDTO = new InvoiceDTO();
				  				setInvoiceHeaderData(contractDTO, invDTO);
				  				invDTO.setInvoiceStartDate(invStDate);			
			  					fullCycleFlag="Y";
			  					startDateFlag = true; //2911
				  				
	    		  				newNextInvDate = getNewNextInvoiceDateString(invStDate,month);
	    		  				newNextInvDatelng = newNextInvDate.getTime();
	    		  				//System.out.println("extra invStDate:" + invStDate + "       " + "newNextInvDate:" + newNextInvDate);
			  				}
			  				  		  				
		  					if (chgEndDatelng >= newNextInvDatelng){
		  						invEndDate = subtractDays(newNextInvDate, 1);
				  				System.out.println("else invEndDate----" + invEndDate);
		  					}else{
		  						fullCycleFlag="N";
		  						invEndDate = chargEndDate;
				  				System.out.println("else invEndDate----" + invEndDate);
				  				flag = true;
				  				
				  				Timestamp tempEndDate = subtractDays(newNextInvDate,1);
				  				long tempEndDatelong = tempEndDate.getTime();
				  				
				  				if(chgEndDatelng == tempEndDatelong && startDateFlag){ //2911
			  						fullCycleFlag="Y";
				  				}
				  				
		  					}
		  					 invDTO.setFullCycleFlag(fullCycleFlag);
		  					 invDTO.setInvoiceEndDate(invEndDate);
			  				 invstdatelng = invStDate.getTime();
				  			 invenddatelng = invEndDate.getTime();
	
				  			 if (invstdatelng <= invenddatelng){
				  				 invoiceLineItmList.add(invDTO);
				  			 }
		  					
			  				System.out.println("Invoice " + "---" + "  InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
			  				
			  				if(chgEndDatelng >= newNextInvDatelng){
	    					for(int i=0;i<=diffInMonths/month && flag ;i++){
		        					
	    							invDTO = new InvoiceDTO();
	    							setInvoiceHeaderData(contractDTO, invDTO);
	    							
		        					invStDate = newNextInvDate;
		        					invDTO.setInvoiceStartDate(invStDate);
		        					
		    		  				newNextInvDate = getNewNextInvoiceDateString(invStDate,month);
		    		  				newNextInvDatelng = newNextInvDate.getTime();
		        					
				  					if (chgEndDatelng >= newNextInvDatelng){
				  						fullCycleFlag="Y";
				  						invEndDate = subtractDays(newNextInvDate, 1);
						  				//System.out.println("else invEndDate----" + invEndDate);
				  					}else{
				  						fullCycleFlag="N";  
				  						invEndDate = chargEndDate;
						  				//System.out.println("else invEndDate----" + invEndDate);
						  				flag = false;
						  				
						  				Timestamp tempEndDate = subtractDays(newNextInvDate,1);
						  				long tempEndDatelong = tempEndDate.getTime();
						  				
						  				if(chgEndDatelng == tempEndDatelong)
					  						fullCycleFlag="Y";
						  				
				  					}	
				  					
				  					invDTO.setFullCycleFlag(fullCycleFlag);
		        					invDTO.setInvoiceEndDate(invEndDate);
		        					invoiceLineItmList.add(invDTO);
		        					
					  				System.out.println("Invoice " + i + "  InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
		        					
					  				if( newNextInvDatelng >= currDatelng ){
					  					flag = false;
					  				}
		  				      }      					
		  				}
		  			  }
		  			} catch (Exception e) {
							System.out.println("exception" + e);
		  			}	
				    
	  			//}  
	  		}   
	  	}
    return invoiceLineItmList;
}
		
/**   ******************BILLING INVOICE AMOUNT LOGIC GOES FROM HERE*********************
 * 	
 * @param invoiceDTOList
 * @return
 * @throws ParseException 
 */
	
	
	public static List<InvoiceDTO> getInvoiceAmount(List<InvoiceDTO> invoiceDTOList, String readingFromApplication) throws ParseException{	
		
		for(InvoiceDTO invoiceDTOObj : invoiceDTOList){
		
			if(String.valueOf(invoiceDTOObj.getFullCycleFlag()).equalsIgnoreCase("Y")){      //if it is full cycle
				if(invoiceDTOObj.getBillingFrequence().equalsIgnoreCase("Monthly")){
					invoiceDTOObj.setPreTaxAmount(invoiceDTOObj.getMrcAmount());
					invoiceDTOObj.setTaxAmount(invoiceDTOObj.getPreTaxAmount()*0.08);
					invoiceDTOObj.setAmount(invoiceDTOObj.getPreTaxAmount() + invoiceDTOObj.getTaxAmount());
				}
				else{
					invoiceDTOObj.setPreTaxAmount(invoiceDTOObj.getRcAmount());
					invoiceDTOObj.setTaxAmount(invoiceDTOObj.getPreTaxAmount()*0.08);
					invoiceDTOObj.setAmount(invoiceDTOObj.getPreTaxAmount() + invoiceDTOObj.getTaxAmount());
				}				
			}
			else{
				//Prorated logic goes here 
				InvoiceDTO invoiceDTO = getInvoiceAmountProrated(invoiceDTOObj,readingFromApplication);
				invoiceDTOObj.setAmount(invoiceDTO.getAmount());
			}
		}
		
		return invoiceDTOList;
	}
		
	//Added on 1129
/**
	 * Method will return the prorated amount logic
	 * @param invoiceDTO - InvoiceDTO
	 * @return - InvoiceDTO item
	 */
	public static InvoiceDTO getInvoiceAmountProrated(InvoiceDTO invoiceDTO, String readingFromApplication) throws ParseException{
		
		//getting the values from the DTO
		Timestamp invStartDate = invoiceDTO.getInvoiceStartDate();
		Timestamp invEndDate = invoiceDTO.getInvoiceEndDate();
		Timestamp actualProperCycleStartDate = null;
		String invoiceCycleStart = invoiceDTO.getInvoiceCycleStart();
		String billingFrequence = invoiceDTO.getBillingFrequence();
		Timestamp finalEndDate = invEndDate; //here we are considering finalEndDate is invEndDate only.
		
		double rcAmount = invoiceDTO.getRcAmount();
		double mrcAmount = invoiceDTO.getMrcAmount();	
		double invoiceAmount = 0;
		double startMonth = 0;
		double endMonth = 0;
		double daysDiff = 0;
		double tempInvAmount = 0;
		int monthDays = 0;			
		boolean billingFlag = false;
		String fullCycle="N";
		//1205 String readingFromApplication="smax.properties";
		//get the month of Billingfrequence
		int month = getMonth(billingFrequence, readingFromApplication);
					
		if(invoiceDTO.getBillingFrequence().equalsIgnoreCase("Monthly")){	
			invoiceAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount, readingFromApplication);//calling the method if it is falling under the same month
			invoiceDTO.setPreTaxAmount(round(invoiceAmount,2));
			invoiceDTO.setTaxAmount(invoiceDTO.getPreTaxAmount()*0.08);
			invoiceDTO.setAmount(invoiceDTO.getPreTaxAmount() + invoiceDTO.getTaxAmount());
			//END: Date:11302016 Below code is added for Pre-Tax, Tax and TotalAmount Calculation
			System.out.println("getPreTaxAmount:::::"+invoiceDTO.getPreTaxAmount());
		}
		else {	
				System.out.println("**** getInvoiceAmountProrated Else LOOP  Quarterly SemiAnnaul and Annual****");
				//Divding Quater/semi and annual into monthlyfreq.....
				int tempMonthFreq = 1;
				Timestamp prCycleDate = getProperCycleStartDate(invStartDate, invoiceCycleStart,readingFromApplication);//not req
				actualProperCycleStartDate = prCycleDate;
				Timestamp newNextInvDate = getReqMonth(prCycleDate,tempMonthFreq); //01/05/2016
				//START: MAnoj added for 2811
				long newNextInvDatelng = newNextInvDate.getTime();
				//Timestamp currDate = new Timestamp(System.currentTimeMillis()); // 1205 taking hrs as well
				Timestamp currDate = convertStringDateToTimestamp(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").format(DateFormatter.removeTimeFromDate(new Date())), "yyyy-MM-dd 00:00:00.0");
  				System.out.println("currDate ..." + currDate); //11/28/2016

  				long currDatelng = currDate.getTime();
  				//System.out.println("currDatelng----" + currDatelng);
  				
  				long chgEndDatelng = finalEndDate.getTime();
  				//System.out.println("chgEndDatelng----" + chgEndDatelng);
  				
  				long prCyDatelng = prCycleDate.getTime();
  				//System.out.println("prCyDatelng----" + prCyDatelng);
  				
  				long rcStDatelng = invStartDate.getTime();
  				//System.out.println("rcStDatelng----" + rcStDatelng);
				
				if(rcStDatelng>currDatelng){ //rcStartDate : 12/01/2016
					if(rcStDatelng<actualProperCycleStartDate.getTime()){
						prCycleDate = getProperCycleStartDate(currDate, invoiceCycleStart,readingFromApplication); //11/05/2016
					}else{
						prCycleDate = getProperCycleStartDate(prCycleDate, invoiceCycleStart,readingFromApplication); //11/05/2016
					}
  					System.out.println("MAnoj properCyclestartDate:"+prCycleDate);//11/05/2016
	  				newNextInvDate = getNewNextInvoiceDateString(prCycleDate,tempMonthFreq); //02/05/2017
	  				System.out.println(month+"MAnoj newNextInvDate:"+newNextInvDate);
	  				newNextInvDatelng = newNextInvDate.getTime();
  				}
				//END: MAnoj added for 2811
				LocalDate invoiceStrtDate = Instant.ofEpochMilli(invStartDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate invoEndDate = Instant.ofEpochMilli(invEndDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    long diffInMonths = ChronoUnit.MONTHS.between(invoiceStrtDate, invoEndDate);
				System.out.println("difference b/w two dates:"+diffInMonths);
				
				billingFlag = true;
				Timestamp invStrtDate;
				Timestamp currentInvEndDate;					
				long properCycleDate = prCycleDate.getTime(); //11/05
				long invStartDatelng = invStartDate.getTime(); //12/01
	
				/*long newNextInvDatelng = newNextInvDate.getTime();
				long chgEndDatelng = finalEndDate.getTime();*/
				
				if(invStartDatelng >= properCycleDate){
					invStrtDate = invStartDate;	
					fullCycle="N";
					if(invStartDatelng ==properCycleDate){
						fullCycle="Y";
					}
				}
				else{	  //  pcdate:05/5/2016  ---   rcdate:05/1/2016   5thofMonth, monthly  05/04/2016
					
					invStrtDate = invStartDate;
					currentInvEndDate = subtractDays(prCycleDate, 1);
					
					
  					long invEndDatelng = invEndDate.getTime();		
  					
  					if (chgEndDatelng <= invEndDatelng){		  	                
  						invEndDate = finalEndDate;
  					} 												
  					if (invStartDatelng <= invEndDatelng){	  					
  						invoiceAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount, readingFromApplication);
			  		}
  					
  					System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate + "   invoiceAmount:"+ invoiceAmount);
  				//	System.out.println("   $$$$$$$$$$$$$$$::$$$$$$$$$$$$$$$::$$$$$$$$$$$$$$$:invoiceAmount:"+invoiceAmount);
  					invStartDate=prCycleDate;
  					newNextInvDate = getReqMonth(invStartDate,1);//call the getNewNextInvoiceDate
  				    newNextInvDatelng = newNextInvDate.getTime(); 
  												
				}
				
				
				if (chgEndDatelng >= newNextInvDatelng){	//chga: 02/04/2017 : nextDt:02/05/2017		                
						invEndDate = subtractDays(newNextInvDate, 1);
						billingFlag=true;
				
						if(fullCycle.equals("Y")){
							invoiceAmount = invoiceAmount + mrcAmount;	
							System.out.println("   $$$$$$$$$$$$$$$::mrcAmount:"+mrcAmount);
							//System.out.println("*** tempInvAmount::"+invoiceAmount);						
						}
						else{
							
							Timestamp tempEndDate = subtractDays(newNextInvDate,1);
			  				long tempEndDatelong = tempEndDate.getTime();
			  				
			  				if(chgEndDatelng == tempEndDatelong){
								invoiceAmount = invoiceAmount + mrcAmount;
								//System.out.println("*** tempInvAmount::"+invoiceAmount);
			  				}
			  				else
			  				{
			  					tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount, readingFromApplication);
			  					//System.out.println("*** tempInvAmount::"+tempInvAmount);
			  					invoiceAmount = invoiceAmount + tempInvAmount;
			  					System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate);
		  						System.out.println("   $$$$$$$$$$$$$$$::invoiceAmount:"+invoiceAmount);
			  				}
						}
					}
				else{
						invEndDate = finalEndDate;
						tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount, readingFromApplication);
						//System.out.println("*** tempInvAmount::"+tempInvAmount);
						invoiceAmount = invoiceAmount + tempInvAmount;
						System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate);
						System.out.println("   $$$$$$$$$$$$$$$::tempInvAmount:"+tempInvAmount);
					}
				if(chgEndDatelng >= newNextInvDatelng){

					for(int i=0;i<=diffInMonths && billingFlag ;i++){
							//System.out.println("in for loop:::::::::::::::"+i+":finalEndDate:"+finalEndDate+"invEndDate::::"+invEndDate+"::tempInvAmount:"+tempInvAmount);
					    	if(finalEndDate.getTime() == invEndDate.getTime()){ 
					    		break;
					    	}
							invStartDate = newNextInvDate;	        					
    		  				newNextInvDate = getReqMonth(invStartDate,1);//get the getNewNextInvoiceDate
    		  				newNextInvDatelng = newNextInvDate.getTime();        					
		  					if (chgEndDatelng >= newNextInvDatelng)
		  					{		  		                
		  						invEndDate = subtractDays(newNextInvDate, 1);
		  						invoiceAmount = invoiceAmount + mrcAmount;
		  					//	System.out.println("*** invoiceAmount::"+invoiceAmount);
		  					//	System.out.println("$$$$$$$$$$$$$$$:: For full cycle mrcAmount::"+mrcAmount);
		  					}
		  					else
		  					{
		  						invEndDate = finalEndDate;
		  						//tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
		  						//invoiceAmount = invoiceAmount + tempInvAmount;
		  						
								Timestamp tempEndDate = subtractDays(newNextInvDate,1);
				  				long tempEndDatelong = tempEndDate.getTime();
				  				
				  				if(chgEndDatelng == tempEndDatelong){
									invoiceAmount = invoiceAmount + mrcAmount;
								//	System.out.println("$$$$$$$$$$$$$$$::For full cycle** mrcAmount::"+mrcAmount);
				  				}
				  				else
				  				{
				  					tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount, readingFromApplication);
				  				    invoiceAmount = invoiceAmount + tempInvAmount;
				  				    System.out.println(" invstrDate::" + invStartDate +"  invEndDate::" +invEndDate);
				  				//    System.out.println("   $$$$$$$$$$$$$$$::$$$$$$$$$$$$$$$::$$$$$$$$$$$$$$$:tempInvAmount:"+tempInvAmount);
				  				}
		  					}			  						        					
		  				      }      						        			
			  			}
					invoiceDTO.setPreTaxAmount(round(invoiceAmount,2));
					invoiceDTO.setTaxAmount(invoiceDTO.getPreTaxAmount()*0.08);
					invoiceDTO.setAmount(invoiceDTO.getPreTaxAmount() + invoiceDTO.getTaxAmount());
					//END: Date:11302016 Below code is added for Pre-Tax, Tax and TotalAmount Calculation
			    }			
		return invoiceDTO;
		}
	
		/**
		 * method to calcualte the amount for full cycle and prorated cycle with the Prorated Cycles
		 * @param invStartDate - Timestamp
		 * @param invEndDate - Timestamp
		 * @param rcAmount - rcAmount
		 * @param mrcAmount - mrcAmount
		 * @return - only one line item amount
		 */
		public static double getInvAmountPro(Timestamp invStartDate, Timestamp invEndDate, double rcAmount, double mrcAmount, String readingFromApplication)
		{
			int startMonth = 0;
			int endMonth = 0;
			int daysDiff = 0;
			int monthDays = 0;
			int month = 0;
			double tempInvAmount = 0;
			double invAmount=0.0;
			
			//get month from both the dates invStartDate and invEndDate
			//TODO: 1205
		    startMonth = getMonthFromTimestamp(invStartDate);
			endMonth = getMonthFromTimestamp(invEndDate);
			
			if(startMonth == endMonth)  // ex:11/1/2016 - 11/26/2016;    
			{
				System.out.println("****If both dates are under the same month ****");//TODO: 1205
				int invStDate = getDayFromTimestampDateFormat(invStartDate);
				int invEdDate = getDayFromTimestampDateFormat(invEndDate);
				
				daysDiff = invEdDate - invStDate+1;
				month = startMonth;//get the month number from invStDat, As the invStdate and invEndDate both of them fall under same month;
				System.out.println("Month::"+month);
				//1205 String readingFromApplication="smax.properties";	  	 
			  	 
			  	String str=month+"";//need to pass only string to get the days from propertyfile
				int DaysInAMonth = getDay(str,readingFromApplication); //this method is taking only String
				System.out.println("DaysIn"+month+"th Month::"+DaysInAMonth);
				invAmount = (mrcAmount/DaysInAMonth)*daysDiff;		 
			}
			else{
				    // ex:11/10/2016 - 12/04/2016;  As it comes under differt months, Invoice should be calculated as (11/10/2016 - 11/30/2016 & 12/1/2016 - 12/04/2016)
					 System.out.println("****If both dates are fallunder the different  month ****");		
			 	     Timestamp m1startDate = invStartDate; //--- 11/10/2016
					 Timestamp m1endDate = getEndDayofMonth(m1startDate); //get the end of the month date by passing m1stdate; --- 11/30/2016
					 //TODO: 1205			 
					 int invStDate = getDayFromTimestampDateFormat(m1startDate);
					 int invEdDate = getDayFromTimestampDateFormat(m1endDate);
								 
					 daysDiff = invEdDate - invStDate+1;
					 System.out.println("Diff b/w two dates ::"+daysDiff);
					 startMonth = getMonthFromTimestamp(m1startDate);
					 month = startMonth ; //As the m1stdate and m1enddate both of them fall under same month;
					 String str = month+"";//need to pass only string to get the days from propertyfile
					 
					 //String readingFromApplication="smax.properties";
					 monthDays = getDay(str, readingFromApplication); //this method is taking only Strin
					 System.out.println("DaysIn"+month+"th Month::"+monthDays);
					 
					 tempInvAmount = (mrcAmount/monthDays)*daysDiff;// -- Invoice for 11/10/2016 - 11/30/2016
					 System.out.println("getInvAmountPro Else loop tempInvAmount ::"+tempInvAmount);
					 invAmount = tempInvAmount;
					 
					 tempInvAmount=0;
					 
			 	     Timestamp m2endDate = invEndDate;//get the invEndDate; --- 12/04/2016
					 Timestamp m2startDate = getFirstDayofMonth(m2endDate);//get the start of the month date by passing m2endate; --- 12/01/2016
					 
					 int invStrtDay = getDayFromTimestampDateFormat(m2startDate);
					 int invEndDay = getDayFromTimestampDateFormat(m2endDate);
					 
					 daysDiff = invEndDay - invStrtDay+1;
					 System.out.println("Diff b/w two dates m2startDate & m2endDate ::"+daysDiff);
					 startMonth = getMonthFromTimestamp(m2startDate);
					 
					 month = startMonth;   //As the m2stdate and m2enddate both of them fall under same month;
				     str = month+"";//need to pass only string to get the days from propertyfile
					
					 monthDays = getDay(str,readingFromApplication);// get the number of days from smax.property file by passing month
					 System.out.println("DaysIn"+month+"th Month::"+monthDays);
					 tempInvAmount = (mrcAmount/monthDays)*daysDiff; //--- Invoice for 12/1/2016 - 12/04/2016			 
					 invAmount = invAmount + tempInvAmount;// --- Invoice amount for (11/10/2016 - 12/04/2016
					 System.out.println("getInvAmountPro Else loop Total getPreTaxAmount::"+invAmount);
				}
				return round(invAmount,2);	
		}

}
		
	
	