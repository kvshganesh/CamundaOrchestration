# CamundaOrchestration
Camunda orchestration with Spring boot and Swagger

ParllelGatewayDemo is a demo application Integrating Camunda with Spring boot and Swagger.
One can Initiate the process, 
Get list of active tasks and 
complete the task. 

<b>Environment details :</b> 
Spring doc version : 1.6.12, 
Spring boot version : 2.6.6 And 
Camunda Version : 7.17 

<B>Prerequisite : </B>
Install Camunda Moduler to create models.
Create a bpmn file using camunda moduler and save it under resources. 

<B>Note :</B> Need to restart the service each time a new bpmn file is added/updated.

Application has to store the process id

Step 1 : Call http://localhost:8080/initTask/processId/businessKey to initiate the process and save the response which is the unique id for the process.
<BR>Step 2 : Call http://localhost:8080/fetchTaskIdList/processId to  get the list of active task id's for the given process. Where processId is the response we recive at step 1.
<BR>Step 3 : Call http://localhost:8080/completeTask/taskId To complete the task where TaskID is the id that we recive at step 2. 

