package fi.nukkujat;

import java.util.List;

import static fi.nukkujat.Const.Saanto.VIIKONLOPUT_VAPAAT_EIKA_LISTASSA;
import static fi.nukkujat.Const.Vuoro.VAPAA;

public class HelpMeh {
    public static List<Character> viikonlopunTuikkia(List<Character> tyavuorolista, int aloitusPaiva) {

        System.out.println("viikonlopunTuikkiaTuikii " + tyavuorolista + " aloituspaiva " + aloitusPaiva);
        int paivaNro = 0;
        int lisatyt = 0;

        if (VIIKONLOPUT_VAPAAT_EIKA_LISTASSA) {
            int counter = 0 + aloitusPaiva;
            for (paivaNro = 0; paivaNro < tyavuorolista.size()+1; paivaNro++) {
                if (counter == 5) {
                    tyavuorolista.add(paivaNro, VAPAA);
                    tyavuorolista.add(paivaNro, VAPAA);
                    lisatyt = lisatyt + 2;
                    counter = -2;
                }
                if (counter == 6) {
                    tyavuorolista.add(paivaNro, VAPAA);
                    lisatyt = lisatyt + 1;
                    counter = -1;
                }
                counter++;
            }
        }
        System.out.println("viikonlopunTuikkiaTuikittu: " + tyavuorolista + " viikonloppupaivialisatty: " + lisatyt);
        return tyavuorolista;
    }
}
