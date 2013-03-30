/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_ru_job")
/*     */ public class ActRuJob
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActGeBytearray actGeBytearray;
/*     */   private Integer rev;
/*     */   private String type;
/*     */   private Timestamp lockExpTime;
/*     */   private String lockOwner;
/*     */   private Boolean exclusive;
/*     */   private String executionId;
/*     */   private String processInstanceId;
/*     */   private String procDefId;
/*     */   private Integer retries;
/*     */   private String exceptionMsg;
/*     */   private Timestamp duedate;
/*     */   private String repeat;
/*     */   private String handlerType;
/*     */   private String handlerCfg;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  49 */     this.id = id;
/*     */   }
/*  55 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="exception_stack_id_")
/*     */   public ActGeBytearray getActGeBytearray() { return this.actGeBytearray; }
/*     */ 
/*     */   public void setActGeBytearray(ActGeBytearray actGeBytearray)
/*     */   {
/*  59 */     this.actGeBytearray = actGeBytearray;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  64 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  68 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="type_", nullable=false)
/*     */   public String getType() {
/*  73 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  77 */     this.type = type;
/*     */   }
/*     */   @Column(name="lock_exp_time_", length=29)
/*     */   public Timestamp getLockExpTime() {
/*  82 */     return this.lockExpTime;
/*     */   }
/*     */ 
/*     */   public void setLockExpTime(Timestamp lockExpTime) {
/*  86 */     this.lockExpTime = lockExpTime;
/*     */   }
/*     */   @Column(name="lock_owner_")
/*     */   public String getLockOwner() {
/*  91 */     return this.lockOwner;
/*     */   }
/*     */ 
/*     */   public void setLockOwner(String lockOwner) {
/*  95 */     this.lockOwner = lockOwner;
/*     */   }
/*     */   @Column(name="exclusive_")
/*     */   public Boolean getExclusive() {
/* 100 */     return this.exclusive;
/*     */   }
/*     */ 
/*     */   public void setExclusive(Boolean exclusive) {
/* 104 */     this.exclusive = exclusive;
/*     */   }
/*     */   @Column(name="execution_id_", length=64)
/*     */   public String getExecutionId() {
/* 109 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/* 113 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="process_instance_id_", length=64)
/*     */   public String getProcessInstanceId() {
/* 118 */     return this.processInstanceId;
/*     */   }
/*     */ 
/*     */   public void setProcessInstanceId(String processInstanceId) {
/* 122 */     this.processInstanceId = processInstanceId;
/*     */   }
/*     */   @Column(name="proc_def_id_", length=64)
/*     */   public String getProcDefId() {
/* 127 */     return this.procDefId;
/*     */   }
/*     */ 
/*     */   public void setProcDefId(String procDefId) {
/* 131 */     this.procDefId = procDefId;
/*     */   }
/*     */   @Column(name="retries_")
/*     */   public Integer getRetries() {
/* 136 */     return this.retries;
/*     */   }
/*     */ 
/*     */   public void setRetries(Integer retries) {
/* 140 */     this.retries = retries;
/*     */   }
/*     */   @Column(name="exception_msg_", length=4000)
/*     */   public String getExceptionMsg() {
/* 145 */     return this.exceptionMsg;
/*     */   }
/*     */ 
/*     */   public void setExceptionMsg(String exceptionMsg) {
/* 149 */     this.exceptionMsg = exceptionMsg;
/*     */   }
/*     */   @Column(name="duedate_", length=29)
/*     */   public Timestamp getDuedate() {
/* 154 */     return this.duedate;
/*     */   }
/*     */ 
/*     */   public void setDuedate(Timestamp duedate) {
/* 158 */     this.duedate = duedate;
/*     */   }
/*     */   @Column(name="repeat_")
/*     */   public String getRepeat() {
/* 163 */     return this.repeat;
/*     */   }
/*     */ 
/*     */   public void setRepeat(String repeat) {
/* 167 */     this.repeat = repeat;
/*     */   }
/*     */   @Column(name="handler_type_")
/*     */   public String getHandlerType() {
/* 172 */     return this.handlerType;
/*     */   }
/*     */ 
/*     */   public void setHandlerType(String handlerType) {
/* 176 */     this.handlerType = handlerType;
/*     */   }
/*     */   @Column(name="handler_cfg_", length=4000)
/*     */   public String getHandlerCfg() {
/* 181 */     return this.handlerCfg;
/*     */   }
/*     */ 
/*     */   public void setHandlerCfg(String handlerCfg) {
/* 185 */     this.handlerCfg = handlerCfg;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuJob
 * JD-Core Version:    0.6.0
 */