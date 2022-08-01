package com.example.usuario.app.models.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.usuario.app.models.entity.Usuario;

public interface UsuarioService {

	public Iterable<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deleteById(Long id);
}
