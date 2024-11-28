package org.example.orm_final_corsework1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

@Entity
public class ResetUsernamePasswordAdmin {
    @Id
    private String adminUserID;

    private String adminUsername;

    private String adminPassword;
}
