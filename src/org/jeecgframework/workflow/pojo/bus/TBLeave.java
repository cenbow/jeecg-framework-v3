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
/*    */ @Table(name="t_b_leave")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TBLeave extends TSBaseBus
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Date begintime;
/*    */   private Date endtime;
/*    */   private String reason;
/*    */   private Date realbegintime;
/*    */   private Date realendtime;
/*    */ 
/*    */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="begintime")
/*    */   public Date getBegintime()
/*    */   {
/* 35 */     return this.begintime;
/*    */   }
/*    */ 
/*    */   public void setBegintime(Date begintime) {
/* 39 */     this.begintime = begintime;
/*    */   }
/* 45 */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="endtime")
/*    */   public Date getEndtime() { return this.endtime; }
/*    */ 
/*    */   public void setEndtime(Date endtime)
/*    */   {
/* 49 */     this.endtime = endtime;
/*    */   }
/*    */   @Column(name="reason", length=5000)
/*    */   public String getReason() {
/* 54 */     return this.reason;
/*    */   }
/*    */ 
/*    */   public void setReason(String reason) {
/* 58 */     this.reason = reason;
/*    */   }
/* 64 */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="realbegintime")
/*    */   public Date getRealbegintime() { return this.realbegintime; }
/*    */ 
/*    */   public void setRealbegintime(Date realbegintime)
/*    */   {
/* 68 */     this.realbegintime = realbegintime;
/*    */   }
/* 74 */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="realendtime")
/*    */   public Date getRealendtime() { return this.realendtime; }
/*    */ 
/*    */   public void setRealendtime(Date realendtime)
/*    */   {
/* 78 */     this.realendtime = realendtime;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBLeave
 * JD-Core Version:    0.6.0
 */