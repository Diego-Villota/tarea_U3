package vista;

import java.util.List;
import java.util.Scanner;

import modelo.Equipo;
import modelo.Partido;

public class VistaTorneo {
    private final Scanner scanner;

    public VistaTorneo(Scanner scanner) {
        this.scanner = scanner;
    }

    public String leerOpcion() {
        System.out.println("\n<--- Torneo --->");
        System.out.println("1. Crear y sortear torneo");
        System.out.println("2. Mostrar torneo");
        System.out.println("3. Guardar torneo");
        System.out.println("4. Cargar torneo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextLine().trim();
    }

    public String leerNombreEquipo(int numero) {
        System.out.print("Nombre del equipo " + numero + ": ");
        return scanner.nextLine();
    }

    public String leerRutaArchivo() {
        System.out.print("Ruta del archivo: ");
        return scanner.nextLine().trim();
    }

    public void mostrarTorneo(List<List<Partido>> etapas, Equipo campeon) {
        if (etapas.isEmpty()) {
            System.out.println("Aún no se ha realizado el sorteo.");
            return;
        }

        String[] nombresEtapas = {"Octavos", "Cuartos", "Semifinales", "Final"};
        for (int i = 0; i < etapas.size(); i++) {
            String nombre = i < nombresEtapas.length ? nombresEtapas[i] : "Etapa " + (i + 1);
            System.out.println("\n" + nombre);
            for (Partido partido : etapas.get(i)) {
                System.out.println(partido.getCompetidor().getNombre()
                        + " vs " + partido.getOponente().getNombre());
            }
        }
        if (campeon != null) {
            System.out.println("\nCampeón: " + campeon.getNombre());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
}
