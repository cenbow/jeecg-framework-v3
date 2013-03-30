/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import jeecg.system.pojo.base.TSBaseBus;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_b_expenditure")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TBExpenditure extends TSBaseBus
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Double expenmoney;
/*    */   private Short expentype;
/*    */   private String expenreson;
/*    */ 
/*    */   @Column(name="expenmoney", precision=12)
/*    */   public Double getExpenmoney()
/*    */   {
/* 26 */     return this.expenmoney;
/*    */   }
/*    */ 
/*    */   public void setExpenmoney(Double expenmoney) {
/* 30 */     this.expenmoney = expenmoney;
/*    */   }
/*    */   @Column(name="expentype")
/*    */   public Short getExpentype() {
/* 35 */     return this.expentype;
/*    */   }
/*    */ 
/*    */   public void setExpentype(Short expentype) {
/* 39 */     this.expentype = expentype;
/*    */   }
/*    */   @Column(name="expenreson", length=2000)
/*    */   public String getExpenreson() {
/* 44 */     return this.expenreson;
/*    */   }
/*    */ 
/*    */   public void setExpenreson(String expenreson) {
/* 48 */     this.expenreson = expenreson;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBExpenditure
 * JD-Core Version:    0.6.0
 */