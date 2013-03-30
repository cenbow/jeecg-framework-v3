/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*    */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*    */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*    */ import org.jeecgframework.workflow.pojo.base.TPProcesspro;
/*    */ 
/*    */ public class ProcessHandle
/*    */ {
/*    */   private String taskId;
/*    */   private String businessKey;
/*    */   private String processDefinitionKey;
/*    */   private String taskDefinitionKey;
/*    */   private TPProcess tpProcess;
/*    */   private TPProcessnode tpProcessnode;
/*    */   private TPForm tpForm;
/*    */   List<TPProcesspro> tpProcesspros;
/*    */ 
/*    */   public List<TPProcesspro> getTpProcesspros()
/*    */   {
/* 29 */     return this.tpProcesspros;
/*    */   }
/*    */ 
/*    */   public void setTpProcesspros(List<TPProcesspro> tpProcesspros) {
/* 33 */     this.tpProcesspros = tpProcesspros;
/*    */   }
/*    */ 
/*    */   public String getTaskId() {
/* 37 */     return this.taskId;
/*    */   }
/*    */ 
/*    */   public void setTaskId(String taskId) {
/* 41 */     this.taskId = taskId;
/*    */   }
/*    */ 
/*    */   public String getProcessDefinitionKey() {
/* 45 */     return this.processDefinitionKey;
/*    */   }
/*    */ 
/*    */   public void setProcessDefinitionKey(String processDefinitionKey) {
/* 49 */     this.processDefinitionKey = processDefinitionKey;
/*    */   }
/*    */ 
/*    */   public String getTaskDefinitionKey() {
/* 53 */     return this.taskDefinitionKey;
/*    */   }
/*    */ 
/*    */   public void setTaskDefinitionKey(String taskDefinitionKey) {
/* 57 */     this.taskDefinitionKey = taskDefinitionKey;
/*    */   }
/*    */ 
/*    */   public TPProcess getTpProcess() {
/* 61 */     return this.tpProcess;
/*    */   }
/*    */ 
/*    */   public void setTpProcess(TPProcess tpProcess) {
/* 65 */     this.tpProcess = tpProcess;
/*    */   }
/*    */ 
/*    */   public TPProcessnode getTpProcessnode() {
/* 69 */     return this.tpProcessnode;
/*    */   }
/*    */ 
/*    */   public void setTpProcessnode(TPProcessnode tpProcessnode) {
/* 73 */     this.tpProcessnode = tpProcessnode;
/*    */   }
/*    */ 
/*    */   public TPForm getTpForm() {
/* 77 */     return this.tpForm;
/*    */   }
/*    */ 
/*    */   public void setTpForm(TPForm tpForm) {
/* 81 */     this.tpForm = tpForm;
/*    */   }
/*    */ 
/*    */   public String getBusinessKey() {
/* 85 */     return this.businessKey;
/*    */   }
/*    */ 
/*    */   public void setBusinessKey(String businessKey) {
/* 89 */     this.businessKey = businessKey;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.ProcessHandle
 * JD-Core Version:    0.6.0
 */