package offDoc.web;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
 
import java.util.Date;
import java.util.List;

import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
 
import application.model.Applicant;
import application.model.Application;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import estimate.model.MaterialGrid;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

public class mainAppPiv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//PivDetailPK pivDetailPK = new PivDetailPK();
	//	pivDetailPK.setPivNo("R70");
    	//pivDetailPK.setDeptId("510.20");
    	
    	//PivDetailEjb pivDetailEjb = new PivDetailEjb();
		//PivDetail pivDetail = pivDetailEjb.findById(pivDetailPK);
		
		//ApplicantEjb applicantEjb=new ApplicantEjb();
		//Applicant applicant = applicantEjb.findById(pivDetail.getIdNo());
		//PrintPiv pe = new  PrintPiv(pivDetail,applicant);
		PayingInVoucher piv = new PayingInVoucher();
		BigDecimal d = new BigDecimal("343");
		
		 piv.setBankName("Sampath Bank");
		 piv.setBankBranchName("Pelawatte");
		 piv.setCEBBranch("Colombo City");
		 piv.setCostCenterNo("cost center");
		 piv.setDate("12/12/2011");
		 piv.setReferenceNo("reference number");
		 piv.setPIVNumber("XN P 009#$ 2122");
		 piv.setJobDescription("Job Description");
		 piv.setDepositorIDNumber("8120019301v");
		 piv.setDepositorName("Manoj Lakmal");		 
		 piv.setStreetAddress("Street address");
		 piv.setSuburb("suberb");
		 piv.setPostalCode("postal code");
		 piv.setCity("city");
		 piv.setBankCode("bank code");
		 piv.setBranchCode("branch code");
		 piv.setChequeNo("2134231423423");
		 piv.setChequeDate("23/11/1985");
		 piv.setAmountInWords("TWO HUNDERD FIFTY FIVE RUPPEES ONLY");
		 piv.setAmountInFigures("2,250.00");
		 piv.setPreparedBy("prepared by");
		 piv.setMiscellaneousIncome(new BigDecimal("11"));
		 
		 piv.setEletricityDebtors(new BigDecimal("12350000"));
		 piv.setSecurityDeposit(new BigDecimal("35766"));
     	 piv.setServiceConnectionOrElectricitySchemes(new BigDecimal("765734"));
     	 piv.setTenderDeposits(new BigDecimal("567657"));
     	piv.setMiscellaneousDeposits(new BigDecimal("234235"));
     	 piv.setCashInTransit(d);
     	 piv.setForDishonouredCheques(new BigDecimal("32421"));
     	 piv.setSubTotal(new BigDecimal("12350453"));
     	piv.setVat(new BigDecimal("34657"));
     	 piv.setGrandTotal(new BigDecimal("43456"));
		 piv.setLoanAmount(new BigDecimal("574534"));
		 piv.setLoanReference("loan reference");
//		 
//		
//		 
//		 piv.setCetifiedBy("Manoj");
//		
//		
//		
//		
//		 

//		
//		
//		
//		 piv.setIfBankLoanIsGranted(true);
//		

//		 
//		
//		
//		
//		
//		
//		
//		
//		
//		  
//		
//		 
		
		  
		 
		 
		 
		PrintPiv pe = new  PrintPiv(piv);
		 pe.Print(true);
	  

		 
	}




}


 class Converter
{
	private double getPlace( String number ){
		switch( number.length() ){
		case 1:
			return DefinePlace.UNITS;
		case 2:
			return DefinePlace.TENS;
		case 3:
			return DefinePlace.HUNDREDS;
		case 4:
			return DefinePlace.THOUSANDS;
		case 5:
			return DefinePlace.TENTHOUSANDS;
		case 6:
			return DefinePlace.LAKHS;
		case 7:
			return DefinePlace.TENLAKHS;
		case 8:
			return DefinePlace.CRORES;
		case 9:
			return DefinePlace.TENCRORES;
		}//switch
		return 0.0;
	}// getPlace

