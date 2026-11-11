package modelo;

public class Estudiante extends Usuario {
    public Estudiante(String codigo, String nombre, long deuda) { super(codigo, nombre, deuda); }
    @Override public int cupoMaximo() { return 2; }
    @Override public double factorPlazo() { return 1.0; }
    @Override public boolean tieneAvalMicroscopio() { return false; }
}
