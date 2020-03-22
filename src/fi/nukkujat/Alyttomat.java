package fi.nukkujat;


import java.util.ArrayList;
import java.util.List;

public class Alyttomat {


    public int kuinkaHuano(List<Character> tyavuorolista) {
        int sakko = 0;

        for (int paivaNro = 0; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(Vuoro.YO)) {
                YokasittelynTulos tulos = yokasittelija(tyavuorolista, paivaNro);
                sakko = sakko + tulos.rimpsunSakot;
                paivaNro = paivaNro + tulos.rimpsu.size();
            }

        }

        return sakko;
    }

    private YokasittelynTulos yokasittelija(List<Character> tyavuorolista, int paivaNro) {
        boolean valmis = false;
        int rimsunSakot = 0;

        List<Character> rimpsu = new ArrayList<>();

        while (!valmis) {
            Character nykyVuoro = tyavuorolista.get(paivaNro);
            if (!nykyVuoro.equals(Vuoro.MUUVUORO)) {
                rimpsu.add(nykyVuoro);
            }
            valmis = true;
        }
        YokasittelynTulos yokasittelynTulos = new YokasittelynTulos(rimpsu, rimsunSakot);
        return yokasittelynTulos;
    }

    final class YokasittelynTulos {
        private final List<Character> rimpsu;
        private final int rimpsunSakot;

        public YokasittelynTulos(List<Character> rimpsu, int rimpsunSakot) {
            this.rimpsu = rimpsu;
            this.rimpsunSakot = rimpsunSakot;
        }

        public List<Character> getRimpsu() {
            return rimpsu;
        }

        public int getRimpsunSakot() {
            return rimpsunSakot;
        }
    }

    public static class Vuoro {
        public final static Character YO = 'y';
        public final static Character VAPAA = 'v';
        public final static Character NUKKUMA = 'n';
        public final static Character MUUVUORO = 'm';

    }

}