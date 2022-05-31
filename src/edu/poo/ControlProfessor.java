package edu.poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControlProfessor{

	private IProfessorDAO dao = new ProfessoresDAOImpl();

	private ObservableList<Professor> ProfessoresLista = FXCollections.observableArrayList();
	private TableView<Professor> table = new TableView<>();

	private LongProperty id = new SimpleLongProperty();
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty salario = new SimpleStringProperty("", null);
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>();

	public LongProperty idProperty() {
		return id;
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public StringProperty enderecoProperty() {
		return endereco;
	}

	public StringProperty cpfProperty() {
		return cpf;
	}

	public StringProperty telefoneProperty() {
		return telefone;
	}

	public StringProperty SalarioProperty() {
		return salario;
	}

	public ObjectProperty<LocalDate> nascimentoProperty() {
		return nascimento;
	}

	@SuppressWarnings("unchecked")
	public ControlProfessor() {

		TableColumn<Professor, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Professor, String> colunaCpf = new TableColumn<>("CPF");
		TableColumn<Professor, String> colunaEndereco = new TableColumn<>("Endereço");
		TableColumn<Professor, String> colunaNascimento = new TableColumn<>("Data de Nascimento");
		TableColumn<Professor, String> colunaTelefone = new TableColumn<>("Telefone");
		TableColumn<Professor, String> colunaSalario = new TableColumn<>("Salario");
		TableColumn<Professor, String> colunaId = new TableColumn<>("ID");

		colunaNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<Professor, String>("cpf"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
		colunaSalario.setCellValueFactory(new PropertyValueFactory<Professor, String>("salario"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
		colunaId.setCellValueFactory(new PropertyValueFactory<Professor, String>("id"));
		colunaNascimento.setCellValueFactory(data -> {
			LocalDate dataNasc = data.getValue().getNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataNasc.format(formatter));
		});

		table.getColumns().addAll(colunaId, colunaNome, colunaCpf, colunaNascimento, colunaTelefone, colunaSalario,
				colunaEndereco);
		table.setItems(ProfessoresLista);
	}

	public void adicionar() {
		Professor Professor = new Professor();
		Professor.setNome(nome.get());
		Professor.setCpf(cpf.get());
		Professor.setEndereco(endereco.get());
		Professor.setTelefone(telefone.get());
		Professor.setSalario(salario.get());
		Professor.setNascimento(nascimento.get());
		Professor.setId(id.get());

		ProfessoresLista.add(Professor);
		dao.inserir(Professor);
	}

	public void pesquisar() {
		List<Professor> lista = dao.consultar(nome.get());
		ProfessoresLista.clear();
		ProfessoresLista.addAll(lista);
	}

	public TableView<Professor> getTable() {
		return table;
	}

}
