package com.example.Security.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
/*@Table(name = "User")*/
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String title;
    private String company;
    private String description;
    private String career;
    @Column(name = "updateDate")
    private Date updateDate;
    private String text;

    @Enumerated(EnumType.STRING)
    private Modality modality;

}
