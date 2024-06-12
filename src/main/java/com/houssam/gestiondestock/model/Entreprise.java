package com.houssam.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "entreprise")
public class Entreprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEntreprise;

    @CreatedDate
    @Column(name = "dateCreation", nullable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @Column(name = "dateModification", nullable = false)
    @JsonIgnore
    private Instant dateModification;
    @Column(name = "nom")
    private String nom;
    @Column(name = "description")
    private String description;
    @Column(name = "adresse")
    private Adresse adresse;
    @Column(name = "codeFiscal")
    private String codeFiscal;
    @Column(name = "photo")
    private String photo;
    @Column(name = "email")
    private String email;
    @Column(name = "numTel")
    private String numTel;
    @Column(name = "siteWebUrl")
    private String siteWebUrl;
    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles;

}
