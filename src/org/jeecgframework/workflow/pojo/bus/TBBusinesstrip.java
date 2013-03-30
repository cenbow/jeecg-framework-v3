/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Temporal;
/*    */ import javax.persistence.TemporalType;
/*    */ import jeecg.system.pojo.base.TSBaseBus;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_b_businesstrip")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TBBusinesstrip extends TSBaseBus
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String bustriplocale;
/*    */   private Short peoplenum;
/*    */   private Date begintime;
/*    */   private Date endtime;
/*    */   private Short goouttype;
/*    */   private String bustripreson;
/*    */   private Double bustripmoney;
/*    */ 
/*    */   @Column(name="bustriplocale", length=100)
/*    */   public String getBustriplocale()
/*    */   {
/* 34 */     return this.bustriplocale;
/*    */   }
/*    */ 
/*    */   public void setBustriplocale(String bustriplocale) {
/* 38 */     this.bustriplocale = bustriplocale;
/*    */   }
/*    */   @Column(name="peoplenum")
/*    */   public Short getPeoplenum() {
/* 43 */     return this.peoplenum;
/*    */   }
/*    */ 
/*    */   public void setPeoplenum(Short peoplenum) {
/* 47 */     this.peoplenum = peoplenum;
/*    */   }
/* 53 */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="begintime")
/*    */   public Date getBegintime() { return this.begintime; }
/*    */ 
/*    */   public void setBegintime(Date begintime)
/*    */   {
/* 57 */     this.begintime = begintime;
/*    */   }
/* 63 */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="endtime")
/*    */   public Date getEndtime() { return this.endtime; }
/*    */ 
/*    */   public void setEndtime(Date endtime)
/*    */   {
/* 67 */     this.endtime = endtime;
/*    */   }
/*    */   @Column(name="goouttype")
/*    */   public Short getGoouttype() {
/* 72 */     return this.goouttype;
/*    */   }
/*    */ 
/*    */   public void setGoouttype(Short goouttype) {
/* 76 */     this.goouttype = goouttype;
/*    */   }
/*    */   @Column(name="bustripreson", length=2000)
/*    */   public String getBustripreson() {
/* 81 */     return this.bustripreson;
/*    */   }
/*    */ 
/*    */   public void setBustripreson(String bustripreson) {
/* 85 */     this.bustripreson = bustripreson;
/*    */   }
/*    */   @Column(name="bustripmoney", scale=0)
/*    */   public Double getBustripmoney() {
/* 90 */     return this.bustripmoney;
/*    */   }
/*    */ 
/*    */   public void setBustripmoney(Double bustripmoney) {
/* 94 */     this.bustripmoney = bustripmoney;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBBusinesstrip
 * JD-Core Version:    0.6.0
 */