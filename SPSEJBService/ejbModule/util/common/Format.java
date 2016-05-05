package util.common;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Format {
	public String FormatDate() {
		String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		// System.out.println("Today is " + sdf.format(calendar.getTime()));
		return sdf.format(calendar.getTime());

	}
	
	public Date StrToDate(String str_date) {
		try{
			
		
			//String str_date="11-June-07";
	        DateFormat formatter ; 
	        Date date ; 
	        formatter = new SimpleDateFormat("yyyy-MM-dd");
	        date = (Date)formatter.parse(str_date);    
	        return date;
		 } catch (ParseException e) {
			 System.out.println("Exception :"+e);
			 return null;
		}    
		     
	   }

	
	
	public String formatDate(Date dt)
	{
		if (dt == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
	    return sdf.format(dt);
	}
	
	public String formatDate2(Date dt)
	{
		if (dt == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
	    return sdf.format(dt);
	}
	

	public String FormatDate(String DATE_FORMAT) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		// System.out.println("Today is " + sdf.format(calendar.getTime()));
		return sdf.format(calendar.getTime());

	}

	public String FormatTime() {
		String TIME_FORMAT = "hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		// System.out.println("Today is " + sdf.format(calendar.getTime()));
		return sdf.format(calendar.getTime());

	}

	public String FormatTime(String TIME_FORMAT) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		// System.out.println("Today is " + sdf.format(calendar.getTime()));
		return sdf.format(calendar.getTime());

	}
	public Date getDefaultDate()
    {
          Calendar cal = Calendar.getInstance();
          cal.set(1900,0,1);
          return cal.getTime();
    }

	
	public String getYear() {
		String DATE_FORMAT = "yy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance(); // today
		//calendar.getTime().
		return sdf.format(calendar.getTime());

	}
	
	
	
	
	public String fromatCurrency(BigDecimal value)
	{
		if (value == null)
			return null;
		NumberFormat nf = NumberFormat.getInstance();
		 nf.setMaximumFractionDigits(2);
		    nf.setMinimumFractionDigits(2);
	   return  nf.format(value);
	}
	
	public static void main(String[] args){
		Format format=new Format();
		//format.setMsgLineList("");
		System.out.println(format.formatDate2(new Date()).substring(0, 4).length());
		System.out.println(format.formatDate2(new Date()).substring(0, 4));
		System.out.println(format.formatDate2(new Date()).substring(5, 7));
		//System.out.print(format.StrToDate("14/12/2010"));
	}

}
