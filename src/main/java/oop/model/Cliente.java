/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.model;

/**
 *
 * @author USUARIO
 */
public class Cliente extends Persona {
    private String email;
    
    public Cliente(String name, String lastname, String documentId, String phone, String address){
        super(name,  lastname,  documentId,  phone,  address);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void pagarVisita() {
       
    }
    public void pagarMedicamento() {
       
    }
    public void pagarSolicitarAtencion() {
       
    }

    @Override
    public void saludar() {
        System.err.println("Hola! soy el cliente"+ super.getNombres());
    }
}
