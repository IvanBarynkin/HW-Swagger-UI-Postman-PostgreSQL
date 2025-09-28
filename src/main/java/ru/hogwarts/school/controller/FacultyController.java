package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Optional<Faculty> faculty = facultyService.get(facultyId);
        return faculty.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/students/{facultyId}")
    public ResponseEntity<List<Student>> getFacultyStudents(@PathVariable Long facultyId) {
        Optional<List<Student>> students = facultyService.getStudents(facultyId);
        return students.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> filterFacultyByColorOrName(@RequestParam(required = false) String color, @RequestParam(required = false) String name ) {
        if (color != null && name != null) {
            return ResponseEntity.ok(facultyService.colorAndNameFilter(color, name));
        }
        if(color != null){
            return ResponseEntity.ok(facultyService.colorFilter(color));
        }
        if(name != null){
            return ResponseEntity.ok(facultyService.nameFilter(name));
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.create(faculty);
        return ResponseEntity.ok(createFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.update(faculty.getId(), faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedFaculty);
        }
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.delete(facultyId);
        return ResponseEntity.ok().build();
    }
}
