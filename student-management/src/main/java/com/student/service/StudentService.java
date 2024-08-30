package com.student.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.student.dto.StudentDto;
import com.student.entities.StudentEntity;
import com.student.exception.StudentServiceException;

public interface StudentService {

	ResponseEntity addPatientToDB(StudentDto studentDto) throws StudentServiceException;

	List<StudentEntity> getAllStudent() throws StudentServiceException;

	ResponseEntity<StudentDto> updateStudentById(int studentId, StudentEntity studentEntity) throws StudentServiceException;

	StudentEntity getStudentById(int studentId) throws StudentServiceException;

	void deleteStudentById(int studentId) throws StudentServiceException;

	StudentEntity getStudentByName(String studentName) throws StudentServiceException;

	List<StudentEntity> getAllStudentByCollege(String studentCollege) throws StudentServiceException;

}
