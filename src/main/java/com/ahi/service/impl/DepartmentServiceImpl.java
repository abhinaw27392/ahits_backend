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
import com.ahi.entity.AhiDepartments;
import com.ahi.entity.AhiUser;
import com.ahi.model.DepartmentsModel;
import com.ahi.repository.DepartmentsRepository;
import com.ahi.repository.UserRepository;
import com.ahi.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentsRepository departmentsRepository;

	@Autowired
	private UserRepository ahiUserRepository;

	@Override
	public DepartmentsModel addDepartment(DepartmentsModel dm) throws AHCustomException {
		try {
			AhiDepartments departments = new AhiDepartments();
			departments.setDepartmentName(dm.getDepartmentName());
			departments.setDepartmentDescription(dm.getDepartmentDescription());
			departments.setHeadedByUser(ahiUserRepository.findById(dm.getHeadedByUserId()).get());
			departmentsRepository.save(departments);
			dm.setDepartmentId(departments.getDepartmentId());
			return dm;
		} catch (Exception e) {
			log.error("Error while adding Department" + e);
			throw new AHCustomException("Error while adding Department");
		}
	}

	@Override
	public boolean deleteDepartment(Integer departmentId) throws AHCustomException {
		try {
			Optional<AhiDepartments> departments = departmentsRepository.findById(departmentId);
			if (departments.isPresent())
				log.debug("The entity is present");
			else
				log.debug("The entity is not present");
			AhiDepartments department = departments.get();
			departmentsRepository.delete(department);
			return true;
		} catch (Exception e) {
			log.error("Error while deleting Departments");
			throw new AHCustomException("Error while deleting Department::" + departmentId);
		}
	}

	@Override
	public DepartmentsModel updateDepartment(DepartmentsModel dm) throws AHCustomException {
		try {
			AhiDepartments department = departmentsRepository.findById(dm.getDepartmentId()).get();
			if (department == null)
				throw new AHCustomException("Error wnile udpating department.. No Department found for Department id:::"
						+ dm.getDepartmentId());
			department.setDepartmentName(dm.getDepartmentName());
			department.setDepartmentDescription(dm.getDepartmentDescription());
			department.setHeadedByUser(ahiUserRepository.findById(dm.getHeadedByUserId()).get());
			departmentsRepository.save(department);
			return dm;
		} catch (Exception e) {
			log.error("Error while adding Department");
			throw new AHCustomException("Error while updating Department");
		}
	}

	@Override
	public List<DepartmentsModel> getAllDepartments() throws AHCustomException {
		try {
			List<DepartmentsModel> models = new ArrayList<>();
			Iterable<AhiDepartments> Departments = departmentsRepository.findAll();
			Iterator<AhiDepartments> iterator = Departments.iterator();
			while (iterator.hasNext()) {
				DepartmentsModel dm = new DepartmentsModel();
				AhiDepartments department = iterator.next();
				dm.setDepartmentId(department.getDepartmentId());
				dm.setDepartmentDescription(department.getDepartmentDescription());
				dm.setDepartmentName(department.getDepartmentName());
				AhiUser headedByUser = department.getHeadedByUser();
				if (headedByUser != null) {
					dm.setHeadedByUserId(headedByUser.getId());
					dm.setHeadedBy(String.format("%s %s", headedByUser.getFirstName(), headedByUser.getLastName()));
				}
				models.add(dm);
			}
			return models;
		} catch (Exception e) {
			log.error("Error while getting Departments");
			throw new AHCustomException("Error while getting Departments");
		}
	}

}
