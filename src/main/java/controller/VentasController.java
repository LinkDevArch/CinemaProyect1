package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.CalcularPrecios;
import model.CineData;
import model.HorarioSala;
import utilities.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VentasController {

    @FXML
    private GridPane gridSala1;

    @FXML
    private GridPane gridSala2;

    @FXML
    private GridPane gridSala3;

    private GridPane gridActual;

    @FXML
    private ComboBox<HorarioSala> cbFuncion;

    @FXML
    private ComboBox<String> cbSala;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblSillasDisponibles;

    private final CineData cineData = CineData.getInstance();

    public final List<Button> sillasSeleccionadas = new ArrayList<>(); // Lista de sillas seleccionadas

    private final CalcularPrecios calcularPrecios = new CalcularPrecios();

    //Metodo de inicialización
    @FXML
    public void initialize() {
        inicializarGridPanes();
        configurarComboboxes();
        crearBotones();
        configurarListenerDisponibles();
    }

    //Botones FXML
    @FXML
    void confirmarCompra(ActionEvent event) {

        if (sillasSeleccionadas.isEmpty()) {
            mostrarAlerta("No ha seleccionado ninguna silla.");
        }

        HorarioSala funcion = cbFuncion.getValue();
        if (funcion != null) {
            List<String> sillasCompradas = sillasSeleccionadas.stream()
                    .map(Button::getText)
                    .collect(Collectors.toList());

            cineData.agregarSillasOcupadas(funcion, sillasCompradas);

            double totalCompra = calcularPrecios.calcularTotal(sillasSeleccionadas);

            for (Button silla : sillasSeleccionadas) {
                silla.setStyle("-fx-background-color: #F44336;"); // Ocupado (rojo)
                silla.setDisable(true); // Deshabilitar silla
            }
            sillasSeleccionadas.clear(); // Limpiar la lista de seleccionados
            actualizarTotal(); // Reiniciar el total

            //Actualiza total de sillas de la funcion despues de confirmar la compra
            if (cbFuncion.getValue() != null) {
                int totalSillas = calcularTotalSillas(cbFuncion.getValue().getSala());
                int ocupadas = cineData.getSillasOcupadas(cbFuncion.getValue()).size();
                int disponibles = totalSillas - ocupadas;
                lblSillasDisponibles.setText(String.valueOf(disponibles));
            }

            //Datos para la factura

            String pelicula = funcion.getPelicula().getNombre();
            String sala = funcion.getSala();
            String franja = funcion.getFranjaHoraria();
            String idioma = funcion.getPelicula().getIdioma();
            String tipo = funcion.getPelicula().getTipo();
            String totalFactura = String.valueOf(totalCompra);

            String sillasFactura = sillasCompradas.stream().collect(Collectors.joining(", "));


            //Iniciar vista de factura

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.FACTURA_VIEW));
                AnchorPane pane = loader.load();

                FacturaController factura = loader.getController();

                factura.Facturar(pelicula, sala, franja, idioma, sillasFactura, tipo, totalFactura);

                Stage stage = new Stage();
                Scene scene = new Scene(pane);

                stage.setScene(scene);
                stage.setTitle("Factura");
                stage.setResizable(false);
                stage.show();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            mostrarAlerta("Seleccione una funcion");
        }


    }

    @FXML
    void bSalir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void inicializarGridPanes() {
        gridSala1.setVisible(false);
        gridSala2.setVisible(false);
        gridSala3.setVisible(false);

        cbSala.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, nuevaSala) -> {
            if (nuevaSala != null) {
                mostrarGridSala(nuevaSala);
            }
        });
    }


    private void mostrarGridSala(String sala) {
        gridSala1.setVisible(false);
        gridSala2.setVisible(false);
        gridSala3.setVisible(false);

        switch (sala) {
            case "Sala 1":
                gridActual = gridSala1;
                break;
            case "Sala 2":
                gridActual = gridSala2;
                break;
            case "Sala 3":
                gridActual = gridSala3;
                break;
        }
        if (gridActual != null) {
            gridActual.setVisible(true);
        }
    }


    public void crearBotones() {
        crearBotonesParaSala(gridSala1, 6, 12, "Sala 1");
        crearBotonesParaSala(gridSala2, 6, 12, "Sala 2");
        crearBotonesParaSala(gridSala3, 6, 12, "Sala 3");
    }

    private void crearBotonesParaSala(GridPane grid, int filasGenerales, int columnas, String sala) {
        grid.getChildren().clear();

        // Obtiene la función seleccionada
        HorarioSala funcion = cbFuncion.getValue();
        List<String> sillasOcupadas = funcion != null ? cineData.getSillasOcupadas(funcion) : new ArrayList<>();

        // Crear botones generales
        for (int fila = 0; fila < filasGenerales; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                char letraFila = (char) ('A' + fila);
                Button silla = new Button(letraFila + String.valueOf(columna + 1));


                if (sala.equals("Sala 3")) {
                    silla.setUserData("3d");
                    // Color base para sillas 3D
                    silla.setStyle("-fx-background-color: #4CAF50;");
                } else {
                    // Para salas 1 y 2, se dividen en dos secciones:
                    if (fila < filasGenerales) {
                        silla.setUserData("general");
                        silla.setStyle("-fx-background-color: #4CAF50;"); // verde para generales
                    } else {
                        silla.setUserData("preferencial");
                        silla.setStyle("-fx-background-color: #FFC107;"); // amarillo para preferenciales
                    }
                }

                // Verificar si está ocupada
                if (sillasOcupadas.contains(silla.getText())) {
                    silla.setStyle("-fx-background-color: #F44336;"); // rojo para ocupado
                    silla.setDisable(true);
                } else {
                    // Manejador de selección
                    silla.setOnAction(e -> manejarSeleccionSilla(silla));
                }
                grid.add(silla, columna, fila);
            }
        }

        // Crear botones preferenciales para sala 1 y sala 2
        if (!sala.equals("Sala 3")) {
            for (int fila = filasGenerales; fila < filasGenerales + 2; fila++) {
                for (int columna = 0; columna < 9; columna++) {
                    char letraFila = (char) ('A' + fila);
                    Button silla = new Button(letraFila + String.valueOf(columna + 1));

                    silla.setUserData("preferencial");
                    silla.setStyle("-fx-background-color: #FFC107;");

                    if (sillasOcupadas.contains(silla.getText())) {
                        silla.setStyle("-fx-background-color: #F44336;");
                        silla.setDisable(true);
                    } else {
                        silla.setOnAction(e -> manejarSeleccionSilla(silla));
                    }
                    grid.add(silla, columna, fila);
                }
            }
        }
    }

    private void manejarSeleccionSilla(Button silla) {
        if (silla.getStyle().contains("#2196F3")) {
            // Restaurar al color original
            String tipo = (String) silla.getUserData();
            switch (tipo) {
                case "general":
                    silla.setStyle("-fx-background-color: #4CAF50;");
                    break;
                case "preferencial":
                    silla.setStyle("-fx-background-color: #FFC107;");
                    break;
                case "3d":
                    silla.setStyle("-fx-background-color: #4CAF50;");
                    break;
            }
            sillasSeleccionadas.remove(silla);
        } else if (!silla.getStyle().contains("#F44336")) {
            silla.setStyle("-fx-background-color: #2196F3;");
            sillasSeleccionadas.add(silla);
        }
        actualizarTotal();
    }

    private void actualizarTotal() {
        lblTotal.setText(String.valueOf(calcularPrecios.calcularTotal(sillasSeleccionadas)));
    }

    //Metodo para calcular el total de sillas disponibles
    private int calcularTotalSillas(String sala) {
        if ("Sala 3".equals(sala)) {
            return 6 * 12; // 72 sillas
        } else {
            return (6 * 12) + (2 * 9); // 90 sillas para salas 1 y 2
        }
    }

    //Metodo para calcular las sillas disponibles despues de una compra
    private void configurarListenerDisponibles() {
        cbFuncion.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, nuevaFuncion) -> {
            if (nuevaFuncion != null) {
                // Calcula el total de sillas de la sala en la función
                int totalSillas = calcularTotalSillas(nuevaFuncion.getSala());
                // Obtiene las sillas ocupadas para esa función
                int ocupadas = cineData.getSillasOcupadas(nuevaFuncion).size();
                int disponibles = totalSillas - ocupadas;
                lblSillasDisponibles.setText(String.valueOf(disponibles));
            }
        });
    }

    private void configurarComboboxes() {
        // Configurar ComboBox de salas
        cbSala.getItems().addAll("Sala 1", "Sala 2", "Sala 3");

        // Configurar ComboBox de funciones
        cbFuncion.setItems(cineData.getHorarios());

        // Cómo se muestran los elementos en el ComboBox
        cbFuncion.setCellFactory(param -> new ListCell<HorarioSala>() {
            @Override
            protected void updateItem(HorarioSala horario, boolean empty) {
                super.updateItem(horario, empty);
                if (empty || horario == null) {
                    setText("");
                } else {
                    setText(horario.getPelicula().getNombre()
                            + " (" + horario.getPelicula().getTipo() + ") - "
                            + horario.getPelicula().getIdioma() + " " + horario.getFranjaHoraria());
                }
            }
        });

        // Personalizar el texto seleccionado
        cbFuncion.setButtonCell(new ListCell<HorarioSala>() {
            @Override
            protected void updateItem(HorarioSala horario, boolean empty) {
                super.updateItem(horario, empty);
                if (empty || horario == null) {
                    setText("");
                } else {
                    setText(horario.getPelicula().getNombre()
                            + " (" + horario.getPelicula().getTipo() + ") - "
                            + horario.getPelicula().getIdioma() + " " + horario.getFranjaHoraria());
                }
            }
        });

        // Filtrar funciones por sala seleccionada
        cbSala.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, nuevaSala) -> {
            if (nuevaSala != null) {
                // Filtrar las funciones según la sala seleccionada
                cbFuncion.setItems(cineData.getHorarios().filtered(h -> h.getSala().equals(nuevaSala)));
                cbFuncion.getSelectionModel().clearSelection();
                // Mostrar el grid correspondiente
                mostrarGridSala(nuevaSala);
                // Recrear el grid para la sala seleccionada
                if (gridActual != null) {
                    crearBotonesParaSala(gridActual, 6, 12, nuevaSala);
                }
                // Limpiar la lista de sillas seleccionadas
                sillasSeleccionadas.clear();
            }
        });


        cbFuncion.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, nuevaFuncion) -> {
            if (nuevaFuncion != null && gridActual != null) {
                // Al cambiar la función, se recrea el grid para actualizar las sillas ocupadas
                crearBotonesParaSala(gridActual, 6, 12, nuevaFuncion.getSala());
                sillasSeleccionadas.clear();
            }
        });

    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
