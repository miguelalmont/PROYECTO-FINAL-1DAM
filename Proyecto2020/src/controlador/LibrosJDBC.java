/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;
import vista.Login;

/**
 *
 * @author migue
 */
public class LibrosJDBC extends conexion.Conexion{
    
    private final String SQL_INSERT
            = "INSERT INTO libros(ISBN, autor, titulo, editorial, anio, n_paginas, user_libro) VALUES(?,?,?,?,?,?,?)";

    private final String SQL_UPDATE
            = "UPDATE libros SET ISBN = ?, autor = ?, titulo = ?, editorial = ?, anio = ?, n_paginas = ? WHERE ISBN = ? AND user_libro = ?";

    private final String SQL_DELETE
            = "DELETE FROM libros WHERE ISBN = ? AND user_libro = ?";

    private final String SQL_SELECT
            = "SELECT ISBN, autor, titulo, editorial, anio, n_paginas FROM libros WHERE user_libro = ? ORDER BY ISBN";
    
    public int insert(Libro libro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio		
        int rows = 0; //registros afectados
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setString(index++, libro.getISBN());//param 1 => ?
            stmt.setString(index++, libro.getAutor());//param 2 => ?
            stmt.setString(index++, libro.getTitulo());
            stmt.setString(index++, libro.getEditorial());
            stmt.setInt(index++, libro.getAnio());
            stmt.setInt(index++, libro.getnPaginas());
            stmt.setInt(index, Login.user.getId());
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//no. registros afectados
            System.out.println("Registros afectados:" + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(conn);
        }
        return rows;
    }
    
    public int update(Libro libro, String iSBNold) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, libro.getISBN());
            stmt.setString(index++, libro.getAutor());
            stmt.setString(index++, libro.getTitulo());
            stmt.setString(index++, libro.getEditorial());
            stmt.setInt(index++, libro.getAnio());
            stmt.setInt(index++, libro.getnPaginas());
            stmt.setString(index++, iSBNold);
            stmt.setInt(index, Login.user.getId());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(conn);
        }
        return rows;
    }
    
    public int delete(String iSBN) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            int index = 1;
            stmt.setString(index++, iSBN);
            stmt.setInt(index, Login.user.getId());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(conn);
        }
        return rows;
    }
    
    public List<Libro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Libro libro = null;
        List<Libro> libros = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, Login.user.getId());
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
            close(rs);
            close(stmt);
            close(conn);
        }
        return libros;
    }
    
    public int existeISBN(String iSBN) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT count(ISBN) FROM libros WHERE ISBN = ?";
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, iSBN);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                return rs.getInt(1);
            }
            else {
                return 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return 1;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }
}
