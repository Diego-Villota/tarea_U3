package excepciones;

public class SorteoInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    public SorteoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
