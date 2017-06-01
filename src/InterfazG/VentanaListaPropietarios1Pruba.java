/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazG;

import Modelo.DataBaseSegura;
import Modelo.Propietario;
import controlador.MyError;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class VentanaListaPropietarios1Pruba extends javax.swing.JFrame {

    /**
     * Creates new form VentanaListaPropietarios
     */
    JFrame padre;
    ArrayList<Propietario> listaProp;
    ArrayList<String> listaProvin;

    public VentanaListaPropietarios1Pruba(JFrame padre, ArrayList<Propietario> propie) {
        this.listaProp = propie;
        this.padre = padre;
        setVisible(true);
        // Obtener las provincias de BBD
        /* misProvincias.add("Zaragoza");
          misProvincias.add("Huesca");
            misProvincias.add("Teruel");
        datos = new ComboBoxModel(misProvincias);*/
        initComponents();
        rellenaTabla();
    }

    VentanaListaPropietarios1Pruba(VentanaPrincipal aThis, VentanaListaPropietarios1Pruba p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLista = new javax.swing.JTable();
        jcmbProvincia = new javax.swing.JComboBox<>();
        jcmbNumero = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista Propietarios");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("LISTA PROPIETARIOS");

        jtLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "NOMBRE", "APELLIDO", "TELEFONO", "PROVINCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtLista);

        jcmbProvincia.setModel(new DefaultComboBoxModel<String>(creaModeloComobo()));
        jcmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbProvinciaActionPerformed(evt);
            }
        });

        jcmbNumero.setModel(new DefaultComboBoxModel<String>(creaModeloComoboNumeros()));
        jcmbNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbNumeroActionPerformed(evt);
            }
        });

        jLabel2.setText("PROVINCIA");

        jLabel3.setText("NUMERO");

        jbVolver.setText("VOLVER");
        jbVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jbVolver))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jcmbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbVolver)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        padre.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosed

    private void jbVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverActionPerformed
        padre.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbVolverActionPerformed

    private void jcmbNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbNumeroActionPerformed
        limpiaTabla();
        try {
            listaProp = DataBaseSegura.listaPropietarios(jcmbProvincia.getSelectedItem().toString(), Integer.parseInt(jcmbNumero.getSelectedItem().toString()));
            
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError(ex.getMessage(), padre);
        }
        if (!listaProp.isEmpty()) {
            rellenaTabla();
        } else {
            VentanaNotificaciones.ventanaError("NO HAY PROPIETARIOS", padre);
        }
    }//GEN-LAST:event_jcmbNumeroActionPerformed

    private void jcmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbProvinciaActionPerformed
        limpiaTabla();
        try {
//////            System.out.println(jcmbProvincia.getSelectedItem().toString());
//            System.out.println(Integer.parseInt(jcmbNumero.getSelectedItem().toString()));
            listaProp = DataBaseSegura.listaPropietarios(jcmbProvincia.getSelectedItem().toString(), Integer.parseInt(jcmbNumero.getSelectedItem().toString()));
            if (!listaProp.isEmpty()) {
                rellenaTabla();
            } else {
                VentanaNotificaciones.ventanaError("NO HAY PROPIETARIOS", padre);
            }
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError(ex.getMessage(), padre);
        }

    }//GEN-LAST:event_jcmbProvinciaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbVolver;
    private javax.swing.JComboBox<String> jcmbNumero;
    private javax.swing.JComboBox<String> jcmbProvincia;
    private javax.swing.JTable jtLista;
    // End of variables declaration//GEN-END:variables

    private void rellenaTabla() {
        DefaultTableModel tablaP = (DefaultTableModel) jtLista.getModel();
        for (Propietario p : listaProp) {
            System.err.println(p.getDni());
            tablaP.addRow(p.toArray());
        }
    }

    private void limpiaTabla() {
        DefaultTableModel tablaP = (DefaultTableModel) jtLista.getModel();
        while (tablaP.getRowCount() >= 1) {
            tablaP.removeRow(0);
        }
    }

    private String[] creaModeloComoboNumeros() {
        try {
            ArrayList numeros;
            numeros = DataBaseSegura.numeroDeCoches();
            int n = numeros.size();
            String[] numero = new String[n];
            for (int i = 0; i < n; i++) {
                numero[i] = numeros.get(i).toString();
            }
            return numero;
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError(ex.getMessage(), padre);
            return null;
        }
    }

    private String[] creaModeloComobo() {
        try {
            ArrayList ciudades = DataBaseSegura.provincias();
            int n = ciudades.size();
            String[] nombres = new String[n];
            for (int i = 0; i < n; i++) {
                Propietario p = (Propietario) ciudades.get(i);
                System.out.println(p.getProvincia());
                nombres[i] = p.getProvincia();
            }
            return nombres;
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError("ERRO", padre);
            return null;
        }
    }

}
