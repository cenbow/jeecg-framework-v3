/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_hi_taskinst")
/*     */ public class ActHiTaskinst
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String procDefId;
/*     */   private String taskDefKey;
/*     */   private String procInstId;
/*     */   private String executionId;
/*     */   private String name;
/*     */   private String parentTaskId;
/*     */   private String description;
/*     */   private String owner;
/*     */   private String assignee;
/*     */   private Timestamp startTime;
/*     */   private Timestamp endTime;
/*     */   private Long duration;
/*     */   private String deleteReason;
/*     */   private Integer priority;
/*     */   private Timestamp dueDate;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  42 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  46 */     this.id = id;
/*     */   }
/*     */   @Column(name="proc_def_id_", length=64)
/*     */   public String getProcDefId() {
/*  51 */     return this.procDefId;
/*     */   }
/*     */ 
/*     */   public void setProcDefId(String procDefId) {
/*  55 */     this.procDefId = procDefId;
/*     */   }
/*     */   @Column(name="task_def_key_")
/*     */   public String getTaskDefKey() {
/*  60 */     return this.taskDefKey;
/*     */   }
/*     */ 
/*     */   public void setTaskDefKey(String taskDefKey) {
/*  64 */     this.taskDefKey = taskDefKey;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  69 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  73 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="execution_id_", length=64)
/*     */   public String getExecutionId() {
/*  78 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  82 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  87 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  91 */     this.name = name;
/*     */   }
/*     */   @Column(name="parent_task_id_", length=64)
/*     */   public String getParentTaskId() {
/*  96 */     return this.parentTaskId;
/*     */   }
/*     */ 
/*     */   public void setParentTaskId(String parentTaskId) {
/* 100 */     this.parentTaskId = parentTaskId;
/*     */   }
/*     */   @Column(name="description_", length=4000)
/*     */   public String getDescription() {
/* 105 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 109 */     this.description = description;
/*     */   }
/*     */   @Column(name="owner_")
/*     */   public String getOwner() {
/* 114 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(String owner) {
/* 118 */     this.owner = owner;
/*     */   }
/*     */   @Column(name="assignee_")
/*     */   public String getAssignee() {
/* 123 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 127 */     this.assignee = assignee;
/*     */   }
/*     */   @Column(name="start_time_", nullable=false, length=29)
/*     */   public Timestamp getStartTime() {
/* 132 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Timestamp startTime) {
/* 136 */     this.startTime = startTime;
/*     */   }
/*     */   @Column(name="end_time_", length=29)
/*     */   public Timestamp getEndTime() {
/* 141 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Timestamp endTime) {
/* 145 */     this.endTime = endTime;
/*     */   }
/*     */   @Column(name="duration_")
/*     */   public Long getDuration() {
/* 150 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(Long duration) {
/* 154 */     this.duration = duration;
/*     */   }
/*     */   @Column(name="delete_reason_", length=4000)
/*     */   public String getDeleteReason() {
/* 159 */     return this.deleteReason;
/*     */   }
/*     */ 
/*     */   public void setDeleteReason(String deleteReason) {
/* 163 */     this.deleteReason = deleteReason;
/*     */   }
/*     */   @Column(name="priority_")
/*     */   public Integer getPriority() {
/* 168 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 172 */     this.priority = priority;
/*     */   }
/*     */   @Column(name="due_date_", length=29)
/*     */   public Timestamp getDueDate() {
/* 177 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */   public void setDueDate(Timestamp dueDate) {
/* 181 */     this.dueDate = dueDate;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiTaskinst
 * JD-Core Version:    0.6.0
 */