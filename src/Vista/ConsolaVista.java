package vista;

import controlador.Resultado;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsolaVista {
    private final Scanner entrada = new Scanner(System.in);

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

    public void mostrarMensaje(String mensaje) {
        System.out.println("  " + mensaje);
    }

    public String pedirTexto(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return entrada.hasNextLine() ? entrada.nextLine().trim() : "0";
    }

    public LocalDate pedirFecha(String etiqueta) {
        while (true) {
            try {
                return LocalDate.parse(pedirTexto(etiqueta + " (aaaa-mm-dd)"));
            } catch (DateTimeParseException e) {
                mostrarMensaje("Fecha no válida. Use el formato aaaa-mm-dd.");
            }
        }
    }

    public String pedirOpcion() {
        System.out.println("\n1. Procesar las 25 peticiones del mostrador");
        System.out.println("2. Prestar un equipo");
        System.out.println("3. Devolver un equipo");
        System.out.println("4. Reservar un equipo");
        System.out.println("5. Ver deudas y log");
        System.out.println("0. Salir");
        return pedirTexto("Opción");
    }
}