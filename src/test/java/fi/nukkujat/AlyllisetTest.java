package fi.nukkujat;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Alylliset.*;
import static fi.nukkujat.HelpMe.teeKopio;
import static fi.nukkujat.Main.tulosta;

public class AlyllisetTest {

    //Bad practise to have three tests in one
    @Test
    public void t23_reaCVSJalihavoitaTesti() throws Exception {
        /*
        6x6
        lihavoitaTest.txt
        1;2;3;4;5;6
        2;3;4;5;6;7
        3;4;5;6;7;8
        4;5;6;7;8;9
        5;6;7;8;9;10
        6;7;8;9;10;11
        */
        int size = 6;

        List<List<Integer>> luetutArvot = readCVS("lihavoitaTest.txt");
        int rows = luetutArvot.size();
        int columnsTop = luetutArvot.get(0).size();
        int columnsBottom = luetutArvot.get(rows - 1).size();
        Assert.assertEquals(6, rows);
        Assert.assertEquals(6, columnsTop);
        Assert.assertEquals(6, columnsBottom);

        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 2);

        int irows = integers.length;
        int icolumnsTop = integers[0].length;
        int icolumnsBottom = integers[integers.length - 1].length;
        Assert.assertEquals(12, irows);
        Assert.assertEquals(12, icolumnsTop);
        Assert.assertEquals(12, icolumnsBottom);
        for (int i = 0; i < size * 2 / 2; i++) {
            Assert.assertEquals(integers[i][0], integers[size + i][size]);
            Assert.assertEquals(integers[0][i], integers[size][size + i]);
            Assert.assertEquals(integers[i][i], integers[size + i][size + i]);
        }

        integers = lihavoittaMatriisi(luetutArvot, 3);

        irows = integers.length;
        icolumnsTop = integers[0].length;
        icolumnsBottom = integers[integers.length - 1].length;
        Assert.assertEquals(irows, 18);
        Assert.assertEquals(icolumnsTop, 18);
        Assert.assertEquals(icolumnsBottom, 18);
        for (int i = 0; i < size * 3 / 2; i++) {
            Assert.assertEquals(integers[i][0], integers[size + i][size]);
            Assert.assertEquals(integers[0][i], integers[size][size + i]);
            Assert.assertEquals(integers[i][i], integers[size + i][size + i]);
        }
    }

    @Test
    public void t24_flipTheNumbersTest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("lihavoitaTest.txt");

        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 2);
        tulosta(integers);
        Assert.assertEquals(integers[0][0].intValue(), 1);
        Assert.assertEquals(integers[11][11].intValue(), 11);

        Integer[][] flippedIntegers = flipTheNumbers(integers, 11);
        tulosta(flippedIntegers);
        Assert.assertEquals(10, flippedIntegers[0][0].intValue());
        Assert.assertEquals(0, flippedIntegers[11][11].intValue());
    }

    //TODO: refactor bad practise 3 testable features in one test
    @Test
    public void t25_findSmallestInRowAndMakeItZeroTest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        //        82;83;69;92
        //        77;37;49;92
        //        11;69;5;86
        //        8;9;98;23
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        tulosta(smallestInRowAndMakeItZero);
        //        10 9 23 0
        //        15 55 43 0
        //        75 17 81 0
        //        90 89 0 75
        //assert 4 new zeroes
        Assert.assertEquals(0, smallestInRowAndMakeItZero[0][3].intValue());
        Assert.assertEquals(0, smallestInRowAndMakeItZero[1][3].intValue());
        Assert.assertEquals(0, smallestInRowAndMakeItZero[2][3].intValue());
        Assert.assertEquals(0, smallestInRowAndMakeItZero[3][2].intValue());
    }

    //TODO: refactor bad practise 3 testable features in one test
    @Test
    public void t26_findSmallestInColumnAndMakeItZeroTest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        //        82;83;69;92
        //        77;37;49;92
        //        11;69;5;86
        //        8;9;98;23
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(flippedIntegers);
        tulosta(smallestInColumnAndMakeItZero);
        //        0 0 29 0
        //        5 46 49 0
        //        71 14 93 6
        //        74 74 0 69
        //assert 5 new zeroes
        Assert.assertEquals(0, smallestInColumnAndMakeItZero[0][0].intValue());
        Assert.assertEquals(0, smallestInColumnAndMakeItZero[0][1].intValue());
        Assert.assertEquals(0, smallestInColumnAndMakeItZero[0][3].intValue());
        Assert.assertEquals(0, smallestInColumnAndMakeItZero[1][3].intValue());
        Assert.assertEquals(0, smallestInColumnAndMakeItZero[3][2].intValue());
    }

    @Test
    public void t27_findSmallestAmountOfLinesToCoverAllZeroesTest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        tulosta(smallestInRowAndMakeItZero);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(smallestInRowAndMakeItZero);
        tulosta(smallestInColumnAndMakeItZero);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroes = findSmallestAmountOfLinesToCoverAllZeroes(smallestInColumnAndMakeItZero);
        tulosta(smallestInColumnAndMakeItZero,smallestAmountOfLinesToCoverAllZeroes);
    }

    @Test
    public void t28_makeMoreZerosTest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(smallestInRowAndMakeItZero);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroes = findSmallestAmountOfLinesToCoverAllZeroes(smallestInColumnAndMakeItZero);
//        0 0 23 0
//        5 46 43 0
//        65 8 81 0
//        80 80 0 75
        Integer[][] moreZeros = makeMoreZeros(smallestInColumnAndMakeItZero, smallestAmountOfLinesToCoverAllZeroes);
        tulosta(moreZeros);
//        0 0 28 5
//        0 41 43 0
//        60 3 81 0
//        75 75 0 75
        Assert.assertEquals(28, moreZeros[0][2].intValue()); // + 5
        Assert.assertEquals(5, moreZeros[0][3].intValue()); // + 5
        Assert.assertEquals(0, moreZeros[1][0].intValue()); // - 5
        Assert.assertEquals(3, moreZeros[2][1].intValue()); // - 5
        Assert.assertEquals(75, moreZeros[3][3].intValue()); // +-0

    }

    @Test
    public void t29_isEnoughLinesFound() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(smallestInRowAndMakeItZero);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroes = findSmallestAmountOfLinesToCoverAllZeroes(smallestInColumnAndMakeItZero);
        Integer[][] moreZeros = makeMoreZeros(smallestInColumnAndMakeItZero, smallestAmountOfLinesToCoverAllZeroes);
//        0 0 28 5
//        0 41 43 0
//        60 3 81 0
//        75 75 0 75
        List<Viiva> smallestAmountOfLinesToCoverAllZeroesSecondRound = findSmallestAmountOfLinesToCoverAllZeroes(moreZeros);
        boolean enoughLinesFound = isEnoughLinesFound(moreZeros, smallestAmountOfLinesToCoverAllZeroesSecondRound);
        Assert.assertEquals(true, enoughLinesFound);
        // Nollien ylivetämiseen tarvitaan nyt yhtä monta viivaa kuin matrixin koko
        // Optimaalinen vastaus pitäisi nyt löytyä
    }

    @Test
    public void t30_cellSelectiontest() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("zeroTest.txt");
        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 1);
        Integer[][] flippedIntegers = flipTheNumbers(integers, 100);
        Integer[][] smallestInRowAndMakeItZero = findSmallestInRowAndMakeItZero(flippedIntegers);
        Integer[][] smallestInColumnAndMakeItZero = findSmallestInColumnAndMakeItZero(smallestInRowAndMakeItZero);
        List<Viiva> smallestAmountOfLinesToCoverAllZeroes = findSmallestAmountOfLinesToCoverAllZeroes(smallestInColumnAndMakeItZero);
        Integer[][] moreZeros = makeMoreZeros(smallestInColumnAndMakeItZero, smallestAmountOfLinesToCoverAllZeroes);
//        0 0 28 5
//        0 41 43 0
//        60 3 81 0
//        75 75 0 75
        List<Viiva> smallestAmountOfLinesToCoverAllZeroesSecondRound = findSmallestAmountOfLinesToCoverAllZeroes(moreZeros);
        boolean enoughLinesFound = isEnoughLinesFound(moreZeros, smallestAmountOfLinesToCoverAllZeroesSecondRound);
        Assert.assertEquals(true, enoughLinesFound);
        Integer[] integers1 = selectTheCells(moreZeros);
        for (Integer i : integers1) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    @Test
    public void t31_firstAttemptOnTheAssignment() throws Exception {
        List<List<Integer>> luetutArvot = readCVS("kaksitoistaGrid.txt");
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
    }
}
