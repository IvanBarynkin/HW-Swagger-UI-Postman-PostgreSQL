package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.create(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable long studentId) {
        Optional<Student> student = studentService.get(studentId);
        return student.isEmpty()? ResponseEntity.notFound().build() : ResponseEntity.ok(student.get());

    }

    @GetMapping
    public ResponseEntity<List<Student>> filterStudent(@RequestParam int age) {
        List<Student> filteredStudent = studentService.ageFilter(age);
        if (filteredStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filteredStudent);
        }
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.update(student.getId(), student);
        if (updateStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateStudent);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.ok().build();
    }
}
