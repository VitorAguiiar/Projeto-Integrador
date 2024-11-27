package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AlunoModel;
import com.example.demo.repository.Repository;

@Service
public class AlunoImagemService {

    @Autowired
    private Repository alunoImagemRepository;

    public List<AlunoModel> listarTodos() {
        return alunoImagemRepository.findAll();
    }

    public AlunoModel salvar(AlunoModel alunoModel) {
        return alunoImagemRepository.save(alunoModel);
    }

    public AlunoModel buscarPorId(Long id) {
        return alunoImagemRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
    	alunoImagemRepository.deleteById(id);
    }
}
