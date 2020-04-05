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

    public static List<Character> TyoVuorolistaToVuoroLista(List<TyoVuoro> tyoVuoroList) {
        List<Character> charTyovuoroLista = new ArrayList<>();
        for (TyoVuoro tv : tyoVuoroList) {
            charTyovuoroLista.add(tv.getVuoroTyyppi());
        }
        return charTyovuoroLista;
    }

    public static List<Toive> readToiveet(String fileName) throws IOException {
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

    public static List<TyoVuoro> readTyoVuorot(String fileName) throws IOException {
        List<TyoVuoro> vuorot = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        System.out.println(filePath.toAbsolutePath());
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
        // read the first line from the text file
        String line = br.readLine(); // loop until all lines are read
        while (line != null) {
            // use string.split to load a string array with the values from // each line of // the file, using a comma as the delimiter
            String[] attributes = line.split(";");
            TyoVuoro vuoro = makeAVuoro(attributes); // adding book into ArrayList
            vuorot.add(vuoro); // read next line before looping // if end of file reached, line would be null
            line = br.readLine();
        }

        return vuorot;
    }

    public static TyoVuoro makeAVuoro(String[] vuorot) {
        Character c = vuorot[0].charAt(0);
        return new TyoVuoro(Character.toLowerCase(c), Integer.parseInt(vuorot[1]));
    }

    public static Toive makeAWish(String[] toiveet) {
        Character c = toiveet[0].charAt(0);
        Boolean prio = false;
        if (toiveet[1].equals("1")) {
            prio = true;
        }
        return new Toive(Character.toLowerCase(c), Integer.parseInt(toiveet[1]), prio);
    }

    public static List<TyoVuoro> makeRandomVuoroLista(int paivaLkm, String mahdollisetVuorot, int vuoroRyhmalkma) throws huonotParametritException {
        List<TyoVuoro> tyovuoroLista = new ArrayList<>();
        for (int i = 0; i < paivaLkm; i++ ) {
            tyovuoroLista.add( makeARandomVuoro(mahdollisetVuorot, vuoroRyhmalkma));
        }
        return tyovuoroLista;
    }

    public static List<Toive> makeRandomToiveLista(int paivaLkm, String mahdollisetVuorot, int vuoroRyhmalkma) throws huonotParametritException {
        List<Toive> toiveLista = new ArrayList<>();
        for (int i = 0; i < paivaLkm; i++ ) {
            toiveLista.add( makeARandomWish(mahdollisetVuorot, vuoroRyhmalkma));
        }
        return toiveLista;
    }

    public static List<TyoVuoro> makeRandomVuoroLista(int paivaLkm, String mahdollisetVuorot, int vuoroRyhmalkma, int seed) throws huonotParametritException {
        List<TyoVuoro> tyovuoroLista = new ArrayList<>();
        for (int i = 0; i < paivaLkm; i++ ) {
            tyovuoroLista.add( makeARandomVuoro(mahdollisetVuorot, vuoroRyhmalkma,seed));
            seed = seed + 2;
        }
        return tyovuoroLista;
    }

    public static List<Toive> makeRandomToiveLista(int paivaLkm, String mahdollisetVuorot, int vuoroRyhmalkma, int seed) throws huonotParametritException {
        List<Toive> toiveLista = new ArrayList<>();
        for (int i = 0; i < paivaLkm; i++ ) {
            toiveLista.add( makeARandomWish(mahdollisetVuorot, vuoroRyhmalkma,seed));
            seed = seed + 2;
        }
        return toiveLista;
    }

    public static TyoVuoro makeARandomVuoro(String mahdollisetVuorot, int vuoroRyhmalkm) throws huonotParametritException {
        Random random = new Random();
        int seed = random.nextInt();
        return makeARandomVuoro(mahdollisetVuorot,vuoroRyhmalkm,seed);
    }

    public static Toive makeARandomWish(String mahdollisetVuorot, int vuoroRyhmalkm) throws huonotParametritException {
        Random random = new Random();
        int seed = random.nextInt();
        return makeARandomWish(mahdollisetVuorot,vuoroRyhmalkm,seed);
    }

    public static TyoVuoro makeARandomVuoro(String mahdollisetVuorot, int vuoroRyhmalkm, int seed) throws huonotParametritException {
        validateRandomParams(mahdollisetVuorot, vuoroRyhmalkm);
        Random random = new Random(seed);
        int index = random.nextInt(mahdollisetVuorot.length());
        Character arvottuVuoro = mahdollisetVuorot.charAt(index);
        int vuoroRyhma = random.nextInt(vuoroRyhmalkm ) + 3;
        return new TyoVuoro(arvottuVuoro,vuoroRyhma);
    }

    public static Toive makeARandomWish(String mahdollisetVuorot, int vuoroRyhmalkm, int seed) throws huonotParametritException {
        validateRandomParams(mahdollisetVuorot, vuoroRyhmalkm);
        Random random = new Random(seed);
        int index = random.nextInt(mahdollisetVuorot.length());
        Character arvottuVuoro = mahdollisetVuorot.charAt(index);

        Boolean prio = random.nextBoolean();
        return new Toive(arvottuVuoro, randomVuoroRyhma(vuoroRyhmalkm,seed), prio);
    }

    public static int  randomVuoroRyhma(int vuorojenMaara, int seed) {
        Random random = new Random(seed);
        int vuoroRyhma = 0;
        boolean tuittuPaaTyontekija = random.nextBoolean();
        if (tuittuPaaTyontekija) {
            vuoroRyhma = random.nextInt(vuorojenMaara ) + 3;
        }
        return vuoroRyhma;
    }

    public static void validateRandomParams(String mahdollisetVuorot, int vuoroRyhma) throws huonotParametritException {
        if (mahdollisetVuorot.length() > 30 || mahdollisetVuorot.length() < 3) {
            throw new huonotParametritException("Vaara maara erilaisia tyovuoroja =" + mahdollisetVuorot.length());
        }else if (vuoroRyhma < 3 || vuoroRyhma > 6) {
            throw new huonotParametritException("Vaara maara erilaisia vuororyhmia =" + vuoroRyhma);
        }else if (!onNukkumaYovapaa(mahdollisetVuorot)) {
            throw new huonotParametritException("Pakolliset vuorot puuttuvat =" + mahdollisetVuorot);
        }
    }

    public static boolean onNukkumaYovapaa(String vuorot) {
        int s = 0;
        for (int i = 0; i < vuorot.length(); i++) {
            char c = vuorot.charAt(i);
            if (c == 'n' || c == 'y' || c == 'v') {
                s++;
            }
        }
        if (s == 3) {
            return true;
        }
        return false;
    }
}


class huonotParametritException extends Exception {
    public huonotParametritException(String message) {
        super(message);
    }
}