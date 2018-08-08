package com.ahi.web.controllers;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahi.AHCustomException;
import com.ahi.model.TasksModel;
import com.ahi.service.TasksService;

@RequestMapping("/api/tasks")
@RestController()
public class TaskController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TasksService tasksService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TasksModel> saveTask(@RequestBody TasksModel tasksModel, Principal principal)
			throws Exception {
		try {
			if (tasksModel.getTaskId() == null || tasksModel.getTaskId() == 0)
				tasksModel = tasksService.addTask(tasksModel);
			else
				tasksModel = tasksService.updateTask(tasksModel);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
		return new ResponseEntity<TasksModel>(tasksModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTask(@PathVariable Integer taskId) {
		try {
			boolean isDeleted = tasksService.deleteTask(taskId);
			if (isDeleted)
				return new ResponseEntity<String>("Successfully deleted task", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Error while deleting task", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TasksModel>> findAllTask() {

		try {
			List<TasksModel> models = tasksService.getAllTasks();
			return new ResponseEntity<List<TasksModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTasks(@RequestParam(value = "taskIds") Integer[] taskIds) {
		try {
			for (Integer taskId : taskIds) {
				tasksService.deleteTask(taskId);
			}
			return new ResponseEntity<String>("Successfully deleted task(s)", HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}

}
