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
import java.text.DecimalFormat;
import com.ge.billing.dto.ContractDTO;
import com.ge.billing.dto.InvoiceDTO;


public final class BillingUtility_Veeranna {
	
	
	/**
	 * @param filename
	 * @param strKeyName
	 * @return
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
		}
		return str;
	}
	
	/**
	 * @param invoiceCycleStart
	 * @param readingFromApplication
	 * @return
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
	 * @param newInvoiceDate is nothing but Contract start date
	 * @param invoiceCycleStart ex: 5th of month
	 * @param readingFromApplication
	 * @return getProperCycleStartDate
	 */
	public static Timestamp getProperCycleStartDate(Timestamp agreementStartDate, String invoiceCycleStart, String readingFromApplication){
		int day = getDay(invoiceCycleStart, readingFromApplication);
		System.out.println("day::"+day);
		Timestamp properCycleStartDate=null;
		if(agreementStartDate!=null){
			try{	
				int agreementStartDateDay = 0;
				agreementStartDateDay = getDayFromTimestampDateFormat(agreementStartDate);
				//System.out.println("agreementStartDateDay::"+agreementStartDateDay);
				if(day==agreementStartDateDay){ //Proper dates formats for example agsd = 10/10/2016 getDay : 10
					properCycleStartDate = agreementStartDate;
				}else if(agreementStartDateDay>day){ //For exmale agreementStartDate: 10/10/2016: 15 and day : 5
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
					Date agreementDate = formatter.parse(""+agreementStartDate);
					properCycleStartDate = convertStringDateToTimestamp(addOrSubstractDaysFromDate(agreementDate,-(agreementStartDateDay-day),"MM/dd/yyyy"),"MM/dd/yyyy");
				}else if(agreementStartDateDay<day){//For exmale agreementStartDate: 10/02/2016: 2 and day : 5
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
					Date d1 = formatter.parse(""+agreementStartDate);
					properCycleStartDate = convertStringDateToTimestamp(addOrSubstractDaysFromDate(d1, day-agreementStartDateDay,"MM/dd/yyyy"),"MM/dd/yyyy");
				}
			}catch(Exception e){
				System.out.println("agreementStartDateDay exception"+e);
			}
		}
		System.out.println("properCycleStartDate::"+properCycleStartDate);
		return properCycleStartDate;		
	}
	
	public static int getDayFromTimestampDateFormat(Timestamp timestampDate){
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		return cal.get(Calendar.DATE);
	}
	
	public static Timestamp convertStringDateToTimestamp(String strDate, String dateFormat) throws ParseException{
		DateFormat formatter;
		formatter = new SimpleDateFormat(dateFormat);
		Date date = (Date) formatter.parse(strDate);
		java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
		return timeStampDate;
	}
	
	public static String addOrSubstractDaysFromDate(Date aDate, int noOfDays, String dateFormat) {
		Calendar calendar = Calendar.getInstance(); // get the calendar instance
		calendar.setTime(aDate);// set it to today
		calendar.add(Calendar.DATE, noOfDays);
		Date d1 = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		String dater = formatter.format(d1);
		return dater;
	}
	
