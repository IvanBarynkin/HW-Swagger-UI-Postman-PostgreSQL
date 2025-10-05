package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<student> getStudent(@PathVariable long studentId) {
        Optional<student> student = studentService.get(studentId);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{studentId}/faculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long studentId) {
        Optional<Faculty> faculty = studentService.getFaculty(studentId);
        return faculty.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable long id, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<student>> filterAgeStudent(@RequestParam int maxAge, @RequestParam int minAge) {
        List<student> filteredStudent = studentService.ageBetweenFilter(minAge, maxAge);
        if (filteredStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filteredStudent);
        }
    }

    @PostMapping
    public ResponseEntity<student> createStudent(@RequestBody student student) {
        ru.hogwarts.school.model.student createStudent = studentService.create(student);
        return ResponseEntity.ok(createStudent);
    }

    @PutMapping
    public ResponseEntity<student> updateStudent(@RequestBody student student) {
        ru.hogwarts.school.model.student updateStudent = studentService.update(student.getId(), student);
        if (updateStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateStudent);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<student> deleteStudent(@PathVariable long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.ok().build();
    }
}
