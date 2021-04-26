package org.springframework.samples.studentmanage.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.studentmanage.model.Student;
import org.springframework.samples.studentmanage.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StudentController {

	private final StudentRepository repository;

	public StudentController(StudentRepository repository) {
		this.repository = repository;
		// this.visits = visits;
	}

	@GetMapping("/students")
	ResponseEntity<CollectionModel<EntityModel<Student>>> findAll() {
		List<EntityModel<Student>> students = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(student -> EntityModel.of(student, //
						linkTo(methodOn(StudentController.class).findOne(student.getId())).withSelfRel(), //
						linkTo(methodOn(StudentController.class).findAll()).withRel("students"))) //
				.collect(Collectors.toList());
		return ResponseEntity.ok( //
				CollectionModel.of(students, //
						linkTo(methodOn(StudentController.class).findAll()).withSelfRel()));
	}

	@PostMapping("/students")
	ResponseEntity<?> newStudent(@RequestBody Student student) {
		try {
			Student savedStudent = repository.save(student);

			EntityModel<Student> studentResource = EntityModel.of(savedStudent,
					linkTo(methodOn(StudentController.class).findOne(savedStudent.getId())).withSelfRel());

			return ResponseEntity.created(new URI(studentResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
					.body(studentResource);
		}
		catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body("Unable to create" + student + e);
		}
	}

	@GetMapping("/students/{studentId}")
	ResponseEntity<EntityModel<Student>> findOne(@PathVariable int studentId) {
		return repository.findById(studentId)
				.map(student -> EntityModel.of(student,
						linkTo(methodOn(StudentController.class).findOne(student.getId())).withSelfRel(),
						linkTo(methodOn(StudentController.class).findAll()).withRel("students")))
				.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
