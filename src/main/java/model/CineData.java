package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CineData {
    private static final CineData instance = new CineData();
    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();
    private final ObservableList<HorarioSala> horarios = FXCollections.observableArrayList();

    private final Map<HorarioSala, List<String>> sillasOcupadas = new HashMap<>();

    public static CineData getInstance() {
        return instance;
    }

    //Aquí se almacenan las peliculas agregadas
    public ObservableList<Pelicula> getPeliculas() {
        return peliculas;
    }

    //Aqui se almacena la funcion, junto con su pelicula, sala y franja horaria
    public ObservableList<HorarioSala> getHorarios() {
        return horarios;
    }

    //Se usa para almacenar las sillas que han sido ocupadas
    public void agregarSillasOcupadas(HorarioSala funcion, List<String> sillas) {
        sillasOcupadas.computeIfAbsent(funcion, k -> new ArrayList<>()).addAll(sillas);
    }

    public List<String> getSillasOcupadas(HorarioSala funcion) {
        return sillasOcupadas.getOrDefault(funcion, new ArrayList<>());
    }

}
