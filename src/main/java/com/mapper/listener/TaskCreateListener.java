package com.mapper.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskCreateListener implements TaskListener {

	private static final long serialVersionUID = -4841871719745607437L;

	@Override
	public void notify(DelegateTask delegateTask) {
//		Object startuser = delegateTask.getVariable("startuser");
//		delegateTask.setAssignee(startuser+"");
	}

}
