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
        assertEquals(0, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t2_yoloppuu() throws Exception {
        String vuoro = "yyyyymmm";
        List<Character> testiLista = litaChar(vuoro);
        assertEquals(0, Alyttomat.kuinkaHuano(testiLista));
    }

    @Test
    public void t3_kaksiYorimpsua() throws Exception {
        String vuoro = "yyyyymmmyyyy";
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
