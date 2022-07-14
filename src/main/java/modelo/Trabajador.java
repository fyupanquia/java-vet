/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public abstract class Trabajador extends Persona {
    public Trabajador(String ID, String name, String lastname){
        super(ID, name,  lastname);
    }
    public Trabajador(String ID, String name, String lastname, String documentId, String phone, String address){
        super(ID, name,  lastname,  documentId,  phone,  address);
    }
}
