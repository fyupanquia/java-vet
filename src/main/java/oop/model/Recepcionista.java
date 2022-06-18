/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.model;

/**
 *
 * @author USUARIO
 */
public class Recepcionista extends Trabajador {
    public Recepcionista(String name, String lastname, String documentId, String phone, String address){
        super(name,  lastname,  documentId,  phone,  address);
    }

    @Override
    public void cobrarSalario() {
        System.out.println("Cobrar vía recibo por honorarios!");
    }

    @Override
    public void marcarIngreso() {
        System.err.println("Marca ingreso antes de las 7:00 am");
    }
     public void agendar(Cita cita) {
         Cliente cliente = cita.getCliente();
         Mascota mascota = cita.getMascota();
        System.err.println("Estoy agendando una clita para el cliente "+ cliente.getNombres() + " "+ cliente.getApellidos()+ 
                            "y su mascota llamada "+ mascota.getNombre()+
                            "La fecha "+ cita.getFecha().toString());
    }
    @Override
    public void saludar() {
        System.err.println("Hola! soy la recepcionista "+ super.getNombres());
    }
}
