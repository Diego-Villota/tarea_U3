package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;
import modelo.Equipo;
import modelo.Torneo;
import vista.VistaTorneo;

public class ControladorTorneo {
    private final VistaTorneo vista;
    private Torneo torneo;

    public ControladorTorneo(VistaTorneo vista) {
        this.vista = vista;
    }

    public void iniciar() {
        try {
            boolean continuar = true;
            while (continuar) {
                switch (vista.leerOpcion()) {
                    case "1":
                        crearTorneo();
                        break;
                    case "2":
                        mostrarTorneo();
                        break;
                    case "3":
                        guardarTorneo();
                        break;
                    case "4":
                        cargarTorneo();
                        break;
                    case "0":
                        vista.mostrarMensaje("Hasta pronto.");
                        continuar = false;
                        break;
                    default:
                        vista.mostrarError("Opción no válida.");
                }
            }
        } catch (NoSuchElementException e) {
            vista.mostrarMensaje("Entrada finalizada.");
        }
    }

    private void crearTorneo() {
        List<Equipo> equipos = new ArrayList<>();
        while (equipos.size() < 16) {
            try {
                Equipo equipo = new Equipo(vista.leerNombreEquipo(equipos.size() + 1));
                boolean repetido = false;
                for (Equipo registrado : equipos) {
                    if (registrado.getNombre().equalsIgnoreCase(equipo.getNombre())) {
                        repetido = true;
                        break;
                    }
                }
                if (repetido) {
                    vista.mostrarError("Ya existe un equipo con ese nombre.");
                } else {
                    equipos.add(equipo);
                }
            } catch (IllegalArgumentException e) {
                vista.mostrarError(e.getMessage());
            }
        }

        try {
            Torneo nuevoTorneo = new Torneo(equipos);
            nuevoTorneo.realizarSorteo();
            torneo = nuevoTorneo;
            vista.mostrarMensaje("Torneo creado y sorteado.");
            mostrarTorneo();
        } catch (NumeroEquiposInvalidoException | SorteoInvalidoException
                | IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void mostrarTorneo() {
        if (torneo == null) {
            vista.mostrarError("Primero cree o cargue un torneo.");
            return;
        }
        vista.mostrarTorneo(torneo.getEtapas(), torneo.getCampeon());
    }

    private void guardarTorneo() {
        if (torneo == null) {
            vista.mostrarError("Primero cree o cargue un torneo.");
            return;
        }
        String ruta = vista.leerRutaArchivo();
        if (ruta.isEmpty()) {
            vista.mostrarError("La ruta no puede estar vacía.");
            return;
        }
        try {
            torneo.guardar(ruta);
            vista.mostrarMensaje("Torneo guardado.");
        } catch (IOException | SecurityException e) {
            vista.mostrarError("No se pudo guardar el torneo: " + e.getMessage());
        }
    }

    private void cargarTorneo() {
        String ruta = vista.leerRutaArchivo();
        if (ruta.isEmpty()) {
            vista.mostrarError("La ruta no puede estar vacía.");
            return;
        }
        try {
            Torneo torneoCargado = Torneo.cargar(ruta);
            torneo = torneoCargado;
            vista.mostrarMensaje("Torneo cargado.");
        } catch (IOException | ClassNotFoundException | SecurityException e) {
            vista.mostrarError("No se pudo cargar el torneo: " + e.getMessage());
        }
    }
}
