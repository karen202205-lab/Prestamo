package controlador;


public class Resultado {
    private final boolean exito;
    private final String codigo;
    private final String mensaje;

    private Resultado(boolean exito, String codigo, String mensaje) {
        this.exito = exito;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public static Resultado ok(String mensaje) { return new Resultado(true, "OK", mensaje); }
    public static Resultado error(String codigo, String mensaje) { return new Resultado(false, codigo, mensaje); }

    public boolean isExito() { return exito; }
    public String getCodigo() { return codigo; }
    public String getMensaje() { return mensaje; }
}