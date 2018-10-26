package com.mapper.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class MyEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		System.out.println("========"+event.getType());

	}

	@Override
	public boolean isFailOnException() {
		
		return false;
	}

}
