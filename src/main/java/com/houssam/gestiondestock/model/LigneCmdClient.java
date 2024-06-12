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
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "ligneCommandeClient")
public class LigneCmdClient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLigneCmdClient")
    private Integer idLigneCmdClient;

    @CreatedDate
    @Column(name = "dateCreation", nullable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @Column(name = "dateModification", nullable = false)
    @JsonIgnore
    private Instant dateModification;
    @Column(name = "quantite")
    private BigDecimal quantite;
    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;
    @ManyToOne
    @JoinColumn(name = "articleFK")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "commandeClientFK")
    private CommandeClient commandeClient;

}
