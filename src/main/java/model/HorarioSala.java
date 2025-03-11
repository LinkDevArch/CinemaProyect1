package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class HorarioSala {
    private final StringProperty sala = new SimpleStringProperty();
    private final StringProperty franjaHoraria = new SimpleStringProperty();
    private final ObjectProperty<Pelicula> pelicula = new SimpleObjectProperty<>();

    public HorarioSala(String sala, String franjaHoraria, Pelicula pelicula) {
        setSala(sala);
        setFranjaHoraria(franjaHoraria);
        setPelicula(pelicula);
    }

    //Metodos para almacenar y obtener datos de cada funcion en el cine

    // Getters y Setters
    public String getSala() {
        return sala.get();
    }

    public void setSala(String value) {
        sala.set(value);
    }

    public StringProperty salaProperty() {
        return sala;
    }

    public String getFranjaHoraria() {
        return franjaHoraria.get();
    }

    public void setFranjaHoraria(String value) {
        franjaHoraria.set(value);
    }

    public StringProperty franjaHorariaProperty() {
        return franjaHoraria;
    }

    public Pelicula getPelicula() {
        return pelicula.get();
    }

    public void setPelicula(Pelicula value) {
        pelicula.set(value);
    }

    public ObjectProperty<Pelicula> peliculaProperty() {
        return pelicula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioSala that = (HorarioSala) o;
        return Objects.equals(getSala(), that.getSala()) &&
                Objects.equals(getFranjaHoraria(), that.getFranjaHoraria()) &&
                Objects.equals(getPelicula(), that.getPelicula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSala(), getFranjaHoraria(), getPelicula());
    }
}
