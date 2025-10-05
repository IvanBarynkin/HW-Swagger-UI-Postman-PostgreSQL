package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.student;
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

    public student create(student student) {

        return studentRepository.save(student);
    }

    public Optional<student> get(long id) {
        return studentRepository.findById(id);
    }

    public Optional<Faculty> getFaculty(long id) {
        return studentRepository.findById(id).map(student::getFaculty);
    }

    public student update(Long id, student student) {
        return studentRepository.existsById(id) ? null : studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<student> ageFilter(int age) {
        return studentRepository.findByAge(age);
    }

    public List<student> ageBetweenFilter(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

}
