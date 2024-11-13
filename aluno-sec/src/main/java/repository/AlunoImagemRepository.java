package repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AlunoModel;

public interface AlunoImagemRepository extends JpaRepository <AlunoModel, Long > {

}
