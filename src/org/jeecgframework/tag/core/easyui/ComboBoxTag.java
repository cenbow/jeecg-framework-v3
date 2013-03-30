/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.common.model.json.ComboBox;
/*    */ 
/*    */ public class ComboBoxTag extends TagSupport
/*    */ {
/*    */   protected String id;
/*    */   protected String text;
/*    */   protected String url;
/*    */   protected String name;
/*    */   protected Integer width;
/*    */   protected Integer listWidth;
/*    */   protected Integer listHeight;
/*    */   protected boolean editable;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 30 */     return 6;
/*    */   }
/*    */   public int doEndTag() throws JspTagException {
/*    */     try {
/* 34 */       JspWriter out = this.pageContext.getOut();
/* 35 */       out.print(end().toString());
/* 36 */       out.flush();
/*    */     } catch (IOException e) {
/* 38 */       e.printStackTrace();
/*    */     }
/* 40 */     return 6;
/*    */   }
/*    */   public StringBuffer end() {
/* 43 */     StringBuffer sb = new StringBuffer();
/* 44 */     ComboBox comboBox = new ComboBox();
/* 45 */     comboBox.setText(this.text);
/* 46 */     comboBox.setId(this.id);
/* 47 */     sb.append("<script type=\"text/javascript\">$(function() {$('#" + 
/* 49 */       this.name + "').combobox({" + 
/* 50 */       "url:'" + this.url + "&id=" + this.id + "&text=" + this.text + "'," + 
/* 51 */       "editable:'false'," + 
/* 52 */       "valueField:'id'," + 
/* 53 */       "textField:'text'," + 
/* 54 */       "width:'" + this.width + "'," + 
/* 55 */       "listWidth:'" + this.listWidth + "'," + 
/* 56 */       "listHeight:'" + this.listWidth + "'," + 
/* 57 */       "onChange:function(){" + 
/* 58 */       "var val = $('#" + this.name + "').combobox('getValues');" + 
/* 59 */       "$('#" + this.name + "hidden').val(val);" + 
/* 60 */       "}" + 
/* 61 */       "});" + 
/* 62 */       "});" + 
/* 63 */       "</script>");
/* 64 */     sb.append("<input type=\"hidden\" name=\"" + this.name + "\" id=\"" + this.name + "hidden\" > " + 
/* 65 */       "<input class=\"easyui-combobox\" " + 
/* 66 */       "multiple=\"true\" panelHeight=\"auto\" name=\"" + this.name + "name\" id=\"" + this.name + "\" >");
/* 67 */     return sb;
/*    */   }
/*    */   public void setId(String id) {
/* 70 */     this.id = id;
/*    */   }
/*    */   public void setText(String text) {
/* 73 */     this.text = text;
/*    */   }
/*    */   public void setUrl(String url) {
/* 76 */     this.url = url;
/*    */   }
/*    */   public void setName(String name) {
/* 79 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.tag.core.easyui.ComboBoxTag
 * JD-Core Version:    0.6.0
 */