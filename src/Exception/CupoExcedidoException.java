package Exception;


public class CupoExcedidoException extends PrestamoException {
    public CupoExcedidoException(String mensajeTecnico) {
        super("CE-01", "Ya alcanzaste el número máximo de equipos que puedes tener prestados.", mensajeTecnico);
    }
}
