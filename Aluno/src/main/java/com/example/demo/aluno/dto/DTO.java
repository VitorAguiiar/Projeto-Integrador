package com.example.demo.aluno.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record DTO(String user, String telefone, String email, String imagemUrl) {
}
