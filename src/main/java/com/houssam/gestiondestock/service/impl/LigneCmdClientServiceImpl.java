package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.LigneCmdClientDto;
import com.houssam.gestiondestock.mapper.LigneCmdClientMapper;
import com.houssam.gestiondestock.model.LigneCmdClient;
import com.houssam.gestiondestock.repository.LigneCmdClientRepository;
import com.houssam.gestiondestock.service.LigneCmdClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("LigneCmdClientServiceImpl")
@Slf4j
public class LigneCmdClientServiceImpl implements LigneCmdClientService {
    @Autowired
    LigneCmdClientRepository ligneCmdClientRepository;
    @Autowired
    LigneCmdClientMapper ligneCmdClientMapper;

    @Override
    public LigneCmdClientDto save(LigneCmdClientDto ligneCmdClientDto) {

        return ligneCmdClientMapper.ligneCmdClientToLigneCmdClientDto(ligneCmdClientRepository
                .save(ligneCmdClientMapper.ligneCmdClientDtoToLigneCmdClient(ligneCmdClientDto)));
    }

    @Override
    public void delete(Integer id) {
        ligneCmdClientRepository.deleteById(id);

    }


    @Override
    public LigneCmdClientDto getReferenceById(Integer id) {

        return ligneCmdClientMapper.ligneCmdClientToLigneCmdClientDto(ligneCmdClientRepository.getReferenceById(id));
    }

    @Override
    public List<LigneCmdClientDto> findByPrixunitaireGreaterThan(BigDecimal prixunitaire) {


        List<LigneCmdClient> ligneCmdClients = ligneCmdClientRepository.findByPrixunitaireGreaterThan(prixunitaire);
        List<LigneCmdClientDto> ligneCmdClientsDtos = new ArrayList<>();
        ligneCmdClients.stream().forEach(ligneCmdClient -> {
            ligneCmdClientsDtos.add(
                    ligneCmdClientMapper.ligneCmdClientToLigneCmdClientDto(
                            ligneCmdClient));
        });
        return ligneCmdClientsDtos;

    }

    @Override
    public List<LigneCmdClientDto> findByArticle(Integer idArticle) {
        {

            List<LigneCmdClient> ligneCmdClients = ligneCmdClientRepository.findByArticle(idArticle);
            List<LigneCmdClientDto> ligneCmdClientsDtos = new ArrayList<>();
            ligneCmdClients.stream().forEach(ligneCmdClient -> {
                ligneCmdClientsDtos.add(
                        ligneCmdClientMapper.ligneCmdClientToLigneCmdClientDto(
                                ligneCmdClient));
            });
            return ligneCmdClientsDtos;
        }
    }

    @Override
    public List<LigneCmdClientDto> findLigneCmdClientByClientNom(String nomClient) {
        {

            List<LigneCmdClient> ligneCmdClients = ligneCmdClientRepository.findLigneCmdClientByClientNom(nomClient);
            List<LigneCmdClientDto> ligneCmdClientsDtos = new ArrayList<>();
            ligneCmdClients.stream().forEach(ligneCmdClient -> {
                ligneCmdClientsDtos.add(
                        ligneCmdClientMapper.ligneCmdClientToLigneCmdClientDto(
                                ligneCmdClient));
            });
            return ligneCmdClientsDtos;
        }
    }
}
