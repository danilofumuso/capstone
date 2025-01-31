package it.epicode.capstone.active_users.student;

import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.sector.Sector;
import it.epicode.capstone.sector.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SectorRepository sectorRepository;

    @Transactional
    public Student updateStudent(String studentUsername, StudentDTO studentDTO) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppUser appUser = student.getAppUser();
        if (studentDTO.getName() != null) {
            appUser.setName(studentDTO.getName());
        }

        if (studentDTO.getSurname() != null) {
            appUser.setSurname(studentDTO.getSurname());
        }

        if (studentDTO.getDateOfBirth() != null) {
            appUser.setDateOfBirth(studentDTO.getDateOfBirth());
        }

        if (studentDTO.getUsername() != null) {
            appUser.setUsername(studentDTO.getUsername());
        }

        if (studentDTO.getEmail() != null) {
            appUser.setEmail(studentDTO.getEmail());
        }

        if (studentDTO.getPassword() != null) {
            appUser.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        if (studentDTO.getSectorsOfInterest() != null) {
            Set<Sector> sectors = new HashSet<>();

            for (String sectorName : studentDTO.getSectorsOfInterest()) {
                sectorRepository.findByName(sectorName).ifPresent(sectors::add);
            }

            student.setSectorsOfInterest(sectors);
        }

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(String studentUsername) {
        studentRepository.deleteByAppUserUsername(studentUsername);
    }

}
