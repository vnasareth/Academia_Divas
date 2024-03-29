package edu.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunosDAOImpl implements IAlunosDAO {

	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academiadb?allowMultiQueries=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = ""; // Senha banco de dados
	private Connection con;

	public AlunosDAOImpl() {
	        try {
	            Class.forName(JDBC_CLASS);
	            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	@Override
	public void inserir(Aluno aluno) {
		String sql = "INSERT INTO aluno (id, nome, cpf, endereco, nascimento,telefone) ";
		sql += " VALUES (1, ?, ?, ?, ?, ?) ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCpf());
			stmt.setString(3, aluno.getEndereco());
			stmt.setDate(4, java.sql.Date.valueOf(aluno.getNascimento()));
			stmt.setString(5, aluno.getTelefone());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public List<Aluno> consultar(String nome) {
		List<Aluno> lista = new ArrayList<>();
		String sql = "SELECT * FROM aluno WHERE nome LIKE '%" + nome + "%'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setNascimento(rs.getDate("nascimento").toLocalDate());
				aluno.setTelefone(rs.getString("telefone"));
 				
				lista.add(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

}
