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
     * <insertCharHere> = muu kuin yävuoro
     * number = ryhmänumero
     * p = prioriteetti toive !
     * + = toive on tältäosin toteutunut !
     * <p>
     * Toiveet[] = edeltävistä kirjain ja numeroyhdistelmistä koostuvat yksittäiset toiveet päiväjärjestyksessä
     * Vuorot[] = edeltävistä kirjain ja numeroyhdistelmistä koostuvat yksittäiset vuoro päiväjärjestyksessä
     * <p>
     * Tyhjien arvojen kohdalla on välilyönti jotta pyyntöä vastaavan todellisen vuoron näkisi helpommin.
     * Testidataa voi tehdä excelillä exporttaamalla csv kaltaista tietoa
     *
     * @param args the args
     */
    public static void main(String args[]) throws IOException, huonotParametritException {

        List<Toive> toiveet = readToiveet("src/resources/toiveet.txt");
        List<TyoVuoro> vuorot = readTyoVuorot("src/resources/tyovuorolista1.txt");
        tulosta(vuorot, toiveet);

        toiveet = readToiveet("src/resources/toiveet.txt", "Y", "A");
        vuorot = readTyoVuorot("src/resources/tyovuorolista1.txt", "V", "Y");
        tulosta(vuorot, toiveet);

        vuorot = makeRandomVuoroLista(15, "ynvai", 3);
        toiveet = makeRandomToiveLista(15, "ynvai", 3);
        tulosta(vuorot, toiveet);

        for (int i = 0; i < 3; i++) {
            int vuoroRyhmalkm = 3 + i;
            vuorot = makeRandomVuoroLista(7 + i, "ynvxfz", vuoroRyhmalkm);
            toiveet = makeRandomToiveLista(7 + i, "ynvxfz", vuoroRyhmalkm);
            tulosta(vuorot, toiveet);
        }




        // -- vanat Tehtävän 1 kamat:

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
    public static void tulosta(List<TyoVuoro> vuorot, List<Toive> toiveet){
        int tyoVuorolistanSakot = kuinkaHuonoLista(vuorot);
        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(vuorot, toiveet);
        System.out.println("Vuorot =" + vuorot);
        System.out.println("Toiveet=" + toiveet);
        System.out.println("--Yösakot(harjoitus1): " + tyoVuorolistanSakot + " \n--Toivesakot(harjoitus2): " + toiveidenTulos.toiveSakot + "\n");
    }

}
