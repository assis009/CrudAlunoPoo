package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AlunoBoundary extends Application implements EventHandler<ActionEvent> {
			
	private TextField txtId = new TextField("");
	private TextField txtRa = new TextField("");
	private TextField txtNome = new TextField("");
	private TextField txtNascimento = new TextField("");

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private AlunoControl control = new AlunoControl();

	@Override
	public void start(Stage stage) throws Exception {

		GridPane grid = new GridPane();
		Scene scn = new Scene(grid, 600, 400);

		grid.setStyle("-fx-margin: 30px");

		grid.add(new Label("ID: "), 0, 0);
		grid.add(txtId, 1, 0);
		grid.add(new Label("RA: "), 0, 1);
		grid.add(txtRa, 1, 1);
		grid.add(new Label("Nome: "), 0, 2);
		grid.add(txtNome, 1, 2);
		grid.add(new Label("Nascimento: "), 0, 3);
		grid.add(txtNascimento, 1, 3);

		grid.add(btnAdicionar, 0, 9);
		grid.add(btnPesquisar, 1, 9);

		btnAdicionar.addEventFilter(ActionEvent.ACTION, this);
		btnPesquisar.addEventFilter(ActionEvent.ACTION, this);

		stage.setScene(scn);
		stage.setTitle("Gestão de Alunos");
		stage.show();
	}

	public Aluno boundaryToEntity() {
		Aluno e = new Aluno();
		
		try {
			
			e.setId(Long.parseLong(txtId.getText()));
			e.setRa(txtRa.getText());
			e.setNome(txtNome.getText());
			e.setNascimento(null);
			LocalDate d = LocalDate.parse(txtNascimento.getText(), formatter);
			e.setNascimento(d);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return e;
	}
	
	public void entityToBoundary(Aluno e) {
		
		if(e != null) {
			
			try {
				txtId.setText(String.valueOf(e.getId()));
				txtRa.setText(e.getRa());
				txtNome.setText(e.getNome());
				
				String nasc = e.getNascimento().format(formatter);
				txtNascimento.setText(nasc);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}



	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == btnAdicionar) {
			Aluno a = this.boundaryToEntity();
			control.adicionar(a);
		} else if (event.getSource() == btnPesquisar) {
			Aluno a = control.pesquisar(txtNome.getText());
			this.entityToBoundary(a);

		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
