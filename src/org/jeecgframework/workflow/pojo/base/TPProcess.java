/*     */ package org.jeecgframework.workflow.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import jeecg.system.pojo.base.TSType;
/*     */ import jeecg.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_p_process")
/*     */ public class TPProcess extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private TSType TSType;
/*     */   private TSUser TSUser;
/*     */   private String processname;
/*     */   private Short processstate;
/*     */   private String processxmlpath;
/*     */   private byte[] processxml;
/*     */   private String processkey;
/*     */   private String note;
/*  39 */   private Set<TPProcesspro> TPProcesspros = new HashSet(0);
/*  40 */   private List<TPProcessnode> TPProcessnodes = new ArrayList(0);
/*     */ 
/*  46 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="typeid")
/*     */   public TSType getTSType() { return this.TSType; }
/*     */ 
/*     */   public void setTSType(TSType TSType)
/*     */   {
/*  50 */     this.TSType = TSType;
/*     */   }
/*  56 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="userid")
/*     */   public TSUser getTSUser() { return this.TSUser; }
/*     */ 
/*     */   public void setTSUser(TSUser TSUser)
/*     */   {
/*  60 */     this.TSUser = TSUser;
/*     */   }
/*     */   @Column(name="processname", length=50)
/*     */   public String getProcessname() {
/*  65 */     return this.processname;
/*     */   }
/*     */ 
/*     */   public void setProcessname(String processname) {
/*  69 */     this.processname = processname;
/*     */   }
/*     */   @Column(name="processstate")
/*     */   public Short getProcessstate() {
/*  74 */     return this.processstate;
/*     */   }
/*     */ 
/*     */   public void setProcessstate(Short processstate) {
/*  78 */     this.processstate = processstate;
/*     */   }
/*     */   @Column(name="processxmlpath", length=100)
/*     */   public String getProcessxmlpath() {
/*  83 */     return this.processxmlpath;
/*     */   }
/*     */ 
/*     */   public void setProcessxmlpath(String processxmlpath) {
/*  87 */     this.processxmlpath = processxmlpath;
/*     */   }
/*     */   @Column(name="processxml")
/*     */   public byte[] getProcessxml() {
/*  92 */     return this.processxml;
/*     */   }
/*     */ 
/*     */   public void setProcessxml(byte[] processxml) {
/*  96 */     this.processxml = processxml;
/*     */   }
/*     */   @Column(name="processkey", length=100)
/*     */   public String getProcesskey() {
/* 101 */     return this.processkey;
/*     */   }
/*     */ 
/*     */   public void setProcesskey(String processkey) {
/* 105 */     this.processkey = processkey;
/*     */   }
/*     */   @Column(name="note", length=200)
/*     */   public String getNote() {
/* 110 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 114 */     this.note = note;
/*     */   }
/* 118 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcess")
/*     */   public Set<TPProcesspro> getTPProcesspros() { return this.TPProcesspros; }
/*     */ 
/*     */   public void setTPProcesspros(Set<TPProcesspro> TPProcesspros)
/*     */   {
/* 122 */     this.TPProcesspros = TPProcesspros;
/*     */   }
/* 126 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcess")
/*     */   public List<TPProcessnode> getTPProcessnodes() { return this.TPProcessnodes; }
/*     */ 
/*     */   public void setTPProcessnodes(List<TPProcessnode> TPProcessnodes)
/*     */   {
/* 130 */     this.TPProcessnodes = TPProcessnodes;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPProcess
 * JD-Core Version:    0.6.0
 */