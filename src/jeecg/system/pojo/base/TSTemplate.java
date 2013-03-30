package jeecg.system.pojo.base;

// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

/**
 * TTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_template")
public class TSTemplate extends IdEntity implements java.io.Serializable {
	private String templatename;
	private String templatepath;
	private byte[] templatecontent;
	private String templatecode;
	private Set<TSParameter> TSParameters = new HashSet<TSParameter>(0);
	@Column(name = "templatename", nullable = false, length = 100)
	public String getTemplatename() {
		return this.templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	@Column(name = "templatepath", length = 300)
	public String getTemplatepath() {
		return this.templatepath;
	}

	public void setTemplatepath(String templatepath) {
		this.templatepath = templatepath;
	}

	@Column(name = "templatecontent", length = 1)
	public byte[] getTemplatecontent() {
		return this.templatecontent;
	}

	public void setTemplatecontent(byte[] templatecontent) {
		this.templatecontent = templatecontent;
	}

	@Column(name = "templatecode", length = 20)
	public String getTemplatecode() {
		return this.templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSTemplate")
	public Set<TSParameter> getTSParameters() {
		return this.TSParameters;
	}

	public void setTSParameters(Set<TSParameter> TSParameters) {
		this.TSParameters = TSParameters;
	}

}