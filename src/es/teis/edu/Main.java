/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.teis.edu;

import es.teis.data.IPersistencia;
import es.teis.data.PartidoObjectPersistencia;
import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.DOMXMLService;
import es.teis.model.Partido;
import java.nio.file.Paths;
import java.util.ArrayList;
import es.teis.dataXML.IXMLService;

/**
 *
 * @author maria
 */
public class Main {

    private static String ELECCIONES_INPUT_FILE = Paths.get("src", "docs", "elecciones.xml").toString();
    private static String ELECCIONES_OUTPUT_FILE = Paths.get("src", "docs", "elecciones_output.dat").toString();

    private static float UMBRAL_PORCENTAJE = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            IXMLService servicioXML = new DOMXMLService();
            ArrayList<Partido> partidos = servicioXML.leerPartidos(ELECCIONES_INPUT_FILE, UMBRAL_PORCENTAJE);

            IPersistencia objectPersistencia = new PartidoObjectPersistencia();
            objectPersistencia.escribir(partidos, ELECCIONES_OUTPUT_FILE);

            partidos = objectPersistencia.leerTodo(ELECCIONES_OUTPUT_FILE);

            mostrar(partidos);

        } catch (LecturaException ex) {
            System.err.println("Se ha detectado un problema de lectura: " + ex.getMessage() + " La ruta del fichero que ha causado el problema es: " + ex.getRutaFichero());
        } catch (Exception ex) {
            System.err.println("Se ha detectado un problema: " + ex.getMessage());
        }

    }

    private static void mostrar(ArrayList<Partido> partidos) {
        System.out.println("Se han leído: ");
        for (Partido partido : partidos) {
            System.out.println(partido);

        }
    }

}
