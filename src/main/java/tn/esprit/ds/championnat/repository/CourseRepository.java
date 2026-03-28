package tn.esprit.ds.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}