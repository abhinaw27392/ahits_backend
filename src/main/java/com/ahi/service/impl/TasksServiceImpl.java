package com.ahi.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahi.AHCustomException;
import com.ahi.entity.AhiTasks;
import com.ahi.entity.AhiUser;
import com.ahi.model.TasksModel;
import com.ahi.repository.TasksRepository;
import com.ahi.repository.UserRepository;
import com.ahi.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TasksRepository tasksRepository;

	@Autowired
	private UserRepository ahiUserRepository;

	@Override
	public TasksModel addTask(TasksModel tm) throws AHCustomException {
		try {
			AhiTasks tasks = new AhiTasks();
			tasks.setTaskName(tm.getTaskName());
			tasks.setTaskDescription(tm.getTaskDescription());
			tasks.setUser(ahiUserRepository.findById(tm.getUserId()).get());
			tasksRepository.save(tasks);
			tm.setTaskId(tasks.getTaskId());
			return tm;
		} catch (Exception e) {
			log.error("Error while adding task" +  e);
			throw new AHCustomException("Error while adding task");
		}
	}

	@Override
	public boolean deleteTask(Integer taskId) throws AHCustomException {
		try {
			Optional<AhiTasks> tasks = tasksRepository.findById(taskId);
			if (tasks.isPresent())
				log.debug("The entity is present");
			else
				log.debug("The entity is not present");
			AhiTasks task = tasks.get();
			tasksRepository.delete(task);
			return true;
		} catch (Exception e) {
			log.error("Error while deleting tasks");
			throw new AHCustomException("Error while deleting task::" + taskId);
		}
	}

	@Override
	public TasksModel updateTask(TasksModel tm) throws AHCustomException {
		try {
			AhiTasks task = tasksRepository.findById(tm.getTaskId()).get();
			if (task == null)
				throw new AHCustomException(
						"Error wnile udpating task.. No task found for task id:::" + tm.getTaskId());
			task.setTaskName(tm.getTaskName());
			task.setTaskDescription(tm.getTaskDescription());
			task.setUser(ahiUserRepository.findById(tm.getUserId()).get());
			tasksRepository.save(task);
			return tm;
		} catch (Exception e) {
			log.error("Error while adding task");
			throw new AHCustomException("Error while updating task");
		}
	}

	@Override
	public List<TasksModel> getAllTasks() throws AHCustomException {
		try {
			List<TasksModel> models = new ArrayList<>();
			Iterable<AhiTasks> tasks = tasksRepository.findAll();
			Iterator<AhiTasks> iterator = tasks.iterator();
			while (iterator.hasNext()) {
				TasksModel tm = new TasksModel();
				AhiTasks task = iterator.next();
				tm.setTaskId(task.getTaskId());
				tm.setTaskDescription(task.getTaskDescription());
				tm.setTaskName(task.getTaskName());
				models.add(tm);
			}
			return models;
		} catch (Exception e) {
			log.error("Error while getting tasks");
			throw new AHCustomException("Error while getting tasks");
		}
	}

	@Override
	public List<TasksModel> getTasksForUser(Integer userId) throws AHCustomException {
		
			List<TasksModel> models = new ArrayList<>();
			Optional<AhiUser> ahiuser = ahiUserRepository.findById(userId);
			if (ahiuser.get() == null)
				throw new AHCustomException("Invalid user id.. " + userId);
			try {
			Iterable<AhiTasks> tasks = tasksRepository.findByUser(ahiuser.get());
			log.debug("tasks::" + tasks.toString() );
			Iterator<AhiTasks> iterator = tasks.iterator();
			while (iterator.hasNext()) {
				log.debug("Inside Iter");
				TasksModel tm = new TasksModel();
				AhiTasks task = iterator.next();
				log.debug("Inside Iter" + task.getTaskName());
				tm.setTaskId(task.getTaskId());
				tm.setTaskDescription(task.getTaskDescription());
				tm.setTaskName(task.getTaskName());
				models.add(tm);
			}
			return models;
		} catch (Exception e) {
			log.error("Error while getting tasks" + e);
			throw new AHCustomException("Error while getting tasks");
		}
	}

}
