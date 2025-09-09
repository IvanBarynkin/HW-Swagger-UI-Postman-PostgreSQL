package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.Service.StudentService;

import java.util.HashMap;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createStudent = studentService.create(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@RequestParam long studentId){
        Student getStudent = studentService.get(studentId);
        if(getStudent == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(getStudent);
        }
    }

    @GetMapping("/filter/{age}")
    public ResponseEntity<HashMap<Long, Student>> filterStudent(@RequestParam int age){
        HashMap<Long,Student> filteredStudent = studentService.ageFilter(age);
        if (filteredStudent == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(filteredStudent);
        }
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student updateStudent = studentService.update(student.getId(),student);
        if(updateStudent == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(updateStudent);
        }
    }
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@RequestParam  long studentId){
        Student deleteStudent = studentService.delete(studentId);
        if(deleteStudent == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(deleteStudent);
        }
    }
}
