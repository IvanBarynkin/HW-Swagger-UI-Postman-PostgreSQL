package ru.hogwarts.school.service;

import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepositor) {
        this.studentRepository = studentRepositor;
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Student get(long id) {
        return studentRepository.findById(id).get();
    }

    public Student update(Long id, Student student) {
        return studentRepository.existsById(id) ? null : studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> ageFilter(Integer age) {
        return studentRepository.findByAge(age);
    }

}
