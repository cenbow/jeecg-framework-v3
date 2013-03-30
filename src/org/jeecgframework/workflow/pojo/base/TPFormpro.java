/*     */ package org.jeecgframework.workflow.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_p_formpro")
/*     */ public class TPFormpro extends IdEntity
/*     */   implements Serializable
/*     */ {
/*  24 */   private TPForm TPForm = new TPForm();
/*     */   private String formproname;
/*     */   private String formproval;
/*     */   private String formprofun;
/*     */   private String formproscript;
/*     */   private String formprokey;
/*     */   private String formprocode;
/*     */   private boolean formprostate;
/*     */   private String formprotype;
/*     */   private String formprocss;
/*     */   private String processopt;
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="formid")
/*     */   public TPForm getTPForm()
/*     */   {
/*  38 */     return this.TPForm;
/*     */   }
/*     */ 
/*     */   public void setTPForm(TPForm TPForm) {
/*  42 */     this.TPForm = TPForm;
/*     */   }
/*     */   @Column(name="formproname", length=50)
/*     */   public String getFormproname() {
/*  47 */     return this.formproname;
/*     */   }
/*     */ 
/*     */   public void setFormproname(String formproname) {
/*  51 */     this.formproname = formproname;
/*     */   }
/*     */   @Column(name="formproval", length=50)
/*     */   public String getFormproval() {
/*  56 */     return this.formproval;
/*     */   }
/*     */ 
/*     */   public void setFormproval(String formproval) {
/*  60 */     this.formproval = formproval;
/*     */   }
/*     */   @Column(name="formprofun", length=50)
/*     */   public String getFormprofun() {
/*  65 */     return this.formprofun;
/*     */   }
/*     */ 
/*     */   public void setFormprofun(String formprofun) {
/*  69 */     this.formprofun = formprofun;
/*     */   }
/*     */   @Column(name="formproscript", length=1000)
/*     */   public String getFormproscript() {
/*  74 */     return this.formproscript;
/*     */   }
/*     */ 
/*     */   public void setFormproscript(String formproscript) {
/*  78 */     this.formproscript = formproscript;
/*     */   }
/*     */   @Column(name="formprokey", length=50)
/*     */   public String getFormprokey() {
/*  83 */     return this.formprokey;
/*     */   }
/*     */ 
/*     */   public void setFormprokey(String formprokey) {
/*  87 */     this.formprokey = formprokey;
/*     */   }
/*     */   @Column(name="formprocode", length=20)
/*     */   public String getFormprocode() {
/*  92 */     return this.formprocode;
/*     */   }
/*     */ 
/*     */   public void setFormprocode(String formprocode) {
/*  96 */     this.formprocode = formprocode;
/*     */   }
/*     */   @Column(name="formprostate")
/*     */   public boolean getFormprostate() {
/* 101 */     return this.formprostate;
/*     */   }
/*     */ 
/*     */   public void setFormprostate(boolean formprostate) {
/* 105 */     this.formprostate = formprostate;
/*     */   }
/*     */   @Column(name="formprotype", length=50)
/*     */   public String getFormprotype() {
/* 110 */     return this.formprotype;
/*     */   }
/*     */ 
/*     */   public void setFormprotype(String formprotype) {
/* 114 */     this.formprotype = formprotype;
/*     */   }
/* 118 */   @Column(name="processopt", length=50)
/*     */   public String getProcessopt() { return this.processopt; }
/*     */ 
/*     */   public void setProcessopt(String processopt)
/*     */   {
/* 122 */     this.processopt = processopt;
/*     */   }
/*     */   @Column(name="formprocss", length=200)
/*     */   public String getFormprocss() {
/* 127 */     return this.formprocss;
/*     */   }
/*     */ 
/*     */   public void setFormprocss(String formprocss) {
/* 131 */     this.formprocss = formprocss;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPFormpro
 * JD-Core Version:    0.6.0
 */