/*     */ package org.jeecgframework.workflow.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import jeecg.system.pojo.base.TSType;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_p_form")
/*     */ public class TPForm extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private TSType TSType;
/*     */   private String formname;
/*     */   private String formaction;
/*     */   private String formurl;
/*     */   private String formkey;
/*     */   private String formcode;
/*     */   private String formnote;
/*  34 */   private List<TPFormpro> TPFormpros = new ArrayList();
/*  35 */   private List<TPProcessnode> TProcessnodes = new ArrayList(0);
/*     */ 
/*  40 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="typeid")
/*     */   public TSType getTSType() { return this.TSType; }
/*     */ 
/*     */   public void setTSType(TSType TSType)
/*     */   {
/*  44 */     this.TSType = TSType;
/*     */   }
/*     */   @Column(name="formname", length=50)
/*     */   public String getFormname() {
/*  49 */     return this.formname;
/*     */   }
/*     */ 
/*     */   public void setFormname(String formname) {
/*  53 */     this.formname = formname;
/*     */   }
/*     */   @Column(name="formaction", length=100)
/*     */   public String getFormaction() {
/*  58 */     return this.formaction;
/*     */   }
/*     */ 
/*     */   public void setFormaction(String formaction) {
/*  62 */     this.formaction = formaction;
/*     */   }
/*     */   @Column(name="formkey", length=30)
/*     */   public String getFormkey() {
/*  67 */     return this.formkey;
/*     */   }
/*     */ 
/*     */   public void setFormkey(String formkey) {
/*  71 */     this.formkey = formkey;
/*     */   }
/*     */   @Column(name="formcode", length=30)
/*     */   public String getFormcode() {
/*  76 */     return this.formcode;
/*     */   }
/*     */ 
/*     */   public void setFormcode(String formcode) {
/*  80 */     this.formcode = formcode;
/*     */   }
/*     */   @Column(name="formnote", length=50)
/*     */   public String getFormnote() {
/*  85 */     return this.formnote;
/*     */   }
/*     */ 
/*     */   public void setFormnote(String formnote) {
/*  89 */     this.formnote = formnote;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPForm")
/*     */   public List<TPFormpro> getTPFormpros() {
/*  94 */     return this.TPFormpros;
/*     */   }
/*     */ 
/*     */   public void setTPFormpros(List<TPFormpro> TPFormpros) {
/*  98 */     this.TPFormpros = TPFormpros;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPForm")
/*     */   public List<TPProcessnode> getTProcessnodes() {
/* 103 */     return this.TProcessnodes;
/*     */   }
/*     */ 
/*     */   public void setTProcessnodes(List<TPProcessnode> TProcessnodes) {
/* 107 */     this.TProcessnodes = TProcessnodes;
/*     */   }
/*     */   @Column(name="formurl", length=100)
/*     */   public String getFormurl() {
/* 112 */     return this.formurl;
/*     */   }
/*     */ 
/*     */   public void setFormurl(String formurl) {
/* 116 */     this.formurl = formurl;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPForm
 * JD-Core Version:    0.6.0
 */