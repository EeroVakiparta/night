package fi.nukkujat;

public class Const {

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
        public final static boolean VIIKONLOPUT_VAPAAT_EIKA_LISTASSA = true;
        public final static boolean POTKUT_VUOROLISTAN_LOPUSSA = false;
        public final static boolean YOVUOROJEN_TARKOITAA_VAIN_MONKIKKOA = false;
        public final static int KIINTEAT_VAPAAT_MIN = 1;
        public final static int KIINTEAT_VAPAAT_MAX = 2;
}

    public static class AloitusPaiva {
        public final static int MAANANTAI = 0;
        public final static int TIISTAI = 1;
        public final static int KESKIVIIKKO = 2;
        public final static int TORSTAI = 3;
        public final static int PERJANTAI = 4;
        public final static int LAUANTAI = 5;
        public final static int SUNNUNTAI = 6;
    }
}
