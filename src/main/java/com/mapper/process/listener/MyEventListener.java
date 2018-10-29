package com.mapper.process.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyEventListener implements ActivitiEventListener {
	private Log logger = LogFactory.getLog(MyEventListener.class);
	
	@Override
	public void onEvent(ActivitiEvent event) {
		logger.info("========"+event.getType());
	}

	@Override
	public boolean isFailOnException() {
		
		return false;
	}

}
