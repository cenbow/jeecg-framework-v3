/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_p_processpro")
/*    */ public class TPProcesspro extends IdEntity
/*    */   implements Serializable
/*    */ {
/* 23 */   private TPProcess TPProcess = new TPProcess();
/*    */   private String processprokey;
/*    */   private String processproname;
/*    */   private String processprotype;
/* 32 */   private TPProcessnode TPProcessnode = new TPProcessnode();
/*    */ 
/* 36 */   @Column(name="processprotype", length=50)
/*    */   public String getProcessprotype() { return this.processprotype; }
/*    */ 
/*    */   public void setProcessprotype(String processprodatatype)
/*    */   {
/* 40 */     this.processprotype = processprodatatype;
/*    */   }
/* 46 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="processid")
/*    */   public TPProcess getTPProcess() { return this.TPProcess; }
/*    */ 
/*    */   public void setTPProcess(TPProcess TPProcess)
/*    */   {
/* 50 */     this.TPProcess = TPProcess;
/*    */   }
/*    */   @Column(name="processproname", length=50)
/*    */   public String getProcessproname() {
/* 55 */     return this.processproname;
/*    */   }
/*    */ 
/*    */   public void setProcessproname(String processproname) {
/* 59 */     this.processproname = processproname;
/*    */   }
/* 63 */   @Column(name="processprokey", length=20)
/*    */   public String getProcessprokey() { return this.processprokey; }
/*    */ 
/*    */   public void setProcessprokey(String processprokey)
/*    */   {
/* 67 */     this.processprokey = processprokey;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="processnodeid")
/*    */   public TPProcessnode getTPProcessnode() {
/* 74 */     return this.TPProcessnode;
/*    */   }
/*    */ 
/*    */   public void setTPProcessnode(TPProcessnode tProcessnode) {
/* 78 */     this.TPProcessnode = tProcessnode;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPProcesspro
 * JD-Core Version:    0.6.0
 */