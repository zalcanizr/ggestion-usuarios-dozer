package com.cice.gestionusuarios.web.rest;

import com.cice.gestionusuarios.service.IUsuarioService;
import com.cice.gestionusuarios.web.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuariosResource {

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UsuarioDTO> findAllUsers(){
        List<UsuarioDTO> listaUsuarios = null;
        listaUsuarios = usuarioService.findAllUsuarios();
        return listaUsuarios;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public UsuarioDTO findUserById(@PathVariable(name = "id") Long id){
        UsuarioDTO usuarioDTO = null;
        Optional<UsuarioDTO> usuario = usuarioService.findUsuarioById(id);
        if(usuario.isPresent()){
            usuarioDTO = usuario.get();
        }
        return usuarioDTO;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long insertUser(@RequestBody UsuarioDTO usuarioDTO) {
        //TODO: dos DTO de usuario. Explicar.
        Long usuario = null;
        usuario = usuarioService.crearUsuario(usuarioDTO);
        return usuario;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public UsuarioDTO updateUser(@PathVariable(name = "id") Long id, @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        UsuarioDTO usuario = null;
        if(id == usuarioDTO.getId()) {
            usuario = usuarioService.actualizarUsuario(usuarioDTO);
        } else {
            throw new Exception();
        }
        return usuario;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public Long deleteUser(@PathVariable(name = "id") Long id){
        Long idEliminado = null;
        idEliminado = usuarioService.eliminarUsuario(id);
        return idEliminado;
    }

}
