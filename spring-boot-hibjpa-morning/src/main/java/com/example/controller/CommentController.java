package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Comment;
import com.example.repository.CommentRepository;

@RestController
@RequestMapping("/api") // Root Level Mapping
public class CommentController {

	// PostMan ---> Spring Container(Internal) ---> RestController(Internal) ---> 
	//Controller ---> Service Layer ---> Service Implementation Layer 
	//---> Model ---> Repository ---> Database.
	
	//Business Layer - Service Layer - DAO Layer
	
	@Autowired
	CommentRepository commentRepository;
	
	@GetMapping("/comments") //Method Level Mapping
	public List<Comment> getAllComments() // http://localhost:8080/api/comments -- GET
	{
		System.out.println("Inside GET...");
		return commentRepository.findAll();
	}
	
	@PostMapping("/postcomments") //Method Level Mapping
	public Comment createComment(@RequestBody Comment comment) // http://localhost:8080/api/postcomments -- POST
	{	// JSON to POJO Conversion - Jackson Converter
		System.out.println("Inside POST...");
		return commentRepository.save(comment);
	}
	
	@GetMapping("/comments/{id}") //Method Level Mapping
	public Comment getCommentById(@PathVariable(value="id") Long commentId) throws Exception // http://localhost:8080/api/dcomments/123 -- DELETE
	{
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new Exception("Invalid Id"));
					
		return comment;
	}
	
	@DeleteMapping("/dcomments/{id}") //Method Level Mapping
	public ResponseEntity<?> deleteComment(@PathVariable(value="id") Long commentId) throws Exception // http://localhost:8080/api/dcomments/123 -- DELETE
	{
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new Exception("Invalid Id"));
		
		commentRepository.delete(comment);
		
		return ResponseEntity.ok().build();
	}
}
