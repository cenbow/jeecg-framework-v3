package jeecg.system.pojo.base;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

/**
 * TMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_message")
public class TSMessage extends IdEntity implements java.io.Serializable {
	private TSUser TSUser;
	private String messagename;
	private String messagecontent;
	private Timestamp sendtime;
	private String sendto;
	private Short status;
	private String note;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public TSUser getTSUser() {
		return this.TSUser;
	}

	public void setTSUser(TSUser TSUser) {
		this.TSUser = TSUser;
	}

	@Column(name = "messagename", length = 50)
	public String getMessagename() {
		return this.messagename;
	}

	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}

	@Column(name = "messagecontent", length = 500)
	public String getMessagecontent() {
		return this.messagecontent;
	}

	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	@Column(name = "sendtime", length = 35)
	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "sendto", length = 100)
	public String getSendto() {
		return this.sendto;
	}

	public void setSendto(String sendto) {
		this.sendto = sendto;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}