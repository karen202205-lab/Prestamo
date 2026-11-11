package Exception;


public class EquipoNoDisponibleException extends PrestamoException {

    private static final long serialVersionUID = 1L;

    public static final String CODIGO_PRESTADO = "EN-01";
    public static final String CODIGO_INCOMPLETO = "EN-02";

    public EquipoNoDisponibleException(String codigo, String mensajeUsuario, String mensajeTecnico) {
        super(codigo, mensajeUsuario, mensajeTecnico);
    }

    public static EquipoNoDisponibleException yaPrestado(String codigoEquipo) {
        return new EquipoNoDisponibleException(CODIGO_PRESTADO,
                "El equipo " + codigoEquipo + " está prestado en este momento. "
                        + "Puede reservarlo para dejar constancia de su interés.",
                "Equipo " + codigoEquipo + " con estado prestado=true");
    }

    public static EquipoNoDisponibleException kitIncompleto(String codigoEquipo, int piezas, int total) {
        return new EquipoNoDisponibleException(CODIGO_INCOMPLETO,
                "El kit " + codigoEquipo + " está incompleto y no se puede prestar por ahora. "
                        + "Puede reservarlo mientras llega la pieza faltante.",
                "Kit " + codigoEquipo + " incompleto: piezas=" + piezas + "/" + total);
    }
}