package job.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SPODRCRD database table.
 * 
 */
@Entity
public class Spodrcrd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpodrcrdPK id;

	@Column(name="AVG_CONSUMP")
	private BigDecimal avgConsump;

    @Temporal( TemporalType.DATE)
	@Column(name="CONNECTED_DATE")
	private Date connectedDate;

	@Column(name="INIT_READING_1")
	private BigDecimal initReading1;

	@Column(name="INIT_READING_2")
	private BigDecimal initReading2;

	@Column(name="INIT_READING_3")
	private BigDecimal initReading3;

	@Column(name="KVA_RATING")
	private BigDecimal kvaRating;

	@Column(name="METER_NO_1")
	private String meterNo1;

	@Column(name="METER_NO_2")
	private String meterNo2;

	@Column(name="METER_NO_3")
	private String meterNo3;

	@Column(name="METER_TYPE_1")
	private String meterType1;

	@Column(name="METER_TYPE_2")
	private String meterType2;

	@Column(name="METER_TYPE_3")
	private String meterType3;

	@Column(name="NO_OF_DIGITS_1")
	private BigDecimal noOfDigits1;

	@Column(name="NO_OF_DIGITS_2")
	private BigDecimal noOfDigits2;

	@Column(name="NO_OF_DIGITS_3")
	private BigDecimal noOfDigits3;

	@Column(name="NO_OF_METERS")
	private BigDecimal noOfMeters;

	@Column(name="OLD_ACC_NO")
	private String oldAccNo;

	@Column(name="PACK_NO")
	private String packNo;

	@Column(name="READER_CODE")
	private String readerCode;

	@Column(name="UPD_BY")
	private String updBy;

    @Temporal( TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="WALK_SEQ")
	private String walkSeq;

    public Spodrcrd() {
    }

	public SpodrcrdPK getId() {
		return this.id;
	}

	public void setId(SpodrcrdPK id) {
		this.id = id;
	}
	
	public BigDecimal getAvgConsump() {
		return this.avgConsump;
	}

	public void setAvgConsump(BigDecimal avgConsump) {
		this.avgConsump = avgConsump;
	}

	public Date getConnectedDate() {
		return this.connectedDate;
	}

	public void setConnectedDate(Date connectedDate) {
		this.connectedDate = connectedDate;
	}

	public BigDecimal getInitReading1() {
		return this.initReading1;
	}

	public void setInitReading1(BigDecimal initReading1) {
		this.initReading1 = initReading1;
	}

	public BigDecimal getInitReading2() {
		return this.initReading2;
	}

	public void setInitReading2(BigDecimal initReading2) {
		this.initReading2 = initReading2;
	}

	public BigDecimal getInitReading3() {
		return this.initReading3;
	}

	public void setInitReading3(BigDecimal initReading3) {
		this.initReading3 = initReading3;
	}

	public BigDecimal getKvaRating() {
		return this.kvaRating;
	}

	public void setKvaRating(BigDecimal kvaRating) {
		this.kvaRating = kvaRating;
	}

	public String getMeterNo1() {
		return this.meterNo1;
	}

	public void setMeterNo1(String meterNo1) {
		this.meterNo1 = meterNo1;
	}

	public String getMeterNo2() {
		return this.meterNo2;
	}

	public void setMeterNo2(String meterNo2) {
		this.meterNo2 = meterNo2;
	}

	public String getMeterNo3() {
		return this.meterNo3;
	}

	public void setMeterNo3(String meterNo3) {
		this.meterNo3 = meterNo3;
	}

	public String getMeterType1() {
		return this.meterType1;
	}

	public void setMeterType1(String meterType1) {
		this.meterType1 = meterType1;
	}

	public String getMeterType2() {
		return this.meterType2;
	}

	public void setMeterType2(String meterType2) {
		this.meterType2 = meterType2;
	}

	public String getMeterType3() {
		return this.meterType3;
	}

	public void setMeterType3(String meterType3) {
		this.meterType3 = meterType3;
	}

	public BigDecimal getNoOfDigits1() {
		return this.noOfDigits1;
	}

	public void setNoOfDigits1(BigDecimal noOfDigits1) {
		this.noOfDigits1 = noOfDigits1;
	}

	public BigDecimal getNoOfDigits2() {
		return this.noOfDigits2;
	}

	public void setNoOfDigits2(BigDecimal noOfDigits2) {
		this.noOfDigits2 = noOfDigits2;
	}

	public BigDecimal getNoOfDigits3() {
		return this.noOfDigits3;
	}

	public void setNoOfDigits3(BigDecimal noOfDigits3) {
		this.noOfDigits3 = noOfDigits3;
	}

	public BigDecimal getNoOfMeters() {
		return this.noOfMeters;
	}

	public void setNoOfMeters(BigDecimal noOfMeters) {
		this.noOfMeters = noOfMeters;
	}

	public String getOldAccNo() {
		return this.oldAccNo;
	}

	public void setOldAccNo(String oldAccNo) {
		this.oldAccNo = oldAccNo;
	}

	public String getPackNo() {
		return this.packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public String getReaderCode() {
		return this.readerCode;
	}

	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getWalkSeq() {
		return this.walkSeq;
	}

	public void setWalkSeq(String walkSeq) {
		this.walkSeq = walkSeq;
	}

}