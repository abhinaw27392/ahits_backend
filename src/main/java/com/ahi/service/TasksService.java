package com.ahi.service;

import java.util.List;

import com.ahi.AHCustomException;
import com.ahi.model.TasksModel;

public interface TasksService {

	TasksModel addTask(TasksModel tm) throws AHCustomException;

	boolean deleteTask(Integer taskId) throws AHCustomException;

	TasksModel updateTask(TasksModel tm) throws AHCustomException;

	List<TasksModel> getAllTasks() throws AHCustomException;

	List<TasksModel> getTasksForUser(Integer userId) throws AHCustomException;

}
