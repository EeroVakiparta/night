package fi.nukkujat;

public class Const {

    public static class Vuoro {
        public final static Character YO = 'y';
        public final static Character VAPAA = 'v';
        public final static Character NUKKUMA = 'n'; // paperimiehellä päivä ei ole vapaapäivä jos sen joutuu nukkumaan
        public final static Character MUUVUORO = 'm'; // muu kuin yävuoro
    }

    public static class Saanto {
        public final static int MAKS_YOT = 5;
        public final static boolean NUKKUMAPAIVA_HYVAKSYTTAVA_VAIN_YON_JALKEEN = true; // Ei sallita nukkumapäiviä muiden vuorojen jälkeen
        public final static boolean NUKKUMAPAIVA_OLTAVA = true; // Penaltia jos nukkumapaiva puuttuu
        public final static boolean VIIKONLOPUT_VAPAAT_EIKA_LISTASSA = true; // Vaikuttaa vain listanmuokkaajaan
        public final static boolean POTKUT_VUOROLISTAN_LOPUSSA = false; // Tarvitseeko vapaista murehtia jos yöstä lentää pihalle
        public final static boolean YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA = false; // • yövuoro(jen)
        public final static int KIINTEAT_VAPAAT_MIN = 1; //TODO: to be implemented
        public final static int KIINTEAT_VAPAAT_MAX = 2; //TODO: to be implemented
    }

    //Jos nyt ymmärsin virheen vakavuuden väärin ja sitä täytyy nopeasti vakavoittaa
    public static class Kerroin {
        public final static int NUKKUMA_VIRHE = 1;
        public final static int VAPAA_VIRHE = 1;
        public final static int YOVUORO_VIRHE = 1;
        public final static int PRIORISOINTI_VIRHE = 100;
    }

    public static class AloitusPaiva { //TODO: to be implemented, myöhempää työnantajan menojen laskentaa varten
        public final static int MAANANTAI = 0;
        public final static int TIISTAI = 1;
        public final static int KESKIVIIKKO = 2;
        public final static int TORSTAI = 3;
        public final static int PERJANTAI = 4;
        public final static int LAUANTAI = 5;
        public final static int SUNNUNTAI = 6;
    }
}
