package dad.maven.imc;

import java.text.NumberFormat;
import java.text.ParseException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Imc extends Application {

	private TextField pesoText, alturaText;
	private Label imcLabel;
	private ImcModel model = new ImcModel();
	private Label pesosLabel;

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
		pesosLabel = new Label("");

		HBox alturaBox = new HBox();
		alturaBox.setAlignment(Pos.BASELINE_CENTER);
		alturaBox.setSpacing(5);
		alturaBox.getChildren().addAll(new Label("Altura: "), alturaText, new Label(" cm"));

		HBox imcBox = new HBox();
		imcBox.setAlignment(Pos.BASELINE_CENTER);
		imcBox.setSpacing(5);
		imcBox.getChildren().addAll(new Label("IMC: "), imcLabel);

		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, imcBox, pesosLabel);

		pesoText.textProperty().bindBidirectional(model.peso, new NumberStringConverter());
		alturaText.textProperty().bindBidirectional(model.altura, new NumberStringConverter());
		model.pesosProperty().bindBidirectional(pesosLabel.textProperty());
		imcLabel.textProperty()
				.bind(model.peso.divide(model.altura.multiply(model.altura)).multiply(10000).asString("%.2f"));
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
            if (model.getPeso() != 0 & model.getAltura() != 0) {
                double resultado = NumberFormat.getInstance().parse(imcLabel.getText()).doubleValue();

                if (resultado < 18.5) {
                    model.setPesos("Bajo peso");
                } else if (resultado >= 18.5 & resultado < 25) {
                    pesosLabel.setText("Normal");
                } else if (resultado >= 25 & resultado < 30) {
                	pesosLabel.setText("Sobrepeso");
                } else if (resultado >= 30) {
                	pesosLabel.setText("Obeso");
                }
            } else {
            	pesosLabel.setText("Bajo peso | Normal | Sobrepeso | Obeso");
            }
        } catch (ParseException e) {
            // e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
