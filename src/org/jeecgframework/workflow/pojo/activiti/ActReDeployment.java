/*    */ package org.jeecgframework.workflow.pojo.activiti;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
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
/*    */ @Table(name="act_re_deployment")
/*    */ public class ActReDeployment
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private String category;
/*    */   private Timestamp deployTime;
/* 30 */   private Set<ActReModel> actReModels = new HashSet(0);
/* 31 */   private Set<ActGeBytearray> actGeBytearraies = new HashSet(0);
/*    */ 
/* 37 */   @Id
/*    */   @GeneratedValue(generator="hibernate-uuid")
/*    */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*    */   public String getId() { return this.id; }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 41 */     this.id = id;
/*    */   }
/*    */   @Column(name="name_")
/*    */   public String getName() {
/* 46 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 50 */     this.name = name;
/*    */   }
/*    */   @Column(name="category_")
/*    */   public String getCategory() {
/* 55 */     return this.category;
/*    */   }
/*    */ 
/*    */   public void setCategory(String category) {
/* 59 */     this.category = category;
/*    */   }
/*    */   @Column(name="deploy_time_", length=29)
/*    */   public Timestamp getDeployTime() {
/* 64 */     return this.deployTime;
/*    */   }
/*    */ 
/*    */   public void setDeployTime(Timestamp deployTime) {
/* 68 */     this.deployTime = deployTime;
/*    */   }
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actReDeployment")
/*    */   public Set<ActReModel> getActReModels() {
/* 73 */     return this.actReModels;
/*    */   }
/*    */ 
/*    */   public void setActReModels(Set<ActReModel> actReModels) {
/* 77 */     this.actReModels = actReModels;
/*    */   }
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actReDeployment")
/*    */   public Set<ActGeBytearray> getActGeBytearraies() {
/* 82 */     return this.actGeBytearraies;
/*    */   }
/*    */ 
/*    */   public void setActGeBytearraies(Set<ActGeBytearray> actGeBytearraies) {
/* 86 */     this.actGeBytearraies = actGeBytearraies;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActReDeployment
 * JD-Core Version:    0.6.0
 */