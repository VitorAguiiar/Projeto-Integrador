package com.example.demo.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*") // Permite acesso de qualquer origem
@RestController
@RequestMapping("api")
public class Controller {

	@Autowired
	Repository repo;

	@PostMapping
	public ResponseEntity<?> salvarUsuario(@RequestBody DTO dto) {
		AlunoModel us = new AlunoModel(dto);
		repo.save(us);
		return ResponseEntity.ok("Salvo com sucesso");
	}

	@GetMapping
	public ResponseEntity<List<AlunoModel>> obterUsuarios() {
		List<AlunoModel> usuarios = repo.findAll();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public AlunoModel obterUsuario(@PathVariable Long id) {
		return repo.findById(id).get();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody DTO dto) {
	    AlunoModel us = repo.findById(id).get();
	    us.updateDTO(dto);// Atualiza o modelo com os dados do DTO
	    repo.save(us);
	    return ResponseEntity.ok("Atualizado com sucesso");
	    		}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
		repo.deleteById(id);
		return ResponseEntity.ok("Deletado com sucesso");
	}
}