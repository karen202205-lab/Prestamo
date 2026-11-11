package modelo;

public class Portatil extends Equipo {
    public Portatil(String codigo, String descripcion) { super(codigo, descripcion); }
    @Override public int plazoBase() { return 5; }
    @Override public int multaDiaria() { return 2000; }
}
