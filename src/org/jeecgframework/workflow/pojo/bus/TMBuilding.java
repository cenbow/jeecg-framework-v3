/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="m_building")
/*    */ public class TMBuilding extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String gid;
/*    */   private String name;
/*    */   private String geom;
/*    */ 
/*    */   @Column(name="name", length=60)
/*    */   public String getName()
/*    */   {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 26 */     this.name = name;
/*    */   }
/*    */   @Column(name="geom")
/*    */   public String getGeom() {
/* 31 */     return this.geom;
/*    */   }
/*    */ 
/*    */   public void setGeom(String geom) {
/* 35 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TMBuilding
 * JD-Core Version:    0.6.0
 */