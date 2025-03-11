package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CineData;
import model.HorarioSala;
import model.Pelicula;

public class AsignarController {

    @FXML
    private ComboBox<String> cbFranja;
    @FXML
    private ComboBox<Pelicula> cbPelicula;
    @FXML
    private ComboBox<String> cbSala;
    @FXML
    private TableView<HorarioSala> tblHorarios;

    @FXML
    private TableColumn<HorarioSala, String> colSala;
    @FXML
    private TableColumn<HorarioSala, String> colFranja;
    @FXML
    private TableColumn<HorarioSala, String> colPelicula;
    @FXML
    private TableColumn<HorarioSala, String> colTipo;

    private final CineData cineData = CineData.getInstance();

    //Metodo de inicialización
    @FXML
    public void initialize() {
        configurarComboboxes();
        configurarTabla();
        configurarFiltros();
    }

    //Botones FXML
    @FXML
    void bAsignar(ActionEvent event) {
        try {
            validarCampos();
            HorarioSala nuevoHorario = new HorarioSala(
                    cbSala.getValue(),
                    cbFranja.getValue(),
                    cbPelicula.getValue()
            );

            validarAsignacion(nuevoHorario);
            cineData.getHorarios().add(nuevoHorario);

        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error de Asignación", e.getMessage());
        }
    }

    @FXML
    void bSalir(ActionEvent event) {
        ((javafx.stage.Stage) tblHorarios.getScene().getWindow()).close();
    }

    private void configurarComboboxes() {
        cbSala.getItems().addAll("Sala 1", "Sala 2", "Sala 3");
        cbFranja.getItems().addAll("14:00 - 16:30", "16:30 - 19:00", "19:00 - 21:00");

        cbPelicula.setCellFactory(param -> new ListCell<Pelicula>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getNombre());
            }
        });

        cbPelicula.setButtonCell(new ListCell<Pelicula>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? "" : item.getNombre());
            }
        });

        cbPelicula.setItems(CineData.getInstance().getPeliculas());
    }

    //Metodos auxiliares
    private void configurarTabla() {
        tblHorarios.setItems(cineData.getHorarios());

        colSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        colFranja.setCellValueFactory(new PropertyValueFactory<>("franjaHoraria"));
        colPelicula.setCellValueFactory(cellData -> cellData.getValue().peliculaProperty().get().nombreProperty());
        colTipo.setCellValueFactory(cellData -> cellData.getValue().peliculaProperty().get().tipoProperty());
    }

    private void configurarFiltros() {
        cbSala.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, nuevaSala) -> {
            if ("Sala 3".equals(nuevaSala)) {
                cbPelicula.setItems(cineData.getPeliculas().filtered(p -> "3D".equals(p.getTipo())));
            } else {
                cbPelicula.setItems(cineData.getPeliculas().filtered(p -> !"3D".equals(p.getTipo())));
            }
        });
    }

    private void validarCampos() {
        if (cbSala.getValue() == null || cbFranja.getValue() == null || cbPelicula.getValue() == null) {
            throw new IllegalArgumentException("Todos los campos son requeridos");
        }
    }

    private void validarAsignacion(HorarioSala horario) {
        // Validar sala 3 solo 3D
        if (horario.getSala().equals("Sala 3") && !horario.getPelicula().getTipo().equals("3D")) {
            throw new IllegalArgumentException("Sala 3 solo admite películas 3D");
        }

        // Validar duplicados
        boolean existeDuplicado = cineData.getHorarios().stream()
                .anyMatch(h -> h.getSala().equals(horario.getSala())
                        && h.getFranjaHoraria().equals(horario.getFranjaHoraria()));

        if (existeDuplicado) {
            throw new IllegalArgumentException("Esta sala ya tiene una función en la franja seleccionada");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
