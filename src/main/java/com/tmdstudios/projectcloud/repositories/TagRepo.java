package com.tmdstudios.projectcloud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.models.Tag;

@Repository
public interface TagRepo extends CrudRepository<Tag, Long> {
	List<Tag> findAll();
	Tag findByIdIs(Long id);
	Tag findByNameIs(String name);
	List<Tag> findAllByProjects(Project project);
	List<Tag> findByProjectsNotContains(Project project);
}
