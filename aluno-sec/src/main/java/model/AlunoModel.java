package model;

import java.util.UUID;

import dto.AlunoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="aluno")
public class AlunoModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private String Nome;
	private String Email;
	private String Telefone;
	
	public AlunoModel (AlunoDto DTO) {
		this.Nome = DTO.Nome();
		this.Email = DTO.Email();
		this.Telefone = DTO.Telefone();
		
	}

	
}
