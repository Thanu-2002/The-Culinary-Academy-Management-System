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
public class AdminDTO {

    @Id
    private String adminUserID;

    private String adminUsername;

    private  String adminEmail;

    private String adminPassword;

//    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
//    @JoinColumn(name = "student_ID")
//    private Students students;
//
//    @ManyToOne(cascade = CascadeType.ALL)// owners end to Culinary_Programs
//    @JoinColumn(name = "program_ID")
//    private Culinary_Programs culinaryPrograms;

}
