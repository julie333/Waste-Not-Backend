package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {


}


