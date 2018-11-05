package com.cice.gestionusuarios.service;

import com.cice.gestionusuarios.web.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    /**
     * Metodo que recupera todos los usuarios de la base de datos
     * @return
     */
    List<UsuarioDTO> findAllUsuarios();

    /**
     * Metodo que recupera un usuario buscando por su id
     * @param idUsuario
     * @return
     */
    Optional<UsuarioDTO> findUsuarioById (Long idUsuario);

    /**
     * Metodos que crea un usuario en base de datos y devuelve el ID generado
     * @param usuario
     * @return
     */
    Long crearUsuario(UsuarioDTO usuario);

    /**
     * Metodo que elimina un usuario de la base de datos y deveulve el ID eliminado
     * @param usuario
     * @return
     */
    Long eliminarUsuario(Long usuario);

    /**
     * Metodo que actaliza la información de un usuario y devuelve una representacion completa de este.
     * @param usuario
     * @return
     */
    UsuarioDTO actualizarUsuario(UsuarioDTO usuario);
}
