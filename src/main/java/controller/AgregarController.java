package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CineData;
import model.Pelicula;


public class AgregarController {
    @FXML
    private TableColumn<Pelicula, String> colDuracion;

    @FXML
    private TableColumn<Pelicula, String> colIdioma;

    @FXML
    private TableColumn<Pelicula, String> colNombre;

    @FXML
    private TableColumn<Pelicula, String> colTipo;

    @FXML
    private TableView<Pelicula> tblPeliculas;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtIdioma;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTipo;

    //Esta instacia se usa para obtener la pelicula en selección de la tabla
    private Pelicula peliculaSeleccionada;

    //Metodo de inicialización
    @FXML
    public void initialize() {
        tblPeliculas.setItems(CineData.getInstance().getPeliculas());

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        // Es un Listener para detectar la selección en la tabla
        tblPeliculas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                peliculaSeleccionada = newSelection; // Asignar la película seleccionada
            }
        });
    }

    //Botones FXML
    @FXML
    void bAnadir(ActionEvent event) {
        if (validarCampos()) {
            Pelicula nuevaPelicula = new Pelicula(
                    txtNombre.getText().trim(),
                    txtIdioma.getText().trim(),
                    txtTipo.getText().trim().toUpperCase(),
                    txtDuracion.getText().trim()
            );

            if (validarTipo(nuevaPelicula.getTipo())) {
                CineData.getInstance().getPeliculas().add(nuevaPelicula);
                limpiarCampos();
            } else {
                mostrarAlerta("Tipos válidos: 34MM, 3D");
            }
        }
    }

    @FXML
    void bEditar(ActionEvent event) {
        peliculaSeleccionada = tblPeliculas.getSelectionModel().getSelectedItem();

        if (peliculaSeleccionada != null) {
            txtNombre.setText(peliculaSeleccionada.getNombre());
            txtIdioma.setText(peliculaSeleccionada.getIdioma());
            txtTipo.setText(peliculaSeleccionada.getTipo());
            txtDuracion.setText(peliculaSeleccionada.getDuracion());
        } else {
            mostrarAlerta("Debe seleccionar una película primero");
        }
    }

    @FXML
    void bGuardar(ActionEvent event) {
        if (peliculaSeleccionada != null && validarCampos()) {
            peliculaSeleccionada.setNombre(txtNombre.getText());
            peliculaSeleccionada.setIdioma(txtIdioma.getText());
            peliculaSeleccionada.setTipo(txtTipo.getText());
            peliculaSeleccionada.setDuracion(txtDuracion.getText());

            tblPeliculas.refresh();
            limpiarCampos();
        }
    }

    @FXML
    void bEliminar(ActionEvent event) {
        Pelicula seleccion = tblPeliculas.getSelectionModel().getSelectedItem();

        if (seleccion != null && CineData.getInstance().getPeliculas().contains(seleccion)) {
            CineData.getInstance().getPeliculas().remove(seleccion);
            tblPeliculas.getSelectionModel().clearSelection();
            limpiarCampos();
        } else {
            mostrarAlerta("Selecciona una película válida");
        }
    }

    @FXML
    void bSalir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isBlank() || txtIdioma.getText().isBlank()
                || txtTipo.getText().isBlank() || txtDuracion.getText().isBlank()) {
            mostrarAlerta("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private boolean validarTipo(String tipo) {
        return tipo.matches("34MM|3D");
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdioma.clear();
        txtTipo.clear();
        txtDuracion.clear();
        tblPeliculas.getSelectionModel().clearSelection();
        peliculaSeleccionada = null;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
