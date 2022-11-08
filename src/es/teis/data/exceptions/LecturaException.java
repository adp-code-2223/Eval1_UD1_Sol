/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.data.exceptions;

/**
 *
 * @author maria
 */
public class LecturaException extends Exception {

    private String rutaFichero;

    public LecturaException(String string, String rutaFichero) {

        super(string);
        this.rutaFichero = rutaFichero;
    }

    public String getRutaFichero() {
        return rutaFichero;
    }
    
    

}
