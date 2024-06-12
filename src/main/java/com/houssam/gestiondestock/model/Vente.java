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
@Table(name = "vente")
public class Vente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVente;

    @CreatedDate
    @Column(name = "dateCreation", nullable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @Column(name = "dateModification", nullable = false)
    @JsonIgnore
    private Instant dateModification;
    @Column(name = "code")
    private String code;
    @Column(name = "dateVente")
    private Instant dateVente;
    @Column(name = "commentaire")
    private String commentaire;
    @OneToMany(mappedBy = "vente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneVente> ligneVentes;
}
