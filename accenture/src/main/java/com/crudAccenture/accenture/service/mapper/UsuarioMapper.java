package com.crudAccenture.accenture.service.mapper;

import com.crudAccenture.accenture.domain.Usuario;
import com.crudAccenture.accenture.service.dto.UsuarioDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {}

