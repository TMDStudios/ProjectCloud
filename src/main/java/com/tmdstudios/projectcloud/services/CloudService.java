package com.tmdstudios.projectcloud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Cloud;
import com.tmdstudios.projectcloud.repositories.CloudRepo;

@Service
public class CloudService {
	@Autowired
	CloudRepo cloudRepo;
	
	public List<Cloud> allCloudWords() {
		return cloudRepo.findAll();
	}
	
	public Cloud addCloudWord(Cloud cloud) {
		return cloudRepo.save(cloud);
	}
}
