package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Equipo> equipos;
    private final List<List<Partido>> etapas;
    private Equipo campeon;

    public Torneo(List<Equipo> equipos) throws NumeroEquiposInvalidoException {
        if (equipos == null || equipos.size() != 16) {
            throw new NumeroEquiposInvalidoException("El torneo debe comenzar con 16 equipos.");
        }
        for (Equipo equipo : equipos) {
            if (equipo == null) {
                throw new IllegalArgumentException("La lista contiene un equipo vacío.");
            }
        }
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                if (equipos.get(i).getNombre().equalsIgnoreCase(equipos.get(j).getNombre())) {
                    throw new IllegalArgumentException("Hay equipos con el mismo nombre.");
                }
            }
        }
        this.equipos = new ArrayList<>(equipos);
        this.etapas = new ArrayList<>();
    }

    public void realizarSorteo() throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        List<List<Partido>> nuevasEtapas = new ArrayList<>();
        List<Equipo> participantes = new ArrayList<>(equipos);
        Random aleatorio = new Random();
        while (participantes.size() > 1) {
            validarCantidadEquipos(participantes.size());
            Collections.shuffle(participantes, aleatorio);
            List<Partido> partidos = new ArrayList<>();
            crearPartidosRecursivamente(participantes, 0, partidos);
            nuevasEtapas.add(partidos);
            List<Equipo> ganadores = new ArrayList<>();
            for (Partido partido : partidos) {
                Equipo ganador;
                if (aleatorio.nextBoolean()) {
                    ganador = partido.getCompetidor();
                } else {
                    ganador = partido.getOponente();
                }
                ganadores.add(ganador);
            }
            participantes = ganadores;
        }
        etapas.clear();
        etapas.addAll(nuevasEtapas);
        campeon = participantes.get(0);
    }

    private void validarCantidadEquipos(int cantidad) throws NumeroEquiposInvalidoException {
        boolean esPotenciaDeDos = cantidad >= 2 && (cantidad & (cantidad - 1)) == 0;
        if (!esPotenciaDeDos) {
            throw new NumeroEquiposInvalidoException("La cantidad de equipos debe ser una potencia de 2 (16,8,4,2,1).");
        }
    }

    private void crearPartidosRecursivamente(List<Equipo> participantes, int indice, List<Partido> partidos)
            throws SorteoInvalidoException {
        if (indice == participantes.size()) {
            return;
        }
        if (indice + 1 >= participantes.size()) {
            throw new SorteoInvalidoException("Quedó un equipo sin oponente.");
        }
        Equipo competidor = participantes.get(indice);
        Equipo oponente = participantes.get(indice + 1);
        if (competidor == null || oponente == null || competidor == oponente) {
            throw new SorteoInvalidoException("Se encontró un emparejamiento inválido.");
        }
        partidos.add(new Partido(competidor, oponente));
        crearPartidosRecursivamente(participantes, indice + 2, partidos);
    }

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }
    public List<List<Partido>> getEtapas() {
        List<List<Partido>> copia = new ArrayList<>();
        for (List<Partido> etapa : etapas) {
            copia.add(Collections.unmodifiableList(etapa));
        }
        return Collections.unmodifiableList(copia);
    }
    public Equipo getCampeon() {
        return campeon;
    }
    public void guardar(String ruta) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
            salida.writeObject(this);
        }
    }
    public static Torneo cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {
            Object objeto = entrada.readObject();
            if (!(objeto instanceof Torneo)) {
                throw new IOException("El archivo no contiene un torneo válido.");
            }
            return (Torneo) objeto;
        }
    }
}
