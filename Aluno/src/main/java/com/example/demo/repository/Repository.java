package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AlunoModel;


public interface Repository extends JpaRepository<AlunoModel, Long> {


}
