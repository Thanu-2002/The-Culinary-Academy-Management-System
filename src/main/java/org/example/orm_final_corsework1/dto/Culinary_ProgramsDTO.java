package org.example.orm_final_corsework1.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.orm_final_corsework1.entity.Admin;
import org.example.orm_final_corsework1.entity.Admission_Coordinator;
import org.example.orm_final_corsework1.entity.Students;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

@Entity
public class Culinary_ProgramsDTO {

    @Id
    private String programID;

    private String programName;

    private String duration;

    private String fee;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //inverse end

    private List<Students> students;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //inverse end to Admission Coordinator Entity
    private List<Admission_Coordinator> admissionCoordinators;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // inverse end to Admin Entity
    private List<Admin> admins;

}