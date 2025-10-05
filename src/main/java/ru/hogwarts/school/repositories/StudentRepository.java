package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<student, Long> {

    List<student> findByAge(int age);

    List<student> findByAgeBetween(int min, int max);
}
