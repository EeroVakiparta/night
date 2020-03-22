package test.fi.nukkujat;

import fi.nukkujat.Alyttomat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class AlyttomatTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void t1_ioob() throws Exception {
        String vuoro = "yyyyy";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(35, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t2_yoloppuu() throws Exception {
        String vuoro = "yym";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(12, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t3_kaksiYorimpsua() throws Exception {
        String vuoro = "yymyy";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(24, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t4_nukkumaVapaanJalkeen() throws Exception {
        String vuoro = "myyyvvvnm";
        List<Character> testiLista = litaChar(vuoro);
        if (Alyttomat.Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
            assertEquals(1, Alyttomat.kuinkaHuano(testiLista));
        } else {
            assertEquals(0, Alyttomat.kuinkaHuano(testiLista));
        }
    }

    @Test
    public void t5_taydellinenMiniTyovuoro() throws Exception {
        String vuoro = "mmmmmyyyynvvvvm";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(0, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t6_vapaaPaivat1() throws Exception {
        String vuoro = "yyyyvvv";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(1, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t7_vapaaPaivat2() throws Exception {
        String vuoro = "yyyyvv";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(4, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t8_vapaaPaivat3() throws Exception {
        String vuoro = "yyyyv";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(17, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t9_taydellinenPidempiTyovuoro() throws Exception {
        String vuoro = "mmmmmmyyynvvvmmmmmmyyyyynvvvvvmmmmmm";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(0, Alyttomat.kuinkaHuano(testiLista));
    }

        private List<Character> litaChar(String lista) {
        List<Character> charLista = new ArrayList<>();
        for (char c : lista.toCharArray()) {
            charLista.add(c);
        }
        return charLista;
    }

} 