	private String getWord( int number ){
		switch( number ){
		case 1:
			return "One";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 0:
			return "Zero";
		case 10:
			return "Ten";
		case 11:
			return "Eleven";
		case 12:
			return "Tweleve";
		case 13:
			return "Thirteen";
		case 14:
			return "Forteen";
		case 15:
			return "Fifteen";
		case 16:
			return "Sixteen";
		case 17:
			return "Seventeen";
		case 18:
			return "Eighteen";
		case 19:
			return "Ninteen";
		case 20:
			return "Twenty";
		case 30:
			return "Thirty";
		case 40:
			return "Forty";
		case 50:
			return "Fifty";
		case 60:
			return "Sixty";
		case 70:
			return "Seventy";
		case 80:
			return "Eighty";
		case 90:
			return "Ninty";
		case 100:
			return "Hundred";
		} //switch
		return "";
	} //getWord

	private String cleanNumber( String number ){
		String cleanedNumber = "";

		cleanedNumber = number.replace( '.', ' ' ).replaceAll( " ", "" );
		cleanedNumber = cleanedNumber.replace( ',', ' ' ).replaceAll( " ", "" );
		if( cleanedNumber.startsWith( "0" ) )
			cleanedNumber = cleanedNumber.replaceFirst( "0", "" );

		return cleanedNumber;
	} //cleanNumber

	public String convertNumber( String number ){
		number = cleanNumber( number );
		double num = 0.0;
		try{
			num = Double.parseDouble( number );
		}catch( Exception e ){
			return "Invalid Number Sent to Convert";
		} //catch

		String returnValue = "";
		while( num > 0 ){
			number = "" + (int)num;
			double place = getPlace(number);
			if( place == DefinePlace.TENS || place == DefinePlace.TENTHOUSANDS || place == DefinePlace.TENLAKHS || place == DefinePlace.TENCRORES ){
				int subNum = Integer.parseInt( number.charAt(0) + "" + number.charAt(1) );

				if( subNum >= 21 && (subNum%10) != 0 ){
					returnValue += getWord( Integer.parseInt( "" + number.charAt(0) ) * 10 ) + " " + getWord( subNum%10 ) ;
				} //if
				else{
					returnValue += getWord(subNum);
				}//else

				if( place == DefinePlace.TENS ){
					num = 0;
				}//if
				else if( place == DefinePlace.TENTHOUSANDS ){
					num -= subNum * DefinePlace.THOUSANDS;
					returnValue += " Thousands ";
				}//if
				else if( place == DefinePlace.TENLAKHS ){
					num -= subNum * DefinePlace.LAKHS;
					returnValue += " Lakhs ";
				}//if
				else if( place == DefinePlace.TENCRORES ){
					num -= subNum * DefinePlace.CRORES;
					returnValue += " Crores ";
				}//if
			}//if
			else{
				int subNum = Integer.parseInt( "" + number.charAt(0) );

				returnValue += getWord( subNum );
				if( place == DefinePlace.UNITS ){
					num = 0;
				}//if
				else if( place == DefinePlace.HUNDREDS ){
					num -= subNum * DefinePlace.HUNDREDS;
					returnValue += " Hundred ";
				}//if
				else if( place == DefinePlace.THOUSANDS ){
					num -= subNum * DefinePlace.THOUSANDS;
					returnValue += " Thousand ";
				}//if
				else if( place == DefinePlace.LAKHS ){
					num -= subNum * DefinePlace.LAKHS;
					returnValue += " Lakh ";
				}//if
				else if( place == DefinePlace.CRORES ){
					num -= subNum * DefinePlace.CRORES;
					returnValue += " Crore ";
				}//if
			}//else
		}//while
		return returnValue;
	}//convert number

	 
} //class

class DefinePlace{
	public static final double UNITS = 1;
	public static final double TENS = 10 * UNITS;
	public static final double HUNDREDS = 10 * TENS;
	public static final double THOUSANDS = 10 * HUNDREDS;
	public static final double TENTHOUSANDS = 10 * THOUSANDS;
	public static final double LAKHS = 10 * TENTHOUSANDS;
	public static final double TENLAKHS = 10 * LAKHS;
	public static final double CRORES = 10 * TENLAKHS;
	public static final double TENCRORES = 10 * CRORES;
} //class
