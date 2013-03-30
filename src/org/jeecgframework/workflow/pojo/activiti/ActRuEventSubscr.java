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
/*     */ @Table(name="act_ru_event_subscr")
/*     */ public class ActRuEventSubscr
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActRuExecution actRuExecution;
/*     */   private Integer rev;
/*     */   private String eventType;
/*     */   private String eventName;
/*     */   private String procInstId;
/*     */   private String activityId;
/*     */   private String configuration;
/*     */   private Timestamp created;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  38 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  42 */     this.id = id;
/*     */   }
/*  48 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="execution_id_")
/*     */   public ActRuExecution getActRuExecution() { return this.actRuExecution; }
/*     */ 
/*     */   public void setActRuExecution(ActRuExecution actRuExecution)
/*     */   {
/*  52 */     this.actRuExecution = actRuExecution;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  57 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  61 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="event_type_", nullable=false)
/*     */   public String getEventType() {
/*  66 */     return this.eventType;
/*     */   }
/*     */ 
/*     */   public void setEventType(String eventType) {
/*  70 */     this.eventType = eventType;
/*     */   }
/*     */   @Column(name="event_name_")
/*     */   public String getEventName() {
/*  75 */     return this.eventName;
/*     */   }
/*     */ 
/*     */   public void setEventName(String eventName) {
/*  79 */     this.eventName = eventName;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  84 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  88 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="activity_id_", length=64)
/*     */   public String getActivityId() {
/*  93 */     return this.activityId;
/*     */   }
/*     */ 
/*     */   public void setActivityId(String activityId) {
/*  97 */     this.activityId = activityId;
/*     */   }
/*     */   @Column(name="configuration_")
/*     */   public String getConfiguration() {
/* 102 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   public void setConfiguration(String configuration) {
/* 106 */     this.configuration = configuration;
/*     */   }
/*     */   @Column(name="created_", nullable=false, length=29)
/*     */   public Timestamp getCreated() {
/* 111 */     return this.created;
/*     */   }
/*     */ 
/*     */   public void setCreated(Timestamp created) {
/* 115 */     this.created = created;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuEventSubscr
 * JD-Core Version:    0.6.0
 */