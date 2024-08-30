package com.student.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.dto.StudentDto;
import com.student.entities.StudentEntity;
import com.student.exception.StudentServiceException;
import com.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public ResponseEntity addPatientToDB(StudentDto studentDto) throws StudentServiceException {

		LocalDate TD = LocalDate.now();
		Date sqlDate = studentDto.getStudentDOB();
		 if(sqlDate == null) {
			throw new StudentServiceException("125", "Student Date Of Birth Should Not Be Null Or Empty ");
		}
		LocalDate DOB = sqlDate.toLocalDate();
		Period period = Period.between(DOB, TD);

		StudentEntity studentEntity = new StudentEntity();

		studentEntity.setStudentName(studentDto.getStudentName());
		studentEntity.setStudentAge(period.getYears());
		studentEntity.setStudentDOB(studentDto.getStudentDOB());
		studentEntity.setStudentCollege(studentDto.getStudentCollege());

		if (period.getYears() < 18)  {
			throw new StudentServiceException("125", " Student Must Be At Least 18 Years Old ");
		}
		
		studentRepo.saveAndFlush(studentEntity);

		return new ResponseEntity<>("Record Inserted", HttpStatus.ACCEPTED);
	}

	@Override
	public List<StudentEntity> getAllStudent() throws StudentServiceException {

		List<StudentEntity> studentList = studentRepo.findAll();
		if (studentList == null || studentList.isEmpty()) {
			throw new StudentServiceException("123", "Student Not Found ");
		}
		List<StudentEntity> collect = studentList.stream().sorted((s1, s2) -> s2.getStudentAge() - s1.getStudentAge())
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public ResponseEntity updateStudentById(int studentId, StudentEntity studentEntity) throws StudentServiceException {

		studentEntity.setStudentId(studentId);

		if (studentRepo.existsById(studentId)) {

			StudentEntity entity = studentRepo.save(studentEntity);

			StudentDto dto = new StudentDto();
			dto.setStudentId(entity.getStudentId());
			dto.setStudentName(entity.getStudentName());
			dto.setStudentAge(entity.getStudentAge());
			dto.setStudentCollege(entity.getStudentCollege());
			dto.setStudentDOB(entity.getStudentDOB());
		} else {
			throw new StudentServiceException("122", "Invalid Student Id");
		}
		return new ResponseEntity<>("Record Updated", HttpStatus.ACCEPTED);
	}

	@Override
	public StudentEntity getStudentById(int studentId) throws StudentServiceException {

		Optional<StudentEntity> findById = studentRepo.findById(studentId);
		StudentEntity studentEntity = findById.orElse(null);
		if (studentEntity == null) {
			throw new StudentServiceException("122", "Invalid Student Id");
		}
		return studentEntity;
	}

	@Override
	public void deleteStudentById(int studentId) throws StudentServiceException {

		if (studentRepo.existsById(studentId)) {
			studentRepo.deleteById(studentId);
		} else {
			throw new StudentServiceException("122", "Invalid Student Id");

		}

	}

	@Override
	public StudentEntity getStudentByName(String studentName) throws StudentServiceException {

		StudentEntity findAllByStudentName = studentRepo.findAllByStudentName(studentName);
		if (findAllByStudentName == null) {
			throw new StudentServiceException("101", "Invalid Student Name");
		}
		return findAllByStudentName;
	}

	@Override
	public List<StudentEntity> getAllStudentByCollege(String studentCollege) throws StudentServiceException {

		List<StudentEntity> findAllByStudentCollege = studentRepo.findAllByStudentCollege(studentCollege);
		if (findAllByStudentCollege == null || findAllByStudentCollege.isEmpty()) {
			throw new StudentServiceException("101", "Invalid College Name");
		}
		return findAllByStudentCollege;
	}

}
