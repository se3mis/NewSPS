package application.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICATION_REFERENCE database table.
 * 
 */
@Entity
@Table(name="APPLICATION_REFBS")
public class ApplicationRefBS implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationRefBSPK id;


	@Column(name="ID_NO")
	private String idNo;

	private String projectno;

	private String status;

    public ApplicationRefBS() {
    }


	

	public ApplicationRefBSPK getId() {
		return id;
	}




	public void setId(ApplicationRefBSPK id) {
		this.id = id;
	}




	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getProjectno() {
		return this.projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApplicationReference [id=" + id + ", idNo=" + idNo + ", projectno=" + projectno
				+ ", status=" + status + "]";
	}
	
	

}