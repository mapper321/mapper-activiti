package com.mapper.process.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapper.core.util.SpringUtil;
import com.mapper.process.script.GroovyScriptEngine;

/**
 * 任务事件基类，采用了模版模式。
 */
public abstract class BaseTaskListener implements TaskListener{
	
	protected Logger logger=LoggerFactory.getLogger(BaseTaskListener.class);
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		logger.debug("enter the baseTaskListener notify method...");
		
		TaskEntity taskEnt=(TaskEntity)delegateTask;
		String nodeId=taskEnt.getExecution().getActivityId();
		String actDefId=taskEnt.getProcessDefinitionId();
		
		//执行子类业务逻辑
		execute(delegateTask,actDefId,nodeId);
		//获取脚本类型
		int scriptType=getScriptType();
		//执行事件脚本
		exeEventScript(delegateTask,scriptType,actDefId,nodeId);
		
	}
	
	/**
	 * 执行子类业务逻辑
	 * @param delegateTask
	 * @param actDefId
	 * @param nodeId
	 */
	protected abstract void execute(DelegateTask delegateTask,String actDefId,String nodeId);
	
	/**
	 * 获取脚本类型。eg:0代表pre(前置脚本),1代表post(后置脚本)
	 * @return
	 */
	protected abstract int getScriptType();

	/**
	 * 执行事件脚本
	 * @param delegateTask
	 * @param scriptType
	 * @param actDefId
	 * @param nodeId
	 */
	private void exeEventScript(DelegateTask delegateTask,int scriptType,String actDefId,String nodeId ){
		logger.debug("enter the baseTaskListener exeEventScript method...");
		GroovyScriptEngine scriptEngine = (GroovyScriptEngine) SpringUtil.getBean("scriptEngine");
		Map<String, Object> vars=delegateTask.getVariables();
		vars.put("greetings", "hello Mapper");
		vars.put("task", delegateTask);
		String script = "testService.greetings(greetings);System.out.println(task)";
		scriptEngine.execute(script, vars);
	}
	
}
