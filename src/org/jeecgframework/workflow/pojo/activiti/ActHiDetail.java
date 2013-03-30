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
/*     */ @Table(name="act_hi_detail")
/*     */ public class ActHiDetail
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String type;
/*     */   private String procInstId;
/*     */   private String executionId;
/*     */   private String taskId;
/*     */   private String actInstId;
/*     */   private String name;
/*     */   private String varType;
/*     */   private Integer rev;
/*     */   private Timestamp time;
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
/*  41 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  45 */     this.id = id;
/*     */   }
/*     */   @Column(name="type_", nullable=false)
/*     */   public String getType() {
/*  50 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  54 */     this.type = type;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  59 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  63 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="execution_id_", length=64)
/*     */   public String getExecutionId() {
/*  68 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  72 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/*  77 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/*  81 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="act_inst_id_", length=64)
/*     */   public String getActInstId() {
/*  86 */     return this.actInstId;
/*     */   }
/*     */ 
/*     */   public void setActInstId(String actInstId) {
/*  90 */     this.actInstId = actInstId;
/*     */   }
/*     */   @Column(name="name_", nullable=false)
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  99 */     this.name = name;
/*     */   }
/*     */   @Column(name="var_type_", length=64)
/*     */   public String getVarType() {
/* 104 */     return this.varType;
/*     */   }
/*     */ 
/*     */   public void setVarType(String varType) {
/* 108 */     this.varType = varType;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/* 113 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/* 117 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="time_", nullable=false, length=29)
/*     */   public Timestamp getTime() {
/* 122 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Timestamp time) {
/* 126 */     this.time = time;
/*     */   }
/*     */   @Column(name="bytearray_id_", length=64)
/*     */   public String getBytearrayId() {
/* 131 */     return this.bytearrayId;
/*     */   }
/*     */ 
/*     */   public void setBytearrayId(String bytearrayId) {
/* 135 */     this.bytearrayId = bytearrayId;
/*     */   }
/*     */   @Column(name="double_", precision=17, scale=17)
/*     */   public Double getDouble_() {
/* 140 */     return this.double_;
/*     */   }
/*     */ 
/*     */   public void setDouble_(Double double_) {
/* 144 */     this.double_ = double_;
/*     */   }
/*     */   @Column(name="long_")
/*     */   public Long getLong_() {
/* 149 */     return this.long_;
/*     */   }
/*     */ 
/*     */   public void setLong_(Long long_) {
/* 153 */     this.long_ = long_;
/*     */   }
/*     */   @Column(name="text_", length=4000)
/*     */   public String getText() {
/* 158 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/* 162 */     this.text = text;
/*     */   }
/*     */   @Column(name="text2_", length=4000)
/*     */   public String getText2() {
/* 167 */     return this.text2;
/*     */   }
/*     */ 
/*     */   public void setText2(String text2) {
/* 171 */     this.text2 = text2;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiDetail
 * JD-Core Version:    0.6.0
 */