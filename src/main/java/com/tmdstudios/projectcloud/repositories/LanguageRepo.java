package com.tmdstudios.projectcloud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmdstudios.projectcloud.models.Language;

@Repository
public interface LanguageRepo extends CrudRepository<Language, Long> {
	List<Language> findAll();
}
