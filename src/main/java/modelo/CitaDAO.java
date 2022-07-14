/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;

public class CitaDAO {
    Conexion conexion;
    public CitaDAO() {
        conexion = new Conexion();
    }
    
    public ArrayList<Cita> listCita(){
        ArrayList listaCita = new ArrayList();
        Cita cita;
        try{
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT "
                    + "C.ID, "
                    + "R.NOMBRES NOMBRE_RECEPCIONISTA, "
                    + "R.APELLIDOS APELLIDO_RECEPCIONISTA, "
                    + "V.NOMBRES NOMBRE_VETERINARIO, "
                    + "V.APELLIDOS APELLIDO_VETERINARIO, "
                    + "CC.NOMBRES NOMBRE_CLIENTE, "
                    + "CC.APELLIDOS APELLIDO_CLIENTE, "
                    + "M.NOMBRE NOMBRE_MASCOTA, "
                    + "C.ATENCION "
                    + "FROM CITAS C "
                    + "INNER JOIN RECEPCIONISTAS R ON C.RECEPCIONISTA=R.ID "
                    + "INNER JOIN VETERINARIOS V ON C.VETERINARIO=V.ID "
                    + "INNER JOIN CLIENTES CC ON C.CLIENTE=CC.ID "
                    + "INNER JOIN MASCOTAS M ON C.MASCOTA=M.ID ");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cita = new Cita(
                        rs.getString(1),
                        new Recepcionista(rs.getString(2), rs.getString(3)), 
                        new Veterinario(rs.getString(4), rs.getString(5)), 
                        new Cliente(rs.getString(6), rs.getString(7)),
                        new Mascota(rs.getString(8)),
                        rs.getString(9)
                );
                listaCita.add(cita);
            }
                      
        }catch(Exception e){}
        
        return listaCita;
    }
    
    public Boolean insertCita(String recepcionista, String veterinario, String cliente, String mascota, String atencion){
        Boolean rptaRegistro=false;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_CITA(?, ?, ?, ?, ?) }");
            
            cs.setString(1, recepcionista.split(":")[0]);
            cs.setString(2, veterinario.split(":")[0]);
            cs.setString(3, cliente.split(":")[0]);
            cs.setString(4, mascota.split(":")[0]);
            cs.setString(5, atencion);
            
            int numFAfectadas=cs.executeUpdate();
            if (numFAfectadas>0){
                rptaRegistro=true;
            }
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return rptaRegistro;
    }
    
    public int editarcliente(String nombres,String apellidos,String dni, String telefono, String direccion, String email){
        int numFA =0;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_EDITAR_CLIENTE(?,?,?,?,?,?) }");
            cs.setString(1, nombres);
            cs.setString(2, apellidos);
            cs.setString(3, dni);
            cs.setString(4, telefono);
            cs.setString(5, direccion);
            cs.setString(6, email);
            numFA =cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }

        return numFA; 
    }
    
    public int eliminarcliente(String dni){
        int numFA=0;
        try{
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_ELIMINAR_CLIENTE(?) }");
            cs.setString(1, dni); 
            numFA=cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        return numFA;
    }
}
