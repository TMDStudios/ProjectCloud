package com.tmdstudios.projectcloud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmdstudios.projectcloud.models.Language;
import com.tmdstudios.projectcloud.models.Project;

@Repository
public interface LanguageRepo extends CrudRepository<Language, Long> {
	List<Language> findAll();
	Language findByIdIs(Long id);
	Language findByNameIs(String name);
	List<Language> findAllByProjects(Project project);
	List<Language> findByProjectsNotContains(Project project);
}
