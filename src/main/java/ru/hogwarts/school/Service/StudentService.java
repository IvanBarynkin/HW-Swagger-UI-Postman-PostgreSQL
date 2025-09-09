package ru.hogwarts.school.Service;

import ru.hogwarts.school.Model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> storage;
    private long id;

    public StudentService(){
        this.storage = new HashMap<Long,Student>();
        this.id = 0;
    }

    public Student create(Student student){
        student.setId(++id);
        storage.put(id,student);
        return storage.get(id);
    }

    public Student get(long id){
        return storage.get(id);
    }

    public Student update(Long id,Student student){
        return storage.put(id,student);
    }

    public Student delete(long id){
        return storage.remove(id);
    }

    public HashMap<Long,Student> ageFilter(Integer age){
        return (HashMap<Long, Student>) storage.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toMap(Student::getId, Function.identity()));
    }

}
