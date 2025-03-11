package model;

import javafx.scene.control.Button;

import java.util.List;

public class CalcularPrecios {

    private static final double PRECIO_GENERAL = 8000;
    private static final double PRECIO_PREFERENCIAL = 12000;
    private static final double PRECIO_3D = 10000;


    public double calcularTotal(List<Button> sillasSeleccionadas) {
        double total = 0;
        for (Button silla : sillasSeleccionadas) {
            String tipo = (String) silla.getUserData();
            if ("general".equals(tipo)) {
                total += PRECIO_GENERAL;
            } else if ("preferencial".equals(tipo)) {
                total += PRECIO_PREFERENCIAL;
            } else if ("3d".equals(tipo)) {
                total += PRECIO_3D;
            }
        }
        return total;
    }

}
