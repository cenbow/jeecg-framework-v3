/*    */ package org.jeecgframework.workflow.pojo.activiti;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="act_id_group")
/*    */ public class ActIdGroup
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private Integer rev;
/*    */   private String name;
/*    */   private String type;
/* 29 */   private Set<ActIdMembership> actIdMemberships = new HashSet(0);
/*    */ 
/* 35 */   @Id
/*    */   @GeneratedValue(generator="hibernate-uuid")
/*    */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*    */   public String getId() { return this.id; }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 39 */     this.id = id;
/*    */   }
/*    */   @Column(name="rev_")
/*    */   public Integer getRev() {
/* 44 */     return this.rev;
/*    */   }
/*    */ 
/*    */   public void setRev(Integer rev) {
/* 48 */     this.rev = rev;
/*    */   }
/*    */   @Column(name="name_")
/*    */   public String getName() {
/* 53 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 57 */     this.name = name;
/*    */   }
/*    */   @Column(name="type_")
/*    */   public String getType() {
/* 62 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 66 */     this.type = type;
/*    */   }
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actIdGroup")
/*    */   public Set<ActIdMembership> getActIdMemberships() {
/* 71 */     return this.actIdMemberships;
/*    */   }
/*    */ 
/*    */   public void setActIdMemberships(Set<ActIdMembership> actIdMemberships) {
/* 75 */     this.actIdMemberships = actIdMemberships;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActIdGroup
 * JD-Core Version:    0.6.0
 */