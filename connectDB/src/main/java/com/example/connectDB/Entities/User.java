package com.example.connectDB.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
public class User {
    @Id
    private Long ID;
    private String NAME;
    private String SURNAME;
    private String USERNAME;
    private String PASSWORD;

    private Boolean ACTIVE;

}
