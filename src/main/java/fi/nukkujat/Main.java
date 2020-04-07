package fi.nukkujat;

import java.io.IOException;
import java.util.List;

import static fi.nukkujat.Alylliset.lihavoittaMatriisi;
import static fi.nukkujat.Alylliset.readCVS;
import static fi.nukkujat.Alyttomat.kuinkaHuonoLista;
import static fi.nukkujat.Alyttomat.kuinkaVihainenTyolainen;

public class Main {

    /**
     * fi.nukkujat.Main.
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
     * Esimerkki: A1p+ = Vuoro A, Ryhmä 1, priorisoitu, toteutui
     * <p>
     * Testidataa voi tehdä excelillä exporttaamalla csv kaltaista tietoa
     *
     * @param args the args
     */
    public static void main(String args[]) throws IOException, huonotParametritException {

        List<List<Integer>> arvot = readCVS("arvot.txt");
        //tulosta(arvot);
        Integer[][] integers = lihavoittaMatriisi(arvot, 2);
        //tulosta(integers);

        List<List<Integer>> tehtava = readCVS("tehtava.txt");
        tulosta(tehtava);
        Integer[][] tehtavaA = lihavoittaMatriisi(tehtava, 2);
        tulosta(tehtavaA);

        // -- vanat Tehtävän 2 kamat:

        /*
        //TODO: fix the paths Maybe help: https://stackoverflow.com/questions/49222296/not-able-to-load-file-from-maven-resources-with-paths-get
        List<Toive> toiveet = readToiveet("toiveet.txt");
        List<TyoVuoro> vuorot = readTyoVuorot("tyovuorolista1.txt");
        tulosta(vuorot, toiveet);

        toiveet = readToiveet("toiveet.txt", "Y", "A");
        vuorot = readTyoVuorot("tyovuorolista1.txt", "V", "Y");
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

        */


        // -- vanat Tehtävän 1 kamat:

//        String randomTyovuorolista = fi.nukkujat.HelpMe.rVuoroLista(30);
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

    public static void tulosta(List<TyoVuoro> vuorot, List<Toive> toiveet) {
        int tyoVuorolistanSakot = kuinkaHuonoLista(vuorot);
        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(vuorot, toiveet);
        System.out.println("Vuorot =" + vuorot);
        System.out.println("Toiveet=" + toiveet);
        System.out.println("--Yösakot(harjoitus1): " + tyoVuorolistanSakot + " \n--Toivesakot(harjoitus2): " + toiveidenTulos.toiveSakot + "\n");
    }

    public static void tulosta(List<List<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void tulosta(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
