package com.experiment.exp.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.experiment.exp.dto.UserDTO;
import com.experiment.exp.model.SampleEntity;
import com.experiment.exp.repository.SampleRepository;
import com.experiment.exp.service.SampleService;


@Service
/*  @Service("customServiceBean")
	You can use the value attribute when:
	You want to give a custom name to the bean 
	so it can be referenced explicitly in your application context 
	or configuration.
	If you use the value attribute to specify the bean name, 
	you can access it explicitly using that name:
	
	@Autowired
	@Qualifier("customServiceBean")
	private MyService myService; 
*/
public class SampleServiceImpl implements SampleService{
	
	private SampleRepository sampleRepository;
	
	
	public SampleServiceImpl(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	@Override
	public String greeting() {
		return "Greeting, this is get mapping";
	}

	@Override
	public String welcome() {
		return "Hello, Spring Boot!";
	}

	@Override
	public Map<String, String> dashboard() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "Hi, How can I help you");
		return response;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		UserDTO userResponseDto = new UserDTO();
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setName(user.getName());
		
		SampleEntity userEntity = new SampleEntity();
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		sampleRepository.save(userEntity);
		
		return userResponseDto;
	}

	@Override
	public String updateUserFully(UserDTO user, Integer id) {
		Optional<SampleEntity> optionalUser = sampleRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new RuntimeException("Id not found in Repository");
		}
		SampleEntity userEntity = new SampleEntity();
		userEntity =  optionalUser.get();
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		sampleRepository.save(userEntity);
		
		return "User Updated Successfully";
	}

	@Override
	public String updateUserPartially(UserDTO user, Integer id) {
		Optional<SampleEntity> optionalUser = sampleRepository.findById(id);
	    if (optionalUser.isEmpty()) {
	        throw new RuntimeException("Id not found in Repository");
	    }
	    SampleEntity userEntity = optionalUser.get();
	    if (user.getEmail() != null) {
	        userEntity.setEmail(user.getEmail());
	    }
	    if (user.getName() != null) {
	        userEntity.setName(user.getName());
	    }
	    sampleRepository.save(userEntity);
	    
	    return "User Updated Successfully";
	}

}
