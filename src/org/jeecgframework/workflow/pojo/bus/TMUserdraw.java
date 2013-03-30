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
/*    */ @Table(name="t_m_userdraw")
/*    */ public class TMUserdraw extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSUser TSUser;
/*    */   private Timestamp drawtime;
/*    */   private String drawname;
/*    */   private String drawcontent;
/*    */   private String note;
/*    */   private String drawDate;
/*    */   private String markercontent;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="userid", nullable=false)
/*    */   public TSUser getTSUser()
/*    */   {
/* 36 */     return this.TSUser;
/*    */   }
/*    */ 
/*    */   public void setTSUser(TSUser TSUser) {
/* 40 */     this.TSUser = TSUser;
/*    */   }
/*    */   @Column(name="drawtime")
/*    */   public Timestamp getDrawtime() {
/* 45 */     return this.drawtime;
/*    */   }
/*    */ 
/*    */   public void setDrawtime(Timestamp drawtime) {
/* 49 */     this.drawtime = drawtime;
/*    */   }
/*    */   @Column(name="drawname", nullable=false, length=100)
/*    */   public String getDrawname() {
/* 54 */     return this.drawname;
/*    */   }
/*    */ 
/*    */   public void setDrawname(String drawname) {
/* 58 */     this.drawname = drawname;
/*    */   }
/*    */   @Column(name="drawcontent", length=3000)
/*    */   public String getDrawcontent() {
/* 63 */     return this.drawcontent;
/*    */   }
/*    */ 
/*    */   public void setDrawcontent(String drawcontent) {
/* 67 */     this.drawcontent = drawcontent;
/*    */   }
/*    */   @Column(name="note", length=100)
/*    */   public String getNote() {
/* 72 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 76 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public String getDrawDate() {
/* 80 */     return this.drawDate;
/*    */   }
/*    */ 
/*    */   public void setDrawDate(String drawDate) {
/* 84 */     this.drawDate = drawDate;
/*    */   }
/*    */ 
/*    */   public String getMarkercontent() {
/* 88 */     return this.markercontent;
/*    */   }
/*    */ 
/*    */   public void setMarkercontent(String markercontent) {
/* 92 */     this.markercontent = markercontent;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TMUserdraw
 * JD-Core Version:    0.6.0
 */