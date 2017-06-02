package InterfazG;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhosep Joel Mendoza Lazo
 */
public class VentanaNotificaciones {
    /**
     * Metodo que recive un mensaje y un JFrame este sera un mensaje de error
     * @param cadena de tipo cadena almacena el mensaje que se dara en el JFrame
     * @param padre recive el JFrame de la ventana que lo llama para poder sacar el mensaje sobre esa misma.
     */

    public static void ventanaError(String cadena, JFrame padre) {
        JOptionPane.showMessageDialog(padre, cadena, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    /** 
     * Metodo que recive un mensaje y un JFrame este sera un mensaje de informacion o de algo correcto
     * @param cadena de tipo cadena almacenara el mensaje 
     * @param padre recive el JFrame de la ventana que lo llama y lanza el mensaje sobre ella misma.
     */

    public static void ventanaOK(String cadena, JFrame padre) {
        JOptionPane.showMessageDialog(padre, cadena, "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
    }
}
