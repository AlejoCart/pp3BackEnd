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
    private Long id_offer;
    private String title;

    /*@JoinTable(name = "Offer_company",
            joinColumns = @JoinColumn(name = "ID",
                    insertable=false,
                    updatable=false))*/
    /*name =
    "fk_cp",*/
    /*@ManyToOne(targetEntity = Company.class,cascade = CascadeType.ALL,fetch
            = FetchType.LAZY)
    @JoinColumn(name = "id_company",insertable=
            false, updatable=false)
    private Company company;*/
    private Long id_company;
    private String description;

    @Column(name = "offer_career")
    private String career;
    @Column(name = "updateDate")
    private Date updateDate;
    private String text;

    @Enumerated(EnumType.STRING)
    private Modality modality;

    /*@ManyToMany(targetEntity = Offer.class)
    @JoinTable(
            name = "user_offer",
            joinColumns = @JoinColumn(name = "id_offer"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> userList;*/

}
