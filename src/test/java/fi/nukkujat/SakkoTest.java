package fi.nukkujat;

import fi.nukkujat.Alyttomat;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.HelpMe.stringToCharList;
import static org.junit.Assert.assertEquals;

public class SakkoTest {

    @Test
    public void t1_ioob() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyy");
        Assert.assertEquals(60, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t2_yoloppuu() throws Exception {
        List<Character> testiLista = stringToCharList("yym");
        Assert.assertEquals(20, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t3_kaksiYoputki() throws Exception {
        List<Character> testiLista = stringToCharList("yymyy");
        Assert.assertEquals(40, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t4_nukkumaVapaanJalkeen() throws Exception {
        List<Character> testiLista = stringToCharList("myyyvvvnm");
        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
            Assert.assertEquals(1, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        } else {
            Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        }
    }

    @Test
    public void t5_taydellinenMiniTyovuoro() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmyyyynvvvvm");
        Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t6_vapaaPaivat_n() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyvvv");
        Assert.assertEquals(2, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t7_vapaaPaivat_nMinusYks() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyvv");
        Assert.assertEquals(8, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t8_vapaaPaivat_nMinusKaks() throws Exception {
        List<Character> testiLista = stringToCharList("yyyyv");
        Assert.assertEquals(30, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t9_taydellinenPidempiTyovuoro() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmmyyynvvvmmmmmmyyyyynvvvvvmmmmmm");
        Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t10_JS_koodarin_tyovuorlista() throws Exception {
        List<Character> testiLista = stringToCharList("mvvvyyymmymvyvmmmyyvyvvmyvvvmym");
        Assert.assertEquals(63, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t11_JS_tyoharjoittelijan_tyovuorlista() throws Exception {
        List<Character> testiLista = stringToCharList("yymvyyvyvvmvvvyyymyyyyyyyyymyvy");
        Assert.assertEquals(187, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t12_JS_tyoharjoittelijan_tyovuorlista_toinenKesaToissa() throws Exception {
        List<Character> testiLista = stringToCharList("mvmyvvvyyyvvymyymvmvyyyvvvmyvmy");
        Assert.assertEquals(31, Alyttomat.kuinkaHuonoLista(testiLista, 0));
    }

    @Test
    public void t13_eiOitalainkaan() throws Exception {
        List<Character> testiLista = stringToCharList("mmnmmmvvvmmvmvmvmvmvnv");
        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
            Assert.assertEquals(2, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        } else {
            Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));
        }
    }

    @Test
    public void t14_javaKoodarinTyovuorolista() throws Exception {
        List<Character> testiLista = stringToCharList("mmmmmvvmmmmmvvmmmmmvvmmmmmvvmmmmmvvmmmmmvvmmmmmvvvvvvv");
        Assert.assertEquals(0, Alyttomat.kuinkaHuonoLista(testiLista, 0));

    }
} 
