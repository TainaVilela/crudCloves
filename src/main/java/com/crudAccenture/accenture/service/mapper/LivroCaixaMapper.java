package com.crudAccenture.accenture.service.mapper;

import com.crudAccenture.accenture.domain.LivroCaixa;
import com.crudAccenture.accenture.service.dto.LivroCaixaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ClienteMapper.class })
public interface LivroCaixaMapper extends EntityMapper<LivroCaixaDTO, LivroCaixa> {
    @Mapping(target = "cliente", source = "cliente", qualifiedByName = "id")
    LivroCaixaDTO toDto(LivroCaixa s);
}
