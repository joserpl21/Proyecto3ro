package Modelo;

import controlador.MyError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jhosep Joel Mendoza Lazo
 */
public class Propietario {

    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String provincia;

    /**
     * Contructor
     *
     * @param dni cadena DNI de propietario
     * @param nombre cadena nombre del propietario
     * @param apellido cadena apellido del propietario
     * @param telefono cadena telefono del propietario
     * @param provincia cadena provincia recibe el nombre de la provincia
     */
    public Propietario(String dni, String nombre, String apellido, String telefono, String provincia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.provincia = provincia;
    }

    public Propietario() {

    }

    /**
     *
     * @param provincia cadena recive el nombre de la provincia
     */
    public Propietario(String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return dni cadena devuelve el DNI del propietario
     */

    public String getDni() {
        return dni;
    }

    /**
     * Setter de la variable DNI de tipo cadena recive el DNI y comprueba que
     * sea correcto
     *
     * @param dni de tipo cadena recive el DNI
     * @throws MyError guarda el error cuando el DNI ingreso es incorrecto
     */

    public void setDni(String dni) throws MyError {
        boolean flag1 = false, flag2;
        Pattern patron = Pattern.compile("\\d{8}?[a-zA-Z]");
        Matcher matcher = patron.matcher(dni);
        while (matcher.find()) {
            flag1 = true;
        }
        if (flag1) {
            flag2 = confirmarDNI(dni);
            if (flag2) {
                this.dni = dni;
            } else {
                throw new MyError("DNI INCORRECTO");
            }
        } else {
            throw new MyError("FORMATO INCORRECTO DE DNI EJ: 78945612Y");
        }
    }

    /**
     *
     * @return nombre de tipo cadena devuelve el nombre
     */

    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre de tipo cadena recive el nombre y comprueba que este
     * correcto
     * @throws MyError guarda el error del nombre
     */

    public void setNombre(String nombre) throws MyError {
        boolean ban = false;
        Pattern patron = Pattern.compile("[A-Za-z]{1,20}");
        Matcher matcher = patron.matcher(nombre);
        while (matcher.find()) {
            ban = true;
        }
        if (ban) {
            this.nombre = nombre;
        } else {
            throw new MyError("Error al introducir el nombre  ");
        }
    }

    /**
     *
     * @return apellido de tipo cadena recive el apellido del propietario
     */

    public String getApellido() {
        return apellido;
    }

    /**
     * Setter de la variable apellido recive el apellido y comprueba que sea
     * correcto
     *
     * @param apellido de tipo cadena recive el apellido
     * @throws MyError guarda el error cuando el apellido es incorrecto
     */

    public void setApellido(String apellido) throws MyError {
        boolean ban = false;
        Pattern patron = Pattern.compile("[A-Za-z]{1,30}");
        Matcher matcher = patron.matcher(apellido);
        while (matcher.find()) {
            ban = true;
        }
        if (ban) {
            this.apellido = apellido;
        } else {
            throw new MyError("Error al introducir el apellido");
        }
        this.apellido = apellido;
    }

    /**
     *
     * @return telefono de tipo cadena devuelve el telefono
     */

    public String getTelefono() {
        return telefono;
    }

    /**
     * Setter de la variable telefono recive el telefono del propietario y
     * comprueba que sea correcto
     *
     * @param telefono de tipo cadena recive el telefono del propieario
     * @throws MyError si el telefono es incorrecto guardara el error
     */

    public void setTelefono(String telefono) throws MyError {
        boolean ban = false;
        //TENGO QUE CONTROLAR QUE SI EL USUARIO METE LETRAS NO LE FUNCIONE 
        Pattern patron = Pattern.compile("\\d{9,11}");
        Matcher matcher = patron.matcher(telefono);
        while (matcher.find()) {
            ban = true;
        }
        if (ban) {
            this.telefono = telefono;
        } else {
            throw new MyError("Telefono mal ingresado");
        }
    }

    /**
     *
     * @return provincia de tipo cadena revuelve el nombre de la provincia
     */

    public String getProvincia() {
        return provincia;
    }

    /**
     * Setter de la variable provincia recive el nombre de la provincia y
     * comprueba que este correcto
     *
     * @param provincia de tipo cadena recive el nombre de la provincia
     * @throws MyError guarda el error si no cumple con el patron
     */

    public void setProvincia(String provincia) throws MyError {
        boolean ban = false;
        Pattern patron = Pattern.compile("[A-Za-z]{1,20}");
        Matcher matcher = patron.matcher(provincia);
        while (matcher.find()) {
            ban = true;
        }
        if (ban) {
            this.provincia = provincia;
        } else {
            throw new MyError("Error al introducir la provincia");
        }

    }

    /**
     *
     * @return vactor array de cadenas revuelve todos los datos del propietario
     */

    public String[] toArray() {
        String[] vector = new String[5];
        vector[0] = dni;
        vector[1] = nombre;
        vector[2] = apellido;
        vector[3] = telefono;
        vector[4] = provincia;
        return vector;
    }

    /**
     * Metodo booleano que recive la cadena de DNI y comprueba que la letra sea
     * correcta
     *
     * @param dni de tipo cadena recive el DNI
     * @return boolean dependiendo de que el DNI sea o no correcto
     * @throws MyError guarda error
     */

    private boolean confirmarDNI(String dni) throws MyError {
        try {
            String cadena = "TRWAGMYFPDXBNJZSQVHLCKE";
            int numero, lon;
            char letraD;
            lon = dni.length();
            numero = Integer.parseInt(dni.substring(0, lon - 1));
            letraD = dni.charAt(lon - 1);
            System.out.println("Letra " + cadena.charAt(numero % 23));
            return cadena.charAt(numero % 23) == letraD;
        } catch (NumberFormatException e) {
            throw new MyError("DNI MAL INGRESADO");
        }
    }

}
