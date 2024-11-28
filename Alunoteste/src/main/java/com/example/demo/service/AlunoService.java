package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.aluno.dto.AlunoDTO;
import com.example.demo.model.AlunoModel;
import com.example.demo.repository.Repository;

@Service
public class AlunoService {

    @Autowired
    private Repository alunoRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/src/imagens/";  // Diretório onde as imagens serão salvas

    // Método para buscar todos os alunos
    public List<AlunoDTO> getAllAlunos() {
        List<AlunoModel> alunos = alunoRepository.findAll();
        return alunos.stream()
                .map(AlunoDTO::new)  // Converte cada AlunoModel em AlunoDTO
                .collect(Collectors.toList());
    }

    // Método para criar um novo aluno
    public AlunoDTO createAluno(AlunoDTO alunoDTO) {
        AlunoModel aluno = new AlunoModel(alunoDTO);
        aluno.setImagem(alunoDTO.getImagem());  // Armazena a URL da imagem
        AlunoModel alunoCriado = alunoRepository.save(aluno);
        return new AlunoDTO(alunoCriado);
    }

    // Método para atualizar um aluno
    public AlunoDTO updateAluno(Long id, AlunoDTO alunoDTO) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            AlunoModel aluno = alunoExistente.get();
            aluno.setnome(alunoDTO.getNome());
            aluno.setTelefone(alunoDTO.getTelefone());
            aluno.setEmail(alunoDTO.getEmail());
            aluno.setImagem(alunoDTO.getImagem());  // Atualiza a URL da imagem
            AlunoModel alunoAtualizado = alunoRepository.save(aluno);
            return new AlunoDTO(alunoAtualizado);
        }
        return null;
    }

    // Método para excluir um aluno
    public boolean deleteAluno(Long id) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            AlunoModel aluno = alunoExistente.get();
            // Exclui o arquivo de imagem associado ao aluno
            String imagemPath = UPLOAD_DIR + aluno.getImagem();
            try {
                Files.deleteIfExists(Paths.get(imagemPath));  // Apaga a imagem se existir
            } catch (IOException e) {
                System.out.println("Erro ao excluir imagem: " + e.getMessage());
            }

            alunoRepository.deleteById(id);  // Deleta o aluno do banco
            return true;
        }
        return false;
    }

    // Método para buscar aluno por ID
    public AlunoDTO getAlunoById(Long id) {
        Optional<AlunoModel> aluno = alunoRepository.findById(id);
        return aluno.map(AlunoDTO::new).orElse(null);
    }
}
