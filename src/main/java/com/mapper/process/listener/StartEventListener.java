package com.mapper.process.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 开始事件监听器。
 * @author ray
 *
 */
public class StartEventListener extends BaseNodeEventListener {

	private final static Log logger=LogFactory.getLog(StartEventListener.class);
	
	@Override
	protected void execute(DelegateExecution execution, String actDefId,String nodeId) {
		logger.info("enter StartEventListener");
	}
	
	

	@Override
	protected Integer getScriptType() {
		return 0;
	}

}
