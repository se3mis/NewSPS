package offDoc.web;

public class mainAppSMC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceMainCard smc = new ServiceMainCard();
		smc.setSMCType("12233333");
		 smc.setSIN("SIN");
	smc.setCostCode("cost code");
		smc.setECSC("ecsc");
		smc.setYear("year");
		smc.setSerialJobNo("serialjobno");
		smc.setNoOfMeters("Three");
		smc.setMeterNumber1("1234567890");
		smc.setMeterNumber2("0987654321");
		smc.setMeterNumber3("5334545435");
		smc.setMeterReading1("5675675674");
		smc.setMeterReading2("76345645654");
		smc.setMeterReading3("456456546");
		smc.setMeterType1("type1");
		smc.setMeterType2("Type2");
		smc.setMeterType3("Type3");
		smc.setNoOfDigits1("34224d");
		smc.setNoOfDigits2("Digits2");
		smc.setNoOfDigits3("Digits3");
 	smc.setConnectedDate("12/12/2011");
		smc.setAverageConsumption("averageConsumption");
		smc.setPaymentMode("Cash");
		smc.setPaymentDate("12/12/2010");
		smc.setSecurityDeposits("12,34,3444.00");
		smc.setServiceMainCharge("4,45,35,35,.00");
		smc.setTax("tax");
		smc.setTotal("total");
		smc.setTariff("tariff");
		smc.setKVA("kVA");
		smc.setConnType("connType");
		smc.setNoOfPhase("noOfPhase");
		smc.setBankReference_PIV2("bankReference_PIV2");
		smc.setNeighboursACNo("neighboursACNo");
		smc.setBankBranchCode("bankBranchCode");
		smc.setLastName("Kumara Perera");
		smc.setFirstName("Meemanage Manoj Lakmal");
		smc.setStreetAddress("89/3, Meemanagoda Para");
		smc.setSuburb("Kalalgoda");
		smc.setCity("Pannipitiya");
		smc.setPostalCode("00024");
		smc.setPIVNumber("pIVNumber");
		smc.setPIVDate("12/12/2010");
		smc.setCustomerType("customerType");
		smc.setReaderCode("readerCode");
		smc.setPackNo("packNo");
		smc.setWalkSequence("walkSequence");
		smc.setOldAcctNumber("oldAcctNumber");
		smc.setLoanCode("x231");
		smc.setLoanDate("08/12/2010");
		smc.setNoOfInstalment("72");
		smc.setLoanAmount("123,345,655.00");
		smc.setInstalmantAmount("23,544.00");
		 
		
		PrintSMC pe = new  PrintSMC(smc);
		pe.Print(true);
	}

}
