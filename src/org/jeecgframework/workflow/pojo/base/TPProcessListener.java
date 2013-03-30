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
/*    */ @Table(name="t_p_processlistener")
/*    */ public class TPProcessListener extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TPListerer TPListerer;
/*    */   private TPProcessnode TPProcessnode;
/*    */   private TPProcess TPProcess;
/*    */   private String nodename;
/*    */   private Short status;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="listenerid")
/*    */   public TPListerer getTPListerer()
/*    */   {
/* 27 */     return this.TPListerer;
/*    */   }
/*    */ 
/*    */   public void setTPListerer(TPListerer TPListerer) {
/* 31 */     this.TPListerer = TPListerer;
/*    */   }
/* 37 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="porcessnodeid")
/*    */   public TPProcessnode getTPProcessnode() { return this.TPProcessnode; }
/*    */ 
/*    */   public void setTPProcessnode(TPProcessnode TPProcessnode)
/*    */   {
/* 41 */     this.TPProcessnode = TPProcessnode;
/*    */   }
/* 45 */   @Column(name="status")
/*    */   public Short getStatus() { return this.status; }
/*    */ 
/*    */   public void setStatus(Short status) {
/* 48 */     this.status = status; } 
/* 53 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="processid")
/*    */   public TPProcess getTPProcess() { return this.TPProcess; }
/*    */ 
/*    */   public void setTPProcess(TPProcess TPProcess)
/*    */   {
/* 57 */     this.TPProcess = TPProcess;
/*    */   }
/* 61 */   @Column(name="nodename", length=50)
/*    */   public String getNodename() { return this.nodename; }
/*    */ 
/*    */   public void setNodename(String nodename)
/*    */   {
/* 65 */     this.nodename = nodename;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPProcessListener
 * JD-Core Version:    0.6.0
 */