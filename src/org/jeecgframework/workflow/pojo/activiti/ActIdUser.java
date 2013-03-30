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
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_id_user")
/*     */ public class ActIdUser
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer rev;
/*     */   private String first;
/*     */   private String last;
/*     */   private String email;
/*     */   private String pwd;
/*     */   private String pictureId;
/*  32 */   private Set<ActIdMembership> actIdMemberships = new HashSet(0);
/*     */ 
/*  38 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  42 */     this.id = id;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  47 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  51 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="first_")
/*     */   public String getFirst() {
/*  56 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(String first) {
/*  60 */     this.first = first;
/*     */   }
/*     */   @Column(name="last_")
/*     */   public String getLast() {
/*  65 */     return this.last;
/*     */   }
/*     */ 
/*     */   public void setLast(String last) {
/*  69 */     this.last = last;
/*     */   }
/*     */   @Column(name="email_")
/*     */   public String getEmail() {
/*  74 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/*  78 */     this.email = email;
/*     */   }
/*     */   @Column(name="pwd_")
/*     */   public String getPwd() {
/*  83 */     return this.pwd;
/*     */   }
/*     */ 
/*     */   public void setPwd(String pwd) {
/*  87 */     this.pwd = pwd;
/*     */   }
/*     */   @Column(name="picture_id_", length=64)
/*     */   public String getPictureId() {
/*  92 */     return this.pictureId;
/*     */   }
/*     */ 
/*     */   public void setPictureId(String pictureId) {
/*  96 */     this.pictureId = pictureId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actIdUser")
/*     */   public Set<ActIdMembership> getActIdMemberships() {
/* 101 */     return this.actIdMemberships;
/*     */   }
/*     */ 
/*     */   public void setActIdMemberships(Set<ActIdMembership> actIdMemberships) {
/* 105 */     this.actIdMemberships = actIdMemberships;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActIdUser
 * JD-Core Version:    0.6.0
 */