package util.common;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;
public enum DetailEstimateStatus {
	
	UNKNOWN(-1L, "UNKNOWN"),
	NEW_APPLICATION(1L, "New"),
	APPLICATION_MODIFIED(2L, "Modified"),
	REJECTED_BY(5L, "Rejected by"),
	
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
	PIVII_FOR_SECDEPOSIT(61L, "PIV II For Security Deposit"),

	SEND_FOR_CONSTRUCTION_MAINTENANCE(70L, "Send for Construction/Maintenance"),
	
	JOB_ALLOCATED(80L, "Job Allocated");
	
	//SEND_FOR_ACCOUNTANT_REVENUE(60L, "Send For Accountant Revenue");

    private final String name;
    private final Long key;
   
    private static final Map<Long, DetailEstimateStatus> applicationStatusKeyMap = new HashMap<Long, DetailEstimateStatus>();
    private static final Map<String, DetailEstimateStatus> applicationStatusNameMap = new HashMap<String, DetailEstimateStatus>();

    static {
		for (DetailEstimateStatus e : EnumSet.allOf(DetailEstimateStatus.class)) {
			applicationStatusKeyMap.put(e.getKey(), e);
			applicationStatusNameMap.put(e.getName(), e);
		}
	}

    private DetailEstimateStatus(Long value, String name) {
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
        return "StandardEstimateStatus[key: " + key + "name: " + name + " ]";
    }

    public static DetailEstimateStatus getType(String name) {
        if (applicationStatusNameMap.containsKey(name))
            return applicationStatusNameMap.get(name);
        else
            return UNKNOWN;
    }

    public static DetailEstimateStatus getType(Long key) {
        if (applicationStatusKeyMap.containsKey(key))
            return applicationStatusKeyMap.get(key);
        else
            return UNKNOWN;
    }
}
