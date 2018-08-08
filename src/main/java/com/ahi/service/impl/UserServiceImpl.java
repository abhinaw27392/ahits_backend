package com.ahi.service.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ahi.AHCustomException;
import com.ahi.entity.AhiTasks;
import com.ahi.entity.AhiUser;
import com.ahi.model.TasksModel;
import com.ahi.model.UserModel;
import com.ahi.repository.UserRepository;
import com.ahi.service.UserService;
import com.ahi.web.to.PasswordConfirmation;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository ahiUserRepository;
	
	@Value("${password.default}")
    private String dafaultPassword;
	
	@Override
	public void saveUser(String loginId, AhiUser user) throws Exception {
		log.info(user.toString());
		log.info("By User: " + loginId);
		if(user.getId() == null){
			user.setPassword(dafaultPassword);
			Optional<AhiUser> userInDb = ahiUserRepository.findByLoginIdIgnoreCase(user.getLoginId());
			if(userInDb.isPresent()){
				throw new Exception("Login ID already exists. Please enter a different Login ID.");
			}
			user.setWhoCreated(loginId);
			user.setWhenCreated(new Date());
		}
		user.setWhoUpdated(loginId);
		user.setWhenUpdated(new Date());
		ahiUserRepository.save(user);
	}

	@Override
	public Iterable<AhiUser> getUsers() {
		return ahiUserRepository.findAll();
	}

	@Override
	public AhiUser getUser(String username) {
		Optional<AhiUser> user = ahiUserRepository.findByLoginIdIgnoreCase(username);
		return user.get();
	}

	@Override
	public void changePassword(String loginId, PasswordConfirmation passwordConfirmation) throws Exception {
		Optional<AhiUser> userInDb = ahiUserRepository.findByLoginIdIgnoreCase(loginId);
		if(!userInDb.isPresent()){
			throw new Exception("User does not exists.");
		}
		AhiUser user = userInDb.get();
		
		if(!passwordConfirmation.getPasswordCurrent().equals(user.getPassword())){
			throw new Exception("Incorrect current password.");
		}
		user.setPassword(passwordConfirmation.getPassword());
		user.setWhoUpdated(loginId);
		user.setWhenUpdated(new Date());
		ahiUserRepository.save(user);

	}
	
	@Override
	public UserModel addUser(UserModel um)  throws AHCustomException {
		try {
			AhiUser user = new AhiUser();
			user.setId(um.getId());
			user.setLoginId(um.getLoginId());
			user.setPassword(um.getPassword());
			user.setActive(true);
			user.setEmail(um.getEmail());
			user.setFirstName(um.getFirstName());
			user.setLastName(um.getLastName());
			user.setDob(um.getDob());
			user.setDesignation(um.getDesignation());
			user.setJoiningDate(um.getJoiningDate());
			user.setRole(um.getRole());
//			Optional<AhiUser> userInDb = ahiUserRepository.findByLoginIdIgnoreCase(um.getSupervisorId());
//			user.setSupervisorId(userInDb.get());
			user.setSupervisorId(um.getSupervisorId());
			ahiUserRepository.save(user);
			return um;
		} catch (Exception e) {
			log.error("Error while adding user" +  e);
			throw new AHCustomException("Error while adding user");
		}
	}
	
	@Override
	public UserModel updateUser(UserModel um) throws AHCustomException {
		try {
			AhiUser user = ahiUserRepository.findById(um.getId()).get();
			if (user == null)
				throw new AHCustomException(
						"Error wnile udpating user.. No user found for user id:::" + um.getId());
			user.setLoginId(um.getLoginId());
//			user.setPassword(um.getPassword());
			user.setActive(true);
//			user.setEmail(um.getEmail());
			user.setFirstName(um.getFirstName());
			user.setLastName(um.getLastName());
			user.setDob(um.getDob());
			user.setDesignation(um.getDesignation());
			user.setJoiningDate(um.getJoiningDate());
			user.setRole(um.getRole());
//			Optional<AhiUser> userInDb = ahiUserRepository.findByLoginIdIgnoreCase(um.getSupervisorId());
			
			user.setSupervisorId(um.getSupervisorId());
			ahiUserRepository.save(user);
			return um;
		} catch (Exception e) {
			log.error("Error while adding user");
			throw new AHCustomException("Error while updating user");
		}
	}
	
	

}
