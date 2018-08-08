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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahi.AHCustomException;
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
	public ResponseEntity<TimesheetModel> saveTimesheet(@RequestBody TimesheetModel tm, Principal principal)
			throws Exception {
		
		try {
			if (tm.getId() == null || tm.getId() == 0)
				tm = timesheetService.addTimesheet(tm);
			else
				tm = timesheetService.updateTimesheet(tm);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}
		return new ResponseEntity<TimesheetModel>(tm, HttpStatus.OK);
	}

	@RequestMapping(value = "/timesheet/fetchData/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TimesheetModel>> findAllData(@PathVariable Integer empId, Principal principal) {
		
		try {
			List<TimesheetModel> models = timesheetService.getAllDatas(empId);
			return new ResponseEntity<List<TimesheetModel>>(models, HttpStatus.OK);
		} catch (AHCustomException e) {
			throw new WebServerException(e.getErrorMessage(), e);
		}

	}


}
