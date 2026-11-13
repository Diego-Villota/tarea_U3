package modelo;

import java.io.Serializable;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nombre;

    public Equipo(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío.");
        }
        this.nombre = nombre.strip();
    }

    public String getNombre() {
        return nombre;
    }
}
