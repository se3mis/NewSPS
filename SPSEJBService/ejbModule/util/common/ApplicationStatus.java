package util.common;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;
public enum ApplicationStatus {
	
	UNKNOWN(-1L, "UNKNOWN"),
	NEW_APPLICATION(1L, "New"),
	
	
	TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER(10L, "To be validate by Electrical Engineer"),
	REJECTED_BY_ELECTRICAL_ENG(5L, "Rejected by Electrical Engineer"),
	VALIDATED_BY_ELECTRICAL_ENG(12L, "Validated by Electrical Engineer/To be Validate by PE"),
	
	TO_BE_VALIDATED_BY_PLANNING_ENGINEER(20L, "To be validate by Planning Engineer"),
	REJECTED_BY_PLANNING_ENG(5L, "Rejected by Planning Engineer"),
	VALIDATED_BY_PLANNING_ENG(21L, "Validated by Planning Engineer/To be Validate by CE"),
	
	TO_BE_VALIDATED_BY_CE(30L, "To be validate by Chief Engineer"),
	REJECTED_BY_CE(5L, "Rejected by Chief Engineer"),
	VALIDATED_BY_CE(32L, "Validated by Chief Engineer/To be Validate by DGM"),
	
	TO_BE_APPROVED_BY_DGM(40L, "To be Approved by DGM"),
	REJECTED_BY_DGM(5L, "Rejected by DGM"),
	VALIDATED_BY_DGM(42L, "Validated by DGM"),
	

	APPROVED_ESTIMATES(50L, "Approved Estimates"),
	
	PIVII_CONFIRMATION(60L, "PIV II Confirmation"),

	SEND_FOR_CONSTRUCTION_MAINTENANCE(60L, "Send for Construction/Maintenance");
	
	
	//SEND_FOR_ACCOUNTANT_REVENUE(60L, "Send For Accountant Revenue");

    private final String name;
    private final Long key;
   
    private static final Map<Long, ApplicationStatus> applicationStatusKeyMap = new HashMap<Long, ApplicationStatus>();
    private static final Map<String, ApplicationStatus> applicationStatusNameMap = new HashMap<String, ApplicationStatus>();

    static {
		for (ApplicationStatus e : EnumSet.allOf(ApplicationStatus.class)) {
			applicationStatusKeyMap.put(e.getKey(), e);
			applicationStatusNameMap.put(e.getName(), e);
		}
	}

    private ApplicationStatus(Long value, String name) {
        this.name = name;
        this.key = value;
       
    }

    public String getName() {
        return name;
    }

    public Long getKey() {
        return key;
    }

   

    public String toString() {
        return "ApplicationStatus[key: " + key + "name: " + name + " ]";
    }

    public static ApplicationStatus getType(String name) {
        if (applicationStatusNameMap.containsKey(name))
            return applicationStatusNameMap.get(name);
        else
            return UNKNOWN;
    }

    public static ApplicationStatus getType(Long key) {
        if (applicationStatusKeyMap.containsKey(key))
            return applicationStatusKeyMap.get(key);
        else
            return UNKNOWN;
    }
}
