/*    */ package org.jeecgframework.workflow.pojo.activiti;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="act_ru_identitylink")
/*    */ public class ActRuIdentitylink
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private ActReProcdef actReProcdef;
/*    */   private ActRuTask actRuTask;
/*    */   private Integer rev;
/*    */   private String groupId;
/*    */   private String type;
/*    */   private String userId;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="hibernate-uuid")
/*    */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*    */   public String getId()
/*    */   {
/* 35 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 39 */     this.id = id;
/*    */   }
/* 45 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="proc_def_id_")
/*    */   public ActReProcdef getActReProcdef() { return this.actReProcdef; }
/*    */ 
/*    */   public void setActReProcdef(ActReProcdef actReProcdef)
/*    */   {
/* 49 */     this.actReProcdef = actReProcdef;
/*    */   }
/* 55 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="task_id_")
/*    */   public ActRuTask getActRuTask() { return this.actRuTask; }
/*    */ 
/*    */   public void setActRuTask(ActRuTask actRuTask)
/*    */   {
/* 59 */     this.actRuTask = actRuTask;
/*    */   }
/*    */   @Column(name="rev_")
/*    */   public Integer getRev() {
/* 64 */     return this.rev;
/*    */   }
/*    */ 
/*    */   public void setRev(Integer rev) {
/* 68 */     this.rev = rev;
/*    */   }
/*    */   @Column(name="group_id_")
/*    */   public String getGroupId() {
/* 73 */     return this.groupId;
/*    */   }
/*    */ 
/*    */   public void setGroupId(String groupId) {
/* 77 */     this.groupId = groupId;
/*    */   }
/*    */   @Column(name="type_")
/*    */   public String getType() {
/* 82 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 86 */     this.type = type;
/*    */   }
/*    */   @Column(name="user_id_")
/*    */   public String getUserId() {
/* 91 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(String userId) {
/* 95 */     this.userId = userId;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActRuIdentitylink
 * JD-Core Version:    0.6.0
 */