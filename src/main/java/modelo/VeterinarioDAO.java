/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;

public class VeterinarioDAO {
     Conexion conexion;
    public VeterinarioDAO() {
        conexion = new Conexion();
   }
    
    public ArrayList<Veterinario> listVeterinario(){
        ArrayList listaVeterinarios = new ArrayList();
        Veterinario veterinario;
        
        try{
            
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT * FROM VETERINARIOS ORDER BY ID DESC");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                veterinario = new Veterinario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) );
                listaVeterinarios.add(veterinario);
            }

        }catch(SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return listaVeterinarios;
    }
    
    public Boolean insertVeterinario(String nombres,String apellidos,String dni, String telefono, String direccion){
        Boolean rptaRegistro=false;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_VETERINARIO(?, ?, ?, ?, ?) }");
            
            cs.setString(1, nombres);
            cs.setString(2, apellidos);
            cs.setString(3, dni);
            cs.setString(4, telefono);
            cs.setString(5, direccion);
            
            int numFAfectadas=cs.executeUpdate();
            if (numFAfectadas>0){
                rptaRegistro=true;
            }
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return rptaRegistro;
    }
    
    public int editarVeterinario(String nombres,String apellidos,String dni, String telefono, String direccion){
        int numFA =0;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_EDITAR_VETERINARIO(?,?,?,?,?) }");
            cs.setString(1, nombres);
            cs.setString(2, apellidos);
            cs.setString(3, dni);
            cs.setString(4, telefono);
            cs.setString(5, direccion);
            numFA =cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }

        return numFA; 
    }
    
    public int eliminarVeterinario(String dni){
        int numFA=0;
        try{
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_ELIMINAR_VETERINARIO(?) }");
            cs.setString(1, dni); 
            numFA=cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        return numFA;
    }
}
