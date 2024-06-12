package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.ClientDto;
import com.houssam.gestiondestock.mapper.ClientMapper;
import com.houssam.gestiondestock.model.Client;
import com.houssam.gestiondestock.repository.ClientRepository;
import com.houssam.gestiondestock.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("ClientServiceImpl")
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;

    @Override
    public ClientDto save(ClientDto clientDto) {

        return clientMapper.clientToClientDto(clientRepository.save(clientMapper.clientDtoToClient(clientDto)));
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);

    }


    @Override
    public ClientDto getReferenceById(Integer id) {

        return clientMapper.clientToClientDto(clientRepository.getReferenceById(id));
    }

    @Override
    public ClientDto findByNomAndPrenom(String nom, String prenom) {

        return clientMapper.clientToClientDto(clientRepository.findByNomAndPrenom(nom, prenom));
    }

    @Override
    public List<ClientDto> findByDateCommandeAfter(Instant dateCommande) {
        List<Client> clients = clientRepository.findClientByDateCommandeAfter(dateCommande);
        List<ClientDto> clientsDtos = new ArrayList<>();
        clients.stream().forEach(client -> {
            clientsDtos.add(clientMapper.clientToClientDto(client));
        });
        return clientsDtos;
    }
}
