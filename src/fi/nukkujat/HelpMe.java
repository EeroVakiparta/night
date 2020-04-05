package fi.nukkujat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static fi.nukkujat.Const.Saanto.VIIKONLOPUT_VAPAAT_EIKA_LISTASSA;
import static fi.nukkujat.Const.Vuoro.VAPAA;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

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

    public static List<Toive> readCSV(String fileName) throws IOException {
        List<Toive> toiveet = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        System.out.println(filePath.toAbsolutePath());
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
            // read the first line from the text file
            String line = br.readLine(); // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from // each line of // the file, using a comma as the delimiter
                String[] attributes = line.split(";");
                Toive toive = makeAWish(attributes); // adding book into ArrayList
                toiveet.add(toive); // read next line before looping // if end of file reached, line would be null
                line = br.readLine();
            }

        return toiveet;
    }

    public static Toive makeAWish(String[] toiveet) {
        Boolean prio = false;
        if (toiveet[1].equals("1")) {
            prio = true;
        }
        return new Toive(toLowerCase(toiveet[0]),Integer.parseInt(toiveet[1]), prio);
    }

}
