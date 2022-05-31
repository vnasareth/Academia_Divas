package edu.poo;

import java.util.List;

public interface IAlunosDAO {
	
	void inserir(Aluno aluno);
	
	List<Aluno> consultar(String nome);

}
