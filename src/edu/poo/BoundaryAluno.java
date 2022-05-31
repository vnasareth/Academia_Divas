package edu.poo;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class BoundaryAluno extends Application {
	
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCPF = new TextField();
	private TextField txtEndereco = new TextField();
	private TextField txtDataNascimento = new TextField();
	private TextField txtTelefone = new TextField();
	
	
	private Button botaoAdicionar = new Button("Adicionar");
	private Button botaoPesquisar = new Button("Pesquisar");
	
	private ControlAluno control = new ControlAluno();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Gestão de Aluno - Academia Divas ");
		
		Label labelId = new Label("Id");
		Label labelNome = new Label("Nome");
		Label labelCPF = new Label("CPF");
		Label labelEndereco = new Label("Endereço");
		Label labelDataNascimento = new Label("Data de Nascimento");
		Label labelTelefone = new Label("Telefone");
		
		
		
		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		GridPane pane = new GridPane();
		borderPane.setTop(gridPane);
		gridPane.setPadding(new Insets (10, 10, 10, 10));
		
	 	gridPane.add(labelId, 0, 0);
		gridPane.add(txtId, 1, 0);
		gridPane.add(labelNome, 0, 1);
		gridPane.add(txtNome, 1, 1);
		gridPane.add(labelCPF, 0, 2);
		gridPane.add(txtCPF, 1, 2);
		gridPane.add(labelEndereco, 0, 3);
		gridPane.add(txtEndereco, 1, 3);
		gridPane.add(labelDataNascimento, 0,4 );
		gridPane.add(txtDataNascimento, 1, 4);
		gridPane.add(labelTelefone, 0, 5);
		gridPane.add(txtTelefone, 1, 5);
		
		gridPane.add(botaoAdicionar, 0, 6);
		gridPane.add(botaoPesquisar, 1, 6);
		
		String estiloTextField = "-fx-background-color: lightgray; " + "-fx-border-color: gray;" +
                "-fx-border-width: 0.7px;";
        txtNome.setStyle(estiloTextField);
        txtId.setStyle(estiloTextField);
        txtEndereco.setStyle(estiloTextField);
        txtTelefone.setStyle(estiloTextField);
        txtDataNascimento.setStyle(estiloTextField);
        botaoAdicionar.setStyle("-fx-background-color: lightgreen;");
        botaoPesquisar.setStyle("-fx-background-color: lightyellow;");
        pane.setStyle("-fx-background-color: darkgray");
        
	
		gridPane.setVgap(10);
		gridPane.setHgap(20);
		
		StringConverter<Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(),converterNumber); 
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtCPF.textProperty(), control.cpfProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), control.enderecoProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
		StringConverter<LocalDate> converterDate = new LocalDateStringConverter();
		Bindings.bindBidirectional(txtDataNascimento.textProperty(), control.nascimentoProperty(), converterDate);
		
        botaoAdicionar.setOnAction((e) -> {
            control.adicionar();
            txtId.setText("");
            txtNome.setText("");
            txtCPF.setText("");
            txtEndereco.setText("");
            txtDataNascimento.setText("");
            txtTelefone.setText("");
        });

        botaoPesquisar.setOnAction((e) -> {
            control.pesquisar();
        });
		
        borderPane.setCenter(control.getTable());
		Scene scene = new Scene(borderPane,600, 600);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(BoundaryAluno.class, args);
		
	}

}
