
package modelo;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {
    Conexion conexion;
    public ClienteDAO() {
        conexion = new Conexion();
   }
    
    public ArrayList<Cliente> listCliente(){
        ArrayList listaCliente = new ArrayList();
        Cliente cliente;
        try{
            Connection accesoDB =conexion.getConnection();
            PreparedStatement ps=accesoDB.prepareStatement("SELECT * FROM CLIENTES");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) );
                cliente.setEmail(rs.getString(7));
                listaCliente.add(cliente);
            }
                      
        }catch(Exception e){}
        
        return listaCliente;
    }
    
    public Boolean insertCliente(String nombres,String apellidos,String dni, String telefono, String direccion, String email){
        Boolean rptaRegistro=false;
        try {
            Connection accesoDB = conexion.getConnection();
            CallableStatement cs=accesoDB.prepareCall("{ call SP_INSERTAR_CLIENTE(?, ?, ?, ?, ?, ?) }");
            
            System.out.println("nombres: "+ nombres);
            System.out.println("apellidos: "+ apellidos);
            System.out.println("dni: "+ dni);
            System.out.println("telefono: "+ telefono);
            System.out.println("direccion: "+ direccion);
            System.out.println("email: "+ email);
            
            cs.setString(1, nombres);
            cs.setString(2, apellidos);
            cs.setString(3, dni);
            cs.setString(4, telefono);
            cs.setString(5, direccion);
            cs.setString(6, email);
            
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
