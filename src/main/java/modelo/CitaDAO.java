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
                    + "R.ID ID_RECEPCIONISTA, "
                    + "R.NOMBRES NOMBRE_RECEPCIONISTA, "
                    + "R.APELLIDOS APELLIDO_RECEPCIONISTA, "
                    + "V.ID ID_VETERINARIO, "
                    + "V.NOMBRES NOMBRE_VETERINARIO, "
                    + "V.APELLIDOS APELLIDO_VETERINARIO, "
                    + "CC.ID ID_CLIENTE, "
                    + "CC.NOMBRES NOMBRE_CLIENTE, "
                    + "CC.APELLIDOS APELLIDO_CLIENTE, "
                    + "M.ID ID_MASCOTA, "
                    + "M.NOMBRE NOMBRE_MASCOTA, "
                    + "C.ATENCION, "
                    + "C.FECHA "
                    + "FROM CITAS C "
                    + "INNER JOIN RECEPCIONISTAS R ON C.RECEPCIONISTA=R.ID "
                    + "INNER JOIN VETERINARIOS V ON C.VETERINARIO=V.ID "
                    + "INNER JOIN CLIENTES CC ON C.CLIENTE=CC.ID "
                    + "INNER JOIN MASCOTAS M ON C.MASCOTA=M.ID "
                    + "ORDER BY C.ID DESC");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cita = new Cita(
                        rs.getString(1),
                        new Recepcionista(rs.getString(2), rs.getString(3), rs.getString(4)), 
                        new Veterinario(rs.getString(5), rs.getString(6), rs.getString(7)), 
                        new Cliente(rs.getString(8), rs.getString(9), rs.getString(10)),
                        new Mascota(rs.getString(11), rs.getString(12)),
                        rs.getString(13), rs.getString(14)
                );
                listaCita.add(cita);
            }
                      
        }catch(Exception e){}
        
        return listaCita;
    }
    
    public Boolean insertCita(String recepcionista, String veterinario, String cliente, String mascota, String atencion, String fecha){
        Boolean rptaRegistro=false;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_CITA(?,?,?,?,?,?) }");
            
            cs.setString(1, recepcionista.split(":")[0]);
            cs.setString(2, veterinario.split(":")[0]);
            cs.setString(3, cliente.split(":")[0]);
            cs.setString(4, mascota.split(":")[0]);
            cs.setString(5, atencion);
            cs.setString(6, fecha);
            
            int numFAfectadas=cs.executeUpdate();
            if (numFAfectadas>0){
                rptaRegistro=true;
            }
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return rptaRegistro;
    }
    
    public int editarCita(String ID, String recepcionista, String veterinario, String cliente, String mascota, String atencion, String fecha){
        int numFA =0;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_EDITAR_CITA(?,?,?,?,?,?,?) }");
            cs.setString(1, ID);
            cs.setString(2, recepcionista.split(":")[0]);
            cs.setString(3, veterinario.split(":")[0]);
            cs.setString(4, cliente.split(":")[0]);
            cs.setString(5, mascota.split(":")[0]);
            cs.setString(6, atencion);
            cs.setString(7, fecha);
            numFA =cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }

        return numFA; 
    }
    
    public int eliminarCita(String ID){
        int numFA=0;
        try{
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_ELIMINAR_CITA(?) }");
            cs.setString(1, ID); 
            numFA=cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        return numFA;
    }
}
