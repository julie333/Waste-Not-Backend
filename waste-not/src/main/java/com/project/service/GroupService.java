package com.project.service;

import java.util.List;

import com.project.domain.Group;
import com.project.domain.Product;
import com.project.domain.User;

public interface GroupService {
	
	public Group createGroup(Group group);
	
	public Group findById(Long GroupId);

	public void deleteById(Long GroupId);
	
	public List<Product> findProductsByGroup(Long GroupId);
	
	public List<User> findMembersByGroup(Long GroupId);

	public List<Group> findAll();
			
}
