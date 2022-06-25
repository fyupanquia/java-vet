/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.model;

import oop.interfaces.IPersona;
import oop.interfaces.ITrabajador;

/**
 *
 * @author USUARIO
 */
public class Veterinario extends Trabajador implements IPersona, ITrabajador {
    public Veterinario(String name, String lastname, String documentId, String phone, String address){
        super(name,  lastname,  documentId,  phone,  address);
    }
    
    @Override
    public void cobrarSalario() {
        System.out.println("Cobrar vía planilla!");
    }

    @Override
    public void marcarIngreso() {
        System.out.println("Marca ingreso después de las 9:00 am");
    }
    
    public void diagnosticar(Mascota mascota) {
        System.out.println("Diagnostica a la mascota llamada "+ mascota.getNombre());
    }
    public void tratarMascota(Mascota mascota) {
        System.out.println("Estoy tratando a la mascota llamada "+ mascota.getNombre());
    }

    @Override
    public void saludar() {
        System.out.println("Hola! soy el veterinario "+ super.getNombres());
    }
}
