package org.example.orm_final_corsework1.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.orm_final_corsework1.entity.Culinary_Programs;
import org.example.orm_final_corsework1.entity.Students;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data


@Entity
public class Admission_CoordinatorDTO {

    @Id
    private String admissionCoUserID;

    private String admissionCoUsername;

    private String admissionCoPassword;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL) // owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

}
