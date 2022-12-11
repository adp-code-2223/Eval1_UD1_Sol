/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.dataXML;

import es.teis.data.exceptions.LecturaException;
import es.teis.model.Partido;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author maria
 */
public class SAXXMLService implements IXMLService {

    @Override
    public ArrayList<Partido> leerPartidos(String ruta) throws LecturaException {
        ArrayList<Partido> partidos = new ArrayList<Partido>();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            //saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File(ruta);
            PartidosHandler handler = new PartidosHandler();
            saxParser.parse(file, handler);
            partidos = handler.getPartidos();
        } catch (Exception ex) {
            throw new LecturaException(ex.getMessage(), ruta);

        }
        return partidos;
    }

    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
