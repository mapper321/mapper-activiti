package com.mapper.process.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 结束事件监听器。
 * 
 * @author ray
 *
 */
public class EndEventListener extends BaseNodeEventListener {
	private final static Log logger = LogFactory.getLog(EndEventListener.class);

	@Override
	protected void execute(DelegateExecution execution, String actDefId, String nodeId) {
		ExecutionEntity ent = (ExecutionEntity) execution;
		if (!ent.isEnded())
			return;

		// 当前的excutionId和主线程相同时。
		if (ent.getId().equals(ent.getProcessInstanceId())) {
			handEnd(ent);
		}

	}

	private void handEnd(ExecutionEntity ent) {

	}

	@Override
	protected Integer getScriptType() {
		return 0;
	}

}
