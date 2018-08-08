package com.ahi.service.impl;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahi.AHCustomException;
import com.ahi.entity.AhiTimesheet;
import com.ahi.model.TimesheetModel;
import com.ahi.repository.TimesheetRepository;
import com.ahi.repository.UserRepository;
import com.ahi.service.TimesheetService;

@Service
public class TimesheetServiceImpl implements TimesheetService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Autowired
	private UserRepository ahiUserRepository;

	@Override
	public TimesheetModel addTimesheet(TimesheetModel tm) throws AHCustomException {
		try {
			AhiTimesheet ts = new AhiTimesheet();
			ts.setProjectName(tm.getProjectName());
			ts.setTaskName(tm.getTaskName());
			ts.setDate(tm.getDate());
			ts.setTotalHours(tm.getTotalHours());
			ts.setEmpId(ahiUserRepository.findById(tm.getEmpId()).get().getId());
			timesheetRepository.save(ts);
			tm.setId(ts.getId());
			return tm;
		} catch (Exception e) {
			log.error("Error while adding task" +  e);
			throw new AHCustomException("Error while adding task");
		}
	}

	@Override
	public TimesheetModel updateTimesheet(TimesheetModel tm) throws AHCustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimesheetModel> getAllDatas(Integer empId) throws AHCustomException {
		// TODO Auto-generated method stub
		return null;
	}

}

