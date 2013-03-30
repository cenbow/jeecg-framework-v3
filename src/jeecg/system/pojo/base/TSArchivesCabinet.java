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
 * @ClassName: TSArchivesCabinet
 * @Description: TODO(档案柜)
 */
@Entity
@Table(name = "t_s_archivescabinet")
public class TSArchivesCabinet extends IdEntity implements Serializable {
	private String cabinetName;// 档案柜名称
	private TSUser TSUser;// 创建人
	private Date createDate;// 创建日期
	private String cabinetNum;// 档案柜编号

	@Column(name = "cabinetname", length = 32)
	public String getCabinetName() {
		return cabinetName;
	}

	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public TSUser getTSUser() {
		return TSUser;
	}

	public void setTSUser(TSUser tSUser) {
		TSUser = tSUser;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "createdate", length = 13)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "cabinetnum", length = 32)
	public String getCabinetNum() {
		return cabinetNum;
	}

	public void setCabinetNum(String cabinetNum) {
		this.cabinetNum = cabinetNum;
	}

}
