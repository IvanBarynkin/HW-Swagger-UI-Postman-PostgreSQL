package ru.hogwarts.school.service;

import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Student update(Long id, Student student) {
        return studentRepository.existsById(id) ? null : studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> ageFilter(int age) {
        return studentRepository.findByAge(age);
    }

}
