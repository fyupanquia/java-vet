/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
/**
 *
 * @author USUARIO
 */
public class Cita {
    private String ID;
    private Mascota mascota;
    private Cliente cliente;
    private Recepcionista recepcionista;
    private Veterinario veterinario;
    private String tipoAtencion;

    public Cita( String ID, Recepcionista recepcionista, Veterinario veterinario,  Cliente cliente, Mascota mascota, String tipoAtencion) {
        this.ID = ID;
        this.mascota = mascota;
        this.cliente = cliente;
        this.recepcionista = recepcionista;
        this.veterinario = veterinario;
        this.tipoAtencion = tipoAtencion;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(String tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }
    
}
