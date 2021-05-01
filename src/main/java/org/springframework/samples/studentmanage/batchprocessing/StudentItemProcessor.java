package org.springframework.samples.studentmanage.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.samples.studentmanage.model.Student;

public class StudentItemProcessor implements ItemProcessor<Student, Student> {

	private static final Logger log = LoggerFactory.getLogger(StudentItemProcessor.class);

	@Override
	public Student process(final Student student) throws Exception {

		log.info("Converting (" + student + ") into (" + student + ")");

		return student;
	}

}

