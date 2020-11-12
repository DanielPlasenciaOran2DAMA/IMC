package dad.maven.imc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ImcModel {

	public DoubleProperty peso = new SimpleDoubleProperty();
	public DoubleProperty altura = new SimpleDoubleProperty();
	public StringProperty pesos = new SimpleStringProperty();

	public final DoubleProperty pesoProperty() {
		return this.peso;
	}

	public final double getPeso() {
		return this.pesoProperty().get();
	}

	public final void setPeso(final double peso) {
		this.pesoProperty().set(peso);
	}

	public final DoubleProperty alturaProperty() {
		return this.altura;
	}

	public final double getAltura() {
		return this.alturaProperty().get();
	}

	public final void setAltura(final double altura) {
		this.alturaProperty().set(altura);
	}

	public final StringProperty pesosProperty() {
		return this.pesos;
	}

	public final String getPesos() {
		return this.pesosProperty().get();
	}

	public final void setPesos(final String pesos) {
		this.pesosProperty().set(pesos);
	}

}
