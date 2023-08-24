package com.example.demo.Service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Capacitaciones;
import com.example.demo.Models.Usuario;

import com.example.demo.Repository.CapacitacionRepositorio;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class CapacitacionesServicioImplements implements CapacitacionesCrudServicio {

    private CapacitacionRepositorio capacitacionRepo;
    private UsuarioRepository usuariosRepo;

    @Autowired
    public void setCapacitacionRepositorio(CapacitacionRepositorio capacitacionRepo) {
        this.capacitacionRepo = capacitacionRepo;
    }

    @Autowired
    public void setUsuariosRepositorio(UsuarioRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    @Override
    public Capacitaciones getCapacitacionById(Integer id) {
        return capacitacionRepo.findById(id).orElse(null);
    }

    @Override
    public Capacitaciones saveCapacitacion(Capacitaciones capacitaciones) {
        return capacitacionRepo.save(capacitaciones);
    }

    @Override
    public Map<Integer, String> ObtenerNombresUsuariosParaCapacitaciones() {
        Map<Integer, String> nombresMap = new HashMap<>();
        Iterable<Capacitaciones> capacitacionesList = capacitacionRepo.findAll();

        for (Capacitaciones capacitacion : capacitacionesList) {
            Integer usuarioId = capacitacion.getUsuario_id();
            Long usuarioId2 = usuarioId.longValue();
            Usuario usuario = usuariosRepo.findById(usuarioId2).orElse(null);
            if (usuario != null) {
                nombresMap.put(usuarioId, usuario.getNombre());
            }
        }

        return nombresMap;
    }

    @Override
    public Iterable<Capacitaciones> listAllCapacitaciones() {
        return capacitacionRepo.findAll();
    }
    
    @Override
    public void eliminarCapacitacion(Integer id) {
        capacitacionRepo.deleteById(id);
    }
}