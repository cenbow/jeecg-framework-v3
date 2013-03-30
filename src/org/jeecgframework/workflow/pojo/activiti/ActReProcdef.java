/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_re_procdef", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"key_", "version_"})})
/*     */ public class ActReProcdef
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer rev;
/*     */   private String category;
/*     */   private String name;
/*     */   private String key;
/*     */   private Integer version;
/*     */   private String deploymentId;
/*     */   private String resourceName;
/*     */   private String dgrmResourceName;
/*     */   private String description;
/*     */   private Boolean hasStartFormKey;
/*     */   private Integer suspensionState;
/*  38 */   private Set<ActRuTask> actRuTasks = new HashSet(0);
/*  39 */   private Set<ActRuExecution> actRuExecutions = new HashSet(0);
/*  40 */   private Set<ActRuIdentitylink> actRuIdentitylinks = new HashSet(0);
/*     */ 
/*  46 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  50 */     this.id = id;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  55 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  59 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="category_")
/*     */   public String getCategory() {
/*  64 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(String category) {
/*  68 */     this.category = category;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  73 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  77 */     this.name = name;
/*     */   }
/*     */   @Column(name="key_", nullable=false)
/*     */   public String getKey() {
/*  82 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/*  86 */     this.key = key;
/*     */   }
/*     */   @Column(name="version_", nullable=false)
/*     */   public Integer getVersion() {
/*  91 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(Integer version) {
/*  95 */     this.version = version;
/*     */   }
/*     */   @Column(name="deployment_id_", length=64)
/*     */   public String getDeploymentId() {
/* 100 */     return this.deploymentId;
/*     */   }
/*     */ 
/*     */   public void setDeploymentId(String deploymentId) {
/* 104 */     this.deploymentId = deploymentId;
/*     */   }
/*     */   @Column(name="resource_name_", length=4000)
/*     */   public String getResourceName() {
/* 109 */     return this.resourceName;
/*     */   }
/*     */ 
/*     */   public void setResourceName(String resourceName) {
/* 113 */     this.resourceName = resourceName;
/*     */   }
/*     */   @Column(name="dgrm_resource_name_", length=4000)
/*     */   public String getDgrmResourceName() {
/* 118 */     return this.dgrmResourceName;
/*     */   }
/*     */ 
/*     */   public void setDgrmResourceName(String dgrmResourceName) {
/* 122 */     this.dgrmResourceName = dgrmResourceName;
/*     */   }
/*     */   @Column(name="description_", length=4000)
/*     */   public String getDescription() {
/* 127 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 131 */     this.description = description;
/*     */   }
/*     */   @Column(name="has_start_form_key_")
/*     */   public Boolean getHasStartFormKey() {
/* 136 */     return this.hasStartFormKey;
/*     */   }
/*     */ 
/*     */   public void setHasStartFormKey(Boolean hasStartFormKey) {
/* 140 */     this.hasStartFormKey = hasStartFormKey;
/*     */   }
/*     */   @Column(name="suspension_state_")
/*     */   public Integer getSuspensionState() {
/* 145 */     return this.suspensionState;
/*     */   }
/*     */ 
/*     */   public void setSuspensionState(Integer suspensionState) {
/* 149 */     this.suspensionState = suspensionState;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actReProcdef")
/*     */   public Set<ActRuTask> getActRuTasks() {
/* 154 */     return this.actRuTasks;
/*     */   }
/*     */ 
/*     */   public void setActRuTasks(Set<ActRuTask> actRuTasks) {
/* 158 */     this.actRuTasks = actRuTasks;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actReProcdef")
/*     */   public Set<ActRuExecution> getActRuExecutions() {
/* 163 */     return this.actRuExecutions;
/*     */   }
/*     */ 
/*     */   public void setActRuExecutions(Set<ActRuExecution> actRuExecutions) {
/* 167 */     this.actRuExecutions = actRuExecutions;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actReProcdef")
/*     */   public Set<ActRuIdentitylink> getActRuIdentitylinks() {
/* 172 */     return this.actRuIdentitylinks;
/*     */   }
/*     */ 
/*     */   public void setActRuIdentitylinks(Set<ActRuIdentitylink> actRuIdentitylinks) {
/* 176 */     this.actRuIdentitylinks = actRuIdentitylinks;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActReProcdef
 * JD-Core Version:    0.6.0
 */