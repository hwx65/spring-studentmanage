package org.springframework.samples.studentmanage.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.studentmanage.model.Student;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends Repository<Student, Integer> {

	@Query("SELECT DISTINCT student FROM Student student WHERE student.name LIKE :name%")
	@Transactional(readOnly = true)
	Collection<Student> findByName(@Param("name") String name);

	@Query("SELECT student FROM Student student WHERE student.id =:id")
	@Transactional(readOnly = true)
	Student findById(@Param("id") String id);

	void save(Student student);

	void delete(Student student);

}
