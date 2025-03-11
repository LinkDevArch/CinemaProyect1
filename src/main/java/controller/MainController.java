package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.Path;

public class MainController {

    //Botones FXML

    @FXML
    void bAgregar(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.AGREGAR_VIEW));

            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(pane);

            stage.setScene(scene);
            stage.setTitle("Agregar");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bAsignar(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.ASIGNAR_VIEW));

            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(pane);

            stage.setScene(scene);
            stage.setTitle("Asignar");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bVentas(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.VENTAS_VIEW));

            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(pane);

            stage.setScene(scene);
            stage.setTitle("Ventas");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void bSalir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
