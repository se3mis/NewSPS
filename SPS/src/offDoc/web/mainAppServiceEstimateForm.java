package offDoc.web;

public class mainAppServiceEstimateForm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceEstimateForm sef = new ServiceEstimateForm();
		sef.setNameWithInitials("cM.M.L.K. Perera");
		sef.setArea("cSri Jayawardanapur;a");
		sef.setCSC("c510.20");		
		sef.setStreetAddress("c89/3, Meemanagoda Para");
		sef.setSuburb("cKalalgoda");
		sef.setCity("cPannipitiya");
		sef.setPostalCode("00024");
		sef.setAppNumber("c2010/SNL/39.2/39992");
		sef.setTelephoneNumber("c0112345986");
		sef.setServiceType("cSingle Phase");
		sef.setTariff("cIndustrial Purpose");
		sef.setItem_P("234324");
		sef.setItem_P_Des("Pole three");
		sef.setItem_S("864576");
		sef.setItem_S_Des("Stay five");
		sef.setItem_St ("89045");
		sef.setItem_St_Des("Polse x");
		sef.setItem_Ls("45765");
		sef.setItem_Ls_Des("Service length");
		sef.setItem_Lb("56756");
		sef.setItem_Lb_Des("bare conductor length");
		sef.setItem_C1("00768");
		sef.setItem_C1_Des("CONVERSION 1 -3");
		sef.setItem_C2("00764");
		sef.setItem_C2_Des("CONVERSION 2-3");
		sef.setSubstation("Kelanitissa");		
		sef.setDistanceFromSS("12,330 Km");
		sef.setTransformerCapacity("1229 MW");
		sef.setTransformerPowerLoad_KVA("234234");
		sef.setFeederPeakLoad_A("33000 A");
		sef.setFeederControlType("Auto Control");
		sef.setSIN("32030-3-3v");
		sef.setPhase("Single");
		sef.setPoleNo("djk239r40-w3ir");
		
		 PrintServiceEstimateForm psef = new PrintServiceEstimateForm(sef);
		psef.Print(true);
	}

}
