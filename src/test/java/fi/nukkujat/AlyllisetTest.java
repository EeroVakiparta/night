package fi.nukkujat;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Alylliset.*;
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

}
