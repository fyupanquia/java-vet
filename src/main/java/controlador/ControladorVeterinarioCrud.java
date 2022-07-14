/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author USUARIO
 */
public class ControladorVeterinarioCrud implements ActionListener, KeyListener{
    JFVeterinarioCrud vistaCRUD=new JFVeterinarioCrud();
    VeterinarioDAO modeloCRUD= new VeterinarioDAO();
    
    public ControladorVeterinarioCrud(JFVeterinarioCrud vistaCRUD, VeterinarioDAO modeloCRUD){
        this.modeloCRUD=modeloCRUD;
        this.vistaCRUD=vistaCRUD;
        
        this.vistaCRUD.btnActualizar.setEnabled(false);
        this.vistaCRUD.btnEliminar.setEnabled(false);
        this.vistaCRUD.btnGuardar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);  
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnActualizar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
    }
   
    public void LlenarTabla(JTable tablaD){
        DefaultTableModel modeloT=new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("Nombres");
        modeloT.addColumn("Apellidos");
        modeloT.addColumn("DNI");
        modeloT.addColumn("Teléfono");
        modeloT.addColumn("Dirección");
        
        Object[]columna=new Object[6];
        ArrayList<Veterinario> veterinarios = modeloCRUD.listVeterinario();
        int numRegistros = veterinarios.size();
        for(int i=0;i<numRegistros;i++){
            Veterinario veterinario = veterinarios.get(i);
            columna[0]=veterinario.getNombres();
            columna[1]=veterinario.getApellidos();
            columna[2]=veterinario.getDni();
            columna[3]=veterinario.getTelefono();
            columna[4]=veterinario.getDireccion();
            modeloT.addRow(columna);
        }      
    }
   
    public void LimpiarElementos(){
        vistaCRUD.txtNombres.setText("");
        vistaCRUD.txtApellidos.setText("");
        vistaCRUD.txtDNI.setText("");
        vistaCRUD.txtTelefono.setText("");
        vistaCRUD.txtDireccion.setText("");
    }
    
     public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==vistaCRUD.btnGuardar) {
            String nombres = vistaCRUD.txtNombres.getText();
            String apellidos = vistaCRUD.txtApellidos.getText();
            String dni = vistaCRUD.txtDNI.getText();
            String telefono = vistaCRUD.txtTelefono.getText();
            String direccion = vistaCRUD.txtDireccion.getText();
            Boolean rptaRegistro =modeloCRUD.insertVeterinario(nombres, apellidos, dni, telefono, direccion);
            if (rptaRegistro) {
                JOptionPane.showMessageDialog(null, "Registro Exitoso");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
               JOptionPane.showMessageDialog(null, "Registro Erróneo");  
            }
        }
      
      
        if (e.getSource()==vistaCRUD.btnListar) {
            LlenarTabla(vistaCRUD.jtDatos);
            //JOptionPane.showMessageDialog(null, "Lista de Registro"); 
        }
                   
        if (e.getSource()==vistaCRUD.btnEditar) {
            int filaEditar = vistaCRUD.jtDatos.getSelectedRow();
            int numFS = vistaCRUD.jtDatos.getSelectedRowCount();
            
            if (filaEditar>=0 && numFS==1 ) {
                vistaCRUD.txtNombres.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 0)));
                vistaCRUD.txtApellidos.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 1)));
                vistaCRUD.txtDNI.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 2)));
                vistaCRUD.txtTelefono.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 3)));
                vistaCRUD.txtDireccion.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 4)));
                
                vistaCRUD.txtDNI.setEditable(false);
                vistaCRUD.btnGuardar.setEnabled(false);
                vistaCRUD.btnEditar.setEnabled(true);
                vistaCRUD.btnActualizar.setEnabled(true);
                vistaCRUD.btnEliminar.setEnabled(true);
              }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
            }
        }
        
        if (e.getSource()==vistaCRUD.btnActualizar) {
            String nombres=vistaCRUD.txtNombres.getText();
            String apellidos=vistaCRUD.txtApellidos.getText();
            String dni=vistaCRUD.txtDNI.getText();
            String telefono=vistaCRUD.txtTelefono.getText();
            String direccion=vistaCRUD.txtDireccion.getText();

            int rptaEdit= modeloCRUD.editarVeterinario(nombres, apellidos, dni, telefono, direccion);
            if(rptaEdit>0){
                JOptionPane.showMessageDialog(null,"Edición exitosa");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
                 JOptionPane.showMessageDialog(null,"No se pudo Guardar");
            }
            
            LimpiarElementos();
            vistaCRUD.btnGuardar.setEnabled(true);
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(false);
            vistaCRUD.btnActualizar.setEnabled(false);
         }   
        
 
       if(e.getSource()==vistaCRUD.btnEliminar){
            String dni = vistaCRUD.txtDNI.getText();

            int rptaEdit= modeloCRUD.eliminarVeterinario(dni);
            if(rptaEdit>0){
                JOptionPane.showMessageDialog(null,"Eliminación exitosa");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo eliminar"); 
            }
        }
    }    

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
