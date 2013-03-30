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
/*     */ @Table(name="act_hi_actinst")
/*     */ public class ActHiActinst
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String procDefId;
/*     */   private String procInstId;
/*     */   private String executionId;
/*     */   private String actId;
/*     */   private String taskId;
/*     */   private String callProcInstId;
/*     */   private String actName;
/*     */   private String actType;
/*     */   private String assignee;
/*     */   private Timestamp startTime;
/*     */   private Timestamp endTime;
/*     */   private Long duration;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   @Column(name="id_", unique=true, nullable=false, length=64)
/*     */   public String getId()
/*     */   {
/*  40 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  44 */     this.id = id;
/*     */   }
/*     */   @Column(name="proc_def_id_", nullable=false, length=64)
/*     */   public String getProcDefId() {
/*  49 */     return this.procDefId;
/*     */   }
/*     */ 
/*     */   public void setProcDefId(String procDefId) {
/*  53 */     this.procDefId = procDefId;
/*     */   }
/*     */   @Column(name="proc_inst_id_", nullable=false, length=64)
/*     */   public String getProcInstId() {
/*  58 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  62 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="execution_id_", nullable=false, length=64)
/*     */   public String getExecutionId() {
/*  67 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  71 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="act_id_", nullable=false)
/*     */   public String getActId() {
/*  76 */     return this.actId;
/*     */   }
/*     */ 
/*     */   public void setActId(String actId) {
/*  80 */     this.actId = actId;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/*  85 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/*  89 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="call_proc_inst_id_", length=64)
/*     */   public String getCallProcInstId() {
/*  94 */     return this.callProcInstId;
/*     */   }
/*     */ 
/*     */   public void setCallProcInstId(String callProcInstId) {
/*  98 */     this.callProcInstId = callProcInstId;
/*     */   }
/*     */   @Column(name="act_name_")
/*     */   public String getActName() {
/* 103 */     return this.actName;
/*     */   }
/*     */ 
/*     */   public void setActName(String actName) {
/* 107 */     this.actName = actName;
/*     */   }
/*     */   @Column(name="act_type_", nullable=false)
/*     */   public String getActType() {
/* 112 */     return this.actType;
/*     */   }
/*     */ 
/*     */   public void setActType(String actType) {
/* 116 */     this.actType = actType;
/*     */   }
/*     */   @Column(name="assignee_", length=64)
/*     */   public String getAssignee() {
/* 121 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 125 */     this.assignee = assignee;
/*     */   }
/*     */   @Column(name="start_time_", nullable=false, length=29)
/*     */   public Timestamp getStartTime() {
/* 130 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Timestamp startTime) {
/* 134 */     this.startTime = startTime;
/*     */   }
/*     */   @Column(name="end_time_", length=29)
/*     */   public Timestamp getEndTime() {
/* 139 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Timestamp endTime) {
/* 143 */     this.endTime = endTime;
/*     */   }
/*     */   @Column(name="duration_")
/*     */   public Long getDuration() {
/* 148 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(Long duration) {
/* 152 */     this.duration = duration;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiActinst
 * JD-Core Version:    0.6.0
 */