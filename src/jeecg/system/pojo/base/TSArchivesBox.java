package jeecg.system.pojo.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * 
 * @ClassName: TSArchivesBox
 * @Description: TODO(档案盒)
 */
@Entity
@Table(name = "t_s_archivesbox")
public class TSArchivesBox extends IdEntity implements Serializable {
	private String archiveTitle;// 档案盒标题
	private String archiveUtil;// 建档单位名称
	private String archiveNum;// 档案号
	private Date archiveCreateData;// 建档时间
	private String archiveCustodyTime;// 保管期限
	private String archiveSecurity;// 密级
	private Short archiveState;//状态：0入柜 1未入柜
	private TSArchivesCabinet TSArchivesCabinet = new TSArchivesCabinet();// 所属档案柜

	@Column(name = "archivetitle", length = 50)
	public String getArchiveTitle() {
		return archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	@Column(name = "archiveutil", length = 50)
	public String getArchiveUtil() {
		return archiveUtil;
	}

	public void setArchiveUtil(String archiveUtil) {
		this.archiveUtil = archiveUtil;
	}

	@Column(name = "archivenum", length = 10)
	public String getArchiveNum() {
		return archiveNum;
	}

	public void setArchiveNum(String archiveNum) {
		this.archiveNum = archiveNum;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "archivecreatedata", length = 35)
	public Date getArchiveCreateData() {
		return archiveCreateData;
	}

	public void setArchiveCreateData(Date archiveCreateData) {
		this.archiveCreateData = archiveCreateData;
	}

	@Column(name = "archivecustodytime", length = 20)
	public String getArchiveCustodyTime() {
		return archiveCustodyTime;
	}

	public void setArchiveCustodyTime(String archiveCustodyTime) {
		this.archiveCustodyTime = archiveCustodyTime;
	}

	@Column(name = "archivesecurity", length = 20)
	public String getArchiveSecurity() {
		return archiveSecurity;
	}

	public void setArchiveSecurity(String archiveSecurity) {
		this.archiveSecurity = archiveSecurity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cabinetid")
	public TSArchivesCabinet getTSArchivesCabinet() {
		return TSArchivesCabinet;
	}

	public void setTSArchivesCabinet(TSArchivesCabinet tSArchivesCabinet) {
		TSArchivesCabinet = tSArchivesCabinet;
	}
	@Column(name = "archivestate")
	public Short getArchiveState() {
		return archiveState;
	}

	public void setArchiveState(Short archiveState) {
		this.archiveState = archiveState;
	}
}
