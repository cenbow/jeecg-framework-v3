/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_ge_bytearray")
/*     */ public class ActGeBytearray
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActReDeployment actReDeployment;
/*     */   private Integer rev;
/*     */   private String name;
/*     */   private String bytes;
/*     */   private Boolean generated;
/*  31 */   private Set<ActReModel> actReModelsForEditorSourceExtraValueId = new HashSet(0);
/*  32 */   private Set<ActReModel> actReModelsForEditorSourceValueId = new HashSet(0);
/*  33 */   private Set<ActRuJob> actRuJobs = new HashSet(0);
/*  34 */   private Set<ActRuVariable> actRuVariables = new HashSet(0);
/*     */ 
/*  41 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   @Column(name="id_", unique=true, nullable=false, length=64)
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  45 */     this.id = id;
/*     */   }
/*  51 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="deployment_id_")
/*     */   public ActReDeployment getActReDeployment() { return this.actReDeployment; }
/*     */ 
/*     */   public void setActReDeployment(ActReDeployment actReDeployment)
/*     */   {
/*  55 */     this.actReDeployment = actReDeployment;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  60 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  64 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  69 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  73 */     this.name = name;
/*     */   }
/*     */   @Column(name="bytes_")
/*     */   public String getBytes() {
/*  78 */     return this.bytes;
/*     */   }
/*     */ 
/*     */   public void setBytes(String bytes) {
/*  82 */     this.bytes = bytes;
/*     */   }
/*     */   @Column(name="generated_")
/*     */   public Boolean getGenerated() {
/*  87 */     return this.generated;
/*     */   }
/*     */ 
/*     */   public void setGenerated(Boolean generated) {
/*  91 */     this.generated = generated;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actGeBytearrayByEditorSourceExtraValueId")
/*     */   public Set<ActReModel> getActReModelsForEditorSourceExtraValueId() {
/*  96 */     return this.actReModelsForEditorSourceExtraValueId;
/*     */   }
/*     */ 
/*     */   public void setActReModelsForEditorSourceExtraValueId(Set<ActReModel> actReModelsForEditorSourceExtraValueId) {
/* 100 */     this.actReModelsForEditorSourceExtraValueId = actReModelsForEditorSourceExtraValueId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actGeBytearrayByEditorSourceValueId")
/*     */   public Set<ActReModel> getActReModelsForEditorSourceValueId() {
/* 105 */     return this.actReModelsForEditorSourceValueId;
/*     */   }
/*     */ 
/*     */   public void setActReModelsForEditorSourceValueId(Set<ActReModel> actReModelsForEditorSourceValueId) {
/* 109 */     this.actReModelsForEditorSourceValueId = actReModelsForEditorSourceValueId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actGeBytearray")
/*     */   public Set<ActRuJob> getActRuJobs() {
/* 114 */     return this.actRuJobs;
/*     */   }
/*     */ 
/*     */   public void setActRuJobs(Set<ActRuJob> actRuJobs) {
/* 118 */     this.actRuJobs = actRuJobs;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actGeBytearray")
/*     */   public Set<ActRuVariable> getActRuVariables() {
/* 123 */     return this.actRuVariables;
/*     */   }
/*     */ 
/*     */   public void setActRuVariables(Set<ActRuVariable> actRuVariables) {
/* 127 */     this.actRuVariables = actRuVariables;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActGeBytearray
 * JD-Core Version:    0.6.0
 */