package com.ITP.IFKFbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ITP.IFKFbackend.model.Student;

@Repository 
public interface StudentRepository extends JpaRepository<Student,String>{

	 public Student findTopByOrderByStudentIdDesc();
	 
	 List<Student> findByname(String searchText);
	 
	 @Query("from Student s WHERE s.name LIKE %:searchText% OR s.sessionId = :searchText ORDER BY s.studentId")
	 List <Student> searchQuery (@Param("searchText") String name);

	 public List<Student> findBysessionId(String sessionId); 
}
