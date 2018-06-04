/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZarzadzanieBudynkiem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hubii
 */
public class Usterka implements Serializable{
    public String nazwa, opis;
    

    public Usterka(ArrayList<Usterka> listaUsterek,String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
        
        listaUsterek.add(this);
    }
    
    
    
    
   
    
    public static ArrayList<Usterka> odczytUsterki(ArrayList<Usterka> lista){
        File f = new File("usterki.bin");
        FileInputStream fin = null;
        ObjectInputStream o = null;
        System.out.println(f.exists());
        if (f.exists()) {
            System.out.println("Plik usterki istnieje");
            try {
                fin = new FileInputStream(f);
                o = new ObjectInputStream(fin);

                lista = (ArrayList<Usterka>) o.readObject();
                

                System.out.println("Usterki podmienione");

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (o != null) {
                    try {
                        o.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (fin != null) {
                    try {
                        fin.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            System.out.println(lista);
        }
        return lista;
    }
    
    public static void zapiszUsterki(ArrayList<Usterka> lista){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream("usterki.bin");
            oos = new ObjectOutputStream(fout);

            oos.writeObject(lista);
            System.out.println(lista);

        } catch (IOException ex) {
            Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(fout!=null){
                try {
                    fout.close();
                } catch (IOException ex) {
                    Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
