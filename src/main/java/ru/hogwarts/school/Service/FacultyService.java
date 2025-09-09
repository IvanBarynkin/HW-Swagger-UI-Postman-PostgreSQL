package ru.hogwarts.school.Service;

import ru.hogwarts.school.Model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> storage;
    private long id;

    public FacultyService(){
        this.storage = new HashMap<Long,Faculty>();
        this.id = 0;
    }

    public Faculty create(Faculty faculty){
        faculty.setId(++id);
        storage.put(id,faculty);
        return storage.get(id);
    }

    public Faculty get(long id){
        return storage.get(id);
    }

    public Faculty update(Long id, Faculty faculty){
        return storage.put(id,faculty);
    }

    public Faculty delete(long id){
        return storage.remove(id);
    }

    public HashMap<Long,Faculty> colorFilter(String color){
        return (HashMap<Long, Faculty>) storage.values().stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toMap(Faculty::getId, Function.identity()));
    }
}
