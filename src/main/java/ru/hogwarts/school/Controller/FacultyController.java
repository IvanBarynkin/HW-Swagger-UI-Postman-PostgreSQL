package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@RequestParam  Long facultyId){
        Faculty faculty = facultyService.get(facultyId);
        if(faculty == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(faculty);
        }
    }

    @GetMapping("/filter/{color}")
    public ResponseEntity<HashMap<Long,Faculty>> filterFaculty(@RequestParam  String color){
        HashMap<Long,Faculty> filteredFaculty = (HashMap<Long, Faculty>) facultyService.colorFilter(color);
        if (filteredFaculty == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(filteredFaculty);
        }
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty){
        Faculty createFaculty = facultyService.create(faculty);
        return ResponseEntity.ok(createFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.update(faculty.getId(),faculty);
        if(updatedFaculty == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(updatedFaculty);
        }
    }
    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@RequestParam  Long facultyId){
        Faculty deleteFaculty = facultyService.delete(facultyId);
        if(deleteFaculty == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(deleteFaculty);
        }
    }
}
