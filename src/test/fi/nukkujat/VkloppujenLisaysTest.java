package fi.nukkujat;

import fi.nukkujat.Alyttomat;
import fi.nukkujat.HelpMe;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Const.AloitusPaiva;
import static fi.nukkujat.HelpMe.stringToCharList;
import static org.junit.Assert.assertEquals;
//TODO: Doesn't work before the same is added to the wishes also
//TODO: lisää inputti vaihtoehtoja
public class VkloppujenLisaysTest {

    @Test
    public void t1_yoViikkoViikonloputLasketaanMutEiListassa() throws Exception {
        List<Character> testiLista = stringToCharList("yyynv");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.MAANANTAI);
        assertEquals(0, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t2_kaksViikkoaViikonloputLasketaanMutEiListassa() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyyn");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.MAANANTAI);
        assertEquals(1, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t3_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaPerjantaista() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyynvvv");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.PERJANTAI);
        assertEquals(0, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t4_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaSunnuntaista() throws Exception {
        List<Character> testiLista = stringToCharList("yyynvmmmmmynvm");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.SUNNUNTAI);
        assertEquals(1, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

} 
