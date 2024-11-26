package com.example.demo.aluno;

import java.util.List;

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


@CrossOrigin(origins = "*") // Permite acesso de qualquer origem
@RestController
@RequestMapping("api")
public class Controller {
	
	@Autowired
    private AlunoImagemService alunoImagemService;
	
	@Autowired
	Repository repo;

    @GetMapping
    public List<AlunoModel> listar() {
        return alunoImagemService.listarTodos();
    }

    @PostMapping("{id}")
    public ResponseEntity<AlunoModel> criar(@RequestBody AlunoModel alunoImagem) {
        return new ResponseEntity<>(alunoImagemService.salvar(alunoImagem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id) {
    	AlunoModel alunoImagem = alunoImagemService.buscarPorId(id);
        return alunoImagem != null ? ResponseEntity.ok(alunoImagem) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody DTO dto) {
	    AlunoModel us = repo.findById(id).get();
	    us.updateDTO(dto);// Atualiza o modelo com os dados do DTO
	    repo.save(us);
	    return ResponseEntity.ok("Atualizado com sucesso");
	    		}

    @DeleteMapping("/deletetaraluno/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	alunoImagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
