package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Optional<Student> get(long id) {
        return studentRepository.findById(id);
    }

    public Optional<Faculty> getFaculty(long id) {
        return studentRepository.findById(id).map(Student::getFaculty);
    }

    public Student update(Long id, Student student) {
        return studentRepository.existsById(id) ? null : studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> ageFilter(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> ageBetweenFilter(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

}
