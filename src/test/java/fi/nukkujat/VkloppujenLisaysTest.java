package fi.nukkujat;

import fi.nukkujat.Alyttomat;
import fi.nukkujat.HelpMe;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Const.AloitusPaiva;
import static fi.nukkujat.HelpMe.stringToCharList;
import static org.junit.Assert.assertEquals;
//TODO: Doesn't work before the same is added to the wishes also
//TODO: lisää inputti vaihtoehtoja
public class VkloppujenLisaysTest {

    @Test
    public void t15_yoViikkoViikonloputLasketaanMutEiListassa() throws Exception {
        List<Character> testiLista = stringToCharList("yyynv");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.MAANANTAI);
        Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t16_kaksViikkoaViikonloputLasketaanMutEiListassa() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyyn");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.MAANANTAI);
        Assert.assertEquals(2, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t17_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaPerjantaista() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyynvvv");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.PERJANTAI);
        Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

    @Test
    public void t18_kaksViikkoaViikonloputLasketaanMutEiListassaAlkaaSunnuntaista() throws Exception {
        List<Character> testiLista = stringToCharList("yyynvmmmmmynvm");
        List<Character> tuikittuLista = HelpMe.viikonlopunLisaaja(testiLista, AloitusPaiva.SUNNUNTAI);
        Assert.assertEquals(2, Alyttomat.kuinkaHuonoLista(tuikittuLista, 0));
    }

} 
