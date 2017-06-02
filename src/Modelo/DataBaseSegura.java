package Modelo;

import controlador.MyError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseSegura {

    public static Connection conexion;

    /**
     * Metodo que inicializa a la variable conexion
     *
     * @param user recive el nombre del usuario para la conexion a la base de
     * datos
     * @param pass recive la contraseña para conextar con la base de datos
     * @throws SQLException guarda todos los errores que surjan al hacer la
     * conexion
     */
    private static void conectar(String user, String pass) throws SQLException {
        String db = "vehiculos";
        String servidorMysql = "jdbc:mysql://localhost/";
        conexion = DriverManager.getConnection(servidorMysql + db, user, pass);
    }

    /**
     * Metodo que carga el driver de la libreria mysql
     *
     * @throws ClassNotFoundException
     */
    private static void cargarDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * Metodo que cierra la conexion con la base de datos
     *
     * @throws MyError guarda el error si surgiera
     */
    public static void cerrarConexion() throws MyError {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            throw new MyError("Imposible cerrar la base de datos");
        }
    }

    /**
     * Metodo que recive dos cadenas y carga el Driver para luego hacer la
     * conexcion si surgiera algun error seria controlado
     *
     * @param user de tipo cadena necesaria enviarla al metodo conectar
     * @param pass de tipo cadena necesaria enviarla al metodo conectar
     * @return boolean dependiendo si se logra conectar devolvera true o false
     * @throws MyError guarda el error que surja
     */
    public static boolean abrirConexion(String user, String pass) throws MyError {
        final int ERROR_NO_EXISTE_DATABASE = 1049;
        final int ERROR_USUARIO_INCORRECTO = 1045;
        try {
            cargarDriver();
            conectar(user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            if (e.getErrorCode() == ERROR_USUARIO_INCORRECTO) {
                throw new MyError("USUARIO O PASSWORD INCORRECTO");
            }
            if (e.getErrorCode() == ERROR_NO_EXISTE_DATABASE) {

                throw new MyError("NO EXISTE LA BASE DE DATOS");
            } else {
                throw new MyError("IMPOSIBLE ABRIR LA CONEXCION " + e.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            throw new MyError("No se ha creado la libreria " + ex.getMessage());
        }
    }

    /**
     * Metodo que devuelve un objeto de la clase propietario buscandola en la
     * base de datos con la cadena de ingreso que sera el DNI del propietario
     *
     * @param dni de tipo cadena recive el DNI del propietario para luego
     * buscarlo en la base de datos
     * @return propietario si encuentra algun propietario en la base de datos
     * con el DNI
     * @throws MyError si habria algun error al sacar al propietario guardaria
     * el error
     */
    public static Propietario buscaPropietario(String dni) throws MyError {
        String cadena = "SELECT * FROM propietario WHERE dni=?;";
        PreparedStatement pS = null;
        ResultSet rS = null;
        Propietario p = null;
        try {
            System.out.println(cadena);
            pS = conexion.prepareCall(cadena);
            pS.setString(1, dni);
            rS = pS.executeQuery();
            if (rS.next()) {
                p = new Propietario(dni, rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
            }
            return p;
        } catch (SQLException ex) {
            throw new MyError("Error al leer a los propietarios ");
        } finally {
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }

        }
    }

    /**
     * Metodo que devuelve un ArrayList de vehiculos que se encuentra en la base
     * de datos
     *
     * @return ArrayList devuelve la lista de propietarios que hay en la base de
     * datos
     * @throws MyError guarda el error que lanzaria la base de datos
     */
    public static ArrayList<Vehículo> consultaVehiculos() throws MyError {
        ArrayList<Vehículo> listaVehiculos = new ArrayList<>();
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sentencia = "SELECT v.MATRICULA, v.MODELO , v.ANIO , v.propietario ,p.nombre FROM vehiculo v JOIN PROPIETARIO p on v.PROPIETARIO=p.DNI ORDER BY v.ANIO";
        //SELECT v.MODELO ,v.MODELO , v.ANIO , v.PROPIETARIO , p.NOMBRE FROM vehiculo v JOIN PROPIETARIO p on v.PROPIETARIO=p.DNI ORDER BY v.ANIO;
        try {
            pS = conexion.prepareStatement(sentencia);
            rS = pS.executeQuery();
            while (rS.next()) {
                Vehículo v = new Vehículo(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
                listaVehiculos.add(v);
            }
            return listaVehiculos;
        } catch (SQLException ex) {
            throw new MyError("Imposible leer datos de la tabla vehiculos");
        } finally {
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }

        }
    }

    /**
     * Metodo que devuelve un ArrayList de todos lo propietarios que tengan una
     * determinanda provincia y un determinado numero de vehiculos
     *
     * @param provincia de tipo cadena recive el nombre de la provincia para
     * buscarla en la base de datos
     * @param n de tipo entero recive el numero de vehiculos que tiene ese
     * propietario
     * @return ArrayList devuelve una lista con todos los propietarios con esas
     * descripciones
     * @throws MyError guardara el error si no se podria concretar la consulta
     */
    public static ArrayList<Propietario> listaPropietarios(String provincia, int n) throws MyError {
        String sentenciaf = "SELECT p.DNI,p.NOMBRE, p.APELLIDO,p.TELEFONO, p.PROVINCIA , count(v.MATRICULA) FROM vehiculo v RIGHT OUTER JOIN PROPIETARIO p "
                + "on v.PROPIETARIO=p.DNI WHERE p.PROVINCIA=? GROUP BY p.DNI,p.NOMBRE, p.APELLIDO,p.TELEFONO, p.PROVINCIA HAVING COUNT(v.MATRICULA)=?";
        PreparedStatement pS = null;
        ResultSet rS = null;
        ArrayList<Propietario> listaVehiculoPropietario = new ArrayList<>();
        try {
//            System.err.println(sentenciaf);
            pS = conexion.prepareStatement(sentenciaf);
            pS.setString(1, provincia);
            pS.setString(2, String.valueOf(n));

            rS = pS.executeQuery();
//            System.err.println(rS.next());
            while (rS.next()) {
                Propietario p = new Propietario(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
                System.out.println(p.getDni());
                listaVehiculoPropietario.add(p);
            }
            return listaVehiculoPropietario;
        } catch (SQLException ex) {
            throw new MyError("Imposible leer datos de la tabla propietarios");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible cerrar la base de datos");
                }
            }
        }

    }

//    public static ArrayList<String> numeroDeCoches() throws MyError {
//        ArrayList<String> numeros = new ArrayList<>();
//        PreparedStatement pS = null;
//        ResultSet rS = null;
//        String sentencia = "SELECT  DISTINCT(count(v.MATRICULA)) FROM vehiculo v JOIN PROPIETARIO p on v.PROPIETARIO=p.DNI  GROUP BY p.DNI,p.NOMBRE, p.APELLIDO,p.TELEFONO, p.PROVINCIA ORDER BY count(v.MATRICULA)";
//        try {
//            pS = conexion.prepareStatement(sentencia);
//            rS = pS.executeQuery();
//            while (rS.next()) {
//                String a = rS.getString(1);
//                numeros.add(a);
//            }
//            return numeros;
//        } catch (SQLException ex) {
//            throw new MyError("Error al sacar el numero de coches de cada propietario" + ex.getMessage());
//        } finally {
//            if (pS != null) {
//                try {
//                    pS.close();
//                } catch (SQLException ex) {
//                    throw new MyError("Imposible cerrar la base de datos");
//                }
//            }
//            if (rS != null) {
//                try {
//                    rS.close();
//                } catch (SQLException ex) {
//                    throw new MyError("Imposible cerrar la base de datos");
//                }
//
//            }
//        }
//    }
    /**
     * Metodo que recive un objeto de la clase vehiculo para luego tomar sus
     * variables y subirlas creando un nuevo vehiculo en la base de datos
     *
     * @param e objeto de la clase Vehículo
     * @throws MyError guarda el error si habria algun problema al guardar
     * vehiculo
     */
    public static void guardaVehiculo(Vehículo e) throws MyError {
        String cadena = "INSERT INTO `vehiculo`(`matricula`, `modelo`, `anio`, `propietario`) VALUES (?,?,?,?);";
        PreparedStatement st = null;
        try {
            st = conexion.prepareStatement(cadena);
            st.setString(1, e.getMatricula());
            st.setString(2, e.getModelo());
            st.setInt(3, Integer.parseInt(e.getAnio()));
            st.setString(4, e.getPropietario());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyError("Imposible c " + ex.getMessage());
        } catch (NumberFormatException es) {
            throw new MyError("Telefono mal ingresado ");
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible terminar correctament , comprueba  " + ex.getMessage());
                }
            }
        }
    }
//    public static ArrayList<Propietario> provincias() throws MyError {
//        String sentencia = "SELECT DISTINCT(provincia)FROM propietario ";
//        PreparedStatement pS = null;
//        ResultSet rS = null;
//        ArrayList<Propietario> provincias = new ArrayList();
//        try {
//            pS = conexion.prepareStatement(sentencia);
//            rS = pS.executeQuery();
//            while (rS.next()) {
//                Propietario p = new Propietario(rS.getString(1));
//                provincias.add(p);
//            }
//            return provincias;
//        } catch (SQLException ex) {
//            throw new MyError("Imposible determinar provincias  ");
//        } finally {
//            if (pS != null) {
//                try {
//                    pS.close();
//                } catch (SQLException ex) {
//                    throw new MyError("Imposible cerrar la base de datos");
//                }
//            }
//            if (rS != null) {
//                try {
//                    rS.close();
//                } catch (SQLException ex) {
//                    throw new MyError("Imposible cerrar la base de datos");
//                }
//
//            }
//        }
//
//    }

    /**
     * Metodo que actualiza el vehiculo en la base de datos tiene como entrada
     * un objeto de la clase vehiculo y una cadena que seria el DNI del
     * propietario
     *
     * @param v objeto de la clase vehiculo que seria el vehiculoa la que haique
     * actualizar los datos
     * @param dni de tipo cadena que recive el DNI del propietario del vehiculo
     * para guardarlo
     * @throws MyError si habria algun error al ejecutar la consulta se lanzaria
     * un mensaje
     */
    public static void actualizaVehiculo(Vehículo v, String dni) throws MyError {
        String cadena = "UPDATE vehiculo SET propietario=? WHERE matricula=?";
        PreparedStatement st = null;
        try {
            st = conexion.prepareStatement(cadena);
            st.setString(1, dni);
            st.setString(2, v.getMatricula());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyError("Imposible actualizar datos conpruebe que el dni del nuevo propietario exista ");
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible terminar correctament , comprueba  " + ex.getMessage());
                }
            }
        }
    }

    /**
     * metodo que guarda al propietario en la base de datos reciviendo un objeto
     * de la clase propietario para sacar sus datos
     *
     * @param p objeto de la clase propietario tendra los datos del propietario
     * que haique guardar
     * @throws MyError si habria algun error al ejecutar la consulta se
     * guardaria el error
     */
    public static void guardaPropietario(Propietario p) throws MyError {
        String cadena = "INSERT INTO propietario VALUES(?,?,?,?,?);";
        PreparedStatement st = null;
        try {
            st = conexion.prepareStatement(cadena);
            st.setString(1, p.getDni());
            st.setString(2, p.getNombre());
            st.setString(3, p.getApellido());
            st.setString(4, p.getTelefono());
            st.setString(5, p.getProvincia());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyError("Imposible crear el Propietario:");
        } catch (NumberFormatException es) {
            throw new MyError("FORMATO MAL INGRESADO");
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new MyError("Imposible terminar correctament , comprueba  " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Metodo que elimina un vehiculo de la base de datos recive el DNI del
     * propietario para hacerlo
     *
     * @param propietario de tipo cadena recive el DNI de su propietario
     * @return n entero dependiendo de la filas borradas en la base de datos
     * @throws MyError si habria algun error al ejecutar la consulta guardaria
     * el error
     */
    public static int eliminarVehiculo(String propietario) throws MyError {
        String cadena = "Delete FROM vehiculo WHERE propietario=?";
        PreparedStatement pS = null;
        int n;
        try {
            pS = conexion.prepareStatement(cadena);
            pS.setString(1, propietario);
            n = pS.executeUpdate();
            return n;
        } catch (SQLException ex) {
            throw new MyError("Fallo al eliminar vehiculo ");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Metodo que elimina a un propietario de la base de datos cuando reciva el
     * DNI
     *
     * @param dni de tipo cadena recive el DNI del propietario para ejecutar la
     * consulta
     * @return n entero devuelve el numero de filas eliminas en la base de datos
     * @throws MyError guarda el error si se diera el caso
     */
    public static int eliminarPropietario(String dni) throws MyError {
        String sentencia = "DELETE FROM propietario WHERE dni=?";
        PreparedStatement pS = null;
        int n;
        try {
            pS = conexion.prepareStatement(sentencia);
            pS.setString(1, dni);
            n = pS.executeUpdate();
            return n;
        } catch (SQLException ex) {
            throw new MyError("Fallo al eliminar Propietario ");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Metodo que devuelve a todos los propietarios de la base de datos
     *
     * @return listaProp lista de todos los propietarios
     * @throws MyError guarda el error al ejecutar la consulta
     */
    public static ArrayList<Propietario> listaPropietariosSolos() throws MyError {
        ArrayList<Propietario> listaProp = new ArrayList<>();
        PreparedStatement pS = null;
        String sentencia = "SELECT * FROM propietario;";
        ResultSet rS = null;
        try {
            pS = conexion.prepareStatement(sentencia);
            rS = pS.executeQuery();
            while (rS.next()) {
                Propietario p = new Propietario(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
                listaProp.add(p);
            }
            return listaProp;
        } catch (SQLException ex) {
            throw new MyError("ERROR AL ESCOJER LOS DATOS ");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Metodo que devuelve la lista de propietarios segun su numero de coches
     *
     * @param n de tipo entero recive el numero de coches que tiene cada
     * propietario
     * @return listaProp lista de todos los vehiculos que cumplan con la
     * condicion
     * @throws MyError si habria algun error al hacer la consulta guardaria el
     * error
     */
    public static ArrayList<Propietario> listaPropietariosNumeroDeCoches(int n) throws MyError {
        ArrayList<Propietario> listaProp = new ArrayList<>();
        PreparedStatement pS = null;
        String sentencia = "SELECT p.DNI,p.NOMBRE, p.APELLIDO,p.TELEFONO, p.PROVINCIA , count(v.MATRICULA) FROM vehiculo v RIGHT OUTER JOIN PROPIETARIO p on v.PROPIETARIO=p.DNI  GROUP BY p.DNI,p.NOMBRE, p.APELLIDO,p.TELEFONO, p.PROVINCIA HAVING COUNT(v.MATRICULA)=?;";
        ResultSet rS = null;
        try {
            pS = conexion.prepareStatement(sentencia);
            pS.setString(1, String.valueOf(n));
            rS = pS.executeQuery();
            while (rS.next()) {
                Propietario p = new Propietario(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
                listaProp.add(p);
            }
            return listaProp;
        } catch (SQLException ex) {
            throw new MyError("ERROR AL COGER LOS DATOS DE LA LISTA ");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Metodo que devuelve una lista dependiendo de la provincia en que se
     * encuentre el propieario
     *
     * @param prinvincia de tipo cadena almacena el nombre de la provincia del
     * propieario
     * @return listaProp lista de todos los propietarios que esten en la
     * provincia introducida
     * @throws MyError si la consulta no se llevaria acabo guardara el error
     */
    public static ArrayList<Propietario> listaPropietariosProvincia(String prinvincia) throws MyError {
        ArrayList<Propietario> listaProp = new ArrayList<>();
        PreparedStatement pS = null;
        String sentencia = "SELECT * FROM propietario WHERE provincia=?;";
        ResultSet rS = null;
        try {
            pS = conexion.prepareStatement(sentencia);
            pS.setString(1, prinvincia);
            rS = pS.executeQuery();
            while (rS.next()) {
                Propietario p = new Propietario(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getString(5));
                listaProp.add(p);
            }
            return listaProp;
        } catch (SQLException ex) {
            throw new MyError("ERROR AL ESCOJER LOS DATOS DE LA PROVINCIA");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError("No se puede cerrar la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Metodo que devuelve la lista de vehiculos que tengan el DNI del
     * propietario
     *
     * @param propietario de tipo cadena recive el DNI del propietario para
     * utilizarlo en la base de datos
     * @return listaVehiculosPro lista de todos los vehiculos que tengan el
     * mismo propieatario
     * @throws MyError si la consulta no se ejecutaria guardara el error
     */

    public static ArrayList<Vehículo> listaVehiculosPro(String propietario) throws MyError {
        ArrayList<Vehículo> listaVehiculosPro = new ArrayList<>();
        PreparedStatement pS = null;
        String sentencia = "SELECT * FROM vehiculo WHERE propietario=?";
        ResultSet rS = null;
        try {
            pS = conexion.prepareStatement(sentencia);
            pS.setString(1, propietario);
            rS = pS.executeQuery();
            while (rS.next()) {
                Vehículo v = new Vehículo(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4));
                listaVehiculosPro.add(v);
            }
            return listaVehiculosPro;
        } catch (SQLException ex) {
            throw new MyError("ERROR AL ESCOJER LOS DATOS DEL NUMERO DE VEHICULOS");
        } finally {
            if (pS != null) {
                try {
                    pS.close();
                } catch (SQLException ex) {
                    throw new MyError(ex.getMessage() + ex.getSQLState());
                }
            }
            if (rS != null) {
                try {
                    rS.close();
                } catch (SQLException ex) {
                    throw new MyError(ex.getMessage() + ex.getSQLState());
                }
            }
        }
    }

}
