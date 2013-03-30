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
/*     */ @Table(name="act_hi_varinst")
/*     */ public class ActHiVarinst
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String procInstId;
/*     */   private String executionId;
/*     */   private String taskId;
/*     */   private String name;
/*     */   private String varType;
/*     */   private Integer rev;
/*     */   private String bytearrayId;
/*     */   private Double double_;
/*     */   private Long long_;
/*     */   private String text;
/*     */   private String text2;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  46 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  50 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="execution_id_", length=64)
/*     */   public String getExecutionId() {
/*  55 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  59 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/*  64 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/*  68 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="name_", nullable=false)
/*     */   public String getName() {
/*  73 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  77 */     this.name = name;
/*     */   }
/*     */   @Column(name="var_type_", length=100)
/*     */   public String getVarType() {
/*  82 */     return this.varType;
/*     */   }
/*     */ 
/*     */   public void setVarType(String varType) {
/*  86 */     this.varType = varType;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  91 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  95 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="bytearray_id_", length=64)
/*     */   public String getBytearrayId() {
/* 100 */     return this.bytearrayId;
/*     */   }
/*     */ 
/*     */   public void setBytearrayId(String bytearrayId) {
/* 104 */     this.bytearrayId = bytearrayId;
/*     */   }
/*     */   @Column(name="double_", precision=17, scale=17)
/*     */   public Double getDouble_() {
/* 109 */     return this.double_;
/*     */   }
/*     */ 
/*     */   public void setDouble_(Double double_) {
/* 113 */     this.double_ = double_;
/*     */   }
/*     */   @Column(name="long_")
/*     */   public Long getLong_() {
/* 118 */     return this.long_;
/*     */   }
/*     */ 
/*     */   public void setLong_(Long long_) {
/* 122 */     this.long_ = long_;
/*     */   }
/*     */   @Column(name="text_", length=4000)
/*     */   public String getText() {
/* 127 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/* 131 */     this.text = text;
/*     */   }
/*     */   @Column(name="text2_", length=4000)
/*     */   public String getText2() {
/* 136 */     return this.text2;
/*     */   }
/*     */ 
/*     */   public void setText2(String text2) {
/* 140 */     this.text2 = text2;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiVarinst
 * JD-Core Version:    0.6.0
 */