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
/*     */ @Table(name="act_re_model")
/*     */ public class ActReModel
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private ActGeBytearray actGeBytearrayByEditorSourceExtraValueId;
/*     */   private ActGeBytearray actGeBytearrayByEditorSourceValueId;
/*     */   private ActReDeployment actReDeployment;
/*     */   private Integer rev;
/*     */   private String name;
/*     */   private String key;
/*     */   private String category;
/*     */   private Timestamp createTime;
/*     */   private Timestamp lastUpdateTime;
/*     */   private Integer version;
/*     */   private String metaInfo;
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
/*  51 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="editor_source_extra_value_id_")
/*     */   public ActGeBytearray getActGeBytearrayByEditorSourceExtraValueId() { return this.actGeBytearrayByEditorSourceExtraValueId; }
/*     */ 
/*     */   public void setActGeBytearrayByEditorSourceExtraValueId(ActGeBytearray actGeBytearrayByEditorSourceExtraValueId)
/*     */   {
/*  55 */     this.actGeBytearrayByEditorSourceExtraValueId = actGeBytearrayByEditorSourceExtraValueId;
/*     */   }
/*  61 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="editor_source_value_id_")
/*     */   public ActGeBytearray getActGeBytearrayByEditorSourceValueId() { return this.actGeBytearrayByEditorSourceValueId; }
/*     */ 
/*     */   public void setActGeBytearrayByEditorSourceValueId(ActGeBytearray actGeBytearrayByEditorSourceValueId)
/*     */   {
/*  65 */     this.actGeBytearrayByEditorSourceValueId = actGeBytearrayByEditorSourceValueId;
/*     */   }
/*  71 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="deployment_id_")
/*     */   public ActReDeployment getActReDeployment() { return this.actReDeployment; }
/*     */ 
/*     */   public void setActReDeployment(ActReDeployment actReDeployment)
/*     */   {
/*  75 */     this.actReDeployment = actReDeployment;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  80 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  84 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  89 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  93 */     this.name = name;
/*     */   }
/*     */   @Column(name="key_")
/*     */   public String getKey() {
/*  98 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/* 102 */     this.key = key;
/*     */   }
/*     */   @Column(name="category_")
/*     */   public String getCategory() {
/* 107 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(String category) {
/* 111 */     this.category = category;
/*     */   }
/*     */   @Column(name="create_time_", length=29)
/*     */   public Timestamp getCreateTime() {
/* 116 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Timestamp createTime) {
/* 120 */     this.createTime = createTime;
/*     */   }
/*     */   @Column(name="last_update_time_", length=29)
/*     */   public Timestamp getLastUpdateTime() {
/* 125 */     return this.lastUpdateTime;
/*     */   }
/*     */ 
/*     */   public void setLastUpdateTime(Timestamp lastUpdateTime) {
/* 129 */     this.lastUpdateTime = lastUpdateTime;
/*     */   }
/*     */   @Column(name="version_")
/*     */   public Integer getVersion() {
/* 134 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(Integer version) {
/* 138 */     this.version = version;
/*     */   }
/*     */   @Column(name="meta_info_", length=4000)
/*     */   public String getMetaInfo() {
/* 143 */     return this.metaInfo;
/*     */   }
/*     */ 
/*     */   public void setMetaInfo(String metaInfo) {
/* 147 */     this.metaInfo = metaInfo;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActReModel
 * JD-Core Version:    0.6.0
 */