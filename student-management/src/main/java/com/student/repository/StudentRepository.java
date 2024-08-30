package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	StudentEntity findAllByStudentName(String studentName);

	List<StudentEntity> findAllByStudentCollege(String studentCollege);

}
