/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorCitaCrud;
import controlador.ControladorClienteCrud;
import controlador.ControladorMascotaCrud;
import controlador.ControladorRecepcionistaCrud;
import controlador.ControladorVeterinarioCrud;
import modelo.CitaDAO;
import modelo.ClienteDAO;
import modelo.MascotaDAO;
import modelo.RecepcionistaDAO;
import modelo.VeterinarioDAO;

/**
 *
 * @author USUARIO
 */
public class JFDashboard extends javax.swing.JFrame {

    /**
     * Creates new form frmDashboard
     */
    public JFDashboard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        btnVeterinarios = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnRecepcionistas = new javax.swing.JButton();
        btnMascotas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de control");
        setResizable(false);

        jLabel1.setText("BIENVENIDO");

        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnVeterinarios.setText("VETERINARIOS");
        btnVeterinarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVeterinariosActionPerformed(evt);
            }
        });

        btnCitas.setText("CITAS");
        btnCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRecepcionistas.setText("RECEPCIONISTAS");
        btnRecepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionistasActionPerformed(evt);
            }
        });

        btnMascotas.setText("MASCOTAS");
        btnMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMascotasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addComponent(jSeparator2)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVeterinarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCitas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRecepcionistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMascotas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRecepcionistas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVeterinarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMascotas)
                .addGap(8, 8, 8)
                .addComponent(btnCitas)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        /*
        JFClienteCrud frmc = new JFClienteCrud();
        frmc.show();
        dispose();
        */
        
        JFClienteCrud vistaC = new JFClienteCrud();
        ClienteDAO modeloC = new ClienteDAO();
        ControladorClienteCrud controladorC = new ControladorClienteCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaC.show();
        dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnVeterinariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVeterinariosActionPerformed
        // TODO add your handling code here:
        JFVeterinarioCrud vistaC = new JFVeterinarioCrud();
        VeterinarioDAO modeloC = new VeterinarioDAO();
        ControladorVeterinarioCrud controladorC = new ControladorVeterinarioCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaC.show();
        dispose();
    }//GEN-LAST:event_btnVeterinariosActionPerformed

    private void btnCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasActionPerformed
        // TODO add your handling code here:
        JFCitaCrud vistaC = new JFCitaCrud();
        CitaDAO modeloC = new CitaDAO();
        ControladorCitaCrud controladorC = new ControladorCitaCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaC.show();
        dispose();
    }//GEN-LAST:event_btnCitasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionistasActionPerformed
        // TODO add your handling code here:
        JFRecepcionistaCrud vistaC = new JFRecepcionistaCrud();
        RecepcionistaDAO modeloC = new RecepcionistaDAO();
        ControladorRecepcionistaCrud controladorC = new ControladorRecepcionistaCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaC.show();
        dispose();
    }//GEN-LAST:event_btnRecepcionistasActionPerformed

    private void btnMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMascotasActionPerformed
        // TODO add your handling code here:
        JFMascotaCrud vistaC = new JFMascotaCrud();
        MascotaDAO modeloC = new MascotaDAO();
        ControladorMascotaCrud controladorC = new ControladorMascotaCrud(vistaC, modeloC);
        
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        vistaC.show();
        dispose();
    }//GEN-LAST:event_btnMascotasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnMascotas;
    private javax.swing.JButton btnRecepcionistas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVeterinarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
