package fi.nukkujat;

import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Const.Kerroin;
import static fi.nukkujat.Const.Saanto;
import static fi.nukkujat.Const.Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN;
import static fi.nukkujat.Const.Saanto.YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA;
import static fi.nukkujat.Const.Vuoro.*;
import static fi.nukkujat.HelpMe.TyoVuorolistaToVuoroLista;
import static fi.nukkujat.HelpMe.stringToCharList;


public class Alyttomat {
    /**
     * Kuinka huono lista int.
     * <p>
     * Yrittää kertoa kuinka huono annettu vuorolista on
     * Löydetyt yövuorot tutkitaan vertaamalla niitä sääntöihin
     * Palautetaan löydettyjen yövuoroputkien yhteen lasketut sakkopisteet
     * Paljon sakkopisteitä -> huonompi vuorolista
     * Nopeamman saa aina tehtyä myähemmi
     * <p>
     * @param tyavuorolista   the tyavuorolista
     * @param aloitusPaivaNro the aloitus paiva nro
     * @return the int
     */
    public static int kuinkaHuonoLista(List<Character> tyavuorolista, int aloitusPaivaNro) { //TODO: aloitusPaivaNro = palkka+pyhat
        System.out.println("\nkuinkaHuonoLista =" + tyavuorolista);
        int sakko = 0;
        int latestSakko = 0;
        int yorimpsujenLkm = 0;
        int paivaNro = 0;
        int poisnukutuPaivat = 0;

        for (paivaNro = 0; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(YO)) {
                yorimpsujenLkm++;
                System.out.println("Tutkitaan ( " + yorimpsujenLkm + ". ) Yo-putki:");
                YokasittelynTulos tulos = yoPutkenTutkija(tyavuorolista, paivaNro);
                latestSakko = tulos.yoPutkenSakot;
                sakko = sakko + latestSakko;
                paivaNro = paivaNro + tulos.yoPutki.size();
            }

            if (vuoro.equals(NUKKUMA)) {
                poisnukutuPaivat++;
            }

            if (Saanto.POTKUT_VUOROLISTAN_LOPUSSA) {
                // Koska onko sillä väliä sitten vaikka nukkuis ittensä ikiuneen
                sakko = sakko - latestSakko;
            }
        }

