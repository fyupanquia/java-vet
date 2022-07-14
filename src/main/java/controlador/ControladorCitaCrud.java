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

public class ControladorCitaCrud implements ActionListener, KeyListener{
    JFCitaCrud vistaCRUD=new JFCitaCrud();
    CitaDAO modeloCRUD= new CitaDAO();
    
    public ControladorCitaCrud(JFCitaCrud vistaCRUD, CitaDAO modeloCRUD){
        this.modeloCRUD=modeloCRUD;
        this.vistaCRUD=vistaCRUD;
        
        this.vistaCRUD.btnActualizar.setEnabled(false);
        this.vistaCRUD.btnEliminar.setEnabled(false);
        this.vistaCRUD.btnGuardar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);  
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnActualizar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
        
        RecepcionistaDAO recepcionistaDAO = new RecepcionistaDAO();
        ArrayList<Recepcionista> recepcionistas = recepcionistaDAO.listRecepcionista();
        for (Recepcionista recepcionista : recepcionistas) {
            this.vistaCRUD.cmbRecepcionista.addItem(recepcionista.getID() +": " + recepcionista.getNombres()+" "+ recepcionista.getApellidos());
        }
        
        
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        ArrayList<Veterinario> veterinarios = veterinarioDAO.listVeterinario();
        for (Veterinario veterinario : veterinarios) {
            this.vistaCRUD.cmbVeterinario.addItem(veterinario.getID() +": " + veterinario.getNombres()+" "+ veterinario.getApellidos());
        }
        
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.listCliente();
        for (Cliente cliente : clientes) {
            this.vistaCRUD.cmbCliente.addItem(cliente.getID() +": " + cliente.getNombres()+" "+ cliente.getApellidos());
        }
        
        MascotaDAO mascotaDAO = new MascotaDAO();
        ArrayList<Mascota> mascotas = mascotaDAO.listMascota();
        for (Mascota mascota : mascotas) {
            this.vistaCRUD.cmbMascota.addItem(mascota.getID()+": "+mascota.getNombre());
        }
    }
   
    public void LlenarTabla(JTable tablaD){
        
        DefaultTableModel modeloT=new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("ID");
        modeloT.addColumn("Recepcionista");
        modeloT.addColumn("Veterinario");
        modeloT.addColumn("Cliente");
        modeloT.addColumn("Mascota");
        modeloT.addColumn("Atención");
        
        Object[]fila=new Object[6];
        ArrayList<Cita> citas = modeloCRUD.listCita();
        int numRegistros = citas.size();
        for(int i=0;i<numRegistros;i++){
            Cita cita = citas.get(i);
            fila[0]=cita.getID();
            fila[1]=cita.getRecepcionista().getNombres()+' '+ cita.getRecepcionista().getApellidos();
            fila[2]=cita.getVeterinario().getNombres()+' '+ cita.getVeterinario().getApellidos();
            fila[3]=cita.getCliente().getNombres()+' '+ cita.getCliente().getApellidos();
            fila[4]=cita.getMascota().getNombre();
            fila[5]=cita.getTipoAtencion();
            modeloT.addRow(fila);
        }      
        
    }
   
    public void LimpiarElementos(){
        vistaCRUD.cmbRecepcionista.setSelectedIndex(0);
        vistaCRUD.cmbVeterinario.setSelectedIndex(0);
        vistaCRUD.cmbCliente.setSelectedIndex(0);
        vistaCRUD.cmbMascota.setSelectedIndex(0);
        vistaCRUD.cmbAtencion.setSelectedIndex(0);
    }
    
     public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==vistaCRUD.btnGuardar) {
            String recepcionista = vistaCRUD.cmbRecepcionista.getSelectedItem().toString();
            String veterinario = vistaCRUD.cmbVeterinario.getSelectedItem().toString();
            String cliente = vistaCRUD.cmbCliente.getSelectedItem().toString();
            String mascota = vistaCRUD.cmbMascota.getSelectedItem().toString();
            String atencion = vistaCRUD.cmbAtencion.getSelectedItem().toString();
            Boolean rptaRegistro =modeloCRUD.insertCita(recepcionista, veterinario, cliente, mascota, atencion);
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
            /*
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
                vistaCRUD.btnEditar.setEnabled(false);
                vistaCRUD.btnActualizar.setEnabled(true);
                vistaCRUD.btnEliminar.setEnabled(true);
              }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
            }
            */
        }
        
        if (e.getSource()==vistaCRUD.btnActualizar) {
            /*
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
            */
         }   
        
 
       if(e.getSource()==vistaCRUD.btnEliminar){
           /*
            String dni = vistaCRUD.txtDNI.getText();

            int rptaEdit= modeloCRUD.eliminarVeterinario(dni);
            if(rptaEdit>0){
                JOptionPane.showMessageDialog(null,"Eliminación exitosa");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo eliminar"); 
            }
        */
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
