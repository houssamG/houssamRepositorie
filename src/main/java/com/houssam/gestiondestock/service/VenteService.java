package com.houssam.gestiondestock.service;

import com.houssam.gestiondestock.dto.VenteDto;

import java.time.Instant;
import java.util.List;


public interface VenteService {
    VenteDto save(VenteDto venteDto);

    void delete(Integer id);

    VenteDto getReferenceById(Integer id);

    VenteDto findByCode(String code);

    List<VenteDto> findByDateVenteGreaterThan(Instant dateVente);
}
