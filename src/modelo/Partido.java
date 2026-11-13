package modelo;

import java.io.Serializable;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Equipo competidor;
    private final Equipo oponente;

    public Partido(Equipo competidor, Equipo oponente) {
        if (competidor == null || oponente == null) {
            throw new IllegalArgumentException("El partido necesita dos equipos.");
        }
        if (competidor == oponente) {
            throw new IllegalArgumentException("Un equipo no puede jugar contra sí mismo.");
        }
        this.competidor = competidor;
        this.oponente = oponente;
    }

    public Equipo getCompetidor() {
        return competidor;
    }

    public Equipo getOponente() {
        return oponente;
    }
}
