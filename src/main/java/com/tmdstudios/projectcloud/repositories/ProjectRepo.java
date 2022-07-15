package com.tmdstudios.projectcloud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.models.User;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
	List<Project> findAll();
	Project findByIdIs(Long id);
	List<Project> findAllByContributors(User contributors);
}
