package com.mapper.process.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskCreateListener extends BaseTaskListener {
	private static final long serialVersionUID = -4841871719745607437L;
	private Log logger = LogFactory.getLog(TaskCreateListener.class);
	@Override
	protected void execute(DelegateTask delegateTask, String actDefId, String nodeId) {
		logger.info("creating task "+delegateTask);
		//delegateTask.setVariable("taskids", delegateTask.getId());
		
		//设置执行人
		//handleAssign(...)
	}

	@Override
	protected int getScriptType() {
		return 0;
	}

}
