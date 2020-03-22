package fi.nukkujat;


import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.Const.Saanto.*;
import static fi.nukkujat.Const.Vuoro.*;


public class Alyttomat {

    public static int kuinkaHuano(List<Character> tyavuorolista, int aloitusPaivaNro) {
        System.out.println("----");
        System.out.println("KUINKAHUONO");
        System.out.println("sai tyovuorolistan =" + tyavuorolista);
        int sakko = 0;
        int latestSakko = 0;
        int yorimpsujenLkm = 0;
        int paivaNro = 0;

        for (paivaNro = 0 ; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(YO)) {
                YokasittelynTulos tulos = yokasittelija(tyavuorolista, paivaNro);
                latestSakko = tulos.rimpsunSakot;
                sakko = sakko + latestSakko;
                paivaNro = paivaNro + tulos.rimpsu.size();
                yorimpsujenLkm++;
            }

            if (Saanto.POTKUT_VUOROLISTAN_LOPUSSA) {
                sakko = sakko - latestSakko;
            }

        }
        System.out.println("Yörimpsuja löydetty =" + yorimpsujenLkm + "kpl");
        System.out.println("Sakkoa =" + sakko + " ");
        System.out.println("----");
        return sakko;
    }

    private static YokasittelynTulos yokasittelija(List<Character> tyavuorolista, int paivaNro) {
        boolean yoVuorotLoppu = false;
        int rimsunSakot = 0;
        System.out.println("");
        System.out.println("YOKASITTELIJA");
        System.out.println("sai syötteen " + tyavuorolista + " paivanro " + paivaNro);

        List<Character> rimpsu = new ArrayList<>();

        while (paivaNro < tyavuorolista.size()) {
            Character nykyVuoro = tyavuorolista.get(paivaNro);
            if (!nykyVuoro.equals(MUUVUORO)) {
                if (nykyVuoro.equals(YO)) {
                    if (yoVuorotLoppu) {
                        break;
                    }
                }
                if (!nykyVuoro.equals(YO)) {
                    yoVuorotLoppu = true;
                }
                rimpsu.add(nykyVuoro);
            } else {
                break;
            }
            paivaNro++;
        }
        int yot = 0;
        int nukkumaPaivat = 0;
        int vapaaPaivat = 0;
        for (int i = 0; i < rimpsu.size(); i++) {

            Character paiva = rimpsu.get(i);
            if (paiva.equals(YO)) {
                yot++;
            }

            if (paiva.equals(NUKKUMA)) {
                if (NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
                    if (!rimpsu.get(i - 1).equals(YO)) {
                        rimsunSakot++;
                        System.out.println("!!! nukumapäivä väärin +1 penalti ");
                    }
                }
                nukkumaPaivat++;
            }

            if (paiva.equals(VAPAA)) {
                vapaaPaivat++;
            }

        }
        //Yövuoroja voi olla peräkkäin 1 – 5
        if (yot > Saanto.MAKS_YOT) {
            rimsunSakot = rimsunSakot + yot - Saanto.MAKS_YOT;
            System.out.println("!!! Yövuoroja voi olla peräkkäin 1 – 5 " + (yot - Saanto.MAKS_YOT));
        }


        //•	n:n yövuoron jälkeen oltava vähintään n vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
        // Sitä enempi penaltia mitä isompi suhde öitte ja vapaitte välil
        if (yot > vapaaPaivat) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n vapaapäivää =" + (yot - vapaaPaivat));
        }
        if (yot > vapaaPaivat + 1) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! yövuoron jälkeen oltava vähintään n-1 vapaapäivää =" + (yot - vapaaPaivat));
        }
        if (yot > vapaaPaivat + 2) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää =" + (yot - vapaaPaivat));
        }

        if (!YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA) {
            //•	yövuorojen jälkeen oltava vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava vähintään 1 vapaapäivä
            // Sitä enempi penaltia mitä pidempi yärupeama
            if (vapaaPaivat < 1) {
                rimsunSakot = rimsunSakot + yot;
                System.out.println("!!! yövuorojen jälkeen oltava vähintään 1 vapaapäivää =" + (yot));
            }
            if (vapaaPaivat < 2) {
                rimsunSakot = rimsunSakot + yot;
                System.out.println("!!! yövuorojen jälkeen oltava vähintään 2 vapaapäivää =" + (yot));
            }

            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
            if (Saanto.NUKKUMAPAIVA_OLTAVA) {
                if (nukkumaPaivat != 1 && vapaaPaivat < 2) {
                    rimsunSakot = rimsunSakot + yot;
                    System.out.println("!!! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää =" + (yot));
                }

                if (nukkumaPaivat != 1 && vapaaPaivat < 1) {
                    rimsunSakot = rimsunSakot + yot;
                    System.out.println("!!! yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä =" + (yot));
                }
            }
        }


        YokasittelynTulos yokasittelynTulos = new YokasittelynTulos(rimpsu, rimsunSakot);
        System.out.println("Yöt= " + yot + " Nukut= " + nukkumaPaivat + " Vapaat= " + vapaaPaivat);
        System.out.println("kasittelyn tulos " + yokasittelynTulos);
        System.out.println("");
        return yokasittelynTulos;
    }

    static final class YokasittelynTulos {
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

        @Override
        public String toString() {
            return "YokasittelynTulos {" +
                    "rimpsu =" + rimpsu +
                    ", rimpsunSakot =" + rimpsunSakot +
                    '}';
        }
    }


}