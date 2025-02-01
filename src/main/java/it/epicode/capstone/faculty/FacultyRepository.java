package it.epicode.capstone.faculty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Page<Faculty> findByUniversity_Name(String universityName, Pageable pageable);
}
