package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RepositorioPrestamos {
    private final List<Prestamo> activos = new ArrayList<>();
    private boolean fallaSimulada;

    public void simularFalloEnProximoGuardado() { fallaSimulada = true; }

    public void guardar(Prestamo p) throws IOException {
        if (fallaSimulada) {
            fallaSimulada = false; 
            throw new IOException("Se cayó la conexión con el servidor de archivos al guardar el préstamo de "
                    + p.getEquipo().getCodigo());
        }
        activos.add(p);
    }

    public Prestamo buscarActivo(String codigoEquipo) {
        for (Prestamo p : activos) {
            if (p.getEquipo().getCodigo().equals(codigoEquipo)) return p;
        }
        return null;
    }

    public void eliminar(Prestamo p) { activos.remove(p); }
}
