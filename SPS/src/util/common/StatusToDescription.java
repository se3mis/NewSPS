package util.common;

public class StatusToDescription {

	public static final String OPEN="OPEN";
	public static final String TRANSFERRED="TRANSFERRED";
	public static final String UNDERREVISION="UNDERREVISION";
	public static final String UNKNOWN="UNKNOWN";
	
	public static String getStatusdescription(int status){
		
		String description;
		switch (status) {
		  case 1: 
			description=OPEN ;
		    break;
		  case 3: 
			description=TRANSFERRED ;
		    break;
		  case 7: 
			 description=UNDERREVISION ;
		    break;		
		  default: 
			description=UNKNOWN ;
		}
		
		return description;
	}
}
