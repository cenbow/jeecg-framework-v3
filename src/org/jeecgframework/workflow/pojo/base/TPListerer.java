/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_p_listener")
/*    */ public class TPListerer extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String listenereven;
/*    */   private String listenertype;
/*    */   private String listenervalue;
/*    */   private String listenername;
/*    */   private Short listenerstate;
/*    */   private Short typeid;
/* 28 */   private List<TPProcessListener> TProcessListeners = new ArrayList(0);
/*    */ 
/* 32 */   @Column(name="listenereven", length=20)
/*    */   public String getListenereven() { return this.listenereven; }
/*    */ 
/*    */   public void setListenereven(String listenereven)
/*    */   {
/* 36 */     this.listenereven = listenereven;
/*    */   }
/*    */   @Column(name="listenertype", length=20)
/*    */   public String getListenertype() {
/* 41 */     return this.listenertype;
/*    */   }
/*    */ 
/*    */   public void setListenertype(String listenertype) {
/* 45 */     this.listenertype = listenertype;
/*    */   }
/*    */   @Column(name="listenervalue", length=100)
/*    */   public String getListenervalue() {
/* 50 */     return this.listenervalue;
/*    */   }
/*    */ 
/*    */   public void setListenervalue(String listenervalue) {
/* 54 */     this.listenervalue = listenervalue;
/*    */   }
/* 58 */   @Column(name="listenername", length=50)
/*    */   public String getListenername() { return this.listenername; }
/*    */ 
/*    */   public void setListenername(String listenername)
/*    */   {
/* 62 */     this.listenername = listenername;
/*    */   }
/*    */   @Column(name="listenerstate")
/*    */   public Short getListenerstate() {
/* 67 */     return this.listenerstate;
/*    */   }
/*    */ 
/*    */   public void setListenerstate(Short listenerstate) {
/* 71 */     this.listenerstate = listenerstate;
/*    */   }
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPListerer")
/*    */   public List<TPProcessListener> getTProcessListeners() {
/* 76 */     return this.TProcessListeners;
/*    */   }
/*    */ 
/*    */   public void setTProcessListeners(List<TPProcessListener> TProcessListeners) {
/* 80 */     this.TProcessListeners = TProcessListeners;
/*    */   }
/* 84 */   @Column(name="typeid")
/*    */   public Short getTypeid() { return this.typeid; }
/*    */ 
/*    */   public void setTypeid(Short typeid)
/*    */   {
/* 88 */     this.typeid = typeid;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPListerer
 * JD-Core Version:    0.6.0
 */