package com.example.Security.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_company;
    private String name;
    @OneToMany(targetEntity = Offer.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_offer"/*,referencedColumnName = "ID",insertable=
            false, updatable=false*/)
    private List<Offer> offerList;

}
