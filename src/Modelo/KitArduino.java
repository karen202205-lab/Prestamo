package modelo;

import Exception.EquipoNoDisponibleException;

public class KitArduino extends Equipo {
    public static final int PIEZAS_COMPLETO = 6;
    private final int piezas;

    public KitArduino(String codigo, String descripcion, int piezas) {
        super(codigo, descripcion);
        this.piezas = piezas;
    }

    @Override public int plazoBase() { return 7; }
    @Override public int multaDiaria() { return 1500; }

   
    @Override
    public void verificarDisponible() throws EquipoNoDisponibleException {
        super.verificarDisponible();
        if (piezas < PIEZAS_COMPLETO) {
            throw new EquipoNoDisponibleException("EN-02",
                    "El kit está incompleto y no puede prestarse por ahora.",
                    "Kit " + getCodigo() + " tiene " + piezas + " de " + PIEZAS_COMPLETO + " piezas");
        }
    }
}
