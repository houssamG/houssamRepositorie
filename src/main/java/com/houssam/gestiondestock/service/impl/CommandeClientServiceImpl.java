package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.CommandeClientDto;
import com.houssam.gestiondestock.mapper.CommandeClientMapper;
import com.houssam.gestiondestock.model.CommandeClient;
import com.houssam.gestiondestock.repository.CommandeClientRepository;
import com.houssam.gestiondestock.service.CommandeClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("CommandeClientServiceImpl")
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
    @Autowired
    CommandeClientRepository commandeClientRepository;
    @Autowired
    CommandeClientMapper commandeClientMapper;

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        CommandeClient commandeClient = commandeClientMapper.commandeClientDtoToCommandeClient(commandeClientDto);
        CommandeClient cmdClient = commandeClientRepository.save(commandeClient);
        return commandeClientMapper.commandeClientToCommandeClientDto(cmdClient);
    }

    @Override
    public void delete(Integer id) {
        commandeClientRepository.deleteById(id);

    }


    @Override
    public CommandeClientDto getReferenceById(Integer id) {

        return commandeClientMapper.commandeClientToCommandeClientDto(commandeClientRepository.getReferenceById(id));
    }

    @Override
    public CommandeClientDto findByCode(String code) {

        return commandeClientMapper.commandeClientToCommandeClientDto(commandeClientRepository.findByCode(code));
    }

    @Override
    public List<CommandeClientDto> findByDateCommandeAfter(Instant dateCommande) {
        List<CommandeClient> commandeClients = commandeClientRepository.findByDateCommandeAfter(dateCommande);
        List<CommandeClientDto> commandeClientDtos = new ArrayList<>();
        commandeClients.stream().forEach(client -> {
            commandeClientDtos.add(commandeClientMapper.commandeClientToCommandeClientDto(client));
        });
        return commandeClientDtos;
    }

    @Override
    public List<CommandeClientDto> findCommandesClientByClientId(Integer idClient) {

        List<CommandeClient> commandeClients = commandeClientRepository.findCommandesClientByClientId(idClient);
        List<CommandeClientDto> commandeClientDtos = new ArrayList<>();
        commandeClients.stream().forEach(client -> {
            commandeClientDtos.add(commandeClientMapper.commandeClientToCommandeClientDto(client));
        });
        return commandeClientDtos;
    }
}
