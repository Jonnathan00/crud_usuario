package com.example.usuario.app.models.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario.app.models.entity.Usuario;
import com.example.usuario.app.models.services.UsuarioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioServ;
	
	//Metodo Crear
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServ.save(usuario));
	}
	
	//Metodo Listar
	@GetMapping
	public List<Usuario> readAll() {
		
		List<Usuario> usua = StreamSupport
				.stream(usuarioServ.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return usua;
	}
	
	
	//Metodo Buscar Id
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable Long id) {
		
		Optional<Usuario> opus = usuarioServ.findById(id);
		
		if(!opus.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(opus);
	}
	
	//Metodo Actualizar
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Usuario usuarioDetails, @PathVariable(value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioServ.findById(id);
		
		if(!usuario.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		usuario.get().setNombre(usuarioDetails.getNombre());
		usuario.get().setClave(usuarioDetails.getClave());
		usuario.get().setEmail(usuarioDetails.getEmail());
		usuario.get().setEstado(usuarioDetails.getEstado());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServ.save(usuario.get()));
	}
	
	//Metodo Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long id) {
		
		if(!usuarioServ.findById(id).isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		usuarioServ.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
