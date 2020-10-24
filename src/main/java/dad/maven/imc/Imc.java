package dad.maven.imc;

import java.text.NumberFormat;
import java.text.ParseException;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Imc extends Application {

	private DoubleProperty peso = new SimpleDoubleProperty();
	private DoubleProperty altura = new SimpleDoubleProperty();
	private Label imcLabel, pesosLabel;
	private TextField pesoText, alturaText;

	public void start(Stage primaryStage) throws Exception {
		pesoText = new TextField();
		pesoText.setPrefColumnCount(4);

		HBox pesoBox = new HBox(5);
		pesoBox.setAlignment(Pos.CENTER);
		pesoBox.setSpacing(5);
		pesoBox.getChildren().addAll(new Label("Peso: "), pesoText, new Label(" kg"));

		alturaText = new TextField();
		alturaText.setPrefColumnCount(4);

		imcLabel = new Label("");
		pesosLabel = new Label();

		HBox alturaBox = new HBox();
		alturaBox.setAlignment(Pos.CENTER);
		alturaBox.setSpacing(5);
		alturaBox.getChildren().addAll(new Label("Altura: "), alturaText, new Label(" cm"));

		HBox imcBox = new HBox();
		imcBox.setAlignment(Pos.CENTER);
		imcBox.setSpacing(5);
		imcBox.getChildren().addAll(new Label("IMC: "), imcLabel);

		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, imcBox, pesosLabel);

		imcLabel.textProperty().bind(peso.divide(altura.multiply(altura)).multiply(10000).asString());
		imcLabel.textProperty().addListener((o, ov, nv) -> {
			mostrarPesos();
		});

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("IMC.fxml");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void mostrarPesos() {
		try {
			double imc = NumberFormat.getInstance().parse(imcLabel.getText()).doubleValue();
			if (imc < 18.5) {
				pesosLabel.setText("Bajo peso");
			} else if (imc >= 18.5 && imc < 25) {
				pesosLabel.setText("Normal");
			} else if (imc >= 25 && imc < 30) {
				pesosLabel.setText("Sobrepeso");
			} else if (imc >= 30) {
				pesosLabel.setText("Obeso");
			} else {
				pesosLabel.setText("Bajo peso | Normal | Sobrepeso | Obeso");
			}

		} catch (ParseException e) {

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
