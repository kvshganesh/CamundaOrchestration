package com.camundaDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamundaService {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	public String inititateProcess(String process, String businessKey) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process,businessKey);
		System.out.println("Business Key " + processInstance.getBusinessKey());
		System.out.println("ID : " + processInstance.getId());
		return processInstance.getId();
	}
	
	public ActivityInstance getProcessStatus(String processInstanceId) {
		ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
		return activityInstance;
	}
	
	public List<String> getTaskList(String processInstanceId){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("---------------------------------");
		List<Task> taskList = processEngine.getTaskService().createTaskQuery().orderByDueDate().asc().list();
		List<String> taskIds = new ArrayList<>();
		for(Task task : taskList) {
			taskIds.add(task.getId());
			System.out.println("Task : " + task.toString());
		}
		System.out.println("---------------------------------");
		return taskIds;
	}
	
	public String completeTask(String taskid) {
		taskService.claim(taskid,"demo");
		taskService.complete(taskid);
		return "Task Completed";
	}
}
