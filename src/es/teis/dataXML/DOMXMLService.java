/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.dataXML;

import es.teis.data.exceptions.LecturaException;
import es.teis.model.Partido;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author maria
 */
public class DOMXMLService implements IXMLService {
/***
 * 
 * @param ruta ruta al fichero XML que se leerá
 * @param umbral porcentaje mínimo  de votos para obtener ser incluido en el resultado
 * @return ArrayList<Partido> que superan el umbral 
 * @throws LecturaException en caso de que surja cualquier excepción durante la lectura
 */
    @Override
    public ArrayList<Partido> leerPartidos(String ruta, float umbral) throws LecturaException {
        try {
            float votos_porc;
            int num_votos;
            long id;
            int api = 0;
            String nombre;
            Partido partido = null;
            ArrayList<Partido> partidos = new ArrayList<>();

            File inputFile = new File(ruta);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            //elimina hijos con texto vacío y fusiona en un único nodo de texto varios adyacentes.
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(PARTIDO_TAG);

            System.out.println("----------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    votos_porc = Float.parseFloat(eElement.getElementsByTagName(PARTIDO_VOTOS_PORC_TAG).item(0).getTextContent());
                    if (votos_porc > umbral) {

                        id = Long.parseLong(eElement.getAttribute(PARTIDO_ATT_ID));
                     
                        nombre = eElement.getElementsByTagName(PARTIDO_NOMBRE_TAG).item(0).getTextContent();
                        num_votos = Integer.parseInt(eElement.getElementsByTagName(PARTIDO_VOTOS_NUM_TAG).item(0).getTextContent());
                        partido = new Partido(id, nombre, num_votos, votos_porc);

                        partidos.add(partido);
                    }

                }
            }
            return partidos;

        } catch (Exception ex) {
            throw new LecturaException(ex.getMessage(), ruta);
        }
    }

}
