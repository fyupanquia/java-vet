/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Mascota extends Animal implements IAnimal {
    private String ID;
    private String nombre;
    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public Mascota(String ID, String nombre){
        this.ID = ID;
        this.nombre = nombre;
    }
    public Mascota(String ID, String nombre, String edad, String sexo, String color, String raza, String peso, String altura, String foto){
        super.setEdad(Integer.parseInt(edad));
        super.setSexo(sexo);
        super.setColor(color);
        super.setRaza(raza);
        super.setPeso(Double.parseDouble(peso));
        super.setAltura(Double.parseDouble(altura));
        this.ID = ID;
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void quejar() {
        System.out.println("La mascota se queja");
    }

}
