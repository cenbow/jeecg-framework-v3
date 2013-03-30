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
/*    */ @Table(name="t_b_bormoney")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TBBormoney extends TSBaseBus
/*    */   implements Serializable
/*    */ {
/*    */   private Double bormoney;
/*    */   private String bormoneyuse;
/*    */ 
/*    */   @Column(name="bormoney", precision=12)
/*    */   public Double getBormoney()
/*    */   {
/* 23 */     return this.bormoney;
/*    */   }
/*    */ 
/*    */   public void setBormoney(Double bormoney) {
/* 27 */     this.bormoney = bormoney;
/*    */   }
/*    */   @Column(name="bormoneyuse", length=500)
/*    */   public String getBormoneyuse() {
/* 32 */     return this.bormoneyuse;
/*    */   }
/*    */ 
/*    */   public void setBormoneyuse(String bormoneyuse) {
/* 36 */     this.bormoneyuse = bormoneyuse;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBBormoney
 * JD-Core Version:    0.6.0
 */