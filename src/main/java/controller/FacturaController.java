package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FacturaController {

    @FXML
    private Label lblFacIdioma;

    @FXML
    private Label lblFacPelicula;

    @FXML
    private Label lblFacSala;

    @FXML
    private Label lblFacSillas;

    @FXML
    private Label lblFacTipo;

    @FXML
    private Label lblFacTotal;

    @FXML
    private Label lblFacFranja;

    @FXML

    public void initialize() {

    }

    public void Facturar(String pelicula, String sala, String franja, String idioma, String sillas, String tipo, String total) {
        lblFacPelicula.setText(pelicula);
        lblFacSala.setText(sala);
        lblFacIdioma.setText(idioma);
        lblFacSillas.setText(sillas);
        lblFacTipo.setText(tipo);
        lblFacTotal.setText(total);
        lblFacFranja.setText(franja);
    }


}
