package org.jeecgframework.core.common.dao;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSPrjstatus;
import jeecg.system.pojo.base.TSUser;

import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;

public interface ICommonDao extends IGenericBaseCommonDao{
	/**
	 * 检查用户是否存在
	 * */
	public TSUser getUserByUserIdAndUserNameExits(TSUser user);
	public String getUserRole(TSUser user);
	/**
	 * 根据角色编码和状态值获取审批状态
	 * @param prjstate
	 * @param rolecode
	 * @return
	 */
	public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode);
	/**
	 * 文件上传
	 * @param request
	 */
	public <T> T  uploadFile(UploadFile uploadFile);
	/**
	 * 文件上传或预览
	 * @param uploadFile
	 * @return
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);
	/**
	 * 根据模板对象返回指定实体字段的封装MAP对象
	 * @param template
	 * @return
	 */
	public Map<Object,Object> getParametMap(Template template);
	public String getTempleContent(Template template);
	public Map<Object,Object> getDataSourceMap(Template template);
	/**
	 * 生成XML文件
	 * @param fileName XML全路径
	 */
	public HttpServletResponse createXml(ImportFile importFile);
	/**
	 * 解析XML文件
	 * @param fileName XML全路径
	 */
	public void parserXml(String fileName);
	public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree);
	/**
	 * 根据模型生成JSON
	 * @param all 全部对象
	 * @param in  已拥有的对象
	 * @param comboBox 模型
	 * @return
	 */
	public  List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in);
	public  List<TreeGrid> treegrid(List all,TreeGridModel treeGridModel);
}

