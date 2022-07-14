/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class QueryGenerator {
    public static Boolean guardar(String tabla, String[] campos, String[] valores){
        try {
            System.out.println("Insertando en la tabla: "+ tabla+" (PROCESANDO)");
            
            Conexion bd = new Conexion();
            String sql = "INSERT INTO "+ tabla +"(CAMPOS) VALUES (VALORES)";
            String strCampos = "";
            String strValores = "";
            for (int i = 0; i < campos.length; i++) {
                strCampos += campos[i];
                strValores += "?";
                if (i!=campos.length-1) {
                    strCampos += ", ";
                    strValores += ", ";
                }
            }
            sql = sql.replace("CAMPOS", strCampos);
            sql = sql.replace("VALORES", strValores);
            
            System.err.println("sql: "+ sql);
            
            PreparedStatement ps = bd.getConnection().prepareStatement(sql);
            for (int i = 0; i < valores.length; i++) {
                ps.setString(i + 1, valores[i]);
            }
            
            ps.execute();
            System.out.println("Insertando en la tabla: "+ tabla+" (CORRECTO)");
            return true;
        } catch (SQLException e){
            System.out.println("Insertando en la tabla: "+ tabla+" (ERROR)");
            System.err.println("SQLException: "+ e.getMessage());
            return false;
        }        
    }
}
