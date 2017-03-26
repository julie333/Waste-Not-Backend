package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Group;
import com.project.domain.Product;
import com.project.domain.User;
import com.project.repository.GroupRepository;

@Transactional
@Service
public class DefaultGroupService implements GroupService {

	private final GroupRepository groupRepository;
	
	@Autowired
	public DefaultGroupService(GroupRepository groupRepository) {
		super();
		this.groupRepository = groupRepository;
	}
	
	@Override
	public Group createGroup(Group group) {
		
		return this.groupRepository.save(group);

	}

	@Override
	public Group findById(Long GroupId) {
		
		return this.groupRepository.findOne(GroupId);
	}

	@Override
	public void deleteById(Long GroupId) {
		
//		Delete Members
		
		Group group = groupRepository.findOne(GroupId);
		List<User> members = groupRepository.findOne(GroupId).getMembers();

		for (User user : members) {
			user.getGroups().remove(group);
		}
		this.groupRepository.delete(GroupId);	
	}

	@Override
	public List<Product> findProductsByGroup(Long GroupId) {
		
		return this.groupRepository.findOne(GroupId).getProductsSharedToGroup();
	}

	@Override
	public List<User> findMembersByGroup(Long GroupId) {
		
		return this.groupRepository.findOne(GroupId).getMembers();
	}

	@Override
	public List<Group> findAll() {
		
		return this.groupRepository.findAll();
	}

}
