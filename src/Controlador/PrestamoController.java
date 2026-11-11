package controlador;

import Exception.PrestamoException;
import Modelo.RepositorioPrestamos;
import modelo.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PrestamoController {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat PESOS = NumberFormat.getIntegerInstance(Locale.forLanguageTag("es-CO"));
    private static final String MSG_INESPERADO = "No fue posible completar la operación. Intente de nuevo.";

    private final Map<String, Equipo> equipos = new LinkedHashMap<>();
    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();
    private final RepositorioPrestamos repositorio = new RepositorioPrestamos();
    private final List<Reserva> reservas = new ArrayList<>();
    private final List<String> log = new ArrayList<>();

    public PrestamoController() { cargarDatosIniciales(); }

   

    public Resultado prestar(String codigoUsuario, String codigoEquipo, LocalDate fecha) {
        try {
            Usuario u = buscarUsuario(codigoUsuario);
            Equipo e = buscarEquipo(codigoEquipo);

           
            u.verificarSinMulta();
            e.verificarDisponible();
            e.verificarAval(u);
            u.verificarCupo();

            LocalDate limite = fecha.plusDays(e.calcularPlazo(u));
            Prestamo p = new Prestamo(u, e, fecha, limite);

            e.setPrestado(true);
            try {
                repositorio.guardar(p);
                u.tomarEquipo();
            } catch (Exception falla) {
                e.setPrestado(false); 
                throw falla;
            }
            return Resultado.ok("Préstamo registrado: " + e.getCodigo() + " para " + u.getNombre()
                    + ". Fecha límite: " + limite.format(FMT) + ".");
        } catch (PrestamoException ex) {
            return manejarEsperado(fecha, ex);
        } catch (Exception ex) {
            return manejarInesperado(fecha, ex);
        }
    }

    public Resultado devolver(String codigoEquipo, LocalDate fecha) {
        try {
            Prestamo p = repositorio.buscarActivo(codigoEquipo);
            if (p == null) {
                throw new IllegalStateException("El equipo " + codigoEquipo + " no tiene un préstamo activo");
            }
            Equipo e = p.getEquipo();
            Usuario u = p.getUsuario();

            long diasRetraso = Math.max(0, ChronoUnit.DAYS.between(p.getFechaLimite(), fecha));
            long multa = e.calcularMulta(diasRetraso);

            e.setPrestado(false);
            u.liberarEquipo();
            u.sumarDeuda(multa);
            repositorio.eliminar(p);

            String msg = "Devolución registrada: " + e.getCodigo() + ".";
            if (multa > 0) {
                msg += " Retraso de " + diasRetraso + " día(s). Multa: " + pesos(multa)
                        + ". Deuda total: " + pesos(u.getDeuda()) + ".";
            }
            return Resultado.ok(msg);
        } catch (Exception ex) {
            return manejarInesperado(fecha, ex);
        }
    }

    public Resultado reservar(String codigoUsuario, String codigoEquipo, LocalDate fecha) {
        try {
            Usuario u = buscarUsuario(codigoUsuario);
            Equipo e = buscarEquipo(codigoEquipo);
            u.verificarSinMulta();                    
            reservas.add(new Reserva(u, e, fecha));   
            return Resultado.ok("Reserva registrada: " + e.getCodigo() + " para " + u.getNombre() + ".");
        } catch (PrestamoException ex) {
            return manejarEsperado(fecha, ex);
        } catch (Exception ex) {
            return manejarInesperado(fecha, ex);
        }
    }



    public void simularFalloGuardado() { repositorio.simularFalloEnProximoGuardado(); }

    public List<String> consultarDeudas() {
        List<String> lineas = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            lineas.add(u.getNombre() + " (" + u.getCodigo() + "): " + pesos(u.getDeuda()));
        }
        return lineas;
    }

    public List<String> consultarLog() { return Collections.unmodifiableList(log); }


    private Resultado manejarEsperado(LocalDate fecha, PrestamoException ex) {
        registrar(fecha, ex.getCodigo(), ex.getMensajeTecnico());
        return Resultado.error(ex.getCodigo(), ex.getMensajeUsuario());
    }


    private Resultado manejarInesperado(LocalDate fecha, Exception ex) {
        registrar(fecha, "SYS-00", ex.getClass().getSimpleName() + ": " + ex.getMessage());
        return Resultado.error("SYS-00", MSG_INESPERADO);
    }

    private void registrar(LocalDate fecha, String codigo, String tecnico) {
        log.add(fecha.format(FMT) + " | " + codigo + " | " + tecnico);
    }

    private Usuario buscarUsuario(String codigo) {
        Usuario u = usuarios.get(codigo);
        if (u == null) throw new IllegalArgumentException("Usuario inexistente: " + codigo);
        return u;
    }

    private Equipo buscarEquipo(String codigo) {
        Equipo e = equipos.get(codigo);
        if (e == null) throw new IllegalArgumentException("Equipo inexistente: " + codigo);
        return e;
    }

    private static String pesos(long valor) { return "$" + PESOS.format(valor); }

    private void agregar(Equipo e) { equipos.put(e.getCodigo(), e); }
    private void agregar(Usuario u) { usuarios.put(u.getCodigo(), u); }

   
    private void cargarDatosIniciales() {
        agregar(new Portatil("P-01", "Lenovo ThinkPad E14"));
        agregar(new Portatil("P-02", "Lenovo ThinkPad E14"));
        agregar(new Portatil("P-03", "HP ProBook 440"));
        agregar(new Microscopio("M-01", "Óptico binocular"));
        agregar(new Microscopio("M-02", "Óptico binocular"));
        agregar(new KitArduino("K-01", "Arduino UNO, kit básico", 6));
        agregar(new KitArduino("K-02", "Arduino UNO, kit básico", 5));
        agregar(new KitArduino("K-03", "Arduino UNO, kit básico", 6));

        agregar(new Estudiante("E-101", "Camila Rojas", 0));
        agregar(new Estudiante("E-102", "Andrés Pulido", 0));
        agregar(new Estudiante("E-103", "Valentina Mora", 14000));
        agregar(new Docente("D-201", "Roberto Ibáñez", 0, true));
        agregar(new Docente("D-202", "Marta Pardo", 0, false));
        agregar(new Laboratorista("L-301", "Jhon Fredy Salcedo", 0));
    }
}