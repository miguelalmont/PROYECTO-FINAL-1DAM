/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;

/**
 *
 * @author migue
 */
public class LibrosJDBC {
    
    private final String SQL_INSERT
            = "INSERT INTO libros(ISBN, autor, titulo) VALUES(?,?,?)";

    private final String SQL_UPDATE
            = "UPDATE libros SET ISBN = ?, autor = ?, titulo = ?, editorial = ? WHERE ISBN = ?";

    private final String SQL_DELETE
            = "DELETE FROM libros WHERE ISBN = ?";

    private final String SQL_SELECT
            = "SELECT ISBN, autor, titulo, editorial, anio, nPaginas FROM libros ORDER BY ISBN";
    
    public int insert(String iSBN, String autor, String titulo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio		
        int rows = 0; //registros afectados
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setString(index++, iSBN);//param 1 => ?
            stmt.setString(index++, autor);//param 2 => ?
            stmt.setString(index++, titulo);
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//no. registros afectados
            System.out.println("Registros afectados:" + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int update(String iSBNnew, String autor, String titulo, String editorial, String iSBNold) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, iSBNnew);
            stmt.setString(index++, autor);
            stmt.setString(index++, titulo);
            stmt.setString(index++, editorial);
            stmt.setString(index, iSBNold);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int delete(String iSBN) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, iSBN);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public List<Libro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Libro libro = null;
        List<Libro> libros = new ArrayList<Libro>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String iSBN = rs.getString(1);
                String autor = rs.getString(2);
                String titulo = rs.getString(3);
                String editorial = rs.getString(4);
                int anio = rs.getInt(5);
                int nPaginas = rs.getInt(6);
                /*System.out.print(" " + id_persona);
                 System.out.print(" " + nombre);
                 System.out.print(" " + apellido);
                 System.out.println();
                 */
                libro = new Libro();
                libro.setISBN(iSBN);
                libro.setAutor(autor);
                libro.setTitulo(titulo);
                libro.setEditorial(editorial);
                libro.setAnio(anio);
                libro.setnPaginas(nPaginas);
                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return libros;
    }
}
