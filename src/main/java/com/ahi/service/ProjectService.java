package com.ahi.service;

import java.util.List;

import com.ahi.AHCustomException;
import com.ahi.model.ProjectsModel;

public interface ProjectService {

	ProjectsModel addProject(ProjectsModel pm) throws AHCustomException;

	boolean deleteProject(Integer projectId) throws AHCustomException;

	ProjectsModel updateProject(ProjectsModel pm) throws AHCustomException;

	List<ProjectsModel> getAllProjects() throws AHCustomException;


}
