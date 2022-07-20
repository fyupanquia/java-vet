
package modelo;

import java.sql.*;
import java.util.ArrayList;

public class MascotaDAO {
    Conexion conexion;
    public MascotaDAO() {
        conexion = new Conexion();
    }
    
    public Mascota findtMascota(String id){
        Mascota mascota = null;
        
        try{
            
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT * FROM MASCOTAS WHERE ID='"+id+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                mascota = new Mascota(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
            
        }catch(SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return mascota;
    }
    
    public ArrayList<Mascota> listMascota(){
        ArrayList listaMascota = new ArrayList();
        Mascota mascota;
        
        try{
            
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT * FROM MASCOTAS ORDER BY ID DESC");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                mascota = new Mascota(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                listaMascota.add(mascota);
            }

        }catch(SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return listaMascota;
    }
    
    public Boolean insertMascota(String nombre, String edad, String sexo, String color, String raza, String peso, String altura, String filename){
        Boolean rptaRegistro=false;
        
        try {
            
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_MASCOTA(?,?,?,?,?,?,?,?) }");
            
            cs.setString(1, nombre);
            cs.setString(2, edad);
            cs.setString(3, sexo);
            cs.setString(4, color);
            cs.setString(5, raza);
            cs.setString(6, peso);
            cs.setString(7, altura);
            cs.setString(8, filename);
            
            int numFAfectadas=cs.executeUpdate();
            if (numFAfectadas>0){
                rptaRegistro=true;
            }
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return rptaRegistro;
    }
    
    public int editarMascota(String id,String nombre,String edad, String sexo, String color, String raza, String peso, String altura, String filename){
        int numFA =0;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_EDITAR_MASCOTA(?,?,?,?,?,?,?,?,?) }");
            cs.setString(1, id);
            cs.setString(2, nombre);
            cs.setString(3, edad);
            cs.setString(4, sexo);
            cs.setString(5, color);
            cs.setString(6, raza);
            cs.setString(7, peso);
            cs.setString(8, altura);
            cs.setString(9, filename);
            numFA =cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }

        return numFA; 
    }
    
    public int eliminarMascota(String ID){
        int numFA=0;
        try{
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_ELIMINAR_MASCOTA(?) }");
            cs.setString(1, ID);
            numFA=cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        return numFA;
    }
}
