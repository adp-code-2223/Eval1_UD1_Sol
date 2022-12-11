/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.teis.edu;

import es.teis.data.CSVPersistencia;
import es.teis.data.IPersistencia;
import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.DOMXMLService;
import es.teis.model.Partido;
import java.nio.file.Paths;
import java.util.ArrayList;
import es.teis.dataXML.IXMLService;
import es.teis.dataXML.SAXXMLService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author maria
 */
public class MainUD1 {

    private static final String ELECCIONES_INPUT_FILE = Paths.get("src", "docs", "elecciones.xml").toString();
    private static final String ELECCIONES_OUTPUT_FILE_XML = Paths.get("src", "docs", "elecciones_output.xml").toString();

    private static final String ELECCIONES_OUTPUT_FILE_CSV_BK = Paths.get("src", "docs", "elecciones_output.csv.bk").toString();
    private static final String ELECCIONES_OUTPUT_FILE_CSV = Paths.get("src", "docs", "elecciones_output.csv").toString();

   
    public static void main(String[] args) {

        ArrayList<Partido> partidos;
        try {
            partidos = leerPartidosConSAX();
            escribirIdPartidosCSV(partidos);
            escribirPartidosConDOM(partidos);
        } catch (LecturaException ex) {
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
        }

    }

    private static void mostrar(ArrayList<Partido> partidos) {
        System.out.println("Se han leído los siguientes partidos: ");
        for (Partido partido : partidos) {
            System.out.println(partido);

        }
    }

    private static ArrayList<Partido> leerPartidosConSAX() throws LecturaException {
        IXMLService servicioXML = new SAXXMLService();
        ArrayList<Partido> partidos = servicioXML.leerPartidos(ELECCIONES_INPUT_FILE);
        mostrar(partidos);
        return partidos;
    }

    private static void escribirPartidosConDOM(ArrayList<Partido> partidos) {

        IXMLService domXMLService = new DOMXMLService();
        domXMLService.escribir(partidos, ELECCIONES_OUTPUT_FILE_XML);

    }

    private static void escribirIdPartidosCSV(ArrayList<Partido> partidos) {
        IPersistencia csvPersistencia = new CSVPersistencia();
        csvPersistencia.escribir(partidos, ELECCIONES_OUTPUT_FILE_CSV);
        if (Files.exists(Paths.get(ELECCIONES_OUTPUT_FILE_CSV))) {
            try {
                Files.copy(Paths.get(ELECCIONES_OUTPUT_FILE_CSV), Paths.get(ELECCIONES_OUTPUT_FILE_CSV_BK), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            }
        }
    }
}
