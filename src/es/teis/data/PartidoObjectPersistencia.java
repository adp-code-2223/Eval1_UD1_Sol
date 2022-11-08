/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.data;

import es.teis.model.Partido;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class PartidoObjectPersistencia implements IPersistencia {

    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {

        try (
                 FileOutputStream fos = new FileOutputStream(ruta);  ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            for (Partido p : partidos) {
                oos.writeObject(p);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero: " + ruta);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("No se ha podido leer el fichero: " + ruta);
        }
    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {
        boolean EOF = false;

        ArrayList<Partido> partidos = new ArrayList<>();
        try (
                 FileInputStream fis = new FileInputStream(ruta);  ObjectInputStream ois = new ObjectInputStream(fis);) {

            while (!EOF) {
                try {
                    Object o = ois.readObject();

                    if (o instanceof Partido) {
                        Partido partido = (Partido) o;
                        partidos.add(partido);

                    }
                } catch (EOFException eof) {
                    EOF = true;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return partidos;

    }
}
