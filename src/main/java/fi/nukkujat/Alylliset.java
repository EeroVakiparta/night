package fi.nukkujat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Alylliset {


    /**
     * Lihavoitta matriisi int [ ] [ ].
     * <p>
     * Duplikoi parametri matriisin @kertaa x ja y suuntaan
     * <p>
     * Miettiessäni ongelmaa 4x4 kokoisessa matriisissa tuli mieleeni
     * tuplata kaikki arvot. Sitä yritetään tässä. (Temppu todettu kokeilemisen
     * arvoiseksi excelillä käsin pyöritellessä)
     * <p>
     * kertaa = times = 2 = 2x eli 100% kokoa lisää
     * <p>
     * Note: tässä meni aivan liian kauan aikaa väsätä....
     *
     * @param matrix the matrix
     * @return the int [ ] [ ]
     */
    public static Integer[][] lihavoittaMatriisi(List<List<Integer>> matrix, int kertaa) {
        /*
        int rivilkm = matrix.length;
        int sarakelkm = matrix[0].length;

        int[][] lihamaMatrix = matrix;
        List[][] nakki = new ArrayList

        List<List<Integer>> lihavoituMatrix = new ArrayList<>();
        */

        Integer[][] arvot = new Integer[matrix.size() * kertaa][matrix.size() * kertaa];

        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> list = matrix.get(i);
            for (int j = 0; j < list.size(); j++) {
                arvot[i][j] = list.get(j);
            }
        }
        int x = 0;
        int y = 0;
        for (int i = arvot.length / kertaa; i < arvot.length; i++) {
            for (int j = arvot.length / kertaa; j < arvot.length; j++) {
                arvot[i][j] = arvot[x][y];
                arvot[x][j] = arvot[x][y];
                //System.out.println("inserted value" + arvot[x][y] + "to index: " + (x) + "-" + j);
                arvot[i][y] = arvot[x][y];
                //System.out.println("inserted value" + arvot[x][y] + "to index: " + (x) + "-" + (j));
                y++;
            }
            x++;
            y = 0;
        }


        return arvot;
    }

    public static List<List<Integer>> readCVS(String fileName) throws IOException {
        //List<Integer[]> arvot = new ArrayList<>();
        List<List<Integer>> arvot = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        //System.out.println(filePath.toAbsolutePath());
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
        String line = br.readLine();
        while (line != null) {
            String[] row = line.split(";");
            List<Integer> rowInt = new ArrayList<>();
            for (String s : row) {
                rowInt.add(Integer.parseInt(s));
            }
            //Integer[] rowIntArray = rowInt.stream().toArray(n -> new Integer[n]);
            //arvot.add(rowIntArray);
            arvot.add(rowInt);
            line = br.readLine();
        }
        br.close();
        return arvot;
    }
}
