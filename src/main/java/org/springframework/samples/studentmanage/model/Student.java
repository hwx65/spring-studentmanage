package org.springframework.samples.studentmanage.model;

import java.io.Serializable;

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
	private String id;

	public Student() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	private String birthday;

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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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
