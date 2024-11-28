package com.example.demo.model;


import com.example.demo.aluno.dto.AlunoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cadastro")
public class AlunoModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String imagem; // Atributo para a URL da imagem

	public AlunoModel() {
	}

	public AlunoModel(Long id, String nome, String telefone, String email, String imagem) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.imagem = imagem;
	}

	public void updateDTO(AlunoDTO dto) {
	    this.nome = dto.getNome();
	    this.telefone = dto.getTelefone();
	    this.email = dto.getEmail();
	    this.imagem = dto.getImagem();
	}

	public AlunoModel(AlunoDTO dto) {
		this.nome = dto.getNome();
		this.telefone = dto.getTelefone();
		this.email = dto.getEmail();
		this.imagem = dto.getImagem();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String genome() {
		return nome;
	}

	public void setnome(String nome) {
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