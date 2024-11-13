package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import model.AlunoModel;
import service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
	
	
    @Autowired
    private AlunoService alunoService;
   
    @GetMapping("/BuscarAluno")
    public List<AlunoModel> buscartodos(){
    	return alunoService.buscartodos();
    	
    }
    
    @PostMapping("/AdicionarAluno")
    public AlunoModel inserir(AlunoModel aluno) {
    	return alunoService.inserir(aluno);
    	
    }
    
    @PutMapping("/AlterarAluno")
    public AlunoModel alterar(AlunoModel aluno) {
    	return alunoService.alterar(aluno);
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathParam("id") Long id){
    	alunoService.excluir(id);
    	return ResponseEntity.ok().body(null);
    }
    
    
    
    
}
