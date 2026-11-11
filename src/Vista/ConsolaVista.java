package vista;

import controlador.Resultado;
import java.util.List;


public class ConsolaVista {
    public void mostrarPeticion(int n, String descripcion) {
        System.out.println("\n#" + n + " " + descripcion);
    }

    public void mostrarResultado(Resultado r) {
        String marca = r.isExito() ? "  [OK] " : "  [" + r.getCodigo() + "] ";
        System.out.println(marca + r.getMensaje());
    }

    public void mostrarLista(String titulo, List<String> lineas) {
        System.out.println("\n===== " + titulo + " =====");
        for (String l : lineas) System.out.println(l);
    }
}
