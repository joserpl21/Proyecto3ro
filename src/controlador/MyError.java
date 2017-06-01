package controlador;

/**
 *
 * @author Jhosep Joel Mendoza Lazo
 */
public class MyError extends Exception {

    /**
     * Metodo que recive un valor de tipo cadena descriviendo el error
     *
     * @param mensaje de tipo cadena que recive el mensaje
     */
    public MyError(String mensaje) {
        super(mensaje);
    }
}
