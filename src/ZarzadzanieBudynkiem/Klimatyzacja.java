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
public class Klimatyzacja extends UrzadzenieElektryczne implements WlWyl, ZuzycieEnergii, Serializable {

    public Klimatyzacja() {
        this.zuzycie = 9;
    }

    //Przy właczeniu i wyłaczeniu zmienna zuzycieEnergii z obiektu stan zmienia sie automatycznie
    @Override
    public void on(Stan stan) {
        stan.stKlimatyzacja = true;
        this.zwieksz(stan);
    }

    @Override
    public void off(Stan stan) {
        stan.stKlimatyzacja = false;
        this.zmniejsz(stan);
    }

}
