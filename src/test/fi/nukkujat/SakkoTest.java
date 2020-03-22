package test.fi.nukkujat;

import fi.nukkujat.Alyttomat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.HelpMe.stringToCharList;
import static org.junit.Assert.assertEquals;

public class SakkoTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void t1_ioob() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyy");
        assertEquals(35, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t2_yoloppuu() throws Exception {
        List<Character> testiLista = stringToCharList("yym");
        assertEquals(12, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t3_kaksiYorimpsua() throws Exception {
        List<Character> testiLista = stringToCharList("yymyy");
        assertEquals(24, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t4_nukkumaVapaanJalkeen() throws Exception {
        List<Character> testiLista = stringToCharList("myyyvvvnm");
        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
            assertEquals(1, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        } else {
            assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        }
    }

    @Test
    public void t5_taydellinenMiniTyovuoro() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmyyyynvvvvm");
        assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t6_vapaaPaivat1() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyvvv");
        assertEquals(1, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t7_vapaaPaivat2() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyvv");
        assertEquals(4, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t8_vapaaPaivat3() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyv");
        assertEquals(17, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t9_taydellinenPidempiTyovuoro() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyynvvvmmmmmmyyyyynvvvvvmmmmmm");
        assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t10_JS_koodarin_tyovuorlista() throws Exception {
        List<Character> testiLista = stringToCharList("mvvvyyymmymvyvmmmyyvyvvmyvvvmym");
        assertEquals(38, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t11_JS_tyoharjoittelijan_tyovuorlista() throws Exception {
        List<Character> testiLista = stringToCharList("yymvyyvyvvmvvvyyymyyyyyyyyymyvy");
        assertEquals(107, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t12_JS_tyoharjoittelijan_tyovuorlista_toinenKesaToissa() throws Exception {
        List<Character> testiLista = stringToCharList("mvmyvvvyyyvvymyymvmvyyyvvvmyvmy");
        assertEquals(19, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t12_eiOitalainkaan() throws Exception {
        List<Character> testiLista = stringToCharList("mmnmmmvvvmmvmvmvmvmvnv");
        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
            assertEquals(2, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        } else {
            assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        }
    }
} 
