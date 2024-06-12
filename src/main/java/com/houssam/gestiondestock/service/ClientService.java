package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.ClientDto;

import java.time.Instant;
import java.util.List;


public interface ClientService {

    ClientDto save(ClientDto clientDto);

    void delete(Integer id);

    ClientDto getReferenceById(Integer id);

    ClientDto findByNomAndPrenom(String nom, String prenom);

    List<ClientDto> findByDateCommandeAfter(Instant dateCommande);
}
