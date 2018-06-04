package ZarzadzanieBudynkiem;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stan implements Serializable {

    public static final int TEMP = 22;

    public int iloscOsobWBudynku = 0;

    public int iloscWlaman = 0;

    // true - właczone/otwarta       false - wyłaczone/zamkniete
    public boolean stDrzwi = false, stTylnichDrzwi = false, stGaraz = false, stZasilanie = false, stOgrzewanie = false, stKlimatyzacja = false;
    // TODO ustawic swiatla domyslnie na false!

    // 0 - zewnętrzne; 1 - kuchnia; 2 - korytarz; 3 - pokoj dziecka; 4 - sypialnia; 5 - salon
    public boolean[] stSwiatlo = new boolean[6];

    public int tempPowietrza = 22;

    public int zuzycieEnergi = 0;

    public Stan() {
        // - Konstruktor pobiera obiekt z pliku stan.bin i podmienia wartosci ww. zmiennych
        // - jesli plik stan.bin nie istnieje to zostawia nie robi nic
        System.out.println("Sprawdzanie stanu pliku");
        File f = new File("stan.bin");
        FileInputStream fin = null;
        ObjectInputStream o = null;
        System.out.println(f.exists());
        if (f.exists()) {
            System.out.println("Plik istnieje");
            try {
                fin = new FileInputStream(f);
                o = new ObjectInputStream(fin);

                Stan wczytanyStan;
                wczytanyStan = (Stan) o.readObject();

                this.iloscOsobWBudynku = wczytanyStan.iloscOsobWBudynku;
                this.iloscWlaman = wczytanyStan.iloscWlaman;
                this.stDrzwi = wczytanyStan.stDrzwi;
                this.stTylnichDrzwi = wczytanyStan.stTylnichDrzwi;
                this.stGaraz = wczytanyStan.stGaraz;
                this.stZasilanie = wczytanyStan.stZasilanie;
                this.stOgrzewanie = wczytanyStan.stOgrzewanie;
                this.stKlimatyzacja = wczytanyStan.stKlimatyzacja;
                System.arraycopy(wczytanyStan.stSwiatlo, 0, this.stSwiatlo, 0, stSwiatlo.length);
                this.tempPowietrza = wczytanyStan.tempPowietrza;
                this.zuzycieEnergi = wczytanyStan.zuzycieEnergi;
                this.iloscWlaman = wczytanyStan.iloscWlaman;
                System.out.println("Stany podmienione");

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

        }

    } 

    public void zapiszStan() {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream("stan.bin");
            oos = new ObjectOutputStream(fout);

            oos.writeObject(this);

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
    } //todo

    public void wlamanie() {
        // Po wywolaniu gasi swiatla i zamyka drzwi.
        // sprawdzic jak wplywa na przyciski
        iloscWlaman++;
        for (int i = 0; i < 6; i++) {
            offSwiatlo(i);
        }
        stDrzwi = false;
        stTylnichDrzwi = false;
        stGaraz = false;

    }

    public void ustawTemp(int temp) {
        // ustawia temperature potwietrza tworzac nowy obiekt klimy/ogrzewania i zmieniajac jego status w obiekcie stan

        //usawienie stanu temperatury z parametru
        Klimatyzacja klima = new Klimatyzacja();
        Grzejnik grzejnik = new Grzejnik();

        if (temp == TEMP) {
            if (stOgrzewanie) {
                stOgrzewanie = false;
                grzejnik.off(this);
            }
            if (stKlimatyzacja) {
                stKlimatyzacja = false;
                klima.off(this);
            }

        }

        if (temp < TEMP) {
            //wlącza klimatyzacje jesli jest wyłączona
            //wyłącza grzejnik jesli jest włączony
            if (!this.stKlimatyzacja) {
                klima.on(this);
            }

            if (this.stOgrzewanie) {
                grzejnik.off(this);
            }
        }

        if (temp > TEMP) {
            // odwrotnie niz wyzej
            //
            if (!this.stOgrzewanie) {
                grzejnik.on(this);
            }

            if (this.stKlimatyzacja) {
                klima.off(this);
            }
        }
        this.tempPowietrza = temp;
    }

    public void toggleSwiatlo(int nr) {
        Swiatlo swiatlo = new Swiatlo(nr);

        if (this.stSwiatlo[nr]) {
            swiatlo.off(this);
        } else {
            swiatlo.on(this);
        }
    }

    public void offSwiatlo(int nr) {
        Swiatlo swiatlo = new Swiatlo(nr);
        swiatlo.off(this);
        //moze pojebac zuzycie energii

    }

    public void toggleDrzwi(int nr) {
        // 1 glowne
        // 2 tylne
        // 3 garaz

        if (nr == 1) {
            this.stDrzwi = !this.stDrzwi;
        }
        if (nr == 2) {
            this.stTylnichDrzwi = !this.stTylnichDrzwi;
        }
        if (nr == 3) {
            this.stGaraz = !this.stGaraz;
        }

    }

}
