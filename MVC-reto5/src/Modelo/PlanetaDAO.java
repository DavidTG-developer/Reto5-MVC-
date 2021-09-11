
package Modelo;
/**
 *
 * @author David Tellez
 */

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlanetaDAO {
    
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Planeta p =new Planeta();
    
    public Planeta listarNom(String nom){
        Planeta p = new Planeta();
        List<Planeta>datos=new ArrayList<>();
        String sql = "SELECT * FROM planeta WHERE nombre=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, nom);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setCodigo(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPoblacion(rs.getInt(3));
                p.setNivelOxigeno(rs.getFloat(4));
                p.setHabitabilidad(rs.getBoolean(5));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return p;
    }
    public List listar(){
        List<Planeta>datos=new ArrayList<>();
        String sql = "SELECT * FROM planeta";
        try{
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Planeta p= new Planeta();
                p.setCodigo(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPoblacion(rs.getInt(3));
                p.setNivelOxigeno(rs.getFloat(4));
                p.setHabitabilidad(rs.getBoolean(5));
                datos.add(p);
            }
        }catch (Exception e){
            
        }
        return datos;
    }
    public int agregar(Planeta p){
        int r=0;
        String sql = "INSERT INTO planeta(nombre, poblacion, nivel_oxigeno, planeta_habitable) VALUES (?,?,?,?);";
        try {
            con = conectar.getConnection();
            ps=con.prepareStatement(sql);
            //ps.setInt(1,p.getCodigo());
            ps.setString(1, p.getNombre());
            ps.setInt(2,p.getPoblacion());
            ps.setFloat(3,p.getNivelOxigeno());
            ps.setBoolean(4, p.isHabitabilidad());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    public int Actualizar(Planeta p){
        int r=0;
        String sql = "UPDATE planeta SET nombre=?, poblacion=?, nivel_oxigeno=?, planeta_habitable=? WHERE cod_planeta = ?;";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2,p.getPoblacion());
            ps.setFloat(3,p.getNivelOxigeno());
            ps.setBoolean(4, p.isHabitabilidad());
            ps.setInt(5, p.getCodigo());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    public void delete(int id){
        String sql = "DELETE FROM planeta WHERE cod_planeta="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
