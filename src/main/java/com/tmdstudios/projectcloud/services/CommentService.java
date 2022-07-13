package com.tmdstudios.projectcloud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdstudios.projectcloud.models.Comment;
import com.tmdstudios.projectcloud.repositories.CommentRepo;

@Service
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	
	public List<Comment> allComments(){
		return commentRepo.findAll();
	}
	
	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public Comment updateComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public void deleteComment(Comment comment) {
		commentRepo.delete(comment);
	}
}
