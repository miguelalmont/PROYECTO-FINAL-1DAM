/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author migue
 */
public class Nota {
    
    private String tema, contenido;

    public Nota() {
    }

    public Nota(String tema, String contenido) {
        this.tema = tema;
        this.contenido = contenido;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Nota{" + "tema=" + tema + ", contenido=" + contenido + '}';
    }

}
