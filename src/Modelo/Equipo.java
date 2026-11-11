package modelo;

import Exception.AvalRequeridoException;
import Exception.EquipoNoDisponibleException;

public abstract class Equipo {
    private final String codigo;
    private final String descripcion;
    private boolean prestado;

    protected Equipo(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

   
    public abstract int plazoBase();
    public abstract int multaDiaria();

  
    public int calcularPlazo(Usuario usuario) {
        return (int) Math.floor(plazoBase() * usuario.factorPlazo());
    }

   
    public long calcularMulta(long diasRetraso) {
        return diasRetraso * multaDiaria();
    }

    
    public void verificarDisponible() throws EquipoNoDisponibleException {
        if (prestado) {
            throw new EquipoNoDisponibleException("EN-01",
                    "El equipo ya está prestado a otra persona.",
                    "Equipo " + codigo + " ya figura como prestado");
        }
    }

   
    public void verificarAval(Usuario usuario) throws AvalRequeridoException { }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public boolean isPrestado() { return prestado; }
    public void setPrestado(boolean prestado) { this.prestado = prestado; }
}