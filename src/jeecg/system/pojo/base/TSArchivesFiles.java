package jeecg.system.pojo.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * @ClassName: TSArchivesFiles
 * @Description: TODO(文件档案盒关联类)
 */
@Entity
@Table(name = "t_s_archivesfiles")
public class TSArchivesFiles extends IdEntity implements Serializable {
	private TSArchivesBox TSArchivesBox;// 档案盒
	private String fileKey;// 文件主键
	private String entityName;// 文件所属实体

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesboxid")
	public TSArchivesBox getTSArchivesBox() {
		return TSArchivesBox;
	}

	public void setTSArchivesBox(TSArchivesBox tSArchivesBox) {
		TSArchivesBox = tSArchivesBox;
	}
	@Column(name = "filekey", length = 32)
	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	@Column(name = "entityname", length = 32)
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
