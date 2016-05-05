package security.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SAUGRPDL database table.
 * 
 */
@Entity
public class Saugrpdl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaugrpdlPK id;

	@Column(name="ACESS_AUTH")
	private BigDecimal acessAuth;

	@Column(name="APPL_ID")
	private String applId;

	@Column(name="CONF_BY")
	private String confBy;

    @Temporal( TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="DELETE_YN")
	private String deleteYn;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="EDIT_YN")
	private String editYn;

	@Column(name="ENT_BY")
	private String entBy;

    @Temporal( TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="FUNC_ID")
	private String funcId;

	@Column(name="GROUP_ID")
	private String groupId;

	@Column(name="INSERT_YN")
	private String insertYn;

    @Temporal( TemporalType.DATE)
	@Column(name="LOG_DATE")
	private Date logDate;

    @Temporal( TemporalType.DATE)
	@Column(name="LOG_TIME")
	private Date logTime;

	@Column(name="LVL_NO")
	private BigDecimal lvlNo;

	@Column(name="MODI_BY")
	private String modiBy;

    @Temporal( TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="MODI_INACT")
	private BigDecimal modiInact;

	private String oprn;

	@Column(name="PRINT_YN")
	private String printYn;

	@Column(name="PRNT_APPL")
	private String prntAppl;

	@Column(name="PRNT_DEPT")
	private String prntDept;

	@Column(name="PRNT_FUNC")
	private String prntFunc;

	@Column(name="PRNT_GRP_ID")
	private String prntGrpId;

	@Column(name="PRNT_ROL")
	private String prntRol;

	@Column(name="ROL_ID")
	private String rolId;

	@Column(name="SEQ_NO")
	private BigDecimal seqNo;

	private BigDecimal status;

    public Saugrpdl() {
    }

	public SaugrpdlPK getId() {
		return this.id;
	}

	public void setId(SaugrpdlPK id) {
		this.id = id;
	}
	
	public BigDecimal getAcessAuth() {
		return this.acessAuth;
	}

	public void setAcessAuth(BigDecimal acessAuth) {
		this.acessAuth = acessAuth;
	}

	public String getApplId() {
		return this.applId;
	}

	public void setApplId(String applId) {
		this.applId = applId;
	}

	public String getConfBy() {
		return this.confBy;
	}

	public void setConfBy(String confBy) {
		this.confBy = confBy;
	}

	public Date getConfDt() {
		return this.confDt;
	}

	public void setConfDt(Date confDt) {
		this.confDt = confDt;
	}

	public String getDeleteYn() {
		return this.deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEditYn() {
		return this.editYn;
	}

	public void setEditYn(String editYn) {
		this.editYn = editYn;
	}

	public String getEntBy() {
		return this.entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public Date getEntDt() {
		return this.entDt;
	}

	public void setEntDt(Date entDt) {
		this.entDt = entDt;
	}

	public String getFuncId() {
		return this.funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getInsertYn() {
		return this.insertYn;
	}

	public void setInsertYn(String insertYn) {
		this.insertYn = insertYn;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public BigDecimal getLvlNo() {
		return this.lvlNo;
	}

	public void setLvlNo(BigDecimal lvlNo) {
		this.lvlNo = lvlNo;
	}

	public String getModiBy() {
		return this.modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDt() {
		return this.modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	public BigDecimal getModiInact() {
		return this.modiInact;
	}

	public void setModiInact(BigDecimal modiInact) {
		this.modiInact = modiInact;
	}

	public String getOprn() {
		return this.oprn;
	}

	public void setOprn(String oprn) {
		this.oprn = oprn;
	}

	public String getPrintYn() {
		return this.printYn;
	}

	public void setPrintYn(String printYn) {
		this.printYn = printYn;
	}

	public String getPrntAppl() {
		return this.prntAppl;
	}

	public void setPrntAppl(String prntAppl) {
		this.prntAppl = prntAppl;
	}

	public String getPrntDept() {
		return this.prntDept;
	}

	public void setPrntDept(String prntDept) {
		this.prntDept = prntDept;
	}

	public String getPrntFunc() {
		return this.prntFunc;
	}

	public void setPrntFunc(String prntFunc) {
		this.prntFunc = prntFunc;
	}

	public String getPrntGrpId() {
		return this.prntGrpId;
	}

	public void setPrntGrpId(String prntGrpId) {
		this.prntGrpId = prntGrpId;
	}

	public String getPrntRol() {
		return this.prntRol;
	}

	public void setPrntRol(String prntRol) {
		this.prntRol = prntRol;
	}

	public String getRolId() {
		return this.rolId;
	}

	public void setRolId(String rolId) {
		this.rolId = rolId;
	}

	public BigDecimal getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}