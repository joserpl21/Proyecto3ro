/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazG;

import Modelo.DataBaseSegura;
import Modelo.Propietario;
import Modelo.Vehiculo;
import controlador.MyError;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class VentanaListaVehiculos extends javax.swing.JFrame {

    /**
     * Creates new form VentanaListaVehiculos
     */
    JFrame padre;
    ArrayList<Vehiculo> listaVehiculos;
    ArrayList<Propietario> listaPropietarios;
    Vehiculo v;
    int n = 0;
    
    public VentanaListaVehiculos(JFrame padre) {
        try {
            initComponents();
//            this.listaVehiculos = lista;
//            this.listaPropietarios = lista2;
            this.padre = padre;
            setVisible(true);
            listaVehiculos = DataBaseSegura.consultaVehiculos();
            rellenaTabla();
            seleccionTabla();
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError(ex.getMessage(), padre);
        }
    }
    
    public VentanaListaVehiculos(ArrayList<Vehiculo> lista) {
        try {
            initComponents();
            setVisible(true);
            this.listaVehiculos = lista;
            rellenaTabla();
        } catch (MyError ex) {
            VentanaNotificaciones.ventanaError(ex.getMessage(), padre);
        }
        seleccionTabla();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLista = new javax.swing.JTable();
        jbAnadirPro = new javax.swing.JButton();
        jbVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Vehiculos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("LISTA DE VEHICULOS");

        jtLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Modelo", "Año", "Propietaro", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtLista);

        jbAnadirPro.setText("Asignar Propietario");
        jbAnadirPro.setEnabled(false);
        jbAnadirPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnadirProActionPerformed(evt);
            }
        });

        jbVolver.setText("VOLVER");
        jbVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbAnadirPro)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(138, 138, 138))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jbVolver)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jbAnadirPro)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbVolver)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        padre.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jbAnadirProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnadirProActionPerformed
        VentanaNuevoPropietario n = new VentanaNuevoPropietario(v, this);
        setVisible(false);
    }//GEN-LAST:event_jbAnadirProActionPerformed

    private void jtListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtListaMouseClicked
        seleccionTabla();
        v = listaVehiculos.get(n - 1);
    }//GEN-LAST:event_jtListaMouseClicked

    private void jbVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverActionPerformed
        try {
            padre.setVisible(true);
            dispose();
        } catch (NullPointerException e) {
            VentanaPrincipal v = new VentanaPrincipal();
            setVisible(false);
        }

    }//GEN-LAST:event_jbVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAnadirPro;
    private javax.swing.JButton jbVolver;
    private javax.swing.JTable jtLista;
    // End of variables declaration//GEN-END:variables

    private void rellenaTabla() throws MyError {
        DefaultTableModel modeloTabla = (DefaultTableModel) jtLista.getModel();
        for (Vehiculo v : listaVehiculos) {
            modeloTabla.addRow(v.toArray());
        }
    }
    
    private void seleccionTabla() {
        n = jtLista.getSelectedRow() + 1;
        if (n > 0) {
            jbAnadirPro.setEnabled(true);
        }
    }
    
}
