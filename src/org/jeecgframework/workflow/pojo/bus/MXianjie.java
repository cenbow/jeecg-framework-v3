/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="m_xianjie")
/*    */ public class MXianjie extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String kind;
/*    */   private String geom;
/*    */ 
/*    */   @Column(name="name", length=50)
/*    */   public String getName()
/*    */   {
/* 23 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */   @Column(name="kind", length=4)
/*    */   public String getKind() {
/* 32 */     return this.kind;
/*    */   }
/*    */ 
/*    */   public void setKind(String kind) {
/* 36 */     this.kind = kind;
/*    */   }
/*    */   @Column(name="geom")
/*    */   public String getGeom() {
/* 41 */     return this.geom;
/*    */   }
/*    */ 
/*    */   public void setGeom(String geom) {
/* 45 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.MXianjie
 * JD-Core Version:    0.6.0
 */