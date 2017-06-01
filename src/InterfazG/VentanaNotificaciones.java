package InterfazG;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumno
 */
public class VentanaNotificaciones {

    public static void ventanaError(String cadena, JFrame padre) {
        JOptionPane.showMessageDialog(padre, cadena, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void ventanaOK(String cadena, JFrame padre) {
        JOptionPane.showMessageDialog(padre, cadena, "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
    }
}
