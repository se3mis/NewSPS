package security.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the MSG_POOL database table.
 * 
 */
@Entity
@Table(name="MSG_POOL")
public class MsgPool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MSG_ID")
	private String msgId;

    @Temporal( TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	private String msg;

	@Column(name="MSG_CATEGORY")
	private String msgCategory;

	@Column(name="MSG_CODE")
	private String msgCode;

	@Column(name="MSG_TYPE")
	private String msgType;

	private String status;

    public MsgPool() {
    }

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgCategory() {
		return this.msgCategory;
	}

	public void setMsgCategory(String msgCategory) {
		this.msgCategory = msgCategory;
	}

	public String getMsgCode() {
		return this.msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MsgPool [msgId=" + msgId + ", addDate=" + addDate
				+ ", addTime=" + addTime + ", addUser=" + addUser + ", msg="
				+ msg + ", msgCategory=" + msgCategory + ", msgCode=" + msgCode
				+ ", msgType=" + msgType + ", status=" + status + "]";
	}

}