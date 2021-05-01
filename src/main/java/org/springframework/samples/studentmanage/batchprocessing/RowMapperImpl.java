package org.springframework.samples.studentmanage.batchprocessing;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import org.springframework.samples.studentmanage.model.Student;

public class RowMapperImpl implements RowMapper<Student> {

	@Override
	public Student mapRow(RowSet rowSet) throws Exception {
		String[] columns = rowSet.getCurrentRow();
		Student student = new Student();

		student.setId(columns[0]);
		student.setName(columns[1]);
		student.setAcademy(columns[2]);
		student.setPhonenumber(columns[3]);
		student.setGender(columns[4]);
		student.setBirthday(columns[5]);

		return student;
	}

}
