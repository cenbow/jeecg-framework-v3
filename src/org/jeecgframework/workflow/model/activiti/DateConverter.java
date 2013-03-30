/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.beanutils.Converter;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.apache.commons.lang3.time.DateUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class DateConverter
/*    */   implements Converter
/*    */ {
/* 13 */   private static final Logger logger = Logger.getLogger(DateConverter.class);
/*    */   private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
/*    */   private static final String DATETIME_PATTERN_NO_SECOND = "yyyy-MM-dd HH:mm";
/*    */   private static final String DATE_PATTERN = "yyyy-MM-dd";
/*    */   private static final String MONTH_PATTERN = "yyyy-MM";
/*    */ 
/*    */   public Object convert(Class type, Object value)
/*    */   {
/* 25 */     Object result = null;
/* 26 */     if (type == Date.class)
/*    */       try {
/* 28 */         result = doConvertToDate(value);
/*    */       } catch (ParseException e) {
/* 30 */         e.printStackTrace();
/*    */       }
/* 32 */     else if (type == String.class) {
/* 33 */       result = doConvertToString(value);
/*    */     }
/* 35 */     return result;
/*    */   }
/*    */ 
/*    */   private Date doConvertToDate(Object value)
/*    */     throws ParseException
/*    */   {
/* 46 */     Date result = null;
/*    */ 
/* 48 */     if ((value instanceof String)) {
/* 49 */       result = DateUtils.parseDate((String)value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", 
/* 50 */         "yyyy-MM-dd HH:mm", "yyyy-MM" });
/*    */ 
/* 53 */       if ((result == null) && (StringUtils.isNotEmpty((String)value))) {
/*    */         try
/*    */         {
/* 56 */           result = new Date(new Long((String)value).longValue());
/*    */         } catch (Exception e) {
/* 58 */           logger.error("Converting from milliseconds to Date fails!");
/* 59 */           e.printStackTrace();
/*    */         }
/*    */       }
/*    */ 
/*    */     }
/* 64 */     else if ((value instanceof Object[]))
/*    */     {
/* 66 */       Object[] array = (Object[])value;
/*    */ 
/* 68 */       if ((array != null) && (array.length >= 1)) {
/* 69 */         value = array[0];
/* 70 */         result = doConvertToDate(value);
/*    */       }
/*    */     }
/* 73 */     else if (Date.class.isAssignableFrom(value.getClass())) {
/* 74 */       result = (Date)value;
/*    */     }
/* 76 */     return result;
/*    */   }
/*    */ 
/*    */   private String doConvertToString(Object value)
/*    */   {
/* 86 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 87 */     String result = null;
/* 88 */     if ((value instanceof Date)) {
/* 89 */       result = simpleDateFormat.format(value);
/*    */     }
/* 91 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.DateConverter
 * JD-Core Version:    0.6.0
 */