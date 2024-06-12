package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.MouvementStockDto;
import com.houssam.gestiondestock.mapper.MouvementStockMapper;
import com.houssam.gestiondestock.model.MouvementStock;
import com.houssam.gestiondestock.model.TypeMouvement;
import com.houssam.gestiondestock.repository.MouvementStockRepository;
import com.houssam.gestiondestock.service.MouvementStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("MouvementStockServiceImpl")
@Slf4j
public class MouvementStockServiceImpl implements MouvementStockService {
    @Autowired
    MouvementStockMapper mouvementStockMapper;
    @Autowired
    MouvementStockRepository mouvementStockRepository;

    @Override
    public MouvementStockDto save(MouvementStockDto mouvementStockDto) {

        return mouvementStockMapper.mouvementStockToMouvementStockDto
                (mouvementStockRepository.save(mouvementStockMapper.mouvementStockDtoToMouvementStock(
                        mouvementStockDto
                )));
    }

    @Override
    public void delete(Integer id) {
        mouvementStockRepository.deleteById(id);

    }

    @Override
    public MouvementStockDto getReferenceById(Integer id) {

        return mouvementStockMapper.mouvementStockToMouvementStockDto(
                mouvementStockRepository.getReferenceById(id)
        );
    }

    @Override
    public List<MouvementStockDto> findByTypeMouvement(TypeMouvement typeMouvement) {

        List<MouvementStock> mouvementStocks = mouvementStockRepository.findByTypeMouvement(typeMouvement);
        List<MouvementStockDto> mouvementStocksDtos = new ArrayList<>();
        mouvementStocks.stream().forEach(mouvementStock -> {
            mouvementStocksDtos.add(
                    mouvementStockMapper.mouvementStockToMouvementStockDto(
                            mouvementStock));
        });
        return mouvementStocksDtos;

    }

    @Override
    public List<MouvementStockDto> findByDateMouvementGreaterThan(Instant dateMouvement) {

        List<MouvementStock> mouvementStocks = mouvementStockRepository.findByDateMouvementGreaterThan(dateMouvement);
        List<MouvementStockDto> mouvementStocksDtos = new ArrayList<>();
        mouvementStocks.stream().forEach(mouvementStock -> {
            mouvementStocksDtos.add(
                    mouvementStockMapper.mouvementStockToMouvementStockDto(
                            mouvementStock));
        });
        return mouvementStocksDtos;

    }

    @Override
    public List<MouvementStockDto> findByArticle(Integer idArticle) {

        List<MouvementStock> mouvementStocks = mouvementStockRepository.findByArticle(idArticle);
        List<MouvementStockDto> mouvementStocksDtos = new ArrayList<>();
        mouvementStocks.stream().forEach(mouvementStock -> {
            mouvementStocksDtos.add(
                    mouvementStockMapper.mouvementStockToMouvementStockDto(
                            mouvementStock));
        });
        return mouvementStocksDtos;

    }
}
