package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.CommandeClientDto;

import java.time.Instant;
import java.util.List;


public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto commandeClientDto);

    void delete(Integer id);

    CommandeClientDto getReferenceById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findByDateCommandeAfter(Instant dateCommande);

    List<CommandeClientDto> findCommandesClientByClientId(Integer idClient);
}
