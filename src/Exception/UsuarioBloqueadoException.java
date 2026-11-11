package Exception;


public class UsuarioBloqueadoException extends PrestamoException {
    public UsuarioBloqueadoException(String mensajeTecnico) {
        super("UB-01", "Tienes una multa pendiente. Debes pagarla en caja para pedir o reservar equipos.", mensajeTecnico);
    }
}
