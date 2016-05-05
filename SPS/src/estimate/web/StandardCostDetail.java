package estimate.web;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;



public class StandardCostDetail  extends ActionSupport implements SessionAware, ParameterAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	private Map <String, String[]> parameters;
	private List<String> listTariff;
	private List<String> listConnectionType;
	private List<String> listPhase;
	private List<String> listEstimationType;
	private List<String> listPoleType;
	private List<String> listPoleSize;
	private List<String> listLandDispute;
	private List<String> listEcectricityNeeded;
	
	
	
	public List<String> getListEcectricityNeeded() {
		return listEcectricityNeeded;
	}
	public void setListEcectricityNeeded(List<String> listEcectricityNeeded) {
		this.listEcectricityNeeded = listEcectricityNeeded;
	}
	public List<String> getListLandDispute() {
		return listLandDispute;
	}
	public void setListLandDispute(List<String> listLandDispute) {
		this.listLandDispute = listLandDispute;
	}
	public List<String> getListPoleSize() {
		return listPoleSize;
	}
	public void setListPoleSize(List<String> listPoleSize) {
		this.listPoleSize = listPoleSize;
	}
	public List<String> getListPoleType() {
		return listPoleType;
	}
	public void setListPoleType(List<String> listPoleType) {
		this.listPoleType = listPoleType;
	}
	public List<String> getListEstimationType() {
		return listEstimationType;
	}
	public void setListEstimationType(List<String> listEstimationType) {
		this.listEstimationType = listEstimationType;
	}
	public List<String> getListPhase() {
		return listPhase;
	}
	public void setListPhase(List<String> listPhase) {
		this.listPhase = listPhase;
	}
	public Map<String, Object> getSession() {
		return session;	
		
	}
	public List<String> getListConnectionType() {
		return listConnectionType;
	}
	public void setListConnectionType(List<String> listConnectionType) {
		this.listConnectionType = listConnectionType;
	}
	public List<String> getListTariff() {
		return listTariff;
	}
	public void setListTariff(List<String> listTariff) {
		this.listTariff = listTariff;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	public String execute(){	
		
		listTariff = new ArrayList<String>();
		listTariff.add("tariff1");
		listTariff.add("tariff2");
		listTariff.add("tariff3");
		listTariff.add("tariff4");
		
		listConnectionType = new ArrayList<String>();
		listConnectionType.add("type1");
		listConnectionType.add("type2");
		listConnectionType.add("type3");
		listConnectionType.add("type4");
		
		listPhase = new ArrayList<String>();
		listPhase.add("phase1");
		listPhase.add("phase2");
		listPhase.add("phase3");
		listPhase.add("phase4");
		
		listEstimationType = new ArrayList<String>();
		listEstimationType.add("type1");
		listEstimationType.add("type2");
		listEstimationType.add("type3");
		listEstimationType.add("type4");
		
		listPoleType = new ArrayList<String>();
		listPoleType.add("type1");
		listPoleType.add("type2");
		listPoleType.add("type3");
		listPoleType.add("type4");
		
		listPoleSize = new ArrayList<String>();
		listPoleSize.add("size1");
		listPoleSize.add("size2");
		listPoleSize.add("size3");
		listPoleSize.add("size4");		
		
		listLandDispute = new ArrayList<String>();
		listLandDispute.add("dispute1");
		listLandDispute.add("dispute2");
		listLandDispute.add("dispute3");
		listLandDispute.add("dispute4");	
		
		listEcectricityNeeded = new ArrayList<String>();
		listEcectricityNeeded.add("need1");
		listEcectricityNeeded.add("need2");
		listEcectricityNeeded.add("need3");
		listEcectricityNeeded.add("need4");	
		
		return SUCCESS;
	}
}
