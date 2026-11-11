package Exception;


public class PrestamoException extends Exception {
    private final String codigo;
    private final String mensajeUsuario;
    private final String mensajeTecnico;

    public PrestamoException(String codigo, String mensajeUsuario, String mensajeTecnico) {
        super(mensajeTecnico);
        this.codigo = codigo;
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeTecnico = mensajeTecnico;
    }

    public String getCodigo() { return codigo; }
    public String getMensajeUsuario() { return mensajeUsuario; }
    public String getMensajeTecnico() { return mensajeTecnico; }
}
