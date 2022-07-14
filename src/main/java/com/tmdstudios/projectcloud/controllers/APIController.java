package com.tmdstudios.projectcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmdstudios.projectcloud.models.Cloud;
import com.tmdstudios.projectcloud.services.CloudService;

@RestController
public class APIController {
	@Autowired
	private CloudService cloudService;	
	
	@RequestMapping("/api/cloud")
	public List<Cloud> allCloudWords(){
		return cloudService.allCloudWords();
	}
}
