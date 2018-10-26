package com.mapper.process;

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
import org.springframework.web.bind.annotation.RestController;

import com.mapper.core.model.ResultView;

@RestController
@RequestMapping("/eightservice/")
@SuppressWarnings("unused")
public class EightServiceController {
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

	
	@RequestMapping("start")
	public ResultView startProcess() {
		ResultView rtv= new ResultView();
		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("employeeName", "Kermit");
//		variables.put("numberOfDays", new Integer(4));
//		variables.put("vacationMotivation", "I'm really tired!");
		variables.put("startuser", "mapper");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process",variables);
		List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
		taskService.complete(list.get(0).getId(),variables);
//		if(1==1) {
//			throw new NullPointerException();
//		}
		return rtv;
	}
	
	@RequestMapping("complete")
	public ResultView complete() {
		ResultView rtv= new ResultView();
		// Fetch all tasks for the management group
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
		  System.out.println("Task available: " + task.getName());
		}
		Task task = tasks.get(0);

		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "true");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
		return rtv;
	}
	
}
