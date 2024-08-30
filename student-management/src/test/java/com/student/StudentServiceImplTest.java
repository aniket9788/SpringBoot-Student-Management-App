package com.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.student.entities.StudentEntity;
import com.student.exception.StudentServiceException;
import com.student.repository.StudentRepository;
import com.student.service.StudentServiceImpl;

@SpringBootTest (classes= {StudentServiceImplTest.class})
public class StudentServiceImplTest {

	@Mock
	StudentRepository studentRepo;
	
	@InjectMocks
	StudentServiceImpl studentServiceImpl;
	
	@Test
	public void testGetAllStudent() throws StudentServiceException {
		ArrayList<StudentEntity> list = new ArrayList<StudentEntity>();
		
		list.add(new StudentEntity(101,"Aniket","Jd",new Date(0),15));
		list.add(new StudentEntity(102,"Deepak","Anglo",new Date(0),16));
		
		when(studentRepo.findAll()).thenReturn(list);
		
		List<StudentEntity> studentEntity = studentServiceImpl.getAllStudent();
		
		assertEquals(2,studentEntity.size());

	}
	
	
}
