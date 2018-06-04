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
public class Grzejnik extends UrzadzenieElektryczne implements WlWyl, Serializable {

    public Grzejnik() {
        //TOOOOOOO DOOOOOOO zmienic wartosc na poprawna
        this.zuzycie = 60;
    }



    
    //Przy właczeniu i wyłaczeniu zmienna zuzycieEnergii z obiektu stan zmienia sie automatycznie
    @Override
    public void on(Stan stan) {
        stan.stOgrzewanie = true;
        this.zwieksz(stan);
    }

    @Override
    public void off(Stan stan) {
        stan.stOgrzewanie = false;
        this.zmniejsz(stan);
    }

    
    
    
    
    
}
