/*    */ package org.jeecgframework.workflow.pojo.activiti;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="act_ge_property")
/*    */ public class ActGeProperty
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String value;
/*    */   private Integer rev;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="hibernate-uuid")
/*    */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*    */   public String getName()
/*    */   {
/* 26 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 30 */     this.name = name;
/*    */   }
/*    */   @Column(name="value_", length=300)
/*    */   public String getValue() {
/* 35 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 39 */     this.value = value;
/*    */   }
/*    */   @Column(name="rev_")
/*    */   public Integer getRev() {
/* 44 */     return this.rev;
/*    */   }
/*    */ 
/*    */   public void setRev(Integer rev) {
/* 48 */     this.rev = rev;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActGeProperty
 * JD-Core Version:    0.6.0
 */