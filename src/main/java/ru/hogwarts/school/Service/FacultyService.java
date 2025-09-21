package ru.hogwarts.school.Service;

import ru.hogwarts.school.Model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Repositories.FacultyRepository;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {

        return facultyRepository.getReferenceById(id);
    }

    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void delete(long id) {
        facultyRepository.deleteById(id);
    }

    public HashMap<Long, Faculty> colorFilter(String color) {
        return (HashMap<Long, Faculty>) facultyRepository.findAll().stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toMap(Faculty::getId, Function.identity()));
    }
}
