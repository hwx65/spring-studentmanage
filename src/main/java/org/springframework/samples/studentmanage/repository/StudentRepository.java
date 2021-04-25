package org.springframework.samples.studentmanage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.studentmanage.model.Student;

/*
public interface StudentRepository extends CrudRepository<Student, Integer> {

	@Query("SELECT DISTINCT student FROM Student student WHERE student.stuname LIKE :stuname%")
	@Transactional(readOnly = true)
	Iterable<Student> findStudent(@Param("stuname") String stuname);

	@Query("SELECT student FROM Student student WHERE student.id =:id")
	@Transactional(readOnly = true)
	Optional<Student> findById(@Param("id") Integer id);

	void delete(Student student);

	@Override
	Iterable<Student> findAll();
}*/
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
