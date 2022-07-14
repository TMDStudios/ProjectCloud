package com.tmdstudios.projectcloud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Language;
import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.repositories.LanguageRepo;

@Service
public class LanguageService {
	@Autowired
	LanguageRepo languageRepo;
	
	public List<Language> allLanguages(){
		return languageRepo.findAll();
	}
	
	public Language addLanguage(Language language) {
		return languageRepo.save(language);
	}
	
	public Language updateLanguage(Language language) {
		return languageRepo.save(language);
	}
	
	public void deleteLanguage(Language language) {
		languageRepo.delete(language);
	}
	
	public List<Language> getAssignedProjects(Project project){
		return languageRepo.findAllByProjects(project);
	}
	
	public List<Language> getunAssignedProjects(Project project){
		return languageRepo.findByProjectsNotContains(project);
	}
	
	public Language findByName(String name) {
		return languageRepo.findByNameIs(name);
	}
	
	public Language findById(Long id) {
		Optional<Language> optionalLanguage = languageRepo.findById(id);
		if(optionalLanguage.isPresent()) {
			return optionalLanguage.get();
		}else {
			return null;
		}
	}
}