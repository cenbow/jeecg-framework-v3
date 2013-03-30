/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import jeecg.system.pojo.base.TSUser;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_b_approval")
/*    */ public class TBApproval extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSUser TSUser;
/*    */   private String approvalrole;
/*    */   private String approvalopinion;
/*    */   private Timestamp approvaldate;
/*    */   private String note;
/*    */   private Integer approvaloperation;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="userid")
/*    */   public TSUser getTSUser()
/*    */   {
/* 37 */     return this.TSUser;
/*    */   }
/*    */ 
/*    */   public void setTSUser(TSUser TSUser) {
/* 41 */     this.TSUser = TSUser;
/*    */   }
/*    */   @Column(name="approvalrole", nullable=false, length=100)
/*    */   public String getApprovalrole() {
/* 46 */     return this.approvalrole;
/*    */   }
/*    */ 
/*    */   public void setApprovalrole(String approvalrole) {
/* 50 */     this.approvalrole = approvalrole;
/*    */   }
/*    */   @Column(name="approvalopinion", length=300)
/*    */   public String getApprovalopinion() {
/* 55 */     return this.approvalopinion;
/*    */   }
/*    */ 
/*    */   public void setApprovalopinion(String approvalopinion) {
/* 59 */     this.approvalopinion = approvalopinion;
/*    */   }
/*    */   @Column(name="approvaldate", nullable=false, length=35)
/*    */   public Timestamp getApprovaldate() {
/* 64 */     return this.approvaldate;
/*    */   }
/*    */ 
/*    */   public void setApprovaldate(Timestamp approvaldate) {
/* 68 */     this.approvaldate = approvaldate;
/*    */   }
/*    */   @Column(name="note", length=300)
/*    */   public String getNote() {
/* 73 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 77 */     this.note = note;
/*    */   }
/*    */   @Column(name="approvaloperation")
/*    */   public Integer getApprovaloperation() {
/* 82 */     return this.approvaloperation;
/*    */   }
/*    */ 
/*    */   public void setApprovaloperation(Integer approvaloperation) {
/* 86 */     this.approvaloperation = approvaloperation;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBApproval
 * JD-Core Version:    0.6.0
 */