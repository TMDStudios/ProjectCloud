package com.tmdstudios.projectcloud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmdstudios.projectcloud.models.Cloud;

@Repository
public interface CloudRepo extends CrudRepository<Cloud, Long> {
	List<Cloud> findAll();
}
