package com.ITP.IFKFbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ITP.IFKFbackend.model.Student;
import com.ITP.IFKFbackend.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")

public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	
	
	@GetMapping("/students/{studentId}")

	ResponseEntity<?> getStudents(@PathVariable Long studentId){
	
		Optional<Student> students = studentRepository.findById(studentId);
		
		return students.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		
	}
	
	
	
	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateEquipment(
			@PathVariable Long studentId, @RequestBody Student student){
		

		Student result = studentRepository.save(student);
		
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}
