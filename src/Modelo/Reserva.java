package modelo;

import java.time.LocalDate;

public record Reserva(Usuario usuario, Equipo equipo, LocalDate fecha) {
}
