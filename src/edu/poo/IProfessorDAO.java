package edu.poo;

import java.util.List;

public interface IProfessorDAO {
	
	void inserir(Professor professor);
	
	List<Professor> consultar(String professor);	

}
