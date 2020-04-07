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
        Assert.assertEquals(rows, 6);
        Assert.assertEquals(columnsTop, 6);
        Assert.assertEquals(columnsBottom, 6);

        Integer[][] integers = lihavoittaMatriisi(luetutArvot, 2);

        int irows = integers.length;
        int icolumnsTop = integers[0].length;
        int icolumnsBottom = integers[integers.length - 1].length;
        Assert.assertEquals(irows, 12);
        Assert.assertEquals(icolumnsTop, 12);
        Assert.assertEquals(icolumnsBottom, 12);
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

        Integer[][] flippedIntegers = flipTheNumbers(integers,11);
        tulosta(flippedIntegers);
        Assert.assertEquals(flippedIntegers[0][0].intValue(), 10);
        Assert.assertEquals(flippedIntegers[11][11].intValue(), 0);
    }
}
