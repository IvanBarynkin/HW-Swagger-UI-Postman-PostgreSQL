package ru.hogwarts.school.Service;

import ru.hogwarts.school.Model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Repositories.StudentRepository;

import java.util.HashMap;
import java.util.function.Function;
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
        return studentRepository.getReferenceById(id);
    }

    public Student update(Long id, Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public HashMap<Long, Student> ageFilter(Integer age) {
        return (HashMap<Long, Student>) studentRepository.findAll().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toMap(Student::getId, Function.identity()));
    }

}
