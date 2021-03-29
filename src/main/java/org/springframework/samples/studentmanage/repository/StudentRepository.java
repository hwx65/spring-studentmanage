package org.springframework.samples.studentmanage.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.studentmanage.model.Student;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends Repository<Student, Integer> {

	/**
	 * Retrieve {@link Student}s from the data store by stuname, returning all students
	 * whose stuname <i>starts</i> with the given stuname.
	 * @param stuname Value to search for
	 * @return a Collection of matching {@link Student}s (or an empty Collection if none
	 * found)
	 */
	@Query("SELECT DISTINCT student FROM Student student WHERE student.stuname LIKE :stuname%")
	@Transactional(readOnly = true)
	Collection<Student> findByName(@Param("stuname") String stuname);

	/**
	 * Retrieve an {@link Student} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Student} if found
	 */
	@Query("SELECT student FROM Student student WHERE student.id =:id")
	@Transactional(readOnly = true)
	Student findById(@Param("id") Integer id);

	/**
	 * Save an {@link Student} to the data store, either inserting or updating it.
	 * @param student the {@link Student} to save
	 */
	void save(Student student);

	void delete(Student student);

}
