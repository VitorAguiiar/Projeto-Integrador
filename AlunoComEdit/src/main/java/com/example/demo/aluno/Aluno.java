package com.example.demo.aluno;

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
public class Aluno {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String user;
	private String telefone;
	private String email;
	private String imagemUrl; // Atributo para a URL da imagem

	public Aluno() {
	}

	public Aluno(Long id, String user, String telefone, String email, String imagemUrl) {
		this.id = id;
		this.user = user;
		this.telefone = telefone;
		this.imagemUrl = imagemUrl;
	}

	public void updateDTO(DTO dto) {
	    this.user = dto.user();
	    this.telefone = dto.telefone();
	    this.email = dto.email();
	    this.imagemUrl = dto.imagemUrl();
	}

	public Aluno(DTO dto) {
		this.user = dto.user();
		this.telefone = dto.telefone();
		this.email = dto.email();
		this.imagemUrl = dto.imagemUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
	public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

}