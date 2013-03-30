/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
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
/*     */ @Table(name="act_ru_task")
/*     */ public class ActRuTask
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActRuExecution actRuExecutionByExecutionId;
/*     */   private ActReProcdef actReProcdef;
/*     */   private ActRuExecution actRuExecutionByProcInstId;
/*     */   private Integer rev;
/*     */   private String name;
/*     */   private String parentTaskId;
/*     */   private String description;
/*     */   private String taskDefKey;
/*     */   private String owner;
/*     */   private String assignee;
/*     */   private String delegation;
/*     */   private Integer priority;
/*     */   private Timestamp createTime;
/*     */   private Timestamp dueDate;
/*     */   private Integer suspensionState;
/*  44 */   private Set<ActRuIdentitylink> actRuIdentitylinks = new HashSet(0);
/*     */ 
/*  51 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   @Column(name="id_", unique=true, nullable=false, length=64)
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  55 */     this.id = id;
/*     */   }
/*  61 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="execution_id_")
/*     */   public ActRuExecution getActRuExecutionByExecutionId() { return this.actRuExecutionByExecutionId; }
/*     */ 
/*     */   public void setActRuExecutionByExecutionId(ActRuExecution actRuExecutionByExecutionId)
/*     */   {
/*  65 */     this.actRuExecutionByExecutionId = actRuExecutionByExecutionId;
/*     */   }
/*  71 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_def_id_")
/*     */   public ActReProcdef getActReProcdef() { return this.actReProcdef; }
/*     */ 
/*     */   public void setActReProcdef(ActReProcdef actReProcdef)
/*     */   {
/*  75 */     this.actReProcdef = actReProcdef;
/*     */   }
/*  81 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_inst_id_")
/*     */   public ActRuExecution getActRuExecutionByProcInstId() { return this.actRuExecutionByProcInstId; }
/*     */ 
/*     */   public void setActRuExecutionByProcInstId(ActRuExecution actRuExecutionByProcInstId)
/*     */   {
/*  85 */     this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  90 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  94 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  99 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 103 */     this.name = name;
/*     */   }
/*     */   @Column(name="parent_task_id_", length=64)
/*     */   public String getParentTaskId() {
/* 108 */     return this.parentTaskId;
/*     */   }
/*     */ 
/*     */   public void setParentTaskId(String parentTaskId) {
/* 112 */     this.parentTaskId = parentTaskId;
/*     */   }
/*     */   @Column(name="description_", length=4000)
/*     */   public String getDescription() {
/* 117 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 121 */     this.description = description;
/*     */   }
/*     */   @Column(name="task_def_key_")
/*     */   public String getTaskDefKey() {
/* 126 */     return this.taskDefKey;
/*     */   }
/*     */ 
/*     */   public void setTaskDefKey(String taskDefKey) {
/* 130 */     this.taskDefKey = taskDefKey;
/*     */   }
/*     */   @Column(name="owner_")
/*     */   public String getOwner() {
/* 135 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(String owner) {
/* 139 */     this.owner = owner;
/*     */   }
/*     */   @Column(name="assignee_")
/*     */   public String getAssignee() {
/* 144 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 148 */     this.assignee = assignee;
/*     */   }
/*     */   @Column(name="delegation_", length=64)
/*     */   public String getDelegation() {
/* 153 */     return this.delegation;
/*     */   }
/*     */ 
/*     */   public void setDelegation(String delegation) {
/* 157 */     this.delegation = delegation;
/*     */   }
/*     */   @Column(name="priority_")
/*     */   public Integer getPriority() {
/* 162 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 166 */     this.priority = priority;
/*     */   }
/*     */   @Column(name="create_time_", length=29)
/*     */   public Timestamp getCreateTime() {
/* 171 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Timestamp createTime) {
/* 175 */     this.createTime = createTime;
/*     */   }
/*     */   @Column(name="due_date_", length=29)
/*     */   public Timestamp getDueDate() {
/* 180 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */   public void setDueDate(Timestamp dueDate) {
/* 184 */     this.dueDate = dueDate;
/*     */   }
/*     */   @Column(name="suspension_state_")
/*     */   public Integer getSuspensionState() {
/* 189 */     return this.suspensionState;
/*     */   }
/*     */ 
/*     */   public void setSuspensionState(Integer suspensionState) {
/* 193 */     this.suspensionState = suspensionState;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actRuTask")
/*     */   public Set<ActRuIdentitylink> getActRuIdentitylinks() {
/* 198 */     return this.actRuIdentitylinks;
/*     */   }
/*     */ 
/*     */   public void setActRuIdentitylinks(Set<ActRuIdentitylink> actRuIdentitylinks) {
/* 202 */     this.actRuIdentitylinks = actRuIdentitylinks;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuTask
 * JD-Core Version:    0.6.0
 */