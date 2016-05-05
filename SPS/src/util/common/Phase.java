package util.common;

public class Phase {
	private String phaseName;
	private String phaseValue;
	
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getPhaseValue() {
		return phaseValue;
	}
	public void setPhaseValue(String phaseValue) {
		this.phaseValue = phaseValue;
	}
	
	public Phase(String name, String value)
	{
		this.phaseName	 = name;
		this.phaseValue = value;
	}
	
}
