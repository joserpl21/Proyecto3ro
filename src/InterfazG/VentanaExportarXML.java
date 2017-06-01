package InterfazG;

import Modelo.Propietario;
import Modelo.Vehiculo;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class VentanaExportarXML extends javax.swing.JFrame {

    JFrame padre;
    ArrayList<Vehiculo> listaVehiculos;
    ArrayList<Propietario> listaPropietario;
    /**
     * Cosntructor que recive el JFrame de la ventana principal y el arraylist de todos los propietarios junto con el arraylist de todos los vehiculos
     * @param padre
     * @param listaV
     * @param listap 
     */

    public VentanaExportarXML(JFrame padre, ArrayList listaV, ArrayList listap) {
        this.padre = padre;
        this.listaPropietario = listap;
        this.listaVehiculos = listaV;
        setVisible(true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfNombreDocumento = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ventana Exportar");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("EXPORTAR XML");

        jButton1.setText("Exportar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombre de Documento: ");

        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(tfNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1)
                        .addGap(84, 84, 84)
                        .addComponent(jButton2)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //Escribe 
        String nombre = tfNombreDocumento.getText();
        DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dBF.newDocumentBuilder();
            DOMImplementation implentacion = db.getDOMImplementation();
            Document documento = implentacion.createDocument(null, "vehiculos", null);
            documento.setXmlVersion("1.0");
            Element raiz = documento.getDocumentElement();
            ListIterator<Vehiculo> itVe = listaVehiculos.listIterator();
            while (itVe.hasNext()) {
                Vehiculo v = itVe.next();
                Element etiquetaVehiculo = documento.createElement("vehiculo");
                Element etiquetaModelo = documento.createElement("modelo");
                Element etiquetaAnio = documento.createElement("anio");
                Text valorModelo = documento.createTextNode(v.getModelo());
                Text valorAnio = documento.createTextNode(v.getAnio());
                etiquetaVehiculo.setAttribute("matricula", v.getMatricula());
                ListIterator<Propietario> itProp = listaPropietario.listIterator();
                etiquetaModelo.appendChild(valorModelo);
                etiquetaAnio.appendChild(valorAnio);
                etiquetaVehiculo.appendChild(etiquetaModelo);
                etiquetaVehiculo.appendChild(etiquetaAnio);
                while (itProp.hasNext()) {
                    Propietario p = itProp.next();
                    if (v.getPropietario().equals(p.getDni())) {
                        Element etiquetaPropietario = documento.createElement("propietario");
                        Element etiquetNombre = documento.createElement("nombre");
                        Element etiquetaApellido = documento.createElement("apellido");
                        Text valorNombre = documento.createTextNode(p.getNombre());
                        Text valorApellido = documento.createTextNode(p.getApellido());
                        etiquetNombre.appendChild(valorNombre);
                        etiquetaApellido.appendChild(valorApellido);
                        etiquetaPropietario.appendChild(etiquetNombre);
                        etiquetaPropietario.appendChild(etiquetaApellido);
                        etiquetaVehiculo.appendChild(etiquetaPropietario);
                    }
                }
                raiz.appendChild(etiquetaVehiculo);
                Source source = new DOMSource(documento);
                Result destino = new StreamResult("fichero/" + nombre + ".xml");
                Transformer tF = TransformerFactory.newInstance().newTransformer();
                tF.setOutputProperty(OutputKeys.ENCODING, "utf-8");
                tF.setOutputProperty(OutputKeys.INDENT, "YES");
                tF.transform(source, destino);
            }
            VentanaNotificaciones.ventanaOK("EXPORTACION EXITOSA", padre);
            limpiaPantalla();
        } catch (ParserConfigurationException | TransformerException ex) {
            VentanaNotificaciones.ventanaOK("Error al escribir el documento XML", padre);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        padre.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        padre.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfNombreDocumento;
    // End of variables declaration//GEN-END:variables

    private void limpiaPantalla() {
        tfNombreDocumento.setText("");
    }
}
