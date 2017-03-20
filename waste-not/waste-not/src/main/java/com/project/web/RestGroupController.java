package com.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.Group;
import com.project.domain.JsonViews;
import com.project.service.GroupService;

@RestController
@RequestMapping("/groups")
public class RestGroupController {

	private final GroupService groupservice;


	@Autowired
	public RestGroupController(GroupService groupservice) {
		this.groupservice = groupservice;

	}

	@JsonView(JsonViews.Public.class)
	@GetMapping("/{groupId}")
	public Group retrieveProductById(@PathVariable Long groupId) {
		System.out.println(this.groupservice.findById(groupId).getDescription());
		return this.groupservice.findById(groupId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Group> listAllGroups() {
		return this.groupservice.findAll();
	}
	
}
