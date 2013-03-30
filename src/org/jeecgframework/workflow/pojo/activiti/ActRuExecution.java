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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_ru_execution", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"proc_def_id_", "business_key_"})})
/*     */ public class ActRuExecution
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActRuExecution actRuExecutionByParentId;
/*     */   private ActRuExecution actRuExecutionBySuperExec;
/*     */   private ActReProcdef actReProcdef;
/*     */   private ActRuExecution actRuExecutionByProcInstId;
/*     */   private Integer rev;
/*     */   private String businessKey;
/*     */   private String actId;
/*     */   private Boolean isActive;
/*     */   private Boolean isConcurrent;
/*     */   private Boolean isScope;
/*     */   private Boolean isEventScope;
/*     */   private Integer suspensionState;
/*     */   private Integer cachedEntState;
/*  42 */   private Set<ActRuVariable> actRuVariablesForExecutionId = new HashSet(0);
/*  43 */   private Set<ActRuExecution> actRuExecutionsForParentId = new HashSet(0);
/*  44 */   private Set<ActRuVariable> actRuVariablesForProcInstId = new HashSet(0);
/*  45 */   private Set<ActRuTask> actRuTasksForProcInstId = new HashSet(0);
/*  46 */   private Set<ActRuExecution> actRuExecutionsForProcInstId = new HashSet(0);
/*  47 */   private Set<ActRuExecution> actRuExecutionsForSuperExec = new HashSet(0);
/*  48 */   private Set<ActRuTask> actRuTasksForExecutionId = new HashSet(0);
/*  49 */   private Set<ActRuEventSubscr> actRuEventSubscrs = new HashSet(0);
/*     */ 
/*  55 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  59 */     this.id = id;
/*     */   }
/*  65 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="parent_id_")
/*     */   public ActRuExecution getActRuExecutionByParentId() { return this.actRuExecutionByParentId; }
/*     */ 
/*     */   public void setActRuExecutionByParentId(ActRuExecution actRuExecutionByParentId)
/*     */   {
/*  69 */     this.actRuExecutionByParentId = actRuExecutionByParentId;
/*     */   }
/*  75 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="super_exec_")
/*     */   public ActRuExecution getActRuExecutionBySuperExec() { return this.actRuExecutionBySuperExec; }
/*     */ 
/*     */   public void setActRuExecutionBySuperExec(ActRuExecution actRuExecutionBySuperExec)
/*     */   {
/*  79 */     this.actRuExecutionBySuperExec = actRuExecutionBySuperExec;
/*     */   }
/*  85 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_def_id_")
/*     */   public ActReProcdef getActReProcdef() { return this.actReProcdef; }
/*     */ 
/*     */   public void setActReProcdef(ActReProcdef actReProcdef)
/*     */   {
/*  89 */     this.actReProcdef = actReProcdef;
/*     */   }
/*  95 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_inst_id_")
/*     */   public ActRuExecution getActRuExecutionByProcInstId() { return this.actRuExecutionByProcInstId; }
/*     */ 
/*     */   public void setActRuExecutionByProcInstId(ActRuExecution actRuExecutionByProcInstId)
/*     */   {
/*  99 */     this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/* 104 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/* 108 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="business_key_")
/*     */   public String getBusinessKey() {
/* 113 */     return this.businessKey;
/*     */   }
/*     */ 
/*     */   public void setBusinessKey(String businessKey) {
/* 117 */     this.businessKey = businessKey;
/*     */   }
/*     */   @Column(name="act_id_")
/*     */   public String getActId() {
/* 122 */     return this.actId;
/*     */   }
/*     */ 
/*     */   public void setActId(String actId) {
/* 126 */     this.actId = actId;
/*     */   }
/*     */   @Column(name="is_active_")
/*     */   public Boolean getIsActive() {
/* 131 */     return this.isActive;
/*     */   }
/*     */ 
/*     */   public void setIsActive(Boolean isActive) {
/* 135 */     this.isActive = isActive;
/*     */   }
/*     */   @Column(name="is_concurrent_")
/*     */   public Boolean getIsConcurrent() {
/* 140 */     return this.isConcurrent;
/*     */   }
/*     */ 
/*     */   public void setIsConcurrent(Boolean isConcurrent) {
/* 144 */     this.isConcurrent = isConcurrent;
/*     */   }
/*     */   @Column(name="is_scope_")
/*     */   public Boolean getIsScope() {
/* 149 */     return this.isScope;
/*     */   }
/*     */ 
/*     */   public void setIsScope(Boolean isScope) {
/* 153 */     this.isScope = isScope;
/*     */   }
/*     */   @Column(name="is_event_scope_")
/*     */   public Boolean getIsEventScope() {
/* 158 */     return this.isEventScope;
/*     */   }
/*     */ 
/*     */   public void setIsEventScope(Boolean isEventScope) {
/* 162 */     this.isEventScope = isEventScope;
/*     */   }
/*     */   @Column(name="suspension_state_")
/*     */   public Integer getSuspensionState() {
/* 167 */     return this.suspensionState;
/*     */   }
/*     */ 
/*     */   public void setSuspensionState(Integer suspensionState) {
/* 171 */     this.suspensionState = suspensionState;
/*     */   }
/*     */   @Column(name="cached_ent_state_")
/*     */   public Integer getCachedEntState() {
/* 176 */     return this.cachedEntState;
/*     */   }
/*     */ 
/*     */   public void setCachedEntState(Integer cachedEntState) {
/* 180 */     this.cachedEntState = cachedEntState;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByExecutionId")
/*     */   public Set<ActRuVariable> getActRuVariablesForExecutionId() {
/* 185 */     return this.actRuVariablesForExecutionId;
/*     */   }
/*     */ 
/*     */   public void setActRuVariablesForExecutionId(Set<ActRuVariable> actRuVariablesForExecutionId) {
/* 189 */     this.actRuVariablesForExecutionId = actRuVariablesForExecutionId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByParentId")
/*     */   public Set<ActRuExecution> getActRuExecutionsForParentId() {
/* 194 */     return this.actRuExecutionsForParentId;
/*     */   }
/*     */ 
/*     */   public void setActRuExecutionsForParentId(Set<ActRuExecution> actRuExecutionsForParentId) {
/* 198 */     this.actRuExecutionsForParentId = actRuExecutionsForParentId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByProcInstId")
/*     */   public Set<ActRuVariable> getActRuVariablesForProcInstId() {
/* 203 */     return this.actRuVariablesForProcInstId;
/*     */   }
/*     */ 
/*     */   public void setActRuVariablesForProcInstId(Set<ActRuVariable> actRuVariablesForProcInstId) {
/* 207 */     this.actRuVariablesForProcInstId = actRuVariablesForProcInstId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByProcInstId")
/*     */   public Set<ActRuTask> getActRuTasksForProcInstId() {
/* 212 */     return this.actRuTasksForProcInstId;
/*     */   }
/*     */ 
/*     */   public void setActRuTasksForProcInstId(Set<ActRuTask> actRuTasksForProcInstId) {
/* 216 */     this.actRuTasksForProcInstId = actRuTasksForProcInstId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByProcInstId")
/*     */   public Set<ActRuExecution> getActRuExecutionsForProcInstId() {
/* 221 */     return this.actRuExecutionsForProcInstId;
/*     */   }
/*     */ 
/*     */   public void setActRuExecutionsForProcInstId(Set<ActRuExecution> actRuExecutionsForProcInstId) {
/* 225 */     this.actRuExecutionsForProcInstId = actRuExecutionsForProcInstId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionBySuperExec")
/*     */   public Set<ActRuExecution> getActRuExecutionsForSuperExec() {
/* 230 */     return this.actRuExecutionsForSuperExec;
/*     */   }
/*     */ 
/*     */   public void setActRuExecutionsForSuperExec(Set<ActRuExecution> actRuExecutionsForSuperExec) {
/* 234 */     this.actRuExecutionsForSuperExec = actRuExecutionsForSuperExec;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecutionByExecutionId")
/*     */   public Set<ActRuTask> getActRuTasksForExecutionId() {
/* 239 */     return this.actRuTasksForExecutionId;
/*     */   }
/*     */ 
/*     */   public void setActRuTasksForExecutionId(Set<ActRuTask> actRuTasksForExecutionId) {
/* 243 */     this.actRuTasksForExecutionId = actRuTasksForExecutionId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuExecution")
/*     */   public Set<ActRuEventSubscr> getActRuEventSubscrs() {
/* 248 */     return this.actRuEventSubscrs;
/*     */   }
/*     */ 
/*     */   public void setActRuEventSubscrs(Set<ActRuEventSubscr> actRuEventSubscrs) {
/* 252 */     this.actRuEventSubscrs = actRuEventSubscrs;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuExecution
 * JD-Core Version:    0.6.0
 */