package com.experiment.exp.service;

import java.util.Map;

import com.experiment.exp.dto.UserDTO;

public interface SampleService {
	public String greeting();
	public String welcome();
	public Map<String, String> dashboard();
	public UserDTO createUser(UserDTO user);
	public String updateUserFully(UserDTO user, Integer id);
	public String updateUserPartially(UserDTO user, Integer id);
}
