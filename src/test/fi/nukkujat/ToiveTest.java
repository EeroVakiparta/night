package fi.nukkujat;
import fi.nukkujat.Alyttomat;
import org.junit.Test;

import java.util.List;

import static fi.nukkujat.Alyttomat.kuinkaHuonoLista;
import static fi.nukkujat.Alyttomat.kuinkaVihainenTyolainen;
import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.HelpMe.*;
import static org.junit.Assert.assertEquals;

public class ToiveTest {

    @Test
    public void t19_tehtavanAnnonVuorot() throws Exception {
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(10, "ynvabcdefijklmpqrst", 4,123);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(10, "ynvabcdefijklmpqrst", 4,123);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(8, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t20_vahemmanErillaisiaVuoroja() throws Exception {
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(10, "ynvabcd", 6,333);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(10, "ynvabcd", 6,333);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(6, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t21_vahanMahdollisiaVuorotyyppeja() throws Exception {
        //vain yö aamu ja iltavuoro (+nukkuma ja vapaa)
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(5, "ynvai", 3,999);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(5, "ynvai", 3,999);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(8, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t22_kunToiveetEivatKohtaa() throws Exception {
        //Eri seed kuin työvuorolistassa = prioriteetit ja toiveet täysin mönkään
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(30, "ynvaip", 6,12345678);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(30, "ynvaip", 6,98765432);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(40, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(2130, toiveidenTulos.toiveSakot);
        assertEquals(21, toiveidenTulos.prioriteettejaRikottu);
    }
}
