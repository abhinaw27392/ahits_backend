package com.ahi.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.ahi.model.DepartmentsModel;
import com.ahi.service.DepartmentService;

@RequestMapping("/rest/departments")
@RestController()
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartmentsModel>> getAllDepartments() {

		try {
			List<DepartmentsModel> models = departmentService.getAllDepartments();
			return new ResponseEntity<List<DepartmentsModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DepartmentsModel> saveDepartment(@RequestBody DepartmentsModel DepartmentsModel,
			Principal principal) throws Exception {
		try {
			if (DepartmentsModel.getDepartmentId() == null || DepartmentsModel.getDepartmentId() == 0)
				DepartmentsModel = departmentService.addDepartment(DepartmentsModel);
			else
				DepartmentsModel = departmentService.updateDepartment(DepartmentsModel);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
		return new ResponseEntity<DepartmentsModel>(DepartmentsModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteDepartment(@RequestParam("departmentIds") Integer[] departmentIds) {
		try {
			for (Integer departmentId : departmentIds) {
				departmentService.deleteDepartment(departmentId);
			}
			return new ResponseEntity<String>("Successfully deleted Department(s)", HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}

}
