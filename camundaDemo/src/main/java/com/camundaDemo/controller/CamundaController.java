package com.camundaDemo.controller;

import java.util.List;

import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.camundaDemo.service.CamundaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class CamundaController {

	@Autowired
	CamundaService camundaService;

	@Operation(summary = "Initialize the process")
	@RequestMapping(value = "/initTask/{process}/{businessKey}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> initProcess(@Parameter(description = "Process Id to be copied from the moduler (<bpmn:process id=\"<B>Process_1ldeoka</B>\" isExecutable=\"true\">)") @PathVariable(name = "process") String process,
			@Parameter(description = "Unique value of the process to filter from in multi threaded environment. This value can be used to filter in Camunda Console") @PathVariable(name = "businessKey") String businessKey) {
		String response = camundaService.inititateProcess(process, businessKey);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/fetchActivityInstance/{processId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ActivityInstance> getActivityInstance(@Parameter(description = "Process id retrieved from /initTask/{process}/{businessKey} end point.")@PathVariable(name = "processId") String processId) {
		ActivityInstance response = camundaService.getProcessStatus(processId);
		if (response != null)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/fetchTaskIdList/{processId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<String>> fetchTaskList(@Parameter(description = "Process id retrieved from /initTask/{process}/{businessKey} end point.")@PathVariable(name = "processId") String processId) {
		List<String> response = camundaService.getTaskList(processId);
		if (CollectionUtils.isEmpty(response))
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/completeTask/{taskId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> completeTask(@Parameter(description = "Task id retrieved from /fetchTaskIdList/{processId} end point.")@PathVariable(name = "taskId") String taskId) {
		String response = camundaService.completeTask(taskId);
		if (response != null)
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
