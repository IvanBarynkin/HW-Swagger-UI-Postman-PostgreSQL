package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;


import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> get(long id) {
        return facultyRepository.findById(id);
    }

    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.existsById(id) ? null : facultyRepository.save(faculty);
    }

    public void delete(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> colorFilter(String color) {
        return facultyRepository.findByColor(color);
    }
}
