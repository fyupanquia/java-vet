/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.model;

/**
 *
 * @author USUARIO
 */
public class Veterinario extends Trabajador {
    public Veterinario(String name, String lastname, String documentId, String phone, String address){
        super(name,  lastname,  documentId,  phone,  address);
    }
    
    @Override
    public void cobrarSalario() {
        System.out.println("Cobrar vía planilla!");
    }

    @Override
    public void marcarIngreso() {
        System.err.println("Marca ingreso después de las 9:00 am");
    }
    
    public void diagnosticar(Mascota mascota) {
        System.err.println("Diagnostica a la mascota llamada "+ mascota.getNombre());
    }
    public void tratarMascota(Mascota mascota) {
        System.err.println("Estoy tratando a la mascota llamada "+ mascota.getNombre());
    }

    @Override
    public void saludar() {
        System.err.println("Hola! soy el veterinario "+ super.getNombres());
    }
}
