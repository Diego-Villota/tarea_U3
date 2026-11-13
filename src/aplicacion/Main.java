package aplicacion;

import java.util.Scanner;

import controlador.ControladorTorneo;
import vista.VistaTorneo;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            VistaTorneo vista = new VistaTorneo(scanner);
            ControladorTorneo controlador = new ControladorTorneo(vista);
            controlador.iniciar();
        }
    }
}
