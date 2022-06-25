/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.model;

/**
 *
 * @author USUARIO
 */
public abstract class Trabajador extends Persona {
    public Trabajador(String name, String lastname, String documentId, String phone, String address){
        super(name,  lastname,  documentId,  phone,  address);
        //this.email = email;
    }
}
