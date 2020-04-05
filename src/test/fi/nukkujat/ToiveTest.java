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
    public void t1_tehtavanAnnonVuorot() throws Exception {
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(10, "ynvabcdefijklmpqrst", 4,123);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(10, "ynvabcdefijklmpqrst", 4,123);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(5, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t2_vahemmanErillaisiaVuoroja() throws Exception {
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(10, "ynvabcd", 6,333);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(10, "ynvabcd", 6,333);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(3, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t3_vahanMahdollisiaVuorotyyppeja() throws Exception {
        //vain yö aamu ja iltavuoro (+nukkuma ja vapaa)
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(5, "ynvai", 3,999);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(5, "ynvai", 3,999);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(5, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(0, toiveidenTulos.toiveSakot);
        assertEquals(0, toiveidenTulos.prioriteettejaRikottu);
    }

    @Test
    public void t4_kunToiveetEivatKohtaa() throws Exception {
        //Eri seed kuin työvuorolistassa = prioriteetit ja toiveet täysin mönkään
        List<TyoVuoro> randomTyoVuoroLista = makeRandomVuoroLista(30, "ynvaip", 6,123456789);
        List<Toive> randomVuoroToiveLista = makeRandomToiveLista(30, "ynvaip", 6,987654321);

        ToiveidenTulos toiveidenTulos = kuinkaVihainenTyolainen(randomTyoVuoroLista, randomVuoroToiveLista);
        assertEquals(11, kuinkaHuonoLista(randomTyoVuoroLista));
        assertEquals(1220, toiveidenTulos.toiveSakot);
        assertEquals(12, toiveidenTulos.prioriteettejaRikottu);
    }
}
