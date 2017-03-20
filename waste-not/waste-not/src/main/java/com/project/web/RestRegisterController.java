package com.project.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.JsonViews;
import com.project.domain.User;
import com.project.service.UserService;

@RestController
@RequestMapping("/register")
public class RestRegisterController {

	private final UserService userService;

	@Autowired
	public RestRegisterController(UserService userService) {
		this.userService = userService;

	}

@JsonView(JsonViews.NewUser.class)
@PostMapping
public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
	User savedUser = userService.registerNewUser(postedUser);

	URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/register")
			.buildAndExpand(savedUser.getId()).toUri();
	return ResponseEntity.created(location).build();
}

}


//	UriComponents uriComponents = fromMethodCall(
//		on(getClass()).retrieveUserByUsername(savedUser.getUsername())).build();
//
//	return ResponseEntity.created(uriComponents.encode().toUri()).build();
//}
