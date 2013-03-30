package jeecg.system.service;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.workflow.pojo.bus.TBDeclaretable;

import jeecg.system.pojo.base.TSAttachment;


public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
