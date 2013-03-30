/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ @Table(name="act_ru_variable")
/*     */ public class ActRuVariable
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActRuExecution actRuExecutionByExecutionId;
/*     */   private ActRuExecution actRuExecutionByProcInstId;
/*     */   private ActGeBytearray actGeBytearray;
/*     */   private Integer rev;
/*     */   private String type;
/*     */   private String name;
/*     */   private String taskId;
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
/*  40 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  44 */     this.id = id;
/*     */   }
/*  50 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="execution_id_")
/*     */   public ActRuExecution getActRuExecutionByExecutionId() { return this.actRuExecutionByExecutionId; }
/*     */ 
/*     */   public void setActRuExecutionByExecutionId(ActRuExecution actRuExecutionByExecutionId)
/*     */   {
/*  54 */     this.actRuExecutionByExecutionId = actRuExecutionByExecutionId;
/*     */   }
/*  60 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_inst_id_")
/*     */   public ActRuExecution getActRuExecutionByProcInstId() { return this.actRuExecutionByProcInstId; }
/*     */ 
/*     */   public void setActRuExecutionByProcInstId(ActRuExecution actRuExecutionByProcInstId)
/*     */   {
/*  64 */     this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
/*     */   }
/*  70 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="bytearray_id_")
/*     */   public ActGeBytearray getActGeBytearray() { return this.actGeBytearray; }
/*     */ 
/*     */   public void setActGeBytearray(ActGeBytearray actGeBytearray)
/*     */   {
/*  74 */     this.actGeBytearray = actGeBytearray;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  79 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  83 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="type_", nullable=false)
/*     */   public String getType() {
/*  88 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  92 */     this.type = type;
/*     */   }
/*     */   @Column(name="name_", nullable=false)
/*     */   public String getName() {
/*  97 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 101 */     this.name = name;
/*     */   }
/*     */   @Column(name="task_id_", length=64)
/*     */   public String getTaskId() {
/* 106 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/* 110 */     this.taskId = taskId;
/*     */   }
/*     */   @Column(name="double_", precision=17, scale=17)
/*     */   public Double getDouble_() {
/* 115 */     return this.double_;
/*     */   }
/*     */ 
/*     */   public void setDouble_(Double double_) {
/* 119 */     this.double_ = double_;
/*     */   }
/*     */   @Column(name="long_")
/*     */   public Long getLong_() {
/* 124 */     return this.long_;
/*     */   }
/*     */ 
/*     */   public void setLong_(Long long_) {
/* 128 */     this.long_ = long_;
/*     */   }
/*     */   @Column(name="text_", length=4000)
/*     */   public String getText() {
/* 133 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/* 137 */     this.text = text;
/*     */   }
/*     */   @Column(name="text2_", length=4000)
/*     */   public String getText2() {
/* 142 */     return this.text2;
/*     */   }
/*     */ 
/*     */   public void setText2(String text2) {
/* 146 */     this.text2 = text2;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuVariable
 * JD-Core Version:    0.6.0
 */