package fi.nukkujat;

import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Alylliset.*;
import static fi.nukkujat.Alyttomat.kuinkaHuonoLista;
import static fi.nukkujat.Alyttomat.kuinkaVihainenTyolainen;
import static fi.nukkujat.HelpMe.teeKopio;

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

        // TÄSSÄ LYHKÄINEN OHJE KUN UTELIAISUUTTASI VIITSIT TÄNNE EKSYÄ:
        // alla 3 esimerkki CSV:tä jotka ovat tehtävänannon kaltaisia, mutta pienempiä
        // tehtävä itse ei aja loppuun, eikä löydä optimia.
        // 1. kommentoi pois yksi CSV read ja aja maini
        // 2. Tulosteena 2x matriisi jossa jokaisella rivillä/sarakkeella on valittuna vain yksi arvo + arvojen summa
        // 3. koita jotain toista CSVssää

        List<List<Integer>> luetutArvot = readCVS("kahdeksanGrid.txt");
        //List<List<Integer>> luetutArvot = readCVS("kymmenenGrid.txt");
        //List<List<Integer>> luetutArvot = readCVS("kaksitoistaGrid.txt");
        //List<List<Integer>> luetutArvot = readCVS("tehtava.txt");


        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 2);

        Integer[][] kopio = teeKopio(integers);

        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(smallestInRowAndMakeItZero);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroes = findSmallestAmountOfLinesToCoverAllZeroes(smallestInColumnAndMakeItZero);
        Integer[][] moreZeros = makeMoreZeros(smallestInColumnAndMakeItZero, smallestAmountOfLinesToCoverAllZeroes);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroesSecondRound = findSmallestAmountOfLinesToCoverAllZeroes(moreZeros);
        boolean enoughLinesFound = isEnoughLinesFound(moreZeros, smallestAmountOfLinesToCoverAllZeroesSecondRound);
        while (!enoughLinesFound) {
            makeMoreZeros(moreZeros, smallestAmountOfLinesToCoverAllZeroesSecondRound);
            smallestAmountOfLinesToCoverAllZeroesSecondRound = findSmallestAmountOfLinesToCoverAllZeroes(moreZeros);
            enoughLinesFound = isEnoughLinesFound(moreZeros, smallestAmountOfLinesToCoverAllZeroesSecondRound);
        }
        Assert.assertEquals(true, enoughLinesFound);
        Integer[] integers1 = selectTheCells(moreZeros);

        System.out.println("selectTheCells asnwer with amount of rows: " + integers1.length );
        for (int i = 0; i < integers1.length; i++) {

            System.out.println(integers1[i]);

        }
        System.out.println("" );


        System.out.println(" Populating the answer grid with zeroes" );
        Integer[][] answerCoordinates = new Integer[integers1.length][integers1.length];
        for (int i = 0; i < answerCoordinates.length; i++) {
            for (int j = 0; j < answerCoordinates.length; j++) {
                answerCoordinates[i][j] = 0;

            }
        }
        System.out.println(" Answer coordinates before applying answers" );
        tulosta(answerCoordinates);
        System.out.println("" );
        for (int i = 0; i < integers1.length; i++) {
            Integer integer = integers1[i];
            answerCoordinates[integer][i] = 1;

        }
        //TÄSSÄ KOHTAA TAJUSIN ETTÄ NELJÄS NELJÄNNES TAITAA OLLA HUONO,
        System.out.println(" vastaus koordinaatit");
        tulosta(answerCoordinates);
        System.out.println("");
        System.out.println(" Loppuvastaus");
        List<Integer> lopulliset = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < answerCoordinates.length; i++) {
            for (int j = 0; j < answerCoordinates.length; j++) {
                if (answerCoordinates[i][j] == 1) {
                    Integer integer = kopio[i][j];
                    lopulliset.add(integer);
                    total = total + integer;
                }
            }
        }

        System.out.println(lopulliset);
        System.out.println("total: " + total);


        /* alkuperäinen maini tehtävä 3

        List<List<Integer>> arvot = readCVS("arvot.txt");
        //tulosta(arvot);
        Integer[][] integers = lihavoittaMatriisi(arvot, 2);
        //tulosta(integers);

        List<List<Integer>> tehtava = readCVS("tehtava.txt");
        tulosta(tehtava);
        Integer[][] tehtavaA = lihavoittaMatriisi(tehtava, 2);
        tulosta(tehtavaA);

         */

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
        System.out.println();
    }

    public static void tulosta(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void tulosta(Integer[][] matrix, List<Viiva> lines) throws huonotParametritException {
        if (matrix.length != matrix[0].length) {
            throw new huonotParametritException("Only works with matrix with equal rows/colums count =" + matrix.length +" / " + matrix[0].length);
        }

        final int SIZE = matrix.length;
        System.out.println("Before:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }

        for (Viiva line : lines) {
            for (int i = 0; i < SIZE; i++) {
                int index = line.getLineIndex();
                if (line.isHorizontal()) {
                    matrix[index][i] = matrix[index][i] < 0 ? -3 : -1;
                } else {
                    matrix[i][index] = matrix[i][index] < 0 ? -3 : -2;
                }
            }
        }

        System.out.println("\nAfter:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", matrix[i][j] == -1 ? "-" : (matrix[i][j] == -2 ? "|" : (matrix[i][j] == -3 ? "+" : Integer.toString(matrix[i][j]))));
            }
            System.out.println();
        }
    }
}
