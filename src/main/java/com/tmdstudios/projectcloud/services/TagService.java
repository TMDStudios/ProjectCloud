package com.tmdstudios.projectcloud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.models.Tag;
import com.tmdstudios.projectcloud.repositories.TagRepo;

@Service
public class TagService {
	@Autowired
	TagRepo tagRepo;
	
	public List<Tag> allTags(){
		return tagRepo.findAll();
	}
	
	public Tag addTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public Tag updateTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public void deleteTag(Tag tag) {
		tagRepo.delete(tag);
	}
	
	public List<Tag> getAssignedProjects(Project project){
		return tagRepo.findAllByProjects(project);
	}
	
	public List<Tag> getunAssignedProjects(Project project){
		return tagRepo.findByProjectsNotContains(project);
	}
	
	public Tag findByName(String name) {
		return tagRepo.findByNameIs(name);
	}
	
	public Tag findById(Long id) {
		Optional<Tag> optionalTag = tagRepo.findById(id);
		if(optionalTag.isPresent()) {
			return optionalTag.get();
		}else {
			return null;
		}
	}
}