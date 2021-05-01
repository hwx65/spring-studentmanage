package org.springframework.samples.studentmanage.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.samples.studentmanage.model.Student;
import org.springframework.samples.studentmanage.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class StudentController {

	private static final String VIEWS_STUDENT_CREATE_OR_UPDATE_FORM = "students/createOrUpdateStudentForm";

	private final StudentRepository students;

	public StudentController(StudentRepository studentService) {
		this.students = studentService;
		// this.visits = visits;
	}

	// @InitBinder
	// public void setAllowedFields(WebDataBinder dataBinder) {
	// dataBinder.setDisallowedFields("id");
	// }

	@GetMapping("/students/new")
	public String initCreationForm(Map<String, Object> model) {
		Student student = new Student();
		model.put("student", student);
		return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/students/new")
	public String processCreationForm(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.students.save(student);
			return "redirect:/students/" + student.getId();
		}
	}

	@GetMapping("/students/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("student", new Student());
		return "students/findStudents";
	}

	@GetMapping("/students")
	public String processFindForm(Student student, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /students to return all records
		if (student.getName() == null) {
			student.setName(""); // empty string signifies broadest possible search
		}

		Collection<Student> results = this.students.findByName(student.getName());
		if (results.isEmpty()) {
			// no students found
			result.rejectValue("name", "notFound", "not found");
			return "students/findStudents";
		}
		else if (results.size() == 1) {
			// 1 student found
			student = results.iterator().next();
			return "redirect:/students/" + student.getId();
		}
		else {
			// multiple students found
			model.put("selections", results);
			return "students/studentsList";
		}
	}

	@GetMapping("/students/{id}/edit")
	public String initUpdateStudentForm(@PathVariable("id") String id, Model model) {
		Student student = this.students.findById(id);
		model.addAttribute(student);
		return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/students/{id}/edit")
	public String processUpdateStudentForm(@Valid Student student, BindingResult result,
			@PathVariable("id") String id) {
		if (result.hasErrors()) {
			return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
		}
		else {
			if (!id.equals(student.getId())) {
				this.students.delete(id);
			}
			this.students.save(student);
			return "redirect:/students/" + student.getId();
		}
	}

	@GetMapping("/students/{id}/delete")
	public String initDeleteStudentForm(@PathVariable("id") String id, Model model) {
		this.students.delete(id);
		return "redirect:/students/";
	}

	/**
	 * Custom handler for displaying an student.
	 * @param id the ID of the student to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/students/{id}")
	public ModelAndView showStudent(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("students/studentDetails");
		Student student = this.students.findById(id);
		mav.addObject(student);
		return mav;
	}

}
