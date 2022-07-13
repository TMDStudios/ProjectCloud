package com.tmdstudios.projectcloud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Language;
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
}