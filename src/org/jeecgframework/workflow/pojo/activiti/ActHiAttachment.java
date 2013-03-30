/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_hi_attachment")
/*     */ public class ActHiAttachment
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer rev;
/*     */   private String userId;
/*     */   private String name;
/*     */   private String description;
/*     */   private String type;
/*     */   private String taskId;
/*     */   private String procInstId;
/*     */   private String url;
/*     */   private String contentId;
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
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  44 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  48 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="user_id_")
/*     */   public String getUserId() {
/*  53 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId) {
/*  57 */     this.userId = userId;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  62 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  66 */     this.name = name;
/*     */   }
/*     */   @Column(name="description_", length=4000)
/*     */   public String getDescription() {
/*  71 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/*  75 */     this.description = description;
/*     */   }
/*     */   @Column(name="type_")
/*     */   public String getType() {
/*  80 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  84 */     this.type = type;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/*  89 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/*  93 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  98 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/* 102 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="url_", length=4000)
/*     */   public String getUrl() {
/* 107 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 111 */     this.url = url;
/*     */   }
/*     */   @Column(name="content_id_", length=64)
/*     */   public String getContentId() {
/* 116 */     return this.contentId;
/*     */   }
/*     */ 
/*     */   public void setContentId(String contentId) {
/* 120 */     this.contentId = contentId;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiAttachment
 * JD-Core Version:    0.6.0
 */