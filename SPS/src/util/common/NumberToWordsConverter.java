package util.common;


public class NumberToWordsConverter {

	private static final String[] tensNames = { "", "", "TWENTY", "THIRTY",
			"FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY" };
	private static final String[] onesNames = { "", "-ONE", "-TWO", "-THREE",
			"-FOUR", "-FIVE", "-SIX", "-SEVEN", "-EIGHT", "-NINE", "TEN",
			"ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN",
			"SEVENTEEN", "EIGHTEEN", "NINETEEN" };

	public static String evaluate(double number) {
		try {
			long iPart = (long) number;
			//int fPart = (int) Math.round((number - iPart) * 100);
			
			long fPart = (long) Math.round((number - iPart) * 100);
			
			//float fPart = (float) Math.round((number - iPart) * 100);

			StringBuffer buf = new StringBuffer();
			// buf.append("***** ");
			buf.append(NumberToWordsConverter.evaluate(iPart));
			
			
			String fPartString = NumberToWordsConverter.evaluate(fPart);
			if(fPartString != null && !fPartString.equals("")){
				buf.append(" AND ");
				buf.append(NumberToWordsConverter.evaluate(fPart));
				buf.append(" CENTS ");
			}
			
			if (fPart > 0) {
				fPart = fPart;
			} else {
				fPart = 0;
			}
			// buf.append(((fPart > 0) ? fPart : "XX"));
			// buf.append(fPart);
			//buf.append("/100");
			// buf.append(" ***** ");
			String sss =  buf.toString();
                  return sss.toLowerCase();


		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid number string " + ex);
		}
	}

	private static String evaluate(long number) {
		long temp = number;
		long billions = temp / 1000000000;
		temp %= 1000000000;
		long millions = temp / 1000000;
		temp %= 1000000;
		long thousands = temp / 1000;
		temp %= 1000;
		long hundreds = temp / 100;
		temp %= 100;

		StringBuffer result = new StringBuffer(30);
		if (billions > 0)
			result.append(evaluate(billions) + " BILLION ");
		if (millions > 0)
			result.append(evaluate(millions) + " MILLION ");
		if (thousands > 0)
			result.append(evaluate(thousands) + " THOUSAND ");
		if (hundreds > 0)
			result.append(evaluate(hundreds) + " HUNDRED ");
		if (temp != 0) {
			if (0 < temp && temp <= 19)
				result.append(onesNames[(int) temp]);
			else {
				long tens = temp / 10;
				long ones = temp % 10;
				result.append(tensNames[(int) tens] + " ");
				result.append(onesNames[(int) ones]);
			}
		}

		String sss =  result.toString();
                  return sss.toLowerCase();

	}

	

}



