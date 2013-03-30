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
/*     */ @Table(name="act_hi_comment")
/*     */ public class ActHiComment
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String type;
/*     */   private Timestamp time;
/*     */   private String userId;
/*     */   private String taskId;
/*     */   private String procInstId;
/*     */   private String action;
/*     */   private String message;
/*     */   private String fullMsg;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  35 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  39 */     this.id = id;
/*     */   }
/*     */   @Column(name="type_")
/*     */   public String getType() {
/*  44 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  48 */     this.type = type;
/*     */   }
/*     */   @Column(name="time_", nullable=false, length=29)
/*     */   public Timestamp getTime() {
/*  53 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Timestamp time) {
/*  57 */     this.time = time;
/*     */   }
/*     */   @Column(name="user_id_")
/*     */   public String getUserId() {
/*  62 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId) {
/*  66 */     this.userId = userId;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/*  71 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/*  75 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  80 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  84 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="action_")
/*     */   public String getAction() {
/*  89 */     return this.action;
/*     */   }
/*     */ 
/*     */   public void setAction(String action) {
/*  93 */     this.action = action;
/*     */   }
/*     */   @Column(name="message_", length=4000)
/*     */   public String getMessage() {
/*  98 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message) {
/* 102 */     this.message = message;
/*     */   }
/*     */   @Column(name="full_msg_")
/*     */   public String getFullMsg() {
/* 107 */     return this.fullMsg;
/*     */   }
/*     */ 
/*     */   public void setFullMsg(String fullMsg) {
/* 111 */     this.fullMsg = fullMsg;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiComment
 * JD-Core Version:    0.6.0
 */