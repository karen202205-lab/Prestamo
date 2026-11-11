package modelo;

import Exception.AvalRequeridoException;

public class Microscopio extends Equipo {
    public Microscopio(String codigo, String descripcion) { super(codigo, descripcion); }
    @Override public int plazoBase() { return 2; }
    @Override public int multaDiaria() { return 10000; } 

   
    @Override
    public void verificarAval(Usuario usuario) throws AvalRequeridoException {
        if (!usuario.tieneAvalMicroscopio()) {
            throw new AvalRequeridoException("Usuario " + usuario.getCodigo()
                    + " sin aval para el microscopio " + getCodigo());
        }
    }
}
