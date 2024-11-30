package com.example.demo.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoImagemService {

    @Autowired
    private Repository alunoImagemRepository;

    public List<Aluno> listarTodos() {
        return alunoImagemRepository.findAll();
    }

    public Aluno salvar(Aluno alunoModel) {
        return alunoImagemRepository.save(alunoModel);
    }

    public Aluno buscarPorId(Long id) {
        return alunoImagemRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
    	alunoImagemRepository.deleteById(id);
    }
}