	/**
	 * @param days
	 * @param timestamp
	 * @return
	 */
	public static Timestamp getReqDate(int days,Timestamp timestamp) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(timestamp);
		calendar.set(Calendar.DATE, days);
		Date dateToLookBackAfter = calendar.getTime();
		return new Timestamp(dateToLookBackAfter.getTime());
	} 

	
	//public static Timestamp getReqMonth(Timestamp nextInvoiceDate,int days, int month) {
		/**
		 * @param nextInvoiceDate
		 * @param month
		 * @return
		 */
		public static Timestamp getReqMonth(Timestamp nextInvoiceDate, int month) {	
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nextInvoiceDate);
		//calendar.set(Calendar.DATE, days); //TODO
		calendar.add(Calendar.MONTH, month);
		Date dateToLookBackAfter = calendar.getTime();
		return new Timestamp(dateToLookBackAfter.getTime());
	} 
		
		public static Timestamp setReqDay(Timestamp nextInvoiceDate, int day) {	
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(nextInvoiceDate);
			//calendar.set(Calendar.DATE, days); //TODO
			calendar.add(Calendar.DATE, day);
			Date dateToLookBackAfter = calendar.getTime();
			return new Timestamp(dateToLookBackAfter.getTime());
		} 
	
	/**
	 * @param billingFrequency
	 * @param readingFromApplication
	 * @return
	 */
	public static int getMonth(String billingFrequency, String readingFromApplication){
		int getMonthValue=0;
		if(billingFrequency!=null && readingFromApplication!=null){
			try{
				getMonthValue= getDay(billingFrequency, readingFromApplication);
			}
			catch(Exception e){
				System.out.println("exception"+e);		
			}
		}
		return getMonthValue;
	}
	/**
	 * @param strDate1
	 * @param strDate2
	 * @param dateFormat
	 * @return
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
	 * @param agreementStartDate
	 * @param nextInvoiceDate
	 * @param agreementEndDate
	 * @param invoiceCycleStart
	 * @param billingFrequency
	 * @param readingFromApplication
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getNewNextInvoiceDateList(String agreementStartDate, Timestamp nextInvoiceDate, String agreementEndDate, 
		String invoiceCycleStart, String billingFrequency, String readingFromApplication ) throws ParseException{
		List<String> newNextInvoiceList=new ArrayList<String>();
		if(getDifferenceDays(agreementEndDate,agreementStartDate,"yyyy-MM-dd")>0){
			int day = getDay(invoiceCycleStart, readingFromApplication);
			int month = getMonth(billingFrequency, readingFromApplication);
			System.out.println("Month::"+month);
			if(null!=nextInvoiceDate){			
				nextInvoiceDate=getReqDate(day,nextInvoiceDate);
				//System.out.println("getProperCycleStartDate"+nextInvoiceDate);
				newNextInvoiceList=getNewNextInvoiceDate(nextInvoiceDate, month);			
				
				//MAnoj added
				Date startDate = nextInvoiceDate;		
				Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(startDate);						
				Date currentDate = new Date();
				Calendar currentCalendar = new GregorianCalendar();
				currentCalendar.setTime(currentDate);
				LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate currntDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			    long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currntDate);
				System.out.println("difference b/w two dates:"+diffInMonths);
				
				
				 Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
				 String getReqMonth = null;
				 for(int i=0;i<=diffInMonths;i++){
					try {										
						Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
						getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1);
						getProperCyclestartDate = getReqMonth1;					
						newNextInvoiceList.add(getReqMonth);									
					} catch (Exception e) {
						System.out.println("exception" + e);
					}
				}
				
				//END: MAnoj added
				
				//System.out.println("getNewNextInvoiceDate"+list);
				}		
			 else {// nextInvoiceDate == null
					// nextInvoiceDate = agreementStartDate;
					// getNewNextInvoiceDate(agreementStartDate);
				if(null==nextInvoiceDate){
					nextInvoiceDate = convertStringDateToTimestamp(agreementStartDate,"yyyy-MM-dd hh:mm:ss.sss");					
					nextInvoiceDate=getReqDate(day,nextInvoiceDate);
					if (nextInvoiceDate != null) {
						
						if (month == 1) {   //for Monthly Biling freqency 							
							Date startDate = nextInvoiceDate;		
							Calendar startCalendar = new GregorianCalendar();
							startCalendar.setTime(startDate);						
							Date currentDate = new Date();
							Calendar currentCalendar = new GregorianCalendar();
							currentCalendar.setTime(currentDate);
							LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						    LocalDate currntDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						    long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currntDate);
						     
							//int diffMonths = currentCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
							System.out.println("difference b/w two dates:"+diffInMonths);
							Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
							String getReqMonth = null;
						
							for(int i=0;i<=diffInMonths;i++){
								try {										
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;					
									newNextInvoiceList.add(getReqMonth);									
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						}
						 else if (month == 3) {//for querterly Biling freqency	
							Date startDate = nextInvoiceDate;					
							Calendar startCalendar = new GregorianCalendar();
							startCalendar.setTime(startDate);
							
						   Date endDate = new Date();
						   Calendar endCalendar = new GregorianCalendar();
						   endCalendar.setTime(endDate);
						   LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   LocalDate currentDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   System.out.println("startDate"+startDate);
						   long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currentDate);
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/3;i++){
								try {
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;																	
									newNextInvoiceList.add(getReqMonth);									
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						} else if (month == 6) {//for semiannual Billing freqency	
							Date startDate = nextInvoiceDate;					
							Calendar startCalendar = new GregorianCalendar();
							startCalendar.setTime(startDate);
							
						   Date endDate = new Date();
						   Calendar endCalendar = new GregorianCalendar();
						   endCalendar.setTime(endDate);
						   LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   LocalDate currentDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   System.out.println("startDate"+startDate);
						   long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currentDate);
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/6;i++){
								try {
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1);
									getProperCyclestartDate = getReqMonth1;									
									newNextInvoiceList.add(getReqMonth);													
								} catch (Exception e) {
									System.out.println("exception" + e);
								}
							}
						}else {//for annual Billing freqency	
							Date startDate = nextInvoiceDate;					
							Calendar startCalendar = new GregorianCalendar();
							startCalendar.setTime(startDate);
							
						   Date endDate = new Date();
						   Calendar endCalendar = new GregorianCalendar();
						   endCalendar.setTime(endDate);
						   LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   LocalDate currentDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						   System.out.println("startDate"+startDate);
						   System.out.println("new Date()"+new Date());
						   long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currentDate);
						   System.out.println("diffInMonths"+diffInMonths);	
						   Timestamp getProperCyclestartDate = getProperCycleStartDate(nextInvoiceDate, invoiceCycleStart,readingFromApplication);
						   String getReqMonth = null;		
							for(int i=0;i<=diffInMonths/12;i++){
								try {
									Timestamp getReqMonth1=getReqMonth(getProperCyclestartDate, month);
									getReqMonth = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth1);
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
		}	return newNextInvoiceList;	
			 
	}		

	/**
	 * @param nextInvoiceDate
	 * @param month
	 * @return
	 */
	//generic logic for getNewNextInvoiceDate	
	public static ArrayList<String> getNewNextInvoiceDate(Timestamp nextInvoiceDate,int month){
		ArrayList<String> list1 = new ArrayList<String>();
		String newNextInvoiceDate=null;
		try{
			newNextInvoiceDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth(nextInvoiceDate, month));
			list1.add(newNextInvoiceDate);
			//System.out.println("getMonthBillingFreq"+newNextInvoiceDate);
		}
		catch(Exception e){
			System.out.println("exception"+e);		
		}
		return list1;
	}
	
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

	public static List<InvoiceDTO> getInvoiceList(ContractDTO contractDTO) throws ParseException{
	  	List<InvoiceDTO> invoiceDtoList=new ArrayList<InvoiceDTO>();
	  	String readingFromApplication="smax.properties";
	  	try {
			invoiceDtoList = getInvoiceLineItemDateList(contractDTO, readingFromApplication);
		} catch (Exception e) {
			// TODO: handle exception
		}
      return invoiceDtoList;
	}
				
	public static Timestamp subtractDays(Timestamp date, int day) {
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -day);	
		Date subDate = cal.getTime();
		return new Timestamp(subDate.getTime()); 
	}
	
	public static Timestamp getNewNextInvoiceDateString(Timestamp nextInvoiceDate,int month){
		String newNextInvoiceDate=null;
		Timestamp newNextInvDate = null;
		try{
			newNextInvoiceDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(getReqMonth(nextInvoiceDate, month));
			newNextInvDate = convertStringDateToTimestamp(newNextInvoiceDate,"yyyy-MM-dd hh:mm:ss.sss");
		}
		catch(Exception e){
			System.out.println("exception"+e);		
		}
		return newNextInvDate;
	}
	
	public static List<InvoiceDTO> getInvoiceLineItemDateList(ContractDTO contractDTO, String strReadingFromApplication) throws ParseException{
		  
	    //Timestamp agmntStdate=contractDTO.getStartDt();		       
	    //System.out.println(" agmntStdate: " + agmntStdate);
	  
	    ArrayList invoiceLineItmList = new ArrayList();
	  
	    Timestamp rcStartDate=contractDTO.getRcStartDt();		       
	    System.out.println(" rcStartDate: " + rcStartDate);
	    
	    String chargeEndDate=contractDTO.getChargeEndDt().toString();
	    System.out.println(" chargeEndDate: " + chargeEndDate);
	    
  		String invoiceCycleStart= StringUtils.replaceAllWhiteSpaces(contractDTO.getInvoiceCycleStart());
  		System.out.println(" invoiceCycleStart: " + invoiceCycleStart);
  		
  		String billingFrequency= StringUtils.replaceAllWhiteSpaces(contractDTO.getBillingFrequency());
  		System.out.println(" billingFrequency: " + billingFrequency);
  		
  		String readingFromApplication="smax.properties";
  		System.out.println(" readingFromApplication: " + readingFromApplication);
  		
  		//String strFinalEndDate = chargeEndDate;
  		//System.out.println(" strFinalEndDate: " + strFinalEndDate);
  		
  		//List<InvoiceDTO> invoiceDtoList=new ArrayList<InvoiceDTO>();
  		String rcStrtDate=(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss").format(rcStartDate));
	
  		
		if(getDifferenceDays(chargeEndDate,rcStrtDate,"yyyy-MM-dd")>=0 ){

  		int day = getDay(invoiceCycleStart, readingFromApplication);
  		System.out.println(" day: " + day);
  		
  		int month = getMonth(billingFrequency, readingFromApplication);
  		System.out.println("Month::"+month);
  				
  		
  		//check if rcStartDate is null, then set to lineitemstartdate
  		if(rcStartDate==null)
  		{
  			rcStartDate = contractDTO.getLineStartDt();
  		}
  		if(null!=rcStartDate){     		
  			
  			InvoiceDTO generateInvoiceDTO = new InvoiceDTO();
  		    setInvoiceHeaderData(contractDTO, generateInvoiceDTO);
  			      			
  			generateInvoiceDTO.setInvoiceStartDate(rcStartDate);
  			System.out.println("rcStartDate"+rcStartDate);
  			
  			//invoiceDtoList.add(generateInvoiceListDTO);
  			//Timestamp actStartDate = rcStartDate;
  			//rcStartDate = getReqDate(day,rcStartDate);//it will give the propercyclestartdate
  			
  			Timestamp propCycleStDate = getReqDate(day,rcStartDate);//it will give the propercyclestartdate
  			System.out.println("propCycleStDate"+propCycleStDate);
  					  			
  			//if (month == 1) 
  			//{   //for Monthly Biling freqency                                                                                                                
  			
  				Date startDate = rcStartDate;                    
  				Calendar startCalendar = new GregorianCalendar();
  				startCalendar.setTime(startDate);
  				
  				LocalDate agreeStartDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  				LocalDate currntDate = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  				
  				Timestamp currDate = new Timestamp(System.currentTimeMillis());
  				System.out.println("currDate ..." + currDate);
  				
  				long diffInMonths = ChronoUnit.MONTHS.between(agreeStartDate, currntDate);
  				
  				//int diffMonths = currentCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
  				System.out.println("difference b/w two dates::::"+diffInMonths);
  				
  				System.out.println("$$$$$$$$$rcStartDate:"+rcStartDate);
  				//Timestamp getProperCyclestartDate = getProperCycleStartDate(rcStartDate, invoiceCycleStart,readingFromApplication);//not req
  				Timestamp properCyclestartDate = getProperCycleStartDate(rcStartDate, invoiceCycleStart,readingFromApplication);//not req
  				System.out.println("properCyclestartDate ..." + properCyclestartDate);
  				
  				Timestamp newNextInvDate = getNewNextInvoiceDateString(properCyclestartDate,month);
  				System.out.println("newNextInvDate ..." + newNextInvDate);

  				Timestamp chargEndDate=convertStringDateToTimestamp(chargeEndDate,"yyyy-MM-dd hh:mm:ss.sss");
  				System.out.println("chargEndDate ..." + chargEndDate);
  				
  				//Timestamp invoiceEndDate1=subtractDays(getProperCyclestartDate, 1);
  				
  				int dayDiff =  getDifferenceDays(chargeEndDate+"", rcStartDate+"", "yyyy-MM-dd hh:mm:ss.sss");
  				System.out.println("dayDiff b/w two dates::::"+dayDiff);
  				
  				System.out.println("----");
  				System.out.println("----");
  				
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
  				
  				Timestamp invEndDate=null;
  				Timestamp invStDate=null;
  				
  				InvoiceDTO invDTO = new InvoiceDTO();
  				setInvoiceHeaderData(contractDTO, invDTO);
  				InvoiceDTO invDTO1 = new InvoiceDTO();
  				
	  				boolean sameDateFlag = false;
	  				long sameDatelng;
	  				
	  				long invstdatelng;
		  			long invenddatelng;
		  			
		  			String fullCycleFlag="N";
	  				
	  			try{
	  				
	  				if( newNextInvDatelng >= currDatelng ){	
	  					
		  				if(rcStDatelng >= prCyDatelng){     //  pcdate:10/1/2016  ---   rcdate:10/5/2016   1stofMonth, monthly
		  					invStDate = rcStartDate;
		  				    invDTO.setInvoiceStartDate(invStDate);
		  				    
		  				    fullCycleFlag="N";	  			
		  				    
	  						if (rcStDatelng == prCyDatelng)
			  				    fullCycleFlag="Y";	  						

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
				  			    /*if(invenddatelng >= currDatelng){
				  			    	System.out.println("we need to break here");
				  			    }*/
				  			    
			  				System.out.println("***InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
			  				
			  				invStDate = properCyclestartDate;
			  				sameDatelng = invStDate.getTime();
			  				
			  				invDTO = new InvoiceDTO();
			  				setInvoiceHeaderData(contractDTO, invDTO);
			  				invDTO.setInvoiceStartDate(invStDate);
			  				fullCycleFlag="Y";
			  				
    		  				newNextInvDate = getNewNextInvoiceDateString(invStDate,month);
    		  				newNextInvDatelng = newNextInvDate.getTime();
    		  				//System.out.println("extra invStDate:" + invStDate + "       " + "newNextInvDate:" + newNextInvDate);
			  				System.out.println("***sameDatelng:" + sameDatelng + "       " + "chgEndDatelng:" + chgEndDatelng + "    " + "sameDateFlag:" + sameDateFlag);
		  				}
		  				
	  					if (chgEndDatelng >= newNextInvDatelng){	  						
	  						invEndDate = subtractDays(newNextInvDate, 1);
			  				//System.out.println("invEndDate----" + invEndDate);
	  					}else{	  						
	  						fullCycleFlag="N";
	  						invEndDate = chargEndDate;
			  				//System.out.println("invEndDate----" + invEndDate);
	  						
	  						Timestamp tempEndDate = subtractDays(newNextInvDate,1);
			  				long tempEndDatelong = tempEndDate.getTime();
			  				
			  				if(chgEndDatelng == tempEndDatelong)
		  						fullCycleFlag="Y";
			  				
	  					}
	  					
	  					 invDTO.setFullCycleFlag(fullCycleFlag);
		  				 invDTO.setInvoiceEndDate(invEndDate);
		  				 invstdatelng = invStDate.getTime();
			  			 invenddatelng = invEndDate.getTime();
			  			 
			  			 System.out.println("$$$invstdatelng:" + invstdatelng + "       " + "invenddatelng:" + invenddatelng);			  			 
			  			 if (invstdatelng <= invenddatelng){
				  			invoiceLineItmList.add(invDTO);
			  			 }	  					
		  				//System.out.println("-----Invoice Start and End dates----");
		  				System.out.println("Regular --- InvoiceStartDate:" + invStDate + "       " + "InvoiceEndDate:" + invEndDate);
	  				}else{
	  					
		  				System.out.println("else loop newNextInvDate ..." + newNextInvDate);
	  					flag = true;

	  					//invStDate = rcStartDate;
    					//System.out.println("else invStDate ..." + invStDate);
		  				newNextInvDate = getNewNextInvoiceDateString(properCyclestartDate,month);
		  				//System.out.println("else newNextInvDate ..." + newNextInvDate);
		  				
		  				if(rcStDatelng >= prCyDatelng){    //  pcdate:05/1/2016  ---   rcdate:05/5/2016   1stofMonth, monthly
		  					invStDate = rcStartDate;
		  					invDTO.setInvoiceStartDate(invStDate);
		  					
		  					fullCycleFlag="N";
	  						
	  						if (rcStDatelng == prCyDatelng)
			  					fullCycleFlag="Y";

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
			  				
    		  				newNextInvDate = getNewNextInvoiceDateString(invStDate,month);
    		  				newNextInvDatelng = newNextInvDate.getTime();
    		  				//System.out.println("extra invStDate:" + invStDate + "       " + "newNextInvDate:" + newNextInvDate);
		  				}
		  				  		  				
	  					if (chgEndDatelng >= newNextInvDatelng){
	  						//full cycle
	  						invEndDate = subtractDays(newNextInvDate, 1);
			  				System.out.println("else invEndDate----" + invEndDate);
	  					}else{
	  						//pro-rated
	  						fullCycleFlag="N";
	  						invEndDate = chargEndDate;
			  				System.out.println("else invEndDate----" + invEndDate);
			  				flag = true;
			  				
			  				Timestamp tempEndDate = subtractDays(newNextInvDate,1);
			  				long tempEndDatelong = tempEndDate.getTime();
			  				
			  				if(chgEndDatelng == tempEndDatelong)
		  						fullCycleFlag="Y";
			  				
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
			  						fullCycleFlag="N";  //pro-rata basis
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
 */
	
	
	public static List<InvoiceDTO> getInvoiceAmount(List<InvoiceDTO> invoiceDTOList){	
		
		for(InvoiceDTO invoiceDTOObj : invoiceDTOList){
		
			if(String.valueOf(invoiceDTOObj.getFullCycleFlag()).equalsIgnoreCase("Y")){
				if(invoiceDTOObj.getBillingFrequence().equalsIgnoreCase("Monthly")){
					invoiceDTOObj.setAmount(invoiceDTOObj.getMrcAmount());
					//System.out.println("Full cycle monthly amount::"+invoiceDTOObj.getMrcAmount());
				
				}
				else{
					invoiceDTOObj.setAmount(invoiceDTOObj.getRcAmount());
					//System.out.println("$$$Prorated amount for Quart,semi and annual invoiceDTOObj.getRcAmount::"+invoiceDTOObj.getRcAmount());
				}				
			}
			else{
				//invoiceDTOObj.setAmount(0.0);//here prorated logic has TODO
				InvoiceDTO invoiceDTO=getInvoiceAmountProrated(invoiceDTOObj);
				invoiceDTOObj.setAmount(invoiceDTO.getAmount());
				//System.out.println("$$$Prorated amount for Quart,semi and annual::"+invoiceDTO.getAmount());
			}
		}
		
		return invoiceDTOList;
	}

	
//public static List<InvoiceDTO> getInvoiceAmountProrated(List<InvoiceDTO> invoiceDTOList){
//	for(InvoiceDTO invoiceDTO :invoiceDTOList ){
	
	public static InvoiceDTO getInvoiceAmountProrated(InvoiceDTO invoiceDTO){

			
			//getting the values from the DTO
			Timestamp invStartDate=invoiceDTO.getInvoiceStartDate();
			Timestamp invEndDate=invoiceDTO.getInvoiceEndDate();
			
			String invoiceCycleStart=invoiceDTO.getInvoiceCycleStart();
			String billingFrequence=invoiceDTO.getBillingFrequence();
			Timestamp chargEndDate=invEndDate; //here we are considering chargEndDate is invEndDate only.
			
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
			String readingFromApplication="smax.properties";
			//get the month of Billingfrequence
			int month = getMonth(billingFrequence, readingFromApplication);
						
			if(invoiceDTO.getBillingFrequence().equalsIgnoreCase("Monthly")){	
				invoiceAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);//calling the method if it is falling under the same month
				invoiceDTO.setAmount(invoiceAmount);
				System.out.println("amount:::::"+invoiceDTO.getAmount());
			}
			else {	
					System.out.println("**** getInvoiceAmountProrated Else LOOP  Quarterly SemiAnnaul and Annual****");
					Timestamp prCycleDate = getProperCycleStartDate(invStartDate, invoiceCycleStart,readingFromApplication);//not req
					Timestamp newNextInvDate = getReqMonth(prCycleDate,1);
				
					//getting difference months b/w invStartdate and currentdate
					LocalDate invoiceStrtDate = Instant.ofEpochMilli(invStartDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				    LocalDate currntDate = Instant.ofEpochMilli(invEndDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				    long diffInMonths = ChronoUnit.MONTHS.between(invoiceStrtDate, currntDate);
				    
					System.out.println("difference b/w two dates:"+diffInMonths);
					
					billingFlag = true;
					Timestamp invStrtDate;
					Timestamp currentInvEndDate;					
					long properCycleDate = prCycleDate.getTime();
					long invStartDatelng = invStartDate.getTime();
					long newNextInvDatelng = newNextInvDate.getTime();
					long chgEndDatelng = chargEndDate.getTime();
					
					if(invStartDatelng >= properCycleDate){
						invStrtDate = invStartDate;//just same variable we are assigning
						//billingFlag = true;	
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
	  						invEndDate = chargEndDate;
	  					} 												
	  					if (invStartDatelng <= invEndDatelng){	  					
	  						invoiceAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
				  		}
	  					
	  					System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate + "   invoiceAmount:"+ invoiceAmount);
	  					
	  					invStartDate=prCycleDate;
	  					newNextInvDate = getReqMonth(invStartDate,1);//call the getNewNextInvoiceDate
	  				    newNextInvDatelng = newNextInvDate.getTime(); 
	  												
					}
					if (chgEndDatelng >= newNextInvDatelng){		  		                
  						invEndDate = subtractDays(newNextInvDate, 1);
  						billingFlag=true;
					
  						if(fullCycle.equals("Y")){
							invoiceAmount = invoiceAmount + mrcAmount;	
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
			  					tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
			  					//System.out.println("*** tempInvAmount::"+tempInvAmount);
			  					invoiceAmount = invoiceAmount + tempInvAmount;
			  					System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate + "   invoiceAmount:"+ invoiceAmount);
			  				}
						}
  					}
					else{
  						invEndDate = chargEndDate;
  						tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
  						//System.out.println("*** tempInvAmount::"+tempInvAmount);
  						invoiceAmount = invoiceAmount + tempInvAmount;
  						System.out.println(" invstrDate:" + invStartDate +"  invEndDate:" +invEndDate + "   invoiceAmount:"+ invoiceAmount);
  					}
					if(chgEndDatelng >= newNextInvDatelng){

    					for(int i=0;i<diffInMonths && billingFlag ;i++){
	        					
    		
    						    
    					    	invStartDate = newNextInvDate;	        					
	    		  				newNextInvDate = getReqMonth(invStartDate,1);//get the getNewNextInvoiceDate
	    		  				newNextInvDatelng = newNextInvDate.getTime();        					
			  					if (chgEndDatelng >= newNextInvDatelng)
			  					{		  		                
			  						invEndDate = subtractDays(newNextInvDate, 1);
			  						invoiceAmount = invoiceAmount + mrcAmount;
			  					//	System.out.println("*** invoiceAmount::"+invoiceAmount);
			  					}
			  					else
			  					{
			  						invEndDate = chargEndDate;
			  						//tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
			  						//invoiceAmount = invoiceAmount + tempInvAmount;
			  						
									Timestamp tempEndDate = subtractDays(newNextInvDate,1);
					  				long tempEndDatelong = tempEndDate.getTime();
					  				
					  				if(chgEndDatelng == tempEndDatelong){
										invoiceAmount = invoiceAmount + mrcAmount;
									//	System.out.println("*** invoiceAmount::"+invoiceAmount);
					  				}
					  				else
					  				{
					  					tempInvAmount = getInvAmountPro(invStartDate, invEndDate, rcAmount, mrcAmount);
					  				    invoiceAmount = invoiceAmount + tempInvAmount;
					  				    System.out.println(" invstrDate::" + invStartDate +"  invEndDate::" +invEndDate + "   invoiceAmount::"+ invoiceAmount);
					  				}
			  					}			  						        					
	  				      }      						        			
		  			}
				invoiceDTO.setAmount(invoiceAmount);
		    }			
	return invoiceDTO;
}
	
public static double getInvAmountPro(Timestamp invStartDate, Timestamp invEndDate, double rcAmount, double mrcAmount)
{
	int startMonth = 0;
	int endMonth = 0;
	int daysDiff = 0;
	int monthDays = 0;
	int month = 0;
	double tempInvAmount = 0;
	double invAmount=0.0;
	
	//get month from both the dates invStartDate and invEndDate
    startMonth=getMonthFromTimestamp(invStartDate);
	endMonth=getMonthFromTimestamp(invEndDate);
	/*System.out.println("***GetMonthFromTimestamp startMonth::"+startMonth);
	System.out.println("***GetMonthFromTimestamp endMonth::"+endMonth);
	System.out.println("IN getInvAmountPro invStartDate::"+invStartDate);
	System.out.println("IN getInvAmountPro invEndDate::"+invEndDate);
	System.out.println("IN getInvAmountPro rcAmount::"+rcAmount);
	System.out.println("IN getInvAmountPro mrcAmount::"+mrcAmount);
	*/
	
	if(startMonth == endMonth)  // ex:11/1/2016 - 11/26/2016;    
	{
		System.out.println("****If both dates are under the same month ****");
		int invStDate=getDayFromTimestampDateFormat(invStartDate);
		int invEdDate=getDayFromTimestampDateFormat(invEndDate);
		
		 daysDiff = invEdDate - invStDate+1;
		 month =startMonth;//get the month number from invStDat, As the invStdate and invEndDate both of them fall under same month;
		 System.out.println("Month::"+month);
		 String readingFromApplication="smax.properties";	  	 
	  	 
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
			 			 
			 int invStDate=getDayFromTimestampDateFormat(m1startDate);
			 int invEdDate=getDayFromTimestampDateFormat(m1endDate);
						 
			 daysDiff = invEdDate - invStDate+1;
			 System.out.println("Diff b/w two dates ::"+daysDiff);
			 startMonth=getMonthFromTimestamp(m1startDate);
			 month =startMonth ; //As the m1stdate and m1enddate both of them fall under same month;
			 String str=month+"";//need to pass only string to get the days from propertyfile
			 
			 String readingFromApplication="smax.properties";
			 monthDays = getDay(str,readingFromApplication); //this method is taking only Strin
			 System.out.println("DaysIn"+month+"th Month::"+monthDays);
			 
			 tempInvAmount = (mrcAmount/monthDays)*daysDiff;// -- Invoice for 11/10/2016 - 11/30/2016
			 System.out.println("getInvAmountPro Else loop tempInvAmount ::"+tempInvAmount);
			 invAmount = tempInvAmount;
			 
			 tempInvAmount=0;
			 
	 	     Timestamp m2endDate = invEndDate;//get the invEndDate; --- 12/04/2016
			 Timestamp m2startDate = getFirstDayofMonth(m2endDate);//get the start of the month date by passing m2endate; --- 12/01/2016
			 
			 int invStrtDay=getDayFromTimestampDateFormat(m2startDate);
			 int invEndDay=getDayFromTimestampDateFormat(m2endDate);
			 
			 daysDiff = invEndDay - invStrtDay+1;
			 System.out.println("Diff b/w two dates m2startDate & m2endDate ::"+daysDiff);
			 startMonth=getMonthFromTimestamp(m2startDate);
			 
			 month = startMonth;   //As the m2stdate and m2enddate both of them fall under same month;
		     str=month+"";//need to pass only string to get the days from propertyfile
			 
			
			 monthDays = getDay(str,readingFromApplication);// get the number of days from smax.property file by passing month
			 System.out.println("DaysIn"+month+"th Month::"+monthDays);
			 tempInvAmount = (mrcAmount/monthDays)*daysDiff; //--- Invoice for 12/1/2016 - 12/04/2016			 
			 invAmount = invAmount + tempInvAmount;// --- Invoice amount for (11/10/2016 - 12/04/2016
			 System.out.println("getInvAmountPro Else loop Total Amount::"+invAmount);
		}
		return invAmount;	
}
		
      public static int getMonthFromTimestamp(Timestamp timestampDate){
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);		
		return cal.get(Calendar.MONTH)+1;//for months need to be added +1(bcoz its starts with 0);
	}
	   
	public static Timestamp getEndDayofMonth(Timestamp timestampDate){
	//java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2016-09-16 10:10:10.0");
	long timestamp = timestampDate.getTime();
	Calendar cal = Calendar.getInstance();
	cal.setTimeInMillis(timestamp);
	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));	 
	 Date getTime= cal.getTime();
	 return new Timestamp(getTime.getTime());
	}

	public static Timestamp getFirstDayofMonth(Timestamp timestampDate){
		//java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2016-09-16 10:10:10.0");
		long timestamp = timestampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date getTime=cal.getTime();
		return new Timestamp(getTime.getTime());
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
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
	
	/*public static void main(String[] args) throws Exception {
		//SpringApplication.run(TestSpringBootProjectApplication.class, args);
		
		System.out.println("Hello World");
		
		InvoiceDTO invoiceDTO=new InvoiceDTO();		
		invoiceDTO.setInvoiceStartDate(convertStringDateToTimestamp("2016-06-07 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		invoiceDTO.setInvoiceEndDate(convertStringDateToTimestamp("2016-11-03 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		//invoiceDTO.setChargEndDate(convertStringDateToTimestamp("2017-12-06 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		invoiceDTO.setInvoiceCycleStart("11thofSemiAnnual");
		
		invoiceDTO.setRcAmount(4000);
		invoiceDTO.setMrcAmount(3000);		
		invoiceDTO.setBillingFrequence("SemiAnnual");
		
		InvoiceDTO invDTO=getInvoiceAmountProrated(invoiceDTO);
		
		System.out.println("startDate  "+invDTO.getInvoiceStartDate()+ "  end DAte  "+invDTO.getInvoiceEndDate()+"  amount  ::"+invDTO.getAmount());
 
	}*/

	
/*	public static void main(String[] args) throws Exception {	
		//SpringApplication.run(TestSpringBootProjectApplication.class, args);
		
		System.out.println("Hello World");
			
		ContractDTO contractDTO =  new ContractDTO();
		
		contractDTO.setStartDt(convertStringDateToTimestamp("2015-11-01 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		contractDTO.setRcStartDt(convertStringDateToTimestamp("2016-12-01 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		contractDTO.setChargeEndDt(convertStringDateToTimestamp("2017-12-05 00:00:00.000","yyyy-MM-dd hh:mm:ss.sss"));
		contractDTO.setInvoiceCycleStart("5thofMonth");
		contractDTO.setBillingFrequency("Monthly");
		
  		List<InvoiceDTO> invoiceDtoList=getInvoiceList(contractDTO);
		
  		System.out.println("InvoiceList:" + invoiceDtoList.size());
  		
  		InvoiceDTO invDTO = null;
  		
  		for(int i=0;i<invoiceDtoList.size();i++)
  		{
  			invDTO = new InvoiceDTO();
  			invDTO = invoiceDtoList.get(i);
  			
  			System.out.println("InvoiceStartDate:" + invDTO.getInvoiceStartDate() + "   --------   " + invDTO.getInvoiceEndDate() + "  FullCycleFlag =" + invDTO.getFullCycleFlag() );
  		}
 
	}*/
}
		
	
	
