package Modelo;

import controlador.MyError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jhosep Joel Mendoza Lazo
 */
public class Vehículo {

    private String matricula;
    private String modelo;
    private String anio;
    private String propietario;
    private String nombre;

    /**
     * Constructor para guadar los datos del vehiculo
     *
     * @param matricula de tipo cadena recibe el nombre de la matricula
     * @param modelo de tipo cadena recibe el modelo del vehiculo
     * @param anio de tipo cadena recibe el año de la fecha
     * @param propietario de tipo cadena recibe el DNI del propietario
     */
    public Vehículo(String matricula, String modelo, String anio, String propietario) {

        this.matricula = matricula;
        this.modelo = modelo;
        this.anio = anio;
        this.propietario = propietario;

    }

    public Vehículo() {

    }

    /**
     * Constructor de vehiculo que recibe todos los datos del vehiculo y tambien
     * el nombre de su propietario
     *
     * @param matricula de tipo cadena recibe el nombre de la matricula
     * @param modelo de tipo cadena recibe el modelo del vehiculo
     * @param anio de tipo cadena recibe el año de la fecha
     * @param propietario de tipo cadena recibe el DNI del propietario
     * @param nombre de tipo cadena recibe el nombre del propietario
     */
    public Vehículo(String matricula, String modelo, String anio, String propietario, String nombre) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.anio = anio;
        this.propietario = propietario;
        this.nombre = nombre;
    }

    /**
     *
     * @return matricula de tipo cadena devuelve la matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Setter de la matricula que recibe una cadena como tipo de variable de
     * entrada y mediante el patron comprueba que sea un DNI correcto
     *
     * @param matricula cadena recibe el nombre del propietario
     * @throws MyError guarda el error de la matricula
     */
    public void setMatricula(String matricula) throws MyError {
        int lon = matricula.length();
        boolean flag = false;
        if (lon == 8) {
            Pattern patron = Pattern.compile("[0-9]{4}-[a-zA-Z]{3}");
            Matcher matche = patron.matcher(matricula);
            while (matche.find()) {
                flag = true;
            }
            if (flag) {
                this.matricula = matricula;
            } else {
                throw new MyError("MAL FORMATO DE LA MATRICULA Ej: 4567-ERT");
            }
        } else {
            throw new MyError("La matricula tiene que ser de 8 carácteres");
        }
    }

    /**
     *
     * @return modelo de tipo cadena devuelve el modelo del vehiculo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Setter de la varible modelo recibe y comprueva que el modelo este
     * correcto
     *
     * @param modelo de tipo cadena recibe el modelo
     * @throws MyError guarda el error al no cumplir el rango del modelo
     */
    public void setModelo(String modelo) throws MyError {
        int lon = modelo.length();
        if (lon == 0) {
            throw new MyError("El modelo no puede ser nulo");
        }
        if (lon > 25) {
            throw new MyError("El modelo no puede tener mas de 25 carácteres");
        }
        this.modelo = modelo;
    }

    /**
     *
     * @return anio de tipo cadena regresa el año del vehiculo
     */
    public String getAnio() {

        return anio;
    }

    /**
     * Setter de la variable año que recibe una cadena y comprueba que este
     * correcto
     *
     * @param anio de tipo cadena recibe el año
     * @throws MyError guarda el error del año
     */
    public void setAnio(String anio) throws MyError {
        boolean ban2 = false;
        Pattern patron = Pattern.compile("\\d{4,11}");
        Matcher matcher = patron.matcher(anio);
        while (matcher.find()) {
            ban2 = true;
        }
        if (ban2) {
            this.anio = anio;
        } else {
            throw new MyError("AÑO MAL INGRESADO");
        }
    }

    /**
     *
     * @return propietario de tipo cadena devuelve el DNI del propietario
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * Setter de propietario comprueba que el DNI del propietario sea correcto
     *
     * @param propietario de tipo cadena recibe el DNI del propietario
     * @throws MyError guarda el error de la cadena propietario
     */
    public void setPropietario(String propietario) throws MyError {
        int lon = propietario.length();
        if (lon == 9) {
            this.propietario = propietario;
        } else {
            throw new MyError("Solo puede ser de 9 carácteres");
        }
    }

    /**
     *
     * @return vector array de cadenas devuelve todos los datos
     */
    public String[] toArray() {
        String[] vector = new String[5];
        vector[0] = matricula;
        vector[1] = modelo;
        vector[2] = anio;
        vector[3] = propietario;
        vector[4] = nombre;
        return vector;
    }

}
