package fi.nukkujat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static fi.nukkujat.Const.Saanto.VIIKONLOPUT_VAPAAT_EIKA_LISTASSA;
import static fi.nukkujat.Const.Vuoro.VAPAA;

public class HelpMe {
    public static List<Character> viikonlopunLisaaja(List<Character> tyavuorolista, int aloitusPaiva) {

        System.out.println("viikonlopunLisaaja " + tyavuorolista + " aloituspaiva " + aloitusPaiva);
        int paivaNro = 0;
        int lisatyt = 0;

        if (VIIKONLOPUT_VAPAAT_EIKA_LISTASSA) {
            int counter = 0 + aloitusPaiva;
            for (paivaNro = 0; paivaNro < tyavuorolista.size() + 1; paivaNro++) {
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
        System.out.println("viikonlopunLisaajan jalkeen: " + tyavuorolista + " viikonloppupaivia lisatty: " + lisatyt);
        return tyavuorolista;
    }

    public static List<Character> stringToCharList(String lista) {
        List<Character> charLista = new ArrayList<>();
        for (char c : lista.toCharArray()) {
            charLista.add(c);
        }
        return charLista;
    }

    public static String rVuoroLista(int l) {
        String mahdollisetPaivat = "myvn";
        StringBuilder s = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < l; i++) {
            s.append(mahdollisetPaivat.charAt(r.nextInt(mahdollisetPaivat
                    .length())));
        }
        return s.toString();
    }
}
