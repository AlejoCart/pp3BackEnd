package com.example.Security.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column(name = "name")
    private String name;
    private String surname;
    //@Column(name = "username")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    private String career;
    @Enumerated(EnumType.STRING)
    private Role role;//Necesario debido a la implementacion de UserDetails
    private Date birthdate;
    private boolean active;
    private String img;

    /*@ManyToMany(/*mappedBy = "userList",targetEntity = Offer.class)*///Define
    // quien es el
    // dueño
    /*@JoinTable(
            name = "user_offer",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_offer_uo"))
    private List<Offer> offerList;*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
