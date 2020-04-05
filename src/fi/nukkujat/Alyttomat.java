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
     *
     * @param tyavuorolista   the tyavuorolista
     * @param aloitusPaivaNro the aloitus paiva nro
     * @return the int
     */
    public static int kuinkaHuonoLista(List<Character> tyavuorolista, int aloitusPaivaNro) { //TODO: aloitusPaivaNro = palkka+pyhat
        // System.out.println("\nkuinkaHuonoLista =" + tyavuorolista);
        //  System.out.println("\nkuinkaHuonoLista ");
        int sakko = 0;
        int latestSakko = 0;
        int yorimpsujenLkm = 0;
        int paivaNro = 0;
        int poisnukutuPaivat = 0;

        for (paivaNro = 0; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(YO)) {
                yorimpsujenLkm++;
                //System.out.println("Tutkitaan ( " + yorimpsujenLkm + ". ) Yo-putki:");
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

        //System.out.println("Yövuoroputkia löydetty = " + yorimpsujenLkm + " kpl");
        //System.out.println("YoSakkoa yhteensä = " + sakko + "\n");
        return sakko;
    }

    private static YokasittelynTulos yoPutkenTutkija(List<Character> tyavuorolista, int paivaNro) {
        boolean yoVuorotLoppu = false;
        int yoListaSoftCounter = 0;
        int putkenSakot = 0;
        //System.out.println("yoPutkenTutkija alotettu: " + tyavuorolista + " alkupaivanro: " + paivaNro);

        List<Character> yoPutki = new ArrayList<>();

        while (paivaNro < tyavuorolista.size()) {
            Character nykyVuoro = tyavuorolista.get(paivaNro);
            if (nykyVuoro.equals(YO) || nykyVuoro.equals(NUKKUMA) || nykyVuoro.equals(VAPAA)) {
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
        int muutVuorot = 0;
        for (int i = 0; i < yoPutki.size(); i++) {

            Character paiva = yoPutki.get(i);
            if (paiva.equals(YO)) {
                yot++;
            } else if (paiva.equals(NUKKUMA)) {
                if (NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
                    if (!yoPutki.get(i - 1).equals(YO)) {
                        putkenSakot = putkenSakot + (1 * Kerroin.NUKKUMA_VIRHE);
                        //System.out.println("!!! nukumapäivä väärin: " + (1 * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
                    }
                }
                nukkumaPaivat++;
            } else if (paiva.equals(VAPAA)) {
                vapaaPaivat++;
            } else {
                if (yoPutki.get(i - 1).equals(YO)) {
                    // YYMVV pahin virhe, ei voida hyväksyä ikinä, lyödään lekalla -Kyngas
                    putkenSakot = putkenSakot + (1 * Kerroin.LEKA);
                    //System.out.println("!!! Töitä heti yön jäleen: " + (1 * Kerroin.LEKA) + " sakkoa");
                    muutVuorot++;
                }
            }

        }
        //Yövuoroja voi olla peräkkäin 1 – 5
        if (yot > Saanto.MAKS_YOT) {
            putkenSakot = putkenSakot + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE);
            //System.out.println("! Yövuoroja voi olla peräkkäin 1–" + Saanto.MAKS_YOT + ": " + ((yot - Saanto.MAKS_YOT) * Kerroin.YOVUORO_VIRHE) + " sakkoa");
        }

        //•	n:n yövuoron jälkeen oltava vähintään n vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
        // Sitä enempi penaltia mitä isompi suhde öitte ja vapaitte välil
        if (yot > vapaaPaivat) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            //System.out.println("! n:n yövuoron jälkeen oltava vähintään n vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }
        if (yot > vapaaPaivat + 1) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            //System.out.println("! yövuoron jälkeen oltava vähintään n-1 vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }
        if (yot > vapaaPaivat + 2) {
            putkenSakot = putkenSakot + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE);
            //System.out.println("! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää = " + ((yot - vapaaPaivat) * Kerroin.VAPAA_VIRHE) + " sakkoa");
        }

        if (!YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA) {
            //•	yövuorojen jälkeen oltava vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava vähintään 1 vapaapäivä
            // Sitä enempi penaltia mitä pidempi yärupeama
            if (vapaaPaivat < 1) {
                putkenSakot = putkenSakot + (yot * Kerroin.VAPAA_VIRHE);
                //System.out.println("! yövuorojen jälkeen oltava vähintään 1 vapaapäivää = " + (yot * Kerroin.VAPAA_VIRHE) + " sakkoa");
            }
            if (vapaaPaivat < 2) {
                putkenSakot = putkenSakot + (yot * Kerroin.VAPAA_VIRHE);
                //System.out.println("! yövuorojen jälkeen oltava vähintään 2 vapaapäivää = " + (yot * Kerroin.VAPAA_VIRHE) + " sakkoa");
            }

            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
            //•	yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
            if (Saanto.NUKKUMAPAIVA_OLTAVA) {
                if (nukkumaPaivat != 1 && vapaaPaivat < 2) {
                    putkenSakot = putkenSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    //System.out.println("! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää = " + (yot * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
                }

                if (nukkumaPaivat != 1 && vapaaPaivat < 1) {
                    putkenSakot = putkenSakot + (yot * Kerroin.NUKKUMA_VIRHE);
                    //System.out.println("! yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä = " + (yot * Kerroin.NUKKUMA_VIRHE) + " sakkoa");
                }
            }
        }
        YokasittelynTulos yokasittelynTulos = new YokasittelynTulos(yoPutki, putkenSakot);
        //System.out.println("Putkessa Yovuorot: " + yot + " Nukkumapaivat: " + nukkumaPaivat + " Vapaat: " + vapaaPaivat);
        //System.out.println(yokasittelynTulos + "\n");

        return yokasittelynTulos;
    }

    public static int kuinkaHuonoLista(List<TyoVuoro> tyoVuoroList) {
        List<Character> vuoroLista = TyoVuorolistaToVuoroLista(tyoVuoroList);
        //System.out.println("\nVuorot=  " + tyoVuoroList);
        int aloitusPaivaNro = 0; //TODO: aloitusPaivaNro = palkka+pyhat
        return kuinkaHuonoLista(vuoroLista, aloitusPaivaNro);
    }

    public static int kuinkaHuonoLista(String tyoVuoroList) {
        List<Character> tyoVuorot = stringToCharList(tyoVuoroList);
        int aloitusPaivaNro = 0; //TODO: aloitusPaivaNro = palkka+pyhat
        return kuinkaHuonoLista(tyoVuorot, aloitusPaivaNro);
    }


    /**
     * Kuinka vihainen tyolainen toiveiden tulos.
     *
     * Ottaa sisäänsä työvuorot ja toiveet ja vertaa niitä toisiinsta
     *
     * Sakkoja kun allaolevia ei toteuteta:
     *    •	priorisoitu vapaapäivätoive eli toive pitää ehdottomasti toteuttaa.
     *          -> sakko * LEKA(ei hyväksytä)
     *    •	normaali vapaapäivätoive eli olisi kiva, jos toive toteutuisi.
     *          -> sakko * SOFTI_TOIVE_VIRHE(hyväksytään)
     *    •	priorisoitu työvuorotoive eli toive pitää ehdottomasti toteuttaa.
     *          -> sakko * LEKA(ei hyväksytä)
     *    •	normaali työvuorotoive eli olisi kiva, jos toive toteutuisi.
     *          -> sakko * SOFTI_TOIVE_VIRHE(hyväksytään)
     *
     * @param tyavuorolista the tyavuorolista
     * @param toivelista    the toivelista
     * @return the toiveiden tulos
     */
    public static ToiveidenTulos kuinkaVihainenTyolainen(List<TyoVuoro> tyavuorolista, List<Toive> toivelista) {
        int sakko = 0;
        int prioriteettejaRikottu = 0;

        for (int i = 0; i < tyavuorolista.size(); i++) {
            int paivasakko = 0;
            //Lukemisen helpottamiseksi:
            TyoVuoro tyoVuoro = tyavuorolista.get(i);
            Toive toive = new Toive(toivelista.get(i).getVuoroTyyppi(),toivelista.get(i).getVuoroRyhma(),toivelista.get(i).getPriorisoitu(),false);
            Boolean priorisoitu = toive.getPriorisoitu();

            // Jos työntekijä toivoo kuuluvansa johonkin ryhmään
            if (toive.getVuoroRyhma() != 0) {
                // Jos työntekijän ryhmätoivetta ei ole toteltu
                if (toive.getVuoroRyhma() != tyoVuoro.getVuoroRyhma()) {
                    if (priorisoitu) {
                        sakko = sakko + (1 * Kerroin.PRIORISOINTI_VIRHE);
                        prioriteettejaRikottu++;
                        paivasakko++;
                        System.out.println("! priorisoitua ryhmää ei toteltu = " + (1 * Kerroin.PRIORISOINTI_VIRHE) + " sakkoa (" + tyoVuoro.getVuoroRyhma() + " != " + toive.getVuoroRyhma() + ")");
                    } else {
                        sakko = sakko + (1 * Kerroin.SOFTI_TOIVE_VIRHE);
                        paivasakko++;
                        System.out.println("! ryhmäpyyntöön ei vastatu = " + (1 * Kerroin.SOFTI_TOIVE_VIRHE) + " sakkoa (" + tyoVuoro.getVuoroRyhma() + " != " + toive.getVuoroRyhma()+ ")");
                    }
                }
            }
            // Jos työntekijän vuorotoivetta ei ole toteltu
            if (!tyoVuoro.getVuoroTyyppi().equals(toive.getVuoroTyyppi())) {
                // • priorisoitu vapaapäivätoive eli toive pitää ehdottomasti toteuttaa.
                // • priorisoitu työvuorotoive eli toive pitää ehdottomasti toteuttaa.
                if (priorisoitu) {
                    sakko = sakko + (1 * Kerroin.PRIORISOINTI_VIRHE);
                    prioriteettejaRikottu++;
                    paivasakko++;
                    System.out.println("! priorisoitua vuoroa ei toteltu = " + (1 * Kerroin.PRIORISOINTI_VIRHE) + " sakkoa (" + tyoVuoro.getVuoroTyyppi() + " != " + toive.getVuoroTyyppi()+ ")");
                // • normaali vapaapäivätoive eli olisi kiva, jos toive toteutuisi.
                // • normaali työvuorotoive eli olisi kiva, jos toive toteutuisi.
                } else {
                    sakko = sakko + (1 * Kerroin.SOFTI_TOIVE_VIRHE);
                    paivasakko++;
                    System.out.println("! vuoropyyntöön ei vastatu = " + (1 * Kerroin.SOFTI_TOIVE_VIRHE) + " sakkoa (" + tyoVuoro.getVuoroTyyppi() + " != " + toive.getVuoroTyyppi()+ ")");
                }
            }
            if (paivasakko == 0) {
                toive.setTotetunut(true);
                toivelista.set(i, toive);
            }
        }
        ToiveidenTulos tulos = new ToiveidenTulos(toivelista, sakko, prioriteettejaRikottu);
        //System.out.println(tulos.toiveet);
        System.out.println("Toivesakot: " + tulos.toiveSakot);
        System.out.println("Prioriteetteja rikottu: " +tulos.prioriteettejaRikottu);
        return tulos;
    }
}