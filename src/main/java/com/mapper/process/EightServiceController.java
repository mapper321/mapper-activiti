package com.mapper.process;

import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.core.model.ResultView;

@RestController
@RequestMapping("/eightservice/")
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
	public ResultView testEightService() {
		ResultView rtv= new ResultView();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		return rtv;
	}
	
}
