package com.ahi.web.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahi.AHCustomException;
import com.ahi.model.ProjectsModel;
import com.ahi.model.TasksModel;
import com.ahi.model.TimesheetModel;
import com.ahi.service.TimesheetService;

@RequestMapping("/api/timesheet")
@RestController()
public class TimesheetController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimesheetService timesheetService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<TimesheetModel>> saveTimesheet(@RequestBody List<TimesheetModel> tms,
			Principal principal) throws Exception {

		try {
			for (TimesheetModel tm : tms) {
				if (tm.getId() == null || tm.getId() == 0)
					tm = timesheetService.addTimesheet(tm);
				else
					tm = timesheetService.updateTimesheet(tm);
			}

		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
		return new ResponseEntity<List<TimesheetModel>>(tms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fetchData/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TimesheetModel>> findAllData(@PathVariable Integer empId,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
			@RequestParam("toDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) {
		try {
			List<TimesheetModel> models = timesheetService.getAllDatas(empId, fromDate, toDate);
			return new ResponseEntity<List<TimesheetModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}

	}

	// ------------------------------getProjectData---------------------------------------------------------------------------
	@RequestMapping(value = "/projectData/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectsModel>> getProjectData(@PathVariable Integer empId) {
		try {
			List<ProjectsModel> models = timesheetService.getProjectData(empId);
			return new ResponseEntity<List<ProjectsModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}

	// ----------------------------------getTaskData------------------------------------------------------------------------
	@RequestMapping(value = "/taskData/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TasksModel>> getTaskData(@PathVariable Integer empId) {
		try {
			List<TasksModel> models = timesheetService.getTaskData(empId);
			return new ResponseEntity<List<TasksModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
	}

}
