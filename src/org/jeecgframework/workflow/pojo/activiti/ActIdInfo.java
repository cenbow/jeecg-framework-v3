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
/*     */ @Table(name="act_id_info")
/*     */ public class ActIdInfo
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer rev;
/*     */   private String userId;
/*     */   private String type;
/*     */   private String key;
/*     */   private String value;
/*     */   private String password;
/*     */   private String parentId;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  33 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  37 */     this.id = id;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  42 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  46 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="user_id_", length=64)
/*     */   public String getUserId() {
/*  51 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String userId) {
/*  55 */     this.userId = userId;
/*     */   }
/*     */   @Column(name="type_", length=64)
/*     */   public String getType() {
/*  60 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/*  64 */     this.type = type;
/*     */   }
/*     */   @Column(name="key_")
/*     */   public String getKey() {
/*  69 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/*  73 */     this.key = key;
/*     */   }
/*     */   @Column(name="value_")
/*     */   public String getValue() {
/*  78 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/*  82 */     this.value = value;
/*     */   }
/*     */   @Column(name="password_")
/*     */   public String getPassword() {
/*  87 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/*  91 */     this.password = password;
/*     */   }
/*     */   @Column(name="parent_id_")
/*     */   public String getParentId() {
/*  96 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(String parentId) {
/* 100 */     this.parentId = parentId;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActIdInfo
 * JD-Core Version:    0.6.0
 */