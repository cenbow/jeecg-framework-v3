/*     */ package org.jeecgframework.workflow.pojo.bus;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import jeecg.system.pojo.base.TSBaseBus;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_b_declaretable")
/*     */ @PrimaryKeyJoinColumn(name="id")
/*     */ public class TBDeclaretable extends TSBaseBus
/*     */   implements Serializable
/*     */ {
/*     */   private String declareno;
/*     */   private String constructionunit;
/*     */   private String projectname;
/*     */   private String projectlocation;
/*     */   private String administrativeregion;
/*     */   private Date declaredate;
/*     */   private Short declaretype;
/*     */   private String legalrepresentative;
/*     */   private String phone;
/*     */   private String agent;
/*     */   private String agentphone;
/*     */   private String address;
/*     */   private String post;
/*     */   private String projectnum;
/*     */   private String projectaddress;
/*     */   private Double totalconstructionarea;
/*     */   private Double floorarea;
/*     */   private Double undergroundarea;
/*     */   private BigDecimal householdnum;
/*     */   private BigDecimal peoplenum;
/*     */   private String note;
/*     */   private String scandata;
/*     */ 
/*     */   @Column(name="declareno", length=100)
/*     */   public String getDeclareno()
/*     */   {
/*  58 */     return this.declareno;
/*     */   }
/*     */ 
/*     */   public void setDeclareno(String declareno) {
/*  62 */     this.declareno = declareno;
/*     */   }
/*     */   @Column(name="constructionunit", nullable=false, length=100)
/*     */   public String getConstructionunit() {
/*  67 */     return this.constructionunit;
/*     */   }
/*     */ 
/*     */   public void setConstructionunit(String constructionunit) {
/*  71 */     this.constructionunit = constructionunit;
/*     */   }
/*     */   @Column(name="projectname", nullable=false, length=100)
/*     */   public String getProjectname() {
/*  76 */     return this.projectname;
/*     */   }
/*     */ 
/*     */   public void setProjectname(String projectname) {
/*  80 */     this.projectname = projectname;
/*     */   }
/*     */   @Column(name="projectlocation", length=100)
/*     */   public String getProjectlocation() {
/*  85 */     return this.projectlocation;
/*     */   }
/*     */ 
/*     */   public void setProjectlocation(String projectlocation) {
/*  89 */     this.projectlocation = projectlocation;
/*     */   }
/*     */   @Column(name="administrativeregion", length=50)
/*     */   public String getAdministrativeregion() {
/*  94 */     return this.administrativeregion;
/*     */   }
/*     */ 
/*     */   public void setAdministrativeregion(String administrativeregion) {
/*  98 */     this.administrativeregion = administrativeregion;
/*     */   }
/* 104 */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="declaredate", length=13)
/*     */   public Date getDeclaredate() { return this.declaredate; }
/*     */ 
/*     */   public void setDeclaredate(Date declaredate)
/*     */   {
/* 108 */     this.declaredate = declaredate;
/*     */   }
/*     */   @Column(name="declaretype", nullable=false)
/*     */   public Short getDeclaretype() {
/* 113 */     return this.declaretype;
/*     */   }
/*     */ 
/*     */   public void setDeclaretype(Short declaretype) {
/* 117 */     this.declaretype = declaretype;
/*     */   }
/*     */   @Column(name="legalrepresentative", nullable=false, length=10)
/*     */   public String getLegalrepresentative() {
/* 122 */     return this.legalrepresentative;
/*     */   }
/*     */ 
/*     */   public void setLegalrepresentative(String legalrepresentative) {
/* 126 */     this.legalrepresentative = legalrepresentative;
/*     */   }
/*     */   @Column(name="phone", nullable=false, length=30)
/*     */   public String getPhone() {
/* 131 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 135 */     this.phone = phone;
/*     */   }
/*     */   @Column(name="agent", length=10)
/*     */   public String getAgent() {
/* 140 */     return this.agent;
/*     */   }
/*     */ 
/*     */   public void setAgent(String agent) {
/* 144 */     this.agent = agent;
/*     */   }
/*     */   @Column(name="agentphone", length=30)
/*     */   public String getAgentphone() {
/* 149 */     return this.agentphone;
/*     */   }
/*     */ 
/*     */   public void setAgentphone(String agentphone) {
/* 153 */     this.agentphone = agentphone;
/*     */   }
/*     */   @Column(name="address", length=300)
/*     */   public String getAddress() {
/* 158 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 162 */     this.address = address;
/*     */   }
/*     */   @Column(name="post", length=10)
/*     */   public String getPost() {
/* 167 */     return this.post;
/*     */   }
/*     */ 
/*     */   public void setPost(String post) {
/* 171 */     this.post = post;
/*     */   }
/*     */   @Column(name="projectnum", length=50)
/*     */   public String getProjectnum() {
/* 176 */     return this.projectnum;
/*     */   }
/*     */ 
/*     */   public void setProjectnum(String projectnum) {
/* 180 */     this.projectnum = projectnum;
/*     */   }
/*     */   @Column(name="projectaddress", length=200)
/*     */   public String getProjectaddress() {
/* 185 */     return this.projectaddress;
/*     */   }
/*     */ 
/*     */   public void setProjectaddress(String projectaddress) {
/* 189 */     this.projectaddress = projectaddress;
/*     */   }
/*     */   @Column(name="totalconstructionarea", precision=12)
/*     */   public Double getTotalconstructionarea() {
/* 194 */     return this.totalconstructionarea;
/*     */   }
/*     */ 
/*     */   public void setTotalconstructionarea(Double totalconstructionarea) {
/* 198 */     this.totalconstructionarea = totalconstructionarea;
/*     */   }
/*     */   @Column(name="floorarea", precision=12)
/*     */   public Double getFloorarea() {
/* 203 */     return this.floorarea;
/*     */   }
/*     */ 
/*     */   public void setFloorarea(Double floorarea) {
/* 207 */     this.floorarea = floorarea;
/*     */   }
/*     */   @Column(name="undergroundarea", precision=12)
/*     */   public Double getUndergroundarea() {
/* 212 */     return this.undergroundarea;
/*     */   }
/*     */ 
/*     */   public void setUndergroundarea(Double undergroundarea) {
/* 216 */     this.undergroundarea = undergroundarea;
/*     */   }
/*     */   @Column(name="householdnum", precision=131089, scale=0)
/*     */   public BigDecimal getHouseholdnum() {
/* 221 */     return this.householdnum;
/*     */   }
/*     */ 
/*     */   public void setHouseholdnum(BigDecimal householdnum) {
/* 225 */     this.householdnum = householdnum;
/*     */   }
/*     */   @Column(name="peoplenum", precision=131089, scale=0)
/*     */   public BigDecimal getPeoplenum() {
/* 230 */     return this.peoplenum;
/*     */   }
/*     */ 
/*     */   public void setPeoplenum(BigDecimal peoplenum) {
/* 234 */     this.peoplenum = peoplenum;
/*     */   }
/*     */   @Column(name="note", length=300)
/*     */   public String getNote() {
/* 239 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 243 */     this.note = note;
/*     */   }
/*     */   @Column(name="scandata", length=1)
/*     */   public String getScandata() {
/* 248 */     return this.scandata;
/*     */   }
/*     */ 
/*     */   public void setScandata(String scandata) {
/* 252 */     this.scandata = scandata;
/*     */   }
/*     */ }

/* Location:           C:\Users\tyy\Desktop\jeecgframework-core-v3.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.bus.TBDeclaretable
 * JD-Core Version:    0.6.0
 */