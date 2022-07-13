package com.tmdstudios.projectcloud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}