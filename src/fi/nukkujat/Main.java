package fi.nukkujat;

import java.io.IOException;
import java.util.List;

import static fi.nukkujat.Alyttomat.kuinkaHuonoLista;
import static fi.nukkujat.Alyttomat.kuinkaVihainenTyolainen;
import static fi.nukkujat.HelpMe.*;

public class Main {

    /**
     * Main.
     * <p>
     * y = yä
     * n = nukkuma
     * v = vapaa
     * m = muu kuin yävuoro
     *
     * @param args the args
     */
    public static void main(String args[]) throws IOException {

        List<Toive> toiveet = readToiveet("src/resources/toiveet.txt");
        List<TyoVuoro> vuorot = readTyoVuorot("src/resources/tyovuorolista1.txt");

        System.out.println("\nYo ja toive sakot yhteensä: " + (kuinkaHuonoLista(vuorot) + kuinkaVihainenTyolainen(vuorot, toiveet).toiveSakot));

//        for (Toive t : toiveet) {
//            System.out.println(t);
//        }
//
//        for (TyoVuoro v : vuorot) {
//            System.out.println(v);
//        }




//        String randomTyovuorolista = HelpMe.rVuoroLista(30);
//        String vuoden_1979_Finnair_tyovuoromalli = "mmmmmvvyyyyyvmmmmmvvmmmmmvv";
//        String industryFastForwardsRotating = "mmmmyynvvvmmmmyynvvvm";
//        String industrySlowBackwardsRotating = "mmmmvmmmmvyyyynvvvvvm";
//
//        kuinkaHuonoLista(stringToCharList(randomTyovuorolista));
//        kuinkaHuonoLista(stringToCharList(vuoden_1979_Finnair_tyovuoromalli));
//        kuinkaHuonoLista(stringToCharList(industryFastForwardsRotating));
//        kuinkaHuonoLista(stringToCharList(industrySlowBackwardsRotating));
//
//        kuinkaHuonoLista(stringToCharList("yyynvv"));
//        kuinkaHuonoLista(stringToCharList("ynv"));
//        kuinkaHuonoLista(stringToCharList("yvv"));
//        kuinkaHuonoLista(stringToCharList("yv"));
//        kuinkaHuonoLista(stringToCharList("yyynvvv"));
//        kuinkaHuonoLista(stringToCharList("yyynvv"));
//        kuinkaHuonoLista(stringToCharList("yyynv"));
    }
}
