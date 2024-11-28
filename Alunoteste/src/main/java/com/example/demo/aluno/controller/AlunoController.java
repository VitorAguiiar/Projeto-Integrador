package com.example.demo.aluno.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.aluno.dto.AlunoDTO;
import com.example.demo.service.AlunoService;

@RestController
@RequestMapping("/api")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    private static final String UPLOAD_DIR = "src/main/resources/static/src/imagens/";  // Diretório onde as imagens serão salvas

    // Método POST - Criar aluno (com upload de imagem)
    @PostMapping("/criaraluno")
    public ResponseEntity<Object> createAluno (@RequestParam("nome") String nome,
                                               @RequestParam("telefone") String telefone,
                                               @RequestParam("email") String email,
                                               @RequestParam("imagem") MultipartFile imagem) {
        try {
            // Renomeia o arquivo para evitar conflito de nomes
            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + nomeArquivo);
            Files.createDirectories(path.getParent());  // Cria os diretórios, se não existirem
            imagem.transferTo(path);  // Salva o arquivo no diretório especificado

            // Criar o objeto Aluno
            AlunoDTO alunoDTO = new AlunoDTO(null, nome, telefone, email, nomeArquivo);
            AlunoDTO novoAluno = alunoService.createAluno(alunoDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao fazer upload da imagem");
        }
    }

    // Método GET - Obter todos os alunos
    @GetMapping("/buscaraluno")
    public ResponseEntity<List<AlunoDTO>> getAllAlunos() {
        List<AlunoDTO> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    // Método PUT - Atualizar aluno (com upload de imagem)
    @PutMapping("/alteraraluno/{id}")
    public ResponseEntity<Object> updateAluno (@PathVariable Long id,
                                               @RequestParam("nome") String nome,
                                               @RequestParam("telefone") String telefone,
                                               @RequestParam("email") String email,
                                               @RequestParam(value = "imagem", required = false) MultipartFile imagem) {
        try {
            AlunoDTO alunoDTO = new AlunoDTO(id, nome, telefone, email, null); // Inicializa imagem com null

            // Verifica se foi enviada uma nova imagem
            if (imagem != null && !imagem.isEmpty()) {
                String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + nomeArquivo);
                Files.createDirectories(path.getParent());
                imagem.transferTo(path);
                alunoDTO.setImagem(nomeArquivo);  // Se nova imagem, setamos o nome do arquivo
            } else {
                // Caso não haja imagem, manter a imagem atual
                AlunoDTO alunoExistente = alunoService.getAlunoById(id);
                alunoDTO.setImagem(alunoExistente.getImagem());  // Não modifica a imagem, apenas mantém
            }

            AlunoDTO alunoAtualizado = alunoService.updateAluno(id, alunoDTO);

            if (alunoAtualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
            }

            return ResponseEntity.ok(alunoAtualizado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao fazer upload da imagem");
        }
    }

    // Método DELETE - Deletar aluno por ID
    @DeleteMapping("/deletaraluno/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable Long id) {
        boolean deleted = alunoService.deleteAluno(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
    }
}