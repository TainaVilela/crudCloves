package com.crudAccenture.accenture.service.mapper;

import com.crudAccenture.accenture.domain.Cliente;
import com.crudAccenture.accenture.service.dto.ClienteDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClienteDTO toDtoId(Cliente cliente);
}

