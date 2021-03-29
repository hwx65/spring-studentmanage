package org.springframework.samples.studentmanage.service;

import java.beans.Transient;
import java.util.Collection;

import org.springframework.samples.studentmanage.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.samples.studentmanage.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Cacheable(value = "findstudent", key = "#id")
	public Student findById(int id) {
		return studentRepository.findById(id);
	}

	@Cacheable(value = "find_students", key = "#stuname")
	public Collection<Student> findByName(String stuname) {
		return studentRepository.findByName(stuname);
	}

	@CacheEvict(value = "all_student", allEntries = true)
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	@CacheEvict(value = "all_student", allEntries = true)
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

}
