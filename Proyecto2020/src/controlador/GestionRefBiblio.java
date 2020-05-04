package controlador;

import vista.Inicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author migue
 */
public class GestionRefBiblio {
    
    public static Inicio ini;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
                /*LibrosJDBC librosJDBC = new LibrosJDBC();
                UsuariosJDBC usuariosJDBC = new UsuariosJDBC();*/
                
                
	//Prueba del metodo insert
       /* Usuario newUser = new Usuario();
        newUser.setUsuario("prueba");
            newUser.setPassword("prueba");
            newUser.setNombre("prueba");
            newUser.setMail("prueba");
        usuariosJDBC.insert(newUser); */
        
        //Prueba del metodo update
        //librosJDBC.update("01", "Michael Ende", "La Historia Interminable", "Salvat", "01");
		
        //Prueba del metodo delete
        //librosJDBC.delete("01");
      
        //Prueba del metodo select
        //Uso de un objeto libro para encapsular la informacion
        //de un registro de base de datos
        /*List<Libro> libros = librosJDBC.select();
        libros.stream().map((libro) -> {
            System.out.print(libro);
            return libro;
        }).forEachOrdered((_item) -> {
            System.out.println("");
        });*/
        
        /*List<Usuario> usuarios = usuariosJDBC.select();
        usuarios.stream().map((usuario) -> {
            System.out.print(usuario);
            return usuario;
        }).forEachOrdered((_item) -> {
            System.out.println("");
        });*/
            ini = new Inicio();
            ini.setVisible(true);
    }
}