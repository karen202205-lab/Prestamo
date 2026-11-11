package modelo;

public class Laboratorista extends Usuario {
    public Laboratorista(String codigo, String nombre, long deuda) { super(codigo, nombre, deuda); }
    @Override public int cupoMaximo() { return 6; }
    @Override public double factorPlazo() { return 2.0; }
    @Override public boolean tieneAvalMicroscopio() { return true; }
}


