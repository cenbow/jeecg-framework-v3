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
/*     */ @Table(name="act_hi_procinst", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"proc_def_id_", "business_key_"}), @javax.persistence.UniqueConstraint(columnNames={"proc_inst_id_"})})
/*     */ public class ActHiProcinst
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String procInstId;
/*     */   private String businessKey;
/*     */   private String procDefId;
/*     */   private Timestamp startTime;
/*     */   private Timestamp endTime;
/*     */   private Long duration;
/*     */   private String startUserId;
/*     */   private String startActId;
/*     */   private String endActId;
/*     */   private String superProcessInstanceId;
/*     */   private String deleteReason;
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
/*     */   @Column(name="proc_inst_id_", unique=true, nullable=false, length=64)
/*     */   public String getProcInstId() {
/*  49 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  53 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="business_key_")
/*     */   public String getBusinessKey() {
/*  58 */     return this.businessKey;
/*     */   }
/*     */ 
/*     */   public void setBusinessKey(String businessKey) {
/*  62 */     this.businessKey = businessKey;
/*     */   }
/*     */   @Column(name="proc_def_id_", nullable=false, length=64)
/*     */   public String getProcDefId() {
/*  67 */     return this.procDefId;
/*     */   }
/*     */ 
/*     */   public void setProcDefId(String procDefId) {
/*  71 */     this.procDefId = procDefId;
/*     */   }
/*     */   @Column(name="start_time_", nullable=false, length=29)
/*     */   public Timestamp getStartTime() {
/*  76 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Timestamp startTime) {
/*  80 */     this.startTime = startTime;
/*     */   }
/*     */   @Column(name="end_time_", length=29)
/*     */   public Timestamp getEndTime() {
/*  85 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Timestamp endTime) {
/*  89 */     this.endTime = endTime;
/*     */   }
/*     */   @Column(name="duration_")
/*     */   public Long getDuration() {
/*  94 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(Long duration) {
/*  98 */     this.duration = duration;
/*     */   }
/*     */   @Column(name="start_user_id_")
/*     */   public String getStartUserId() {
/* 103 */     return this.startUserId;
/*     */   }
/*     */ 
/*     */   public void setStartUserId(String startUserId) {
/* 107 */     this.startUserId = startUserId;
/*     */   }
/*     */   @Column(name="start_act_id_")
/*     */   public String getStartActId() {
/* 112 */     return this.startActId;
/*     */   }
/*     */ 
/*     */   public void setStartActId(String startActId) {
/* 116 */     this.startActId = startActId;
/*     */   }
/*     */   @Column(name="end_act_id_")
/*     */   public String getEndActId() {
/* 121 */     return this.endActId;
/*     */   }
/*     */ 
/*     */   public void setEndActId(String endActId) {
/* 125 */     this.endActId = endActId;
/*     */   }
/*     */   @Column(name="super_process_instance_id_", length=64)
/*     */   public String getSuperProcessInstanceId() {
/* 130 */     return this.superProcessInstanceId;
/*     */   }
/*     */ 
/*     */   public void setSuperProcessInstanceId(String superProcessInstanceId) {
/* 134 */     this.superProcessInstanceId = superProcessInstanceId;
/*     */   }
/*     */   @Column(name="delete_reason_", length=4000)
/*     */   public String getDeleteReason() {
/* 139 */     return this.deleteReason;
/*     */   }
/*     */ 
/*     */   public void setDeleteReason(String deleteReason) {
/* 143 */     this.deleteReason = deleteReason;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiProcinst
 * JD-Core Version:    0.6.0
 */