/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="m_quhua_zhengzhou")
/*    */ public class MQuhuaZhengzhou
/*    */   implements Serializable
/*    */ {
/*    */   private Integer gid;
/*    */   private Long id;
/*    */   private String name;
/*    */   private String geom;
/*    */ 
/*    */   public MQuhuaZhengzhou()
/*    */   {
/*    */   }
/*    */ 
/*    */   public MQuhuaZhengzhou(Long id, String name, String geom)
/*    */   {
/* 32 */     this.id = id;
/* 33 */     this.name = name;
/* 34 */     this.geom = geom;
/*    */   }
/* 42 */   @Id
/*    */   @GeneratedValue
/*    */   @Column(name="gid", unique=true, nullable=false)
/*    */   public Integer getGid() { return this.gid; }
/*    */ 
/*    */   public void setGid(Integer gid)
/*    */   {
/* 46 */     this.gid = gid;
/*    */   }
/*    */   @Column(name="id", precision=10, scale=0)
/*    */   public Long getId() {
/* 51 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 55 */     this.id = id;
/*    */   }
/*    */   @Column(name="name", length=80)
/*    */   public String getName() {
/* 60 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 64 */     this.name = name;
/*    */   }
/*    */   @Column(name="geom")
/*    */   public String getGeom() {
/* 69 */     return this.geom;
/*    */   }
/*    */ 
/*    */   public void setGeom(String geom) {
/* 73 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.MQuhuaZhengzhou
 * JD-Core Version:    0.6.0
 */