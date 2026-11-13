package excepciones;

public class NumeroEquiposInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    public NumeroEquiposInvalidoException(String mensaje) {
        super(mensaje);
    }
}
