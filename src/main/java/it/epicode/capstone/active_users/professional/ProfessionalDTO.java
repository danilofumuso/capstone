package it.epicode.capstone.active_users.professional;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfessionalDTO {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;
    private String academicCareerPath;
    private String academicCareerPathVideo;
    private String curriculumVitae;
}