        if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN && yorimpsujenLkm == 0) {
            sakko = sakko + (poisnukutuPaivat * Const.Kerroin.NUKKUMA_VIRHE);
        }

        System.out.println("Yövuoroputkia löydetty = " + yorimpsujenLkm + " kpl");
        System.out.println(">>> Sakkoa yhteensä = " + sakko + " <<<<\n");
        return sakko;
    }

    private static YokasittelynTulos yoPutkenTutkija(List<Character> tyavuorolista, int paivaNro) {
        boolean yoVuorotLoppu = false;
        int putkenSakot = 0;
        //System.out.println("yoPutkenTutkija alotettu: " + tyavuorolista + " alkupaivanro: " + paivaNro);

        List<Character> yoPutki = new ArrayList<>();

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
                yoPutki.add(nykyVuoro);
            } else {
                break;
            }
            paivaNro++;
        }
        int yot = 0;
        int nukkumaPaivat = 0;
        int vapaaPaivat = 0;
        for (int i = 0; i < yoPutki.size(); i++) {

            Character paiva = yoPutki.get(i);
            if (paiva.equals(YO)) {
                yot++;
            }

            if (paiva.equals(NUKKUMA)) {
                if (NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
                    if (!yoPutki.get(i - 1).equals(YO)) {
                        putkenSakot = putkenSakot + (1 * Kerroin.NUKKUMA_VIRHE);
                        System.out.println("!!! nukumapäivä väärin: " + (1 * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
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
            putkenSakot = putkenSakot + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE);
            System.out.println("! Yövuoroja voi olla peräkkäin 1–" + Saanto.MAKS_YOT + ": " + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE) + " sakkoa");
        }

        //•	n:n yövuoron jälkeen oltava vähintään n vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
        // Sitä enempi penaltia mitä isompi suhde öitte ja vapaitte välil
        if (yot > vapaaPaivat) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("! n:n yövuoron jälkeen oltava vähintään n vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }
        if (yot > vapaaPaivat + 1) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("! yövuoron jälkeen oltava vähintään n-1 vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }
        if (yot > vapaaPaivat + 2) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            System.out.println("! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }

        if (!YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA) {
            //•	yövuorojen jälkeen oltava vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava vähintään 1 vapaapäivä
            // Sitä enempi penaltia mitä pidempi yärupeama
            if (vapaaPaivat < 1) {
                putkenSakot = putkenSakot + (yot * Kerroin.VAPAA_VIRHE);
                System.out.println("! yövuorojen jälkeen oltava vähintään 1 vapaapäivää = " + (yot * Kerroin.VAPAA_VIRHE) + " sakkoa");
            }
            if (vapaaPaivat < 2) {
                putkenSakot = putkenSakot + (yot * Kerroin.VAPAA_VIRHE);
                System.out.println("! yövuorojen jälkeen oltava vähintään 2 vapaapäivää = " + (yot * Kerroin.VAPAA_VIRHE) + " sakkoa");
            }

            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
            if (Saanto.NUKKUMAPAIVA_OLTAVA) {
                if (nukkumaPaivat != 1 && vapaaPaivat < 2) {
                    putkenSakot = putkenSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    System.out.println("! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää = " + (yot * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
                }

                if (nukkumaPaivat != 1 && vapaaPaivat < 1) {
                    putkenSakot = putkenSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    System.out.println("! yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä = " + (yot * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
                }
            }
        }
        YokasittelynTulos yokasittelynTulos = new YokasittelynTulos(yoPutki, putkenSakot);
        System.out.println("Putkessa Yovuorot: " + yot + " Nukkumapaivat: " + nukkumaPaivat + " Vapaat: " + vapaaPaivat);
        System.out.println(yokasittelynTulos + "\n");

        return yokasittelynTulos;
    }

    public static int kuinkaHuonoLista(List<TyoVuoro> tyoVuoroList) {
        List<Character> vuoroLista = TyoVuorolistaToVuoroLista(tyoVuoroList);
        System.out.println(tyoVuoroList);
        int aloitusPaivaNro = 0; //TODO: aloitusPaivaNro = palkka+pyhat
        return kuinkaHuonoLista(vuoroLista, aloitusPaivaNro);
    }
    public static int kuinkaHuonoLista(String tyoVuoroList) {
        List<Character> tyoVuorot = stringToCharList(tyoVuoroList);
        int aloitusPaivaNro = 0; //TODO: aloitusPaivaNro = palkka+pyhat
        return kuinkaHuonoLista(tyoVuorot, aloitusPaivaNro);
    }


    public static ToiveidenTulos kuinkaVihainenTyolainen(List<TyoVuoro> tyavuorolista, List<Toive> toivelista) {
        int sakko = 0;
        int prioriteettejaRikottu = 0;

        for (int i = 0 ; i < tyavuorolista.size(); i++) {
            //Lukemisen helpottamiseksi:
            TyoVuoro tyoVuoro = tyavuorolista.get(i);
            Toive toive = toivelista.get(i);
            Boolean priorisoitu = toive.getPriorisoitu();

            // Jos työntekijä toivoo kuuluvansa johonkin ryhmään
            if (toive.getVuoroRyhma() != 0) {
                // Jos työntekijän ryhmätoivetta ei ole toteltu
                if (toive.getVuoroRyhma() != tyoVuoro.getVuoroRyhma()) {
                    sakko = sakko + 1;
                    if (priorisoitu) {
                        sakko = sakko + (1 * Kerroin.PRIORISOINTI_VIRHE);
                        prioriteettejaRikottu++;
                    }
                }

            }
            // Jos työntekijän vuorotoivetta ei ole toteltu
            if (!tyoVuoro.getVuoroTyyppi().equals(toive.getVuoroTyyppi())) {
                sakko = sakko + 1;
                if (priorisoitu) {
                    sakko = sakko + (1 * Kerroin.PRIORISOINTI_VIRHE);
                    prioriteettejaRikottu++;
                }
            }


        }
        ToiveidenTulos tulos = new ToiveidenTulos(toivelista,sakko,prioriteettejaRikottu);
        System.out.println(tulos);
        return tulos;
    }
}