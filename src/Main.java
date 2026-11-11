import controlador.PrestamoController;
import vista.ConsolaVista;

import java.time.LocalDate;

public class Main {
    static final ConsolaVista vista = new ConsolaVista();
    static int n = 0;

    static LocalDate d(int dia) { return LocalDate.of(2026, 3, dia); }

    static void paso(String descripcion, controlador.Resultado r) {
        vista.mostrarPeticion(++n, descripcion);
        vista.mostrarResultado(r);
    }

    public static void main(String[] args) {
        PrestamoController c = new PrestamoController();

        paso("2 mar - Camila (E-101) pide P-01", c.prestar("E-101", "P-01", d(2)));
        paso("2 mar - Camila (E-101) pide M-01", c.prestar("E-101", "M-01", d(2)));
        paso("2 mar - Roberto (D-201) pide M-01", c.prestar("D-201", "M-01", d(2)));
        paso("2 mar - Marta (D-202) pide M-02", c.prestar("D-202", "M-02", d(2)));
        paso("2 mar - Valentina (E-103) pide P-02", c.prestar("E-103", "P-02", d(2)));
        paso("2 mar - Andrés (E-102) pide K-02", c.prestar("E-102", "K-02", d(2)));
        paso("2 mar - Andrés (E-102) reserva K-02", c.reservar("E-102", "K-02", d(2)));
        paso("2 mar - Valentina (E-103) reserva K-02", c.reservar("E-103", "K-02", d(2)));
        paso("2 mar - Andrés (E-102) pide P-01", c.prestar("E-102", "P-01", d(2)));
        paso("2 mar - Andrés (E-102) pide P-02", c.prestar("E-102", "P-02", d(2)));
        paso("2 mar - Andrés (E-102) pide P-03", c.prestar("E-102", "P-03", d(2)));
        paso("2 mar - Andrés (E-102) pide K-01", c.prestar("E-102", "K-01", d(2)));
        paso("2 mar - Jhon (L-301) pide M-02", c.prestar("L-301", "M-02", d(2)));
        paso("2 mar - Camila (E-101) reserva M-02", c.reservar("E-101", "M-02", d(2)));
        paso("2 mar - Marta (D-202) pide K-01", c.prestar("D-202", "K-01", d(2)));

        c.simularFalloGuardado(); 
        paso("3 mar - Camila (E-101) pide K-03 (falla el guardado)", c.prestar("E-101", "K-03", d(3)));
        paso("3 mar - Camila (E-101) vuelve a pedir K-03", c.prestar("E-101", "K-03", d(3)));

        paso("6 mar - Jhon (L-301) devuelve M-02", c.devolver("M-02", d(6)));
        paso("7 mar - Camila (E-101) devuelve P-01", c.devolver("P-01", d(7)));
        paso("7 mar - Andrés (E-102) devuelve P-02", c.devolver("P-02", d(7)));
        paso("9 mar - Andrés (E-102) devuelve P-03", c.devolver("P-03", d(9)));
        paso("9 mar - Roberto (D-201) devuelve M-01", c.devolver("M-01", d(9)));
        paso("9 mar - Marta (D-202) devuelve K-01", c.devolver("K-01", d(9)));
        paso("9 mar - Andrés (E-102) pide P-01", c.prestar("E-102", "P-01", d(9)));
        paso("9 mar - Roberto (D-201) pide K-02", c.prestar("D-201", "K-02", d(9)));

        vista.mostrarLista("DEUDA POR PERSONA", c.consultarDeudas());
        vista.mostrarLista("LOG", c.consultarLog());
    }
}