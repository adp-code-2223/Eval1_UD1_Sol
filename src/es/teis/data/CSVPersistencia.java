/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.data;

import es.teis.model.Partido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class CSVPersistencia implements IPersistencia {

    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {

        try ( FileWriter fw = new FileWriter(ruta);  BufferedWriter bf = new BufferedWriter(fw);) {

           

            String cadenaCSV = partidosListToCSVIds(partidos);
            
            
            bf.write(cadenaCSV);
        } catch (IOException ex) {
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
        }

    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/**
 * Recibe una lista de objetos Partido, obtiene sus ids y los convierte a cadena de texto separados por comas
 * @param partidos
 * @return una lista separada por comas de los ids de los partidos 
 */
    private String partidosListToCSVIds(ArrayList<Partido> partidos) {
        String[] partidosArray = new String[partidos.size()];
        for (int i = 0; i < partidos.size(); i++) {
            partidosArray[i] = String.valueOf(partidos.get(i).getId());
        }

        String cadenaCSV = String.join(",", partidosArray);
        return cadenaCSV;
    }

}
