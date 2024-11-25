package com.example.demo.alunoimagem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoImagemService {

    @Autowired
    private AlunoImagemRepository alunoImagemRepository;

    public List<AlunoImagem> listarTodos() {
        return alunoImagemRepository.findAll();
    }

    public AlunoImagem salvar(AlunoImagem alunoImagem) {
        return alunoImagemRepository.save(alunoImagem);
    }

    public AlunoImagem buscarPorId(Long id) {
        return alunoImagemRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
    	alunoImagemRepository.deleteById(id);
    }
}
