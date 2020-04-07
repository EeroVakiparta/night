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


    public static Integer[][] findSmallestInRowAndMakeItZero(Integer[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            int smallest = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if( smallest > matrix[i][j]){
                    smallest = matrix[i][j];
                }
            }
            for (int j = 0; j < size; j++) {
                matrix[i][j] = matrix[i][j] - smallest;
            }
        }
        return matrix;
    }

    public static Integer[][] findSmallestInColumnAndMakeItZero(Integer[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            Integer smallest = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if( smallest > matrix[j][i]){
                    smallest = matrix[j][i];
                }
            }
            for (int j = 0; j < size; j++) {
                matrix[j][i] = matrix[j][i] - smallest;
            }
        }
        return matrix;
    }


    /**
     * Flip the numbers integer [ ] [ ].
     *
     * flips matrix upside down for better observation
     *
     * @param matrix                the matrix
     * @param maxvalueToConvertZero the maxvalue to convert zero
     * @return the integer [ ] [ ]
     */
    public static Integer[][] flipTheNumbers(Integer[][] matrix, int maxvalueToConvertZero) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = maxvalueToConvertZero - matrix[i][j];
            }
        }
        return matrix;
    }

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

        Integer[][] arvot = new Integer[matrix.size() * kertaa][matrix.size() * kertaa];

        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> list = matrix.get(i);
            for (int j = 0; j < list.size(); j++) {
                arvot[i][j] = list.get(j);
            }
        }
        // Juu tää on ihan hirveä tiedetään, oli kiire
        //TODO: refactor
        //TODO: test if this works with bigger @kertaa than 2
        int x = 0;
        int y = 0;
        for (int i = arvot.length / kertaa; i < arvot.length; i++) {
            for (int j = arvot.length / kertaa; j < arvot.length; j++) {
                arvot[i][j] = arvot[x][y];
                arvot[x][j] = arvot[x][y];
                arvot[i][y] = arvot[x][y];
                y++;
            }
            x++;
            y = 0;
        }
        return arvot;
    }

    public static List<List<Integer>> readCVS(String fileName) throws IOException {
        List<List<Integer>> arvot = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
        String line = br.readLine();
        while (line != null) {
            String[] row = line.split(";");
            List<Integer> rowInt = new ArrayList<>();
            for (String s : row) {
                rowInt.add(Integer.parseInt(s));
            }
            // Juu tää on ihan hirveä tiedetään, oli kiire
            //TODO: refactor
            arvot.add(rowInt);
            line = br.readLine();
        }
        br.close();
        return arvot;
    }
}
