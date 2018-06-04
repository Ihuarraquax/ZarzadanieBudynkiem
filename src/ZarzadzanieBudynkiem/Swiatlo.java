package ZarzadzanieBudynkiem;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hubii
 */
public class Swiatlo extends UrzadzenieElektryczne implements WlWyl, ZuzycieEnergii, Serializable {

    int nr;

    public Swiatlo(int nr) {

        this.nr = nr;
        if (nr == 2 || nr == 3 || nr == 4) {
            this.zuzycie = 2;
        }
        if (nr == 1 || nr == 5) {
            this.zuzycie = 3;
        }
        if (nr == 0) {
            this.zuzycie = 5;
        }

    }

    @Override
    public void on(Stan stan) {
        stan.stSwiatlo[nr] = true;
        this.zwieksz(stan);
    }

    @Override
    public void off(Stan stan) {
        if(stan.stSwiatlo[nr]){
            this.zmniejsz(stan);
        }
        stan.stSwiatlo[nr] = false;
    }

}
