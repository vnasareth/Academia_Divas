package edu.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessoresDAOImpl implements IProfessorDAO {

	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academiadb?allowMultiQueries=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = ""; // Senha banco de dados
	private Connection con;

	public ProfessoresDAOImpl() {
	        try {
	            Class.forName(JDBC_CLASS);
	            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	@Override
	public void inserir(Professor professor) {
		String sql = "INSERT INTO professor (id, nome, cpf, endereco, salario,nascimento,telefone) ";
		sql += " VALUES (?, ?, ?, ?, ?, ?,?) ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, professor.getId());
			stmt.setString(2, professor.getNome());
			stmt.setString(3, professor.getCpf());
			stmt.setString(4, professor.getEndereco());
			stmt.setString(5, professor.getSalario());
			stmt.setDate(6, java.sql.Date.valueOf(professor.getNascimento()));
			stmt.setString(7, professor.getTelefone());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public List<Professor> consultar(String nome) {
		List<Professor> lista = new ArrayList<>();
		String sql = "SELECT * FROM professor WHERE nome LIKE '%" + nome + "%'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Professor professor = new Professor();
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("cpf"));
				professor.setEndereco(rs.getString("endereco"));
				professor.setSalario(rs.getString("salario"));
				professor.setNascimento(rs.getDate("nascimento").toLocalDate());
				professor.setTelefone(rs.getString("telefone"));
 				
				lista.add(professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

}
