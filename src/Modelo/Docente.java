package modelo;

public class Docente extends Usuario {
    private final boolean aval;
    public Docente(String codigo, String nombre, long deuda, boolean aval) {
        super(codigo, nombre, deuda);
        this.aval = aval;
    }
    @Override public int cupoMaximo() { return 4; }
    @Override public double factorPlazo() { return 1.5; } 
    @Override public boolean tieneAvalMicroscopio() { return aval; }
}
