package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.AlunoModel;
import repository.AlunoRepository;

public class AlunoService {
	@Autowired
    private AlunoRepository Aluno;
	
	public List<AlunoModel> buscartodos(){
    	return Aluno.findAll();
    }
	
	public AlunoModel inserir(AlunoModel aluno) {
		AlunoModel AlunoNovo = Aluno.saveAndFlush(aluno);//receber novo aluno
		return AlunoNovo;
	}
	
	public AlunoModel alterar(AlunoModel aluno) {
		return Aluno.saveAndFlush(aluno);
	}
	
	public void excluir(Long id) {
		AlunoModel aluno = Aluno.findById(id).get();
		Aluno.delete(aluno);
	}
}
