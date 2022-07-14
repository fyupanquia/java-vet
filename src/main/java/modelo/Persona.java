/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public abstract class Persona {
    private String ID;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public Persona(String ID, String name, String lastname){
        this.ID = ID;
        this.nombres = name;
        this.apellidos = lastname;
    }
    
    public Persona(String ID, String name, String lastname, String documentId, String phone, String address){
        this.ID = ID;
        this.nombres = name;
        this.apellidos = lastname;
        this.dni = documentId;
        this.telefono = phone;
        this.direccion = address;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    //public abstract void saludar();
}
