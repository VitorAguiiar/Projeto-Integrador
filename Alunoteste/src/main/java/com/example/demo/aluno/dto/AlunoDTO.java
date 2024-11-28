package com.example.demo.aluno.dto;

import com.example.demo.model.AlunoModel;

public class AlunoDTO {
private Long id;
private String nome;
private String telefone;
private String email;
private String imagem;  // Campo para armazenar a URL da imagem

// Construtores
public AlunoDTO() {}

public AlunoDTO(Long id, String nome, String telefone, String email, String imagem) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
    this.imagem = imagem;
}

// Construtor para converter AlunoModel (do banco de dados) para AlunoDTO
public AlunoDTO(AlunoModel alunoModel) {
    this.id = alunoModel.getId();
    this.nome = alunoModel.getNome();
    this.telefone = alunoModel.getTelefone();
    this.email = alunoModel.getEmail();
    this.imagem = alunoModel.getImagem();  // Aqui salva a URL da imagem
}

// Getters e Setters
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getTelefone() {
    return telefone;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getImagem() {
    return imagem;
}

public void setImagem(String imagem) {
    this.imagem = imagem;
}
}