package jeecg.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.workflow.pojo.base.TPProcess;

/**
 * 业务配置表
 */
@Entity
@Table(name = "t_s_busconfig")
public class TSBusConfig extends IdEntity implements java.io.Serializable {
	private String busname;//业务名称
	private TSTable TSTable=new TSTable();//业务表
	private TPProcess TPProcess=new TPProcess();
	@Column(name = "busname", length = 30)
	public String getBusname() {
		return busname;
	}

	public void setBusname(String busname) {
		this.busname = busname;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tableid")
	public TSTable getTSTable() {
		return TSTable;
	}

	public void setTSTable(TSTable tSTable) {
		TSTable = tSTable;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "processid")
	public TPProcess getTPProcess() {
		return TPProcess;
	}

	public void setTPProcess(TPProcess tPProcess) {
		TPProcess = tPProcess;
	}



}