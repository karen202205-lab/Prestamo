package Exception;


public class AvalRequeridoException extends PrestamoException {
    public AvalRequeridoException(String mensajeTecnico) {
        super("AV-01", "Este equipo requiere un aval que no tienes registrado.", mensajeTecnico);
    }
}
