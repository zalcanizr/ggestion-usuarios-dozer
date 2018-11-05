package com.cice.gestionusuarios.service.impl;

import com.cice.gestionusuarios.data.entity.UsuarioEntity;
import com.cice.gestionusuarios.data.repository.UsuarioRepository;
import com.cice.gestionusuarios.service.IUsuarioService;
import com.cice.gestionusuarios.service.converter.UsuarioConverter;
import com.cice.gestionusuarios.web.dto.UsuarioDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Autowired
    private Mapper mapper;

    @Override
    public List<UsuarioDTO> findAllUsuarios() {
        List<UsuarioDTO> usuarioDTOList = null;
        usuarioDTOList = usuarioRepository
                .findAll()
                .stream()
                //.map(entity -> usuarioConverter.UsuarioEntityToDTO(entity))
                .map(entity->mapper.map(entity, UsuarioDTO.class))
                .collect(Collectors.toList());

        /*List<UsuarioEntity> usuarioEntityList = usuarioRepository.findAll();
        usuarioDTOList = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuarioEntityList) {
            usuarioDTOList.add(usuarioConverter.UsuarioEntityToDTO(usuarioEntity));
        }*/

        return usuarioDTOList;
    }

    @Override
    public Optional<UsuarioDTO> findUsuarioById(Long idUsuario) {
        Optional<UsuarioDTO> usuarioDTOOptional = Optional.empty();
        Optional<UsuarioEntity> entityOptional = usuarioRepository.findById(idUsuario);
        if(entityOptional.isPresent()){
            usuarioDTOOptional = Optional.of(usuarioConverter.UsuarioEntityToDTO(entityOptional.get()));
        }
        return usuarioDTOOptional;
    }

    @Override
    public Long crearUsuario(UsuarioDTO usuario) {
        Long idUsuario = null;
        UsuarioEntity save = usuarioRepository.save(usuarioConverter.UsuarioDTOToEntity(usuario));
        idUsuario = save.getId();
        return idUsuario;
    }

    @Override
    public Long eliminarUsuario(Long usuario) {
        usuarioRepository.deleteById(usuario);
        return usuario;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioResponse = null;
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(usuario.getId());

        if(usuarioEntityOptional.isPresent()) {
            UsuarioEntity usuarioEntityUnwrapped = usuarioEntityOptional.get();
            usuarioEntityUnwrapped = usuarioConverter.transformEntityDto(usuario, usuarioEntityUnwrapped);
            usuarioRepository.save(usuarioEntityUnwrapped);
            usuarioResponse = usuarioConverter.UsuarioEntityToDTO(usuarioEntityUnwrapped);
        }

        return usuarioResponse;
    }
}
