package com.mapper.process.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.core.model.ResultView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/process/")
@Api(tags={"流程办理相关接口"})
public class ProcessController {
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private FormService formService;

	
	@RequestMapping(value="startprocess",method=RequestMethod.GET)
	@ApiOperation(value="启动流程,无表单", notes="根据流程key启动流程")
	@ApiImplicitParam(name = "processDefinitionKey", value = "流程定义key", required = true,paramType="query")
	public ResultView startProcess(String processDefinitionKey) {
		ResultView rtv= new ResultView();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("startuser", "mapper");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
		List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
		taskService.complete(list.get(0).getId(),variables);
		//rtv.appendObject(processInstance);
		return rtv;
	}
	
	@RequestMapping(value="complete",method=RequestMethod.GET)
	@ApiOperation(value="完成任务", notes="根据taskid执行任务")
	@ApiImplicitParam(name = "taskid", value = "任务id", required = true,paramType="query")
	public ResultView complete(String taskid) {
		ResultView rtv= new ResultView();
		// Fetch all tasks for the management group
//		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().list();
//		for (Task task : tasks) {
//		  System.out.println("Task available: " + task.getName());
//		}
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskService.complete(taskid, taskVariables);
		return rtv;
	}
	
}
