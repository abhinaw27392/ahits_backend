package com.ahi.web.controllers;

import java.security.Principal;
import java.util.List;

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
import com.ahi.model.ProjectsModel;
import com.ahi.service.ProjectService;

@RequestMapping("/rest/projects")
@RestController()
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectsModel>> getAllProjects() {

		try {
			List<ProjectsModel> models = projectService.getAllProjects();
			return new ResponseEntity<List<ProjectsModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectsModel> saveProject(@RequestBody ProjectsModel projectsModel, Principal principal)
			throws Exception {
		try {
			if (projectsModel.getProjectId() == null || projectsModel.getProjectId() == 0)
				projectsModel = projectService.addProject(projectsModel);
			else
				projectsModel = projectService.updateProject(projectsModel);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
		return new ResponseEntity<ProjectsModel>(projectsModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProject(@RequestParam(value = "projectIds") Integer[] projectIds) {
		try {
			for (Integer projectId : projectIds) {
				projectService.deleteProject(projectId);

			}
			return new ResponseEntity<String>("Successfully deleted Project(s)", HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}
}
