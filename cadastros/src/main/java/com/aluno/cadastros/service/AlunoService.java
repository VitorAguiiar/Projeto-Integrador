package com.aluno.cadastros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aluno.cadastros.dto.AlunoDto;
import com.aluno.cadastros.model.AlunoModel;
import com.aluno.cadastros.repository.AlunoRepository;
@Service
public class AlunoService {
	@Autowired
    private AlunoRepository Aluno;
	
	public List<AlunoModel> buscartodos(){
    	return Aluno.findAll();
    }

	
	public AlunoModel alterar(AlunoModel aluno) {
		return Aluno.saveAndFlush(aluno);
	}
	
	public void excluir(Long id) {
		AlunoModel aluno = Aluno.findById(id).get();
		Aluno.delete(aluno);
	}
}
