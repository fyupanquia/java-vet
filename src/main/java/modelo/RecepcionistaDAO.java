package modelo;

import java.sql.*;
import java.util.ArrayList;

public class RecepcionistaDAO {
     Conexion conexion;
    public RecepcionistaDAO() {
        conexion = new Conexion();
   }
    
    public ArrayList<Recepcionista> listRecepcionista(){
        ArrayList listaCliente = new ArrayList();
        Recepcionista recepcionista;
        
        try{
            
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT * FROM RECEPCIONISTAS");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                recepcionista = new Recepcionista(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) );
                listaCliente.add(recepcionista);
            }

        }catch(SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        
        return listaCliente;
    }
    
    public Boolean insertRecepcionista(String nombres,String apellidos,String dni, String telefono, String direccion){
        Boolean rptaRegistro=false;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_RECEPCIONISTA(?, ?, ?, ?, ?) }");
            
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
    
    public int editarRecepcionista(String nombres,String apellidos,String dni, String telefono, String direccion){
        int numFA =0;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_EDITAR_RECEPCIONISTA(?,?,?,?,?) }");
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
    
    public int eliminarRecepcionista(String dni){
        int numFA=0;
        try{
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs = accesoDB.prepareCall("{ call SP_ELIMINAR_RECEPCIONISTA(?) }");
            cs.setString(1, dni); 
            numFA=cs.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: "+ e.getMessage());
        }
        return numFA;
    }
}
