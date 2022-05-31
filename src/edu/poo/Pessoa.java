package edu.poo;

import java.time.LocalDate;

public class Pessoa {
	
	
	private String cpf;
	private String nome;
	private LocalDate nascimento;
	private String endereco;
	private String telefone;
	
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone ;
	}
	
			
	public String getEndereco() {
		return endereco;		
	}		
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", nascimento=" + nascimento + ", endereco=" + endereco
				+ ", telefone=" + telefone + "]";
	}

					
}	
