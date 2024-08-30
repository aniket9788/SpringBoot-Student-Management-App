package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.StudentDto;
import com.student.entities.StudentEntity;
import com.student.exception.StudentServiceException;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
    @Autowired
    private StudentService studentService;
   
    
    @PostMapping(value ="/addStudent")
    public ResponseEntity <String> addStudentToDB(@RequestBody StudentDto studentDto ) throws StudentServiceException{
	   
    	ResponseEntity responseEntity = studentService.addPatientToDB(studentDto);
    	return responseEntity;
    	
    }
    @GetMapping (value ="/students")
    public ResponseEntity<List<StudentEntity>> getAllStudent() throws StudentServiceException{
    	 
    	List<StudentEntity> studentList = studentService.getAllStudent();
    	return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
    
    @GetMapping(value="studentD/{studentId}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int studentId ) throws StudentServiceException{
    	StudentEntity studentEntity = studentService.getStudentById(studentId);
    	return ResponseEntity.status(HttpStatus.OK).body(studentEntity);
    		
    	
    }
    
    @PutMapping(value="student/{studentId}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable int studentId,
    		@RequestBody StudentEntity studentEntity) throws StudentServiceException{
    	HttpEntity<StudentDto> studentResponse = studentService.updateStudentById(studentId,studentEntity);
		return  (ResponseEntity<StudentDto>) studentResponse;
    	
    }
    @DeleteMapping (value="studentS/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int studentId) throws StudentServiceException{
    	studentService.deleteStudentById(studentId);
		return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    	
    }
    @GetMapping(value="studentH/{studentName}")
    public ResponseEntity<StudentEntity> getStudentByName(@PathVariable String studentName ) throws StudentServiceException{
    	StudentEntity studentEntity = studentService.getStudentByName(studentName);
    	return ResponseEntity.status(HttpStatus.OK).body(studentEntity);
    }
    @GetMapping(value="studentC/{studentCollege}")
    public ResponseEntity<List<StudentEntity>> getAllStudentByCollege(@PathVariable String studentCollege ) throws StudentServiceException{
    	List<StudentEntity> studentEntity = studentService.getAllStudentByCollege(studentCollege);
    	return ResponseEntity.status(HttpStatus.OK).body(studentEntity);
    }
}
