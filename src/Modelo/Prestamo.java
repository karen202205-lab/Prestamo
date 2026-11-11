package modelo;

import java.time.LocalDate;

public class Prestamo {
    private final Usuario usuario;
    private final Equipo equipo;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaLimite;

    public Prestamo(Usuario usuario, Equipo equipo, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.usuario = usuario;
        this.equipo = equipo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
    }

    public Usuario getUsuario() { return usuario; }
    public Equipo getEquipo() { return equipo; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaLimite() { return fechaLimite; }
}