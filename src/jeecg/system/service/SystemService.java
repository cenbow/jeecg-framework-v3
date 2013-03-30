package jeecg.system.service;

import org.jeecgframework.core.common.service.CommonService;

import jeecg.system.pojo.base.TSBusConfig;
import jeecg.system.pojo.base.TSPrjstatus;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.pojo.base.TSUser;


public interface SystemService extends CommonService{
//	/**
//	 * 登陆用户检查
//	 * @param user
//	 * @return
//	 * @throws Exception
//	 */
//	public TSUser checkUserExits(TSUser user) throws Exception;
	/**
	 * 日志添加
	 * @param LogContent 内容
	 * @param loglevel 级别
	 * @param operatetype 类型
	 * @param TUser 操作人
	 */
	public void addLog(String LogContent, Short loglevel,Short operatetype);
	/**
	 * 根据角色编码和当前审批状态值获取审批状态
	 */
	public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode);
	/**
	 * 根据流程编码和业务类名获取业务配置类
	 */
	public TSBusConfig getTSBusConfig(Class classname, String processkey);
	/**
	 * 根据类型编码和类型名称获取Type,如果为空则创建一个
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSType getType(String typecode,String typename,TSTypegroup tsTypegroup);
	/**
	 * 根据类型分组编码和名称获取TypeGroup,如果为空则创建一个
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSTypegroup getTypeGroup(String typegroupcode,String typgroupename);
	
	
	
}
