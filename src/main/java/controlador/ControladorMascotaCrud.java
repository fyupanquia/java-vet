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
public class ControladorMascotaCrud implements ActionListener, KeyListener{
    JFMascotaCrud vistaCRUD=new JFMascotaCrud();
    MascotaDAO modeloCRUD= new MascotaDAO();
    
    public ControladorMascotaCrud(JFMascotaCrud vistaCRUD, MascotaDAO modeloCRUD){
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
        modeloT.addColumn("ID");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Edad");
        modeloT.addColumn("Sexo");
        modeloT.addColumn("Color");
        modeloT.addColumn("Raza");
        modeloT.addColumn("Peso");
        modeloT.addColumn("Altura");
        
        Object[] fila = new Object[8];
        ArrayList<Mascota> mascotas = modeloCRUD.listMascota();
        int numRegistros = mascotas.size();
        for(int i=0;i<numRegistros;i++){
            Mascota mascota = mascotas.get(i);
            fila[0]=mascota.getID();
            fila[1]=mascota.getNombre();
            fila[2]=mascota.getEdad();
            fila[3]=mascota.getSexo();
            fila[4]=mascota.getColor();
            fila[5]=mascota.getRaza();
            fila[6]=mascota.getPeso();
            fila[7]=mascota.getAltura();
            modeloT.addRow(fila);
        }      
    }
   
    public void LimpiarElementos(){
        vistaCRUD.txtID.setText("");
        vistaCRUD.txtNombre.setText("");
        vistaCRUD.txtEdad.setText("");
        vistaCRUD.txtColor.setText("");
        vistaCRUD.txtRaza.setText("");
        vistaCRUD.txtPeso.setText("");
        vistaCRUD.txtAltura.setText("");
    }
    
     public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==vistaCRUD.btnGuardar) {
            String nombre = vistaCRUD.txtNombre.getText();
            String edad = vistaCRUD.txtEdad.getText();
            String sexo = vistaCRUD.cmbSexo.getSelectedItem().toString();
            String color = vistaCRUD.txtColor.getText();
            String raza = vistaCRUD.txtRaza.getText();
            String peso = vistaCRUD.txtPeso.getText();
            String altura = vistaCRUD.txtAltura.getText();
            Boolean rptaRegistro =modeloCRUD.insertMascota(nombre, edad, sexo, color, raza, peso, altura);
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

                vistaCRUD.txtID.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 0)));
                vistaCRUD.txtNombre.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 1)));
                vistaCRUD.txtEdad.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 2)));
                vistaCRUD.cmbSexo.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 3)));
                vistaCRUD.txtColor.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 4)));
                vistaCRUD.txtRaza.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 5)));
                vistaCRUD.txtPeso.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 6)));
                vistaCRUD.txtAltura.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 7)));
                
                vistaCRUD.btnGuardar.setEnabled(false);
                vistaCRUD.btnEditar.setEnabled(false);
                vistaCRUD.btnActualizar.setEnabled(true);
                vistaCRUD.btnEliminar.setEnabled(true);
              }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
            }
        }
        
        if (e.getSource()==vistaCRUD.btnActualizar) {
            String ID = vistaCRUD.txtID.getText();
            String nombre = vistaCRUD.txtNombre.getText();
            String edad = vistaCRUD.txtEdad.getText();
            String sexo = vistaCRUD.cmbSexo.getSelectedItem().toString();
            String color = vistaCRUD.txtColor.getText();
            String raza =vistaCRUD.txtRaza.getText();
            String peso = vistaCRUD.txtPeso.getText();
            String altura = vistaCRUD.txtAltura.getText();

            int rptaEdit= modeloCRUD.editarMascota(ID, nombre, edad, sexo, color, raza, peso, altura);
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
            String ID = vistaCRUD.txtID.getText();

            int rptaEdit= modeloCRUD.eliminarMascota(ID);
            if(rptaEdit>0){
                JOptionPane.showMessageDialog(null,"Eliminación exitosa");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo eliminar"); 
            }
            
            vistaCRUD.btnGuardar.setEnabled(true);
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(false);
            vistaCRUD.btnActualizar.setEnabled(false);
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
