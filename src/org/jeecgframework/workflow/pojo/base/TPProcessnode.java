/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_p_processnode")
/*    */ public class TPProcessnode extends IdEntity
/*    */   implements Serializable
/*    */ {
/* 25 */   private TPForm TPForm = new TPForm();
/* 26 */   private TPProcess TPProcess = new TPProcess();
/*    */   private String processnodename;
/*    */   private String processnodecode;
/*    */   private String modelandview;
/* 30 */   private List<TPProcesspro> TPProcesspros = new ArrayList();
/* 31 */   private List<TPProcessListener> TPProcessListeners = new ArrayList();
/*    */ 
/* 36 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="formid")
/*    */   public TPForm getTPForm() { return this.TPForm; }
/*    */ 
/*    */   public void setTPForm(TPForm TPForm)
/*    */   {
/* 40 */     this.TPForm = TPForm;
/*    */   }
/* 46 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="processid")
/*    */   public TPProcess getTPProcess() { return this.TPProcess; }
/*    */ 
/*    */   public void setTPProcess(TPProcess TPProcess)
/*    */   {
/* 50 */     this.TPProcess = TPProcess;
/*    */   }
/*    */   @Column(name="processnodename", length=50)
/*    */   public String getProcessnodename() {
/* 55 */     return this.processnodename;
/*    */   }
/*    */ 
/*    */   public void setProcessnodename(String processnodename) {
/* 59 */     this.processnodename = processnodename;
/*    */   }
/*    */   @Column(name="processnodecode", length=50)
/*    */   public String getProcessnodecode() {
/* 64 */     return this.processnodecode;
/*    */   }
/*    */ 
/*    */   public void setProcessnodecode(String processnodecode) {
/* 68 */     this.processnodecode = processnodecode;
/*    */   }
/* 72 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcessnode")
/*    */   public List<TPProcesspro> getTPProcesspros() { return this.TPProcesspros; }
/*    */ 
/*    */   public void setTPProcesspros(List<TPProcesspro> TPProcesspros)
/*    */   {
/* 76 */     this.TPProcesspros = TPProcesspros;
/*    */   }
/* 80 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcessnode")
/*    */   public List<TPProcessListener> getTPProcessListeners() { return this.TPProcessListeners; }
/*    */ 
/*    */   public void setTPProcessListeners(List<TPProcessListener> tPProcessListeners)
/*    */   {
/* 84 */     this.TPProcessListeners = tPProcessListeners;
/*    */   }
/* 88 */   @Column(name="modelandview", length=50)
/*    */   public String getModelandview() { return this.modelandview; }
/*    */ 
/*    */   public void setModelandview(String modelandview)
/*    */   {
/* 92 */     this.modelandview = modelandview;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPProcessnode
 * JD-Core Version:    0.6.0
 */