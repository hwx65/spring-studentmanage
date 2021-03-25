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

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

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
		if (student.getStuname() == null) {
			student.setStuname(""); // empty string signifies broadest possible search
		}

		// find students by stuname
		Collection<Student> results = this.students.findByName(student.getStuname());
		if (results.isEmpty()) {
			// no students found
			result.rejectValue("stuname", "notFound", "not found");
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

	@GetMapping("/students/{studentId}/edit")
	public String initUpdateStudentForm(@PathVariable("studentId") int studentId, Model model) {
		Student student = this.students.findById(studentId);
		model.addAttribute(student);
		return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/students/{studentId}/edit")
	public String processUpdateStudentForm(@Valid Student student, BindingResult result,
			@PathVariable("studentId") int studentId) {
		if (result.hasErrors()) {
			return VIEWS_STUDENT_CREATE_OR_UPDATE_FORM;
		}
		else {
			student.setId(studentId);
			this.students.save(student);
			return "redirect:/students/{studentId}";
		}
	}

	@GetMapping("/students/{studentId}/delete")
	public String initDeleteStudentForm(@PathVariable("studentId") int studentId, Model model) {
		Student student = this.students.findById(studentId);
		this.students.delete(student);
		return "redirect:/students/";
	}

	/**
	 * Custom handler for displaying an student.
	 * @param studentId the ID of the student to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/students/{studentId}")
	public ModelAndView showStudent(@PathVariable("studentId") int studentId) {
		ModelAndView mav = new ModelAndView("students/studentDetails");
		Student student = this.students.findById(studentId);
		mav.addObject(student);
		return mav;
	}

}
