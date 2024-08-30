package com.student.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentDto {
	private int studentId;
	private String studentName;
	private String studentCollege;
	private Date studentDOB;
	private  int studentAge;

}
