package org.example.orm_final_corsework1.tm;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

public class StudentsTM {

    private String studentID;

    private Date date;

    private String firstName;

    private String lastName;

    private String email;

    private int mobileNumber;

    private String address;

    private String selectedCourse;

}
