package modelo;

import Exception.CupoExcedidoException;
import Exception.UsuarioBloqueadoException;

public abstract class Usuario {
    private final String codigo;
    private final String nombre;
    private long deuda;
    private int equiposEnPoder;

    protected Usuario(String codigo, String nombre, long deudaInicial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.deuda = deudaInicial;
    }

    public abstract int cupoMaximo();               
    public abstract double factorPlazo();           
    public abstract boolean tieneAvalMicroscopio(); 
   
    public void verificarSinMulta() throws UsuarioBloqueadoException {
        if (deuda > 0) {
            throw new UsuarioBloqueadoException("Usuario " + codigo + " con deuda pendiente de $" + deuda);
        }
    }

    public void verificarCupo() throws CupoExcedidoException {
        if (equiposEnPoder >= cupoMaximo()) {
            throw new CupoExcedidoException("Usuario " + codigo + " tiene " + equiposEnPoder
                    + " equipos y su cupo es " + cupoMaximo());
        }
    }

    public void tomarEquipo() { equiposEnPoder++; }
    public void liberarEquipo() { equiposEnPoder--; }
    public void sumarDeuda(long valor) { deuda += valor; }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public long getDeuda() { return deuda; }
}
