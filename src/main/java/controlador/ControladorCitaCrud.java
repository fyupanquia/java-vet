/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
        this.vistaCRUD.cmbMascota.addActionListener(this);
        
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
        modeloT.addColumn("Fecha");
        
        tablaD.getColumnModel().getColumn(6).setMinWidth(0);
        tablaD.getColumnModel().getColumn(6).setMaxWidth(0);
        
        Object[]fila=new Object[7];
        ArrayList<Cita> citas = modeloCRUD.listCita();
        int numRegistros = citas.size();
        for(int i=0;i<numRegistros;i++){
            Cita cita = citas.get(i);
            fila[0]=cita.getID();
            fila[1]=cita.getRecepcionista().getID()+": "+cita.getRecepcionista().getNombres()+' '+ cita.getRecepcionista().getApellidos();
            fila[2]=cita.getVeterinario().getID()+": "+cita.getVeterinario().getNombres()+' '+ cita.getVeterinario().getApellidos();
            fila[3]=cita.getCliente().getID()+": "+cita.getCliente().getNombres()+' '+ cita.getCliente().getApellidos();
            fila[4]=cita.getMascota().getID()+": "+cita.getMascota().getNombre();
            fila[5]=cita.getTipoAtencion();
            fila[6]=cita.getFecha();
            modeloT.addRow(fila);
        }      
        
    }
   
    public void LimpiarElementos(){
        vistaCRUD.txtID.setText("");
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
            Date dfecha = vistaCRUD.jdFecha.getDate();
            if(dfecha==null){
               JOptionPane.showMessageDialog(null, "Ingrese la fecha para la cita");
               return;
            }
            String fecha = String.format("%1$tY-%1$tm-%1$td", dfecha);
            Boolean rptaRegistro =modeloCRUD.insertCita(recepcionista, veterinario, cliente, mascota, atencion, fecha);
            if (rptaRegistro) {
                vistaCRUD.jdFecha.setDate(new Date());
                JOptionPane.showMessageDialog(null, "Registro Exitoso");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
               JOptionPane.showMessageDialog(null, "Registro Erróneo");  
            }
        }      
      
        if (e.getSource()==vistaCRUD.btnListar) {
            LlenarTabla(vistaCRUD.jtDatos);
        }
                   
        if (e.getSource()==vistaCRUD.btnEditar) {
            
            int filaEditar = vistaCRUD.jtDatos.getSelectedRow();
            int numFS = vistaCRUD.jtDatos.getSelectedRowCount();
            
            if (filaEditar>=0 && numFS==1 ) {
                vistaCRUD.txtID.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 0)));
                vistaCRUD.cmbRecepcionista.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 1)));
                vistaCRUD.cmbVeterinario.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 2)));
                vistaCRUD.cmbCliente.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 3)));
                vistaCRUD.cmbMascota.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 4)));
                vistaCRUD.cmbAtencion.setSelectedItem(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 5)));
                
                Date date;
                try {
                    date = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 6)));
                    vistaCRUD.jdFecha.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorCitaCrud.class.getName()).log(Level.SEVERE, null, ex);
                }                
                
                vistaCRUD.btnGuardar.setEnabled(false);
                vistaCRUD.btnEditar.setEnabled(true);
                vistaCRUD.btnActualizar.setEnabled(true);
                vistaCRUD.btnEliminar.setEnabled(true);
              }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
            }
            
        }
        
        if (e.getSource()==vistaCRUD.btnActualizar) {
            String ID = vistaCRUD.txtID.getText();
            String recepcionista = vistaCRUD.cmbRecepcionista.getSelectedItem().toString();
            String veterinario = vistaCRUD.cmbVeterinario.getSelectedItem().toString();
            String cliente = vistaCRUD.cmbCliente.getSelectedItem().toString();
            String mascota = vistaCRUD.cmbMascota.getSelectedItem().toString();
            String atencion = vistaCRUD.cmbAtencion.getSelectedItem().toString();
            Date dfecha = vistaCRUD.jdFecha.getDate();
            if(dfecha==null){
               JOptionPane.showMessageDialog(null, "Ingrese la fecha para la cita");
               return;
            }
            String fecha = String.format("%1$tY-%1$tm-%1$td", dfecha);
                
            int rptaEdit= modeloCRUD.editarCita(ID, recepcionista, veterinario, cliente, mascota, atencion, fecha);
            if(rptaEdit>0){
                vistaCRUD.jdFecha.setDate(new Date());
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
            int rptaEdit = modeloCRUD.eliminarCita(ID);
            if(rptaEdit>0){
                vistaCRUD.jdFecha.setDate(new Date());
                JOptionPane.showMessageDialog(null,"Eliminación exitosa");
                LimpiarElementos();
                LlenarTabla(vistaCRUD.jtDatos);
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo eliminar"); 
            }
        
        }
       
       if(e.getSource()==vistaCRUD.cmbMascota){
           String mascotaID = vistaCRUD.cmbMascota.getSelectedItem().toString();
           System.out.println("mascotaID: "+ mascotaID);
           MascotaDAO md = new MascotaDAO();
           Mascota mascota = md.findtMascota(mascotaID);
           if(mascota!=null){
                ImageIcon imageIcon = new ImageIcon(
                    new ImageIcon("./assets/pets/"+mascota.getFoto())
                        .getImage()
                        .getScaledInstance(
                            vistaCRUD.lblFoto.getWidth(), 
                            vistaCRUD.lblFoto.getHeight(),
                            Image.SCALE_SMOOTH)
                );
                vistaCRUD.lblFoto.setIcon(imageIcon);
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
