package com.example.demo.alunoimagem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*") // Permite acesso de qualquer origem
@RestController
@RequestMapping("/imagens")
public class AlunoImagemController {

    @Autowired
    private AlunoImagemService alunoImagemService;

    @GetMapping
    public List<AlunoImagem> listar() {
        return alunoImagemService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<AlunoImagem> criar(@RequestBody AlunoImagem alunoImagem) {
        return new ResponseEntity<>(alunoImagemService.salvar(alunoImagem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoImagem> buscarPorId(@PathVariable Long id) {
        AlunoImagem alunoImagem = alunoImagemService.buscarPorId(id);
        return alunoImagem != null ? ResponseEntity.ok(alunoImagem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	alunoImagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
