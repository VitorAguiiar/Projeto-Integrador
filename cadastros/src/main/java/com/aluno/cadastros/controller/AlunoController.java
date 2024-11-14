package com.aluno.cadastros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluno.cadastros.dto.AlunoDto;
import com.aluno.cadastros.model.AlunoModel;
import com.aluno.cadastros.service.AlunoService;



@RestController
@RequestMapping("api")
public class AlunoController {
	
	
    @Autowired
    private AlunoService alunoService;
   
    @GetMapping("BuscarAluno")
    public List<AlunoModel> buscartodos(){
    	return alunoService.buscartodos();
    	
    }
//    
//    @PostMapping("AdicionarAluno")
//    public ResponseEntity<?> inserir(@RequestBody AlunoDto dto) {
//    	return alunoService.inserir(dto);
//    	
//    }
    
    @PutMapping("AlterarAluno")
    public AlunoModel alterar(AlunoModel aluno) {
    	return alunoService.alterar(aluno);
    	
    }
    
    @GetMapping("/u")
    public String oi() {
    	return "ola";
    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> excluir(@Param("id") Long id){
//    	alunoService.excluir(id);
//    	return ResponseEntity.ok().body(null);
//    }
    
    
    
    
}
