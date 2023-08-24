package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Pago;
import com.example.demo.Models.Usuario;
import com.example.demo.Repository.PagoRepositorio;

@Service
public class PagoCrudServicio {

	private final PagoRepositorio pagoRepositorio;
	private UsuarioJpaCrudService usuarioJpaCrudService;

    @Autowired
    public PagoCrudServicio(PagoRepositorio pagoRepositorio, UsuarioJpaCrudService usuarioJpaCrudService) {
        this.pagoRepositorio = pagoRepositorio;
        this.usuarioJpaCrudService = usuarioJpaCrudService;
    }

    public Pago guardarPago(Pago pago) {
        return pagoRepositorio.save(pago);
    }
    
    public String obtenerNombreUsuario(Long idUsuario) {
        Usuario usuario = usuarioJpaCrudService.getUsuariosById(idUsuario);
        return usuario.getNombre();
    }
    public List<Pago> obtenerTodosLosPagosConNombres() {
        Iterable<Pago> pagosIterable = pagoRepositorio.findAll();
        List<Pago> pagos = new ArrayList<>();

        for (Pago pago : pagosIterable) {
            Long idUsuario = pago.getId_usuario();
            Usuario usuario = usuarioJpaCrudService.getUsuariosById(idUsuario);

            if (usuario != null) {
                pago.getUsuario().setNombre(usuario.getNombre());
            } else {
                Usuario unknownUser = new Usuario();
                unknownUser.setNombre("Usuario Desconocido");
                pago.setUsuario(unknownUser);
            }
            
            pagos.add(pago);
        }

        return pagos;
    }

}