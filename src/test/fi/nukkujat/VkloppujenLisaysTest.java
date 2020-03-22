package test.fi.nukkujat;

import fi.nukkujat.Alyttomat;
import fi.nukkujat.HelpMeh;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Const.AloitusPaiva;
import static fi.nukkujat.HelpMeh.stringToCharList;
import static org.junit.Assert.assertEquals;

public class VkloppujenLisaysTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void t1_yoViikkoViikonloputLasketaanMutEiListassa() throws Exception {
        String vuoro = "yyynv";
        List<Character> testiLista = stringToCharList(vuoro);
        List<Character> tuikittuLista = HelpMeh.viikonlopunTuikkia(testiLista, AloitusPaiva.MAANANTAI);
        assertEquals(0, Alyttomat.kuinkaHuano(tuikittuLista, 0));
    }

    @Test
    public void t2_kaksViikkoaViikonloputLasketaanMutEiListassa() throws Exception {
        String vuoro = "mmmmmmyyyn";
        List<Character> testiLista = stringToCharList(vuoro);
        List<Character> tuikittuLista = HelpMeh.viikonlopunTuikkia(testiLista, AloitusPaiva.MAANANTAI);
        assertEquals(1, Alyttomat.kuinkaHuano(tuikittuLista, 0));
    }

    @Test
    public void t3_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaPerjantaista() throws Exception {
        String vuoro = "mmmmmmyyynvvv";
        List<Character> testiLista = stringToCharList(vuoro);
        List<Character> tuikittuLista = HelpMeh.viikonlopunTuikkia(testiLista, AloitusPaiva.PERJANTAI);
        assertEquals(0, Alyttomat.kuinkaHuano(tuikittuLista, 0));
    }

    @Test
    public void t4_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaSunnuntaista() throws Exception {
        String vuoro = "yyynvmmmmmynvm";
        List<Character> testiLista = stringToCharList(vuoro);
        List<Character> tuikittuLista = HelpMeh.viikonlopunTuikkia(testiLista, AloitusPaiva.SUNNUNTAI);
        assertEquals(1, Alyttomat.kuinkaHuano(tuikittuLista, 0));
    }

} 
