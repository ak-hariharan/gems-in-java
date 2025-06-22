package com.experiment.exp.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.experiment.exp.dto.UserDTO;
import com.experiment.exp.service.SampleService;

@RestController
@RequestMapping("/api")
public class SampleController {

	private SampleService sampleService;

	public SampleController(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	// -------
	/*
	 * If the class is annotated with @Controller we have to use
	 * 
	 * @ResponeBody to assume that we are returning simply a string. Otherwise, it
	 * will search for an view present in the templates
	 */

//	@RestController
//	@RequestMapping("/api")
//	public class SampleController {

//    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String hello(@RequestParam String name) {
//        return "Hello, " + name +" Spring Boot!";
//    }	
//	}
	// -----
	/*
	 * By default if we annotated with @RestController, It tells Spring to serialize
	 * the returned value directly into the HTTP response body, rather than looking
	 * for a view template.
	 */
	@GetMapping("/greeting")
	public String greeting() {
		return sampleService.greeting();
	}

	// -----
	/*
	 * If you returning the plain string but with produces as
	 * APPLICATION_JSON_VALUE, it tries to serialize the String
	 * "Hello, Spring Boot!" into JSON and it expects the response as JSON
	 */
	@GetMapping(value = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
	public String welcome() {
		return sampleService.welcome();
	}

	/*
	 * While mentioning the produces = JSON_VALUE return a Map<String, String>,
	 * which Spring Boot automatically converts into a valid JSON object Or send it
	 * as DTO for JSON Response
	 */
	@GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> dashboard() {
		return sampleService.dashboard();
	}

	/*
	 * If we explicitly mention consumes = JSON_VALUE as annotated the method
	 * as @GetMapping Spring Boot doesn't recognize or process the request body for
	 * a GET endpoint, even if the Content-Type is application/json and a body is
	 * sent.
	 * 
	 * Spring assumes a body will not be present, which leads to the
	 * HttpMediaTypeNotSupportedException.
	 * 
	 * 
	 * @GetMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	 * public String create(@RequestBody UserDTO user) { return
	 * "User created successfully " + user.getName() + " " + user.getEmail(); }
	 * 
	 * If you want to stick to @GetMapping, you cannot use @RequestBody. Instead,
	 * pass the data as query parameters:
	 * 
	 * @GetMapping("/create") public String create(@RequestParam String
	 * name, @RequestParam String email) { return "User created successfully: " +
	 * name + " " + email; }
	 * 
	 */

	// By default the @RequestBody expects the input as JSON VALUE
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO createUser(@RequestBody UserDTO user) {
		return sampleService.createUser(user);
	}
	
	// Neither annotation enforces full or partial updates by default — 
	// they just route the request to the correct handler method

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUserFully(@RequestBody UserDTO user, @PathVariable Integer id) {
		return sampleService.updateUserFully(user, id);
	}

	// Annotating a method with @PatchMapping does not inherently make it perform a
	// partial update.
	// The behavior of the update depends entirely on the logic you write.
	@PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUserPartially(@RequestBody UserDTO user, @PathVariable Integer id) {
		// Logic for partial update
		return sampleService.updateUserPartially(user, id);
	}

}