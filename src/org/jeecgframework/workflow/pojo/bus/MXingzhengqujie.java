/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="m_xingzhengqujie")
/*    */ public class MXingzhengqujie extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private Long gid_1;
/*    */   private String name;
/*    */   private String geom;
/*    */ 
/*    */   @Column(name="__gid", precision=10, scale=0)
/*    */   public Long getGid_1()
/*    */   {
/* 24 */     return this.gid_1;
/*    */   }
/*    */ 
/*    */   public void setGid_1(Long gid_1) {
/* 28 */     this.gid_1 = gid_1;
/*    */   }
/*    */   @Column(name="name")
/*    */   public String getName() {
/* 33 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 37 */     this.name = name;
/*    */   }
/*    */   @Column(name="geom")
/*    */   public String getGeom() {
/* 42 */     return this.geom;
/*    */   }
/*    */ 
/*    */   public void setGeom(String geom) {
/* 46 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.MXingzhengqujie
 * JD-Core Version:    0.6.0
 */