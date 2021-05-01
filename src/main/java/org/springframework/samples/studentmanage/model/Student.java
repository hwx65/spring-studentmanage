package org.springframework.samples.studentmanage.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	public Student(int id, String name, String gender, Date birthday, String phonenumber, String acaedmy) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday.toLocalDate();
		this.phonenumber = phonenumber;
		this.academy = acaedmy;
	}

	public Student() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

	@Column(name = "name")
	@NotEmpty
	private String name;

	@Column(name = "gender")
	@NotEmpty
	private String gender;

	@Column(name = "birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	@Column(name = "phonenumber")
	@NotEmpty
	private String phonenumber;

	@Column(name = "academy")
	@NotEmpty
	private String academy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("name", this.getName())
				.append("gender", this.getGender()).append("birthday", this.birthday)
				.append("phonenumber", this.phonenumber).append("academy", this.academy).toString();
	}

}
