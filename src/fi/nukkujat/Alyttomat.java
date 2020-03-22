package fi.nukkujat;

import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Const.Kerroin;
import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.Const.Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN;
import static fi.nukkujat.Const.Saanto.YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA;
import static fi.nukkujat.Const.Vuoro.*;


public class Alyttomat {

    public static int kuinkaHuano(List<Character> tyavuorolista, int aloitusPaivaNro) {
        YokasittelynTulos yk = new YokasittelynTulos(null, 1);
        System.out.println("----");
        System.out.println("KUINKAHUONO");
        System.out.println("sai tyovuorolistan =" + tyavuorolista);
        int sakko = 0;
        int latestSakko = 0;
        int yorimpsujenLkm = 0;
        int paivaNro = 0;
        int poisnukutuPaivat = 0;

        for (paivaNro = 0; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(YO)) {
                YokasittelynTulos tulos = yokasittelija(tyavuorolista, paivaNro);
                latestSakko = tulos.rimpsunSakot;
                sakko = sakko + latestSakko;
                paivaNro = paivaNro + tulos.rimpsu.size();
                yorimpsujenLkm++;
            }

            if (vuoro.equals(NUKKUMA)) {
                poisnukutuPaivat++;
            }

            if (Saanto.POTKUT_VUOROLISTAN_LOPUSSA) {
                // Koska vittuako sillä väliä sitten vaikka nukkuis ittensä ikiuneen
                sakko = sakko - latestSakko;
            }
        }

        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN && yorimpsujenLkm == 0) {
            sakko = sakko + (poisnukutuPaivat * Const.Kerroin.NUKKUMA_VIRHE);
        }

        System.out.println("Yörimpsuja löydetty =" + yorimpsujenLkm + "kpl");
        System.out.println("Sakkoa =" + sakko + " ");
        System.out.println("----");
        return sakko;
    }

    private static YokasittelynTulos yokasittelija(List<Character> tyavuorolista, int paivaNro) {
        boolean yoVuorotLoppu = false;
        int rimpsunSakot = 0;
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
                        rimpsunSakot = rimpsunSakot + (1 * Kerroin.NUKKUMA_VIRHE);
                        System.out.println("!!! nukumapäivä väärin " + (1 * Kerroin.NUKKUMA_VIRHE));
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
            rimpsunSakot = rimpsunSakot + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE);
            System.out.println("!!! Yövuoroja voi olla peräkkäin 1 – 5 " + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE));
        }

        //•	n:n yövuoron jälkeen oltava vähintään n vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
        // Sitä enempi penaltia mitä isompi suhde öitte ja vapaitte välil
        if (yot > vapaaPaivat) {
            rimpsunSakot = rimpsunSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n vapaapäivää =" + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE));
        }
        if (yot > vapaaPaivat + 1) {
            rimpsunSakot = rimpsunSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("!!! yövuoron jälkeen oltava vähintään n-1 vapaapäivää =" + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE));
        }
        if (yot > vapaaPaivat + 2) {
            rimpsunSakot = rimpsunSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää =" + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE));
        }

        if (!YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA) {
            //•	yövuorojen jälkeen oltava vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava vähintään 1 vapaapäivä
            // Sitä enempi penaltia mitä pidempi yärupeama
            if (vapaaPaivat < 1) {
                rimpsunSakot = rimpsunSakot + (yot * Kerroin.VAPAA_VIRHE);
                System.out.println("!!! yövuorojen jälkeen oltava vähintään 1 vapaapäivää =" + (yot * Kerroin.VAPAA_VIRHE));
            }
            if (vapaaPaivat < 2) {
                rimpsunSakot = rimpsunSakot + (yot * Kerroin.VAPAA_VIRHE);
                System.out.println("!!! yövuorojen jälkeen oltava vähintään 2 vapaapäivää =" + (yot * Kerroin.VAPAA_VIRHE));
            }

            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
            if (Saanto.NUKKUMAPAIVA_OLTAVA) {
                if (nukkumaPaivat != 1 && vapaaPaivat < 2) {
                    rimpsunSakot = rimpsunSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    System.out.println("!!! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää =" + (yot * Kerroin.NUKKUMA_VIRHE));
                }

                if (nukkumaPaivat != 1 && vapaaPaivat < 1) {
                    rimpsunSakot = rimpsunSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    System.out.println("!!! yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä =" + (yot * Kerroin.NUKKUMA_VIRHE));
                }
            }
        }
        YokasittelynTulos yokasittelynTulos = new YokasittelynTulos(rimpsu, rimpsunSakot);
        System.out.println("Yöt= " + yot + " Nukut= " + nukkumaPaivat + " Vapaat= " + vapaaPaivat);
        System.out.println("kasittelyn tulos " + yokasittelynTulos);
        System.out.println("");
        return yokasittelynTulos;
    }


}