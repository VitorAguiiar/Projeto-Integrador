package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AlunoModel;


public interface Repository extends JpaRepository<AlunoModel, UUID> {


}
