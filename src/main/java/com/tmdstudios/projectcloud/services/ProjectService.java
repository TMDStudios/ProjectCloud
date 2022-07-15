package com.tmdstudios.projectcloud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.models.User;
import com.tmdstudios.projectcloud.repositories.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo projectRepo;
	
	public List<Project> allProjects(){
		return projectRepo.findAll();
	}
	
	public Project addProject(Project project) {
		return projectRepo.save(project);
	}
	
	public Project updateProject(Project project) {
		return projectRepo.save(project);
	}
	
	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}
	
	public Project findById(Long id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		if(optionalProject.isPresent()) {
			return optionalProject.get();
		}else {
			return null;
		}
	}
	
	public List<Project> findByContributor(User contributor) {
		return projectRepo.findAllByContributors(contributor);
	}
}