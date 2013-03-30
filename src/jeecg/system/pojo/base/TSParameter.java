package jeecg.system.pojo.base;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * TParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_parameter")
public class TSParameter extends IdEntity implements java.io.Serializable {
	private TSTemplate TSTemplate;
	private String parametername;
	private String parametercount;
	private String parameterremark;
	private String parametertag;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "templateid")
	public TSTemplate getTSTemplate() {
		return this.TSTemplate;
	}

	public void setTSTemplate(TSTemplate TSTemplate) {
		this.TSTemplate = TSTemplate;
	}

	@Column(name = "parametername", nullable = false, length = 100)
	public String getParametername() {
		return this.parametername;
	}

	public void setParametername(String parametername) {
		this.parametername = parametername;
	}

	@Column(name = "parametercount", length = 100)
	public String getParametercount() {
		return this.parametercount;
	}

	public void setParametercount(String parametercount) {
		this.parametercount = parametercount;
	}

	@Column(name = "parameterremark", length = 100)
	public String getParameterremark() {
		return this.parameterremark;
	}

	public void setParameterremark(String parameterremark) {
		this.parameterremark = parameterremark;
	}

	@Column(name = "parametertag", length = 100)
	public String getParametertag() {
		return this.parametertag;
	}

	public void setParametertag(String parametertag) {
		this.parametertag = parametertag;
	}

}