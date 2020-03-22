package fi.nukkujat;

import java.util.List;

import static fi.nukkujat.Alyttomat.kuinkaHuonoLista;
import static fi.nukkujat.HelpMe.stringToCharList;

public class Main {

    /**
     * Main.
     *
     *  y = yä
     *  n = nukkuma
     *  v = vapaa
     *  m = muu kuin yävuoro
     *
     * @param args the args
     */
    public static void main(String args[]) {

        String randomTyovuorolista = HelpMe.rVuoroLista(20);
        String vuoden_1979_Finnair_tyovuoromalli = "mmmmmvvyyyyyvmmmmmvvmmmmmvv";
        String industryFastForwardsRotating = "mmmmyynvvvmmmmyynvvvm";
        String industrySlowBackwardsRotating = "mmmmvmmmmvyyyynvvvvvm";

        kuinkaHuonoLista(stringToCharList(randomTyovuorolista));
        kuinkaHuonoLista(stringToCharList(vuoden_1979_Finnair_tyovuoromalli));
        kuinkaHuonoLista(stringToCharList(industryFastForwardsRotating));
        kuinkaHuonoLista(stringToCharList(industrySlowBackwardsRotating));

    //        kuinkaHuonoLista(stringToCharList("yyynvv"));
    //
    //        kuinkaHuonoLista(stringToCharList("ynv"));
    //
    //        kuinkaHuonoLista(stringToCharList("yvv"));
    //
    //        kuinkaHuonoLista(stringToCharList("yv"));
    //
    //        kuinkaHuonoLista(stringToCharList("yyynvvv"));
    //
    //        kuinkaHuonoLista(stringToCharList("yyynvv"));
    //
    //        kuinkaHuonoLista(stringToCharList("yyynv"));
    }
}
