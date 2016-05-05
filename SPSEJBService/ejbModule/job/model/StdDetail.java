package job.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class StdDetail implements Serializable {
	
	private BigDecimal 	serviceDistance;
	private BigDecimal 	lineLength;
	private String	connectorType;
	private BigDecimal 	poleNo;
	private BigDecimal 	stayNo;
	private BigDecimal 		strutNo;
	private BigDecimal 		span;
	
	
	
	
	public StdDetail() {
		super();
	}


	public StdDetail(BigDecimal serviceDistance, BigDecimal lineLength,
			String connectorType, BigDecimal poleNo, BigDecimal stayNo,
			BigDecimal strutNo, BigDecimal span) {
		super();
		this.serviceDistance = serviceDistance;
		this.lineLength = lineLength;
		this.connectorType = connectorType;
		this.poleNo = poleNo;
		this.stayNo = stayNo;
		this.strutNo = strutNo;
		this.span = span;
	}


	public BigDecimal getServiceDistance() {
		return serviceDistance;
	}


	public void setServiceDistance(BigDecimal serviceDistance) {
		this.serviceDistance = serviceDistance;
	}


	public BigDecimal getLineLength() {
		return lineLength;
	}


	public void setLineLength(BigDecimal lineLength) {
		this.lineLength = lineLength;
	}


	public String getConnectorType() {
		return connectorType;
	}


	public void setConnectorType(String connectorType) {
		this.connectorType = connectorType;
	}


	public BigDecimal getPoleNo() {
		return poleNo;
	}


	public void setPoleNo(BigDecimal poleNo) {
		this.poleNo = poleNo;
	}


	public BigDecimal getStayNo() {
		return stayNo;
	}


	public void setStayNo(BigDecimal stayNo) {
		this.stayNo = stayNo;
	}


	public BigDecimal getStrutNo() {
		return strutNo;
	}


	public void setStrutNo(BigDecimal strutNo) {
		this.strutNo = strutNo;
	}


	public BigDecimal getSpan() {
		return span;
	}


	public void setSpan(BigDecimal span) {
		this.span = span;
	}
	
	@Override
	public String toString() {
		return "StdDetail [serviceDistance=" + serviceDistance
				+ ", lineLength=" + lineLength + ", connectorType="
				+ connectorType + ", poleNo=" + poleNo + ", stayNo=" + stayNo
				+ ", strutNo=" + strutNo + ", span=" + span + "]";
	}


}
