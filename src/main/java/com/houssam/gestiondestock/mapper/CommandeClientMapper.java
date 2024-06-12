package com.houssam.gestiondestock.mapper;

import com.houssam.gestiondestock.dto.CommandeClientDto;
import com.houssam.gestiondestock.model.CommandeClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommandeClientMapper {
    CommandeClientMapper INSTANCE = Mappers.getMapper(CommandeClientMapper.class);

    @Mapping(target = "ligneCmdClients", ignore = true)
    @Mapping(target = "client", ignore = true)
    CommandeClientDto commandeClientToCommandeClientDto(CommandeClient commandeClient);

    @Mapping(target = "ligneCmdClients", ignore = true)
    @Mapping(target = "client", ignore = true)
    CommandeClient commandeClientDtoToCommandeClient(CommandeClientDto commandeClientDto);
}
