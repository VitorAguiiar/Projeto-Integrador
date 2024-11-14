package com.aluno.cadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluno.cadastros.model.AlunoModel;
@Repository
public interface AlunoRepository extends JpaRepository <AlunoModel, Long > {

}
