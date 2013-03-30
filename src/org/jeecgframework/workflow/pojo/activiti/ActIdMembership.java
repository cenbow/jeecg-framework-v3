/*    */ package org.jeecgframework.workflow.pojo.activiti;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="act_id_membership")
/*    */ public class ActIdMembership
/*    */   implements Serializable
/*    */ {
/*    */   private ActIdGroup actIdGroup;
/*    */   private ActIdUser actIdUser;
/*    */ 
/*    */   @Id
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="group_id_", nullable=false, insertable=false, updatable=false)
/*    */   public ActIdGroup getActIdGroup()
/*    */   {
/* 24 */     return this.actIdGroup;
/*    */   }
/*    */ 
/*    */   public void setActIdGroup(ActIdGroup actIdGroup) {
/* 28 */     this.actIdGroup = actIdGroup;
/*    */   }
/* 34 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="user_id_", nullable=false, insertable=false, updatable=false)
/*    */   public ActIdUser getActIdUser() { return this.actIdUser; }
/*    */ 
/*    */   public void setActIdUser(ActIdUser actIdUser)
/*    */   {
/* 38 */     this.actIdUser = actIdUser;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActIdMembership
 * JD-Core Version:    0.6.0
 */