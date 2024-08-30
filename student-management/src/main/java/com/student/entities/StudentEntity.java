package com.student.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table (name="student_details")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String studentName;
	private String studentCollege;
	private Date studentDOB;
	private  int studentAge;
	
	public StudentEntity(int studentId, String studentName, String studentCollege, Date studentDOB, int studentAge) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentCollege = studentCollege;
		this.studentDOB = studentDOB;
		this.studentAge = studentAge;
	}
	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
