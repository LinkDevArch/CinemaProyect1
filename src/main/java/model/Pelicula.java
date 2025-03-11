package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Esta clase se usa para obtener, mostrar y setear los atributos que una pelicula tiene (Nombre, Idioma, Tipo, Duración)

public class Pelicula {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty idioma = new SimpleStringProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty duracion = new SimpleStringProperty();

    public Pelicula(String nombre, String idioma, String tipo, String duracion) {
        setNombre(nombre);
        setIdioma(idioma);
        setTipo(tipo);
        setDuracion(duracion);
    }

    // Métodos para nombre
    public String getNombre()
    {
        return nombre.get();
    }
    public void setNombre(String value)
    {
        nombre.set(value);
    }
    public StringProperty nombreProperty()
    {
        return nombre;
    }

    // Métodos para idioma
    public String getIdioma()
    {
        return idioma.get();
    }
    public void setIdioma(String value)
    {
        idioma.set(value);
    }
    public StringProperty idiomaProperty()
    {
        return idioma;
    }

    // Métodos para tipo
    public String getTipo()
    {
        return tipo.get();
    }
    public void setTipo(String value)
    {
        tipo.set(value);
    }
    public StringProperty tipoProperty()
    {
        return tipo;
    }

    // Métodos para duración
    public String getDuracion()
    {
        return duracion.get();
    }
    public void setDuracion(String value)
    {
        duracion.set(value);
    }
    public StringProperty duracionProperty()
    {
        return duracion;
    }

    @Override
    public String toString() {
        return getNombre(); // Solo muestra el nombre
    }
}
