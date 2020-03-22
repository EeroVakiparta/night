package fi.nukkujat;


import java.util.ArrayList;
import java.util.List;

public class Alyttomat {

    public static int kuinkaHuano(List<Character> tyavuorolista) {
        System.out.println("----");
        System.out.println("KUINKAHUONO");
        System.out.println("sai tyovuorolistan =" + tyavuorolista);
        int sakko = 0;
        int yorimpsujenLkm = 0;

        for (int paivaNro = 0; paivaNro < tyavuorolista.size(); paivaNro++) {

            Character vuoro = tyavuorolista.get(paivaNro);
            if (vuoro.equals(Vuoro.YO)) {
                YokasittelynTulos tulos = yokasittelija(tyavuorolista, paivaNro);
                sakko = sakko + tulos.rimpsunSakot;
                paivaNro = paivaNro + tulos.rimpsu.size();
                yorimpsujenLkm++;
            }

        }
        System.out.println("Yörimpsuja löydetty =" + yorimpsujenLkm + "kpl");
        System.out.println("Sakkoa =" + sakko + " ");
        System.out.println("----");
        return sakko;
    }

    private static YokasittelynTulos yokasittelija(List<Character> tyavuorolista, int paivaNro) {
        boolean valmis = false;
        boolean yoVuorotLoppu = false;
        int rimsunSakot = 0;
        System.out.println("");
        System.out.println("YOKASITTELIJA");
        System.out.println("sai syötteen " + tyavuorolista + " paivanro " + paivaNro);

        List<Character> rimpsu = new ArrayList<>();

        while (!valmis && paivaNro < tyavuorolista.size() ) {
            Character nykyVuoro = tyavuorolista.get(paivaNro);
            if (!nykyVuoro.equals(Vuoro.MUUVUORO)) {
                if (nykyVuoro.equals(Vuoro.YO)) {
                    if (yoVuorotLoppu) {
                        break;
                    }
                }
                if (!nykyVuoro.equals(Vuoro.YO)) {
                    yoVuorotLoppu = true;
                }
                rimpsu.add(nykyVuoro);
            }else {
                break;
            }
            paivaNro++;
        }
        int yot = 0;
        int nukkumaPaivat = 0;
        int vapaaPaivat = 0;
        for (int i = 0; i < rimpsu.size(); i++) {

            //TODO: tee oma yokasittelija
            Character paiva = rimpsu.get(i);
            if (paiva.equals(Vuoro.YO)) {
                yot++;
            }

            //TODO: tee oma nukkumakasittelija
            if (paiva.equals(Vuoro.NUKKUMA)) {
                if (Saanto.NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN) {
                    if (!rimpsu.get(i - 1).equals(Vuoro.YO)) {
                        rimsunSakot++;
                        System.out.println("!!! nukumapäivä väärin +1 penalti" );
                    }
                }
                nukkumaPaivat++;
            }

            //TODO: tee oma vapaakasittelija
            if (paiva.equals(Vuoro.VAPAA)) {
                vapaaPaivat++;
            }

        }
        //Yövuoroja voi olla peräkkäin 1 – 5
        if (yot > Saanto.MAKS_YOT) {
           rimsunSakot = rimsunSakot + yot - Saanto.MAKS_YOT;
            System.out.println("!!! Yövuoroja voi olla peräkkäin 1 – 5 " + (yot - Saanto.MAKS_YOT) );
        }


        //•	n:n yövuoron jälkeen oltava vähintään n vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
        //•	n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
        // Sitä enempi penaltia mitä isompi suhde öitte ja vapaitte välil
        if (yot > vapaaPaivat) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n vapaapäivää =" + (yot - vapaaPaivat));
        }
        if (yot > vapaaPaivat+1) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! yövuoron jälkeen oltava vähintään n-1 vapaapäivää =" + (yot - vapaaPaivat));
        }
        if (yot > vapaaPaivat+2) {
            rimsunSakot = rimsunSakot + (yot - vapaaPaivat);
            System.out.println("!!! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää =" + (yot - vapaaPaivat));
        }

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

    public static class Vuoro {
        public final static Character YO = 'y';
        public final static Character VAPAA = 'v';
        public final static Character NUKKUMA = 'n';
        public final static Character MUUVUORO = 'm';
    }

    public static class Saanto {
        public final static int MAKS_YOT = 5;
        public final static boolean NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN = true;
        public final static boolean NUKKUMAPAIVA_OLTAVA = true;
        public final static int KIINTEAT_VAPAAT_MIN = 1;
        public final static int KIINTEAT_VAPAAT_MAX = 2;
    }

}