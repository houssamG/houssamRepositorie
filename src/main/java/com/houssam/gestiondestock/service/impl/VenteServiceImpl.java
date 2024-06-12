package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.VenteDto;
import com.houssam.gestiondestock.mapper.VenteMapper;
import com.houssam.gestiondestock.model.Vente;
import com.houssam.gestiondestock.repository.VenteRepository;
import com.houssam.gestiondestock.service.VenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("VenteServiceImpl")
@Slf4j
public class VenteServiceImpl implements VenteService {
    @Autowired
    VenteMapper venteMapper;
    @Autowired
    VenteRepository venteRepository;

    @Override
    public VenteDto save(VenteDto venteDto) {

        return venteMapper.venteToVenteDto(
                venteRepository.save(venteMapper.venteDtoToVente(venteDto)));
    }

    @Override
    public void delete(Integer id) {
        venteRepository.deleteById(id);

    }


    @Override
    public VenteDto getReferenceById(Integer id) {

        return venteMapper.venteToVenteDto(venteRepository.getReferenceById(id));
    }

    @Override
    public VenteDto findByCode(String code) {

        return venteMapper.venteToVenteDto(venteRepository.findByCode(code));
    }

    @Override
    public List<VenteDto> findByDateVenteGreaterThan(Instant dateVente) {

        List<Vente> ventes = venteRepository.findByDateVenteGreaterThan(dateVente);
        List<VenteDto> ventesDtos = new ArrayList<>();
        ventes.stream().forEach(vente -> {
            ventesDtos.add(
                    venteMapper.venteToVenteDto(
                            vente));
        });
        return ventesDtos;

    }
}
