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
public class User implements Serializable {

    String login, pass;

    public static ArrayList<User> odczytUser() {
        File f = new File("konta.bin");
        FileInputStream fin = null;
        ObjectInputStream o = null;
        System.out.println(f.exists());
        ArrayList<User> lista = new ArrayList<>();
        if (f.exists()) {
            System.out.println("Plik konta.bin istnieje");
            try {
                fin = new FileInputStream(f);
                o = new ObjectInputStream(fin);

                lista = (ArrayList<User>) o.readObject();

                System.out.println("lista kont podmieniona");

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

    public static void zapiszUser(ArrayList<User> lista) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream("konta.bin");
            oos = new ObjectOutputStream(fout);

            oos.writeObject(lista);
            System.out.println(lista);

        } catch (IOException ex) {
            Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException ex) {
                    Logger.getLogger(Stan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
