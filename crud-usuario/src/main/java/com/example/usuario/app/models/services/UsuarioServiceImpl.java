package com.example.usuario.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usuario.app.models.entity.Usuario;
import com.example.usuario.app.models.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRep;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		
		return usuarioRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		
		return usuarioRep.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		
		return usuarioRep.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		
		return usuarioRep.save(usuario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		usuarioRep.deleteById(id);
	}

}
