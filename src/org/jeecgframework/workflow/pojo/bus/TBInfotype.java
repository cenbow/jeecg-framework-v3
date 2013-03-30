/*    */ package org.jeecgframework.workflow.pojo.bus;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import jeecg.system.pojo.base.TSAttachment;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_b_infotype")
/*    */ public class TBInfotype extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String typecode;
/*    */   private String typename;
/*    */   private String description;
/*    */   private String note;
/* 28 */   private Set<TSAttachment> TSAttachments = new HashSet(0);
/*    */ 
/*    */   @Column(name="typename", nullable=false, length=100)
/*    */   public String getTypename() {
/* 33 */     return this.typename;
/*    */   }
/*    */ 
/*    */   public void setTypename(String typename) {
/* 37 */     this.typename = typename;
/*    */   }
/*    */   @Column(name="description", length=300)
/*    */   public String getDescription() {
/* 42 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 46 */     this.description = description;
/*    */   }
/*    */   @Column(name="note", length=300)
/*    */   public String getNote() {
/* 51 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 55 */     this.note = note;
/*    */   }
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TBInfotype")
/*    */   public Set<TSAttachment> getTSAttachments() {
/* 60 */     return this.TSAttachments;
/*    */   }
/*    */ 
/*    */   public void setTSAttachments(Set<TSAttachment> TSAttachments) {
/* 64 */     this.TSAttachments = TSAttachments;
/*    */   }
/* 68 */   @Column(name="typecode", length=20)
/*    */   public String getTypecode() { return this.typecode; }
/*    */ 
/*    */   public void setTypecode(String typecode)
/*    */   {
/* 72 */     this.typecode = typecode;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBInfotype
 * JD-Core Version:    0.6.0
 */