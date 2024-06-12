package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.LigneVenteDto;
import com.houssam.gestiondestock.mapper.LigneVenteMapper;
import com.houssam.gestiondestock.model.LigneVente;
import com.houssam.gestiondestock.repository.LigneVenteRepository;
import com.houssam.gestiondestock.service.LigneVenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("LigneVenteServiceImpl")
@Slf4j
public class LigneVenteServiceImpl implements LigneVenteService {
    @Autowired
    LigneVenteMapper ligneVenteMapper;
    @Autowired
    LigneVenteRepository ligneVenteRepository;

    @Override
    public LigneVenteDto save(LigneVenteDto ligneVenteDto) {

        return ligneVenteMapper.ligneVenteToLigneVenteDto
                (ligneVenteRepository.save(ligneVenteMapper.ligneVenteDtoToLigneVente(ligneVenteDto)));
    }

    @Override
    public void delete(Integer id) {
        ligneVenteRepository.deleteById(id);

    }


    @Override
    public LigneVenteDto getReferenceById(Integer id) {

        return ligneVenteMapper.ligneVenteToLigneVenteDto(
                ligneVenteRepository.getReferenceById(id)
        );
    }

    @Override
    public List<LigneVenteDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire) {

        List<LigneVente> ligneVentes = ligneVenteRepository.findByPrixunitaireGreaterThan(prixunitaire);
        List<LigneVenteDto> ligneVentesDtos = new ArrayList<>();
        ligneVentes.stream().forEach(ligneVente -> {
            ligneVentesDtos.add(
                    ligneVenteMapper.ligneVenteToLigneVenteDto(
                            ligneVente));
        });
        return ligneVentesDtos;

    }

    @Override
    public List<LigneVenteDto> findByArticle(Integer idArticle) {

        List<LigneVente> ligneVentes = ligneVenteRepository.findByArticle(idArticle);
        List<LigneVenteDto> ligneVentesDtos = new ArrayList<>();
        ligneVentes.stream().forEach(ligneVente -> {
            ligneVentesDtos.add(
                    ligneVenteMapper.ligneVenteToLigneVenteDto(
                            ligneVente));
        });
        return ligneVentesDtos;

    }

    @Override
    public List<LigneVenteDto> findByVente(Integer idVente) {

        List<LigneVente> ligneVentes = ligneVenteRepository.findByVente(idVente);
        List<LigneVenteDto> ligneVentesDtos = new ArrayList<>();
        ligneVentes.stream().forEach(ligneVente -> {
            ligneVentesDtos.add(
                    ligneVenteMapper.ligneVenteToLigneVenteDto(
                            ligneVente));
        });
        return ligneVentesDtos;

    }
}
