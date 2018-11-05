package com.cice.gestionusuarios.service.converter;

import com.cice.gestionusuarios.data.entity.UsuarioEntity;
import com.cice.gestionusuarios.web.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioDTO UsuarioEntityToDTO(UsuarioEntity usuarioEntity){
        UsuarioDTO usuarioDTO = null;
        usuarioDTO = new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNombre(),
                usuarioEntity.getUser(),
                usuarioEntity.getEmail()
        );
        return usuarioDTO;
    }

    public UsuarioEntity UsuarioDTOToEntity(UsuarioDTO usuarioDTO){
        UsuarioEntity usuarioEntity = null;
        usuarioEntity = new UsuarioEntity(
                usuarioDTO.getId(),
                usuarioDTO.getNombre(),
                usuarioDTO.getUser(),
                null,
                usuarioDTO.getEmail()
        );
        return usuarioEntity;
    }

    public UsuarioEntity transformEntityDto(UsuarioDTO usuarioDTO, UsuarioEntity usuarioEntity){
        if(usuarioDTO.getNombre() != null && !usuarioDTO.getNombre().equals(usuarioEntity.getNombre())){
            usuarioEntity.setNombre(usuarioDTO.getNombre());
        }
        if(usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().equals(usuarioEntity.getEmail())){
            usuarioEntity.setEmail(usuarioDTO.getEmail());
        }
        if(usuarioDTO.getUser() != null && !usuarioDTO.getUser().equals(usuarioEntity.getUser())){
            usuarioEntity.setUser(usuarioDTO.getUser());
        }
        return usuarioEntity;
    }

}
