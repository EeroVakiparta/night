package fi.nukkujat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static fi.nukkujat.Main.tulosta;

public class Alylliset {
    static int[] occupiedCols;
    static Integer[] answer;
    static Integer[][] occupiedCells;
    static int jousto;

    public static Integer[] selectTheCells(Integer[][] matrix) {
        jousto = 0;
        int tryCount = 1000;
        occupiedCols = new int[matrix.length];
        boolean answerNotReady = true;
        int size = matrix.length;
        int nullCount = 0;
        boolean optimization = false;

        occupiedCells = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                occupiedCells[i][j] = 0;
            }
        }


        answer = new Integer[size];
        while (answerNotReady) {
            tryCount--;
            answer = new Integer[size];
            nullCount = 0;
            optimization = optimization(matrix, 0, answer);
            for (Integer i : answer) {
                if (i == null) {
                    nullCount++;
                }
            }
            if (nullCount == 0) {
                answerNotReady = false;
            }
            if (tryCount < 0) {
                break;
            }
            jousto++;
            System.out.println("jousto: " + jousto);
        }
        System.out.println("answer ready= " + optimization);
        return answer;
    }

    private static boolean optimization(Integer[][] matrix, int row, Integer[] answer) {
        boolean lihavoitu = true;

        int size = matrix.length;


        if (row == matrix.length) { // If all rows were assigned a cell
            System.out.println("all rows assigned a cell");
            return true;
        }

        for (int col = 0; col < matrix.length; col++) { // Try all columns
            boolean cellOccupiedInOtherQuadrant = false;
            // Onko solu jo varattu
            //System.out.println("Onko solu jo varattu");
            if (occupiedCells[row][col] == 1) {
                cellOccupiedInOtherQuadrant = true;
            }
            // onko solun varjosolu varattu kun alkuperiäinen on ekassa nejännessä
            if (row < size / 2 && col < size / 2) {

                if (occupiedCells[row + size / 2 ][col + size / 2] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }
                if (occupiedCells[row][col + size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }
                if (occupiedCells[row + size / 2 ][col] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }
            }
            // onko solun varjosolu varattu kun alkuperiäinen on tokassa nejännessä
            if (row < size / 2 && col > size / 2) {
                if (occupiedCells[row + size / 2 ][col - size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row][col - size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row + size / 2 ][col] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

            }
            // onko solun varjosolu varattu kun alkuperiäinen on kolmannessa nejännessä
            if (row > size / 2 && col < size / 2) {
                if (occupiedCells[row - size / 2 ][col + size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row][col + size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row - size / 2 ][col] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

            }
            // onko solun varjosolu varattu kun alkuperiäinen on neljännessä nejännessä
            if (row > size / 2 && col > size / 2) {
                if (occupiedCells[row - size / 2 ][col - size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row][col - size / 2 ] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

                if (occupiedCells[row - size / 2 ][col] == 1) {
                    cellOccupiedInOtherQuadrant = true;
                }

            }
            if (cellOccupiedInOtherQuadrant) {
                System.out.println(" solu JO VARATTU ------------------?? ? " + cellOccupiedInOtherQuadrant);
            }



            if ((matrix[row][col] == 0 || matrix[row][col] < jousto) && occupiedCols[col] == 0 && !cellOccupiedInOtherQuadrant) { // If the current cell at column `col` has a value of zero, and the column is not reserved by a previous row
                answer[row] = col; // Assign the current row the current column cell
                occupiedCells[row][col] = 1;
                System.out.println("found optimal value in row:" + row + " - col: " + col + " value= " + matrix[row][col]);
                //Jos valinta on ekassa neljänneksessä
                if (row < size / 2 && col < size / 2) {
                    System.out.println("assinging on 1 q, blocking others");
//                    matrix[row+size/2-1][col+size/2-1] = -1;
//                    matrix[row][col+size/2-1] = -1;
//                    matrix[row+size/2-1][col] = -1;
                    occupiedCells[row + size / 2 ][col + size / 2 ] = 1;
                    occupiedCells[row][col + size / 2 ] = 1;
                    occupiedCells[row + size / 2 ][col] = 1;
                }
                //Jos valinta on tokassa neljänneksessä
                if (row < size / 2 && col > size / 2) {
                    System.out.println("assinging on 2 q, blocking others");
//                    matrix[row+size/2-1][col-size/2-1] = -1;
//                    matrix[row][col-size/2-1] = -1;
//                    matrix[row+size/2-1][col] = -1;
                    occupiedCells[row + size / 2 ][col - size / 2 ] = 1;
                    occupiedCells[row][col - size / 2 ] = 1;
                    occupiedCells[row + size / 2 ][col] = 1;
                }
                //Jos valinta on kolmannessa neljänneksessä
                if (row > size / 2 && col < size / 2) {
                    System.out.println("assinging on 3 q, blocking others");
//                    matrix[row-size/2-1][col+size/2-1] = -1;
//                    matrix[row][col+size/2-1] = -1;
//                    matrix[row-size/2-1][col] = -1;
                    occupiedCells[row - size / 2 ][col + size / 2 ] = 1;
                    occupiedCells[row][col + size / 2 ] = 1;
                    occupiedCells[row - size / 2 ][col] = 1;
                }
                //Jos valinta on neljännessä neljänneksessä
                if (row > size / 2 && col > size / 2) {
                    System.out.println("assinging on 4 q, blocking others");
//                    matrix[row-size/2-1][col-size/2-1] = -1;
//                    matrix[row][col-size/2-1] = -1;
//                    matrix[row-size/2-1][col] = -1;
                    occupiedCells[row - size / 2 ][col - size / 2 ] = 1;
                    occupiedCells[row][col - size / 2 ] = 1;
                    occupiedCells[row - size / 2 ][col] = 1;
                }
                occupiedCols[col] = 1; // Mark the column as reserved
                if (optimization(matrix, row + 1, answer)) { // If the next rows were assigned successfully a cell from a unique column, return true
                    System.out.println("If the next rows were assigned successfully a cell from a unique column, return true");
                    return true;
                }

                occupiedCols[col] = 0; // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
                occupiedCells[row][col] = 0;
                System.out.println("not able to get cell, releasing blocked and occupied");
                if (row < size / 2 && col < size / 2) {
                    System.out.println("released on 1 q, releasing others");
//                    matrix[row+size/2-1][col+size/2-1] = -1;
//                    matrix[row][col+size/2-1] = -1;
//                    matrix[row+size/2-1][col] = -1;
                    occupiedCells[row + size / 2 ][col + size / 2 ] = 0;
                    occupiedCells[row][col + size / 2 ] = 0;
                    occupiedCells[row + size / 2 ][col] = 0;
                }
                //Jos valinta on tokassa neljänneksessä
                if (row < size / 2 && col > size / 2) {
                    System.out.println("released on 2 q, releasing others");
//                    matrix[row+size/2-1][col-size/2-1] = -1;
//                    matrix[row][col-size/2-1] = -1;
//                    matrix[row+size/2-1][col] = -1;
                    occupiedCells[row + size / 2 ][col - size / 2 ] = 0;
                    occupiedCells[row][col - size / 2 ] = 0;
                    occupiedCells[row + size / 2 ][col] = 0;
                }
                //Jos valinta on kolmannessa neljänneksessä
                if (row > size / 2 && col < size / 2) {
                    System.out.println("released on 3 q, releasing others");
//                    matrix[row-size/2-1][col+size/2-1] = -1;
//                    matrix[row][col+size/2-1] = -1;
//                    matrix[row-size/2-1][col] = -1;
                    occupiedCells[row - size / 2 ][col + size / 2 ] = 0;
                    occupiedCells[row][col + size / 2 ] = 0;
                    occupiedCells[row - size / 2][col] = 0;
                }
                //Jos valinta on neljännessä neljänneksessä
                if (row > size / 2 && col > size / 2) {
                    System.out.println("released on 4 q, releasing others");
//                    matrix[row-size/2-1][col-size/2-1] = -1;
//                    matrix[row][col-size/2-1] = -1;
//                    matrix[row-size/2-1][col] = -1;
                    occupiedCells[row - size / 2 ][col - size / 2] = 0;
                    occupiedCells[row][col - size / 2 ] = 0;
                    occupiedCells[row - size / 2 ][col] = 0;
                }
            }
        }
        System.out.println("Retry");
        jousto = jousto + 1;
        return false; // If no cell were assigned for the current row, return false to go back one row to try to assign to it another cell from another column
    }

    /**
     * Overload optimization(int row) method
     *
     * @return true
     */
    public boolean optimization(Integer[][] matrix, int row, int[] answer) {
        return optimization(matrix, 0, answer);
    } //End optimization


    public static boolean isEnoughLinesFound(Integer[][] matrix, List<Viiva> lines) {
        int size = matrix.length;
        System.out.println("isEnoughLinesFound size=" + size + " lines= " + lines.size());
        return lines.size() == size;
    }

    public static Integer[][] makeMoreZeros(Integer[][] matrix, List<Viiva> lines) throws huonotParametritException {
        if (matrix.length != matrix[0].length) {
            throw new huonotParametritException("Only works with matrix with equal rows/colums count =" + matrix.length + " / " + matrix[0].length);
        }
        int size = matrix.length;
        Integer[][] matrixOriginal = new Integer[size][size]; // not the movie
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixOriginal[i][j] = matrix[i][j];
            }
            System.out.println();
        }
        tulosta(matrixOriginal);

        for (Viiva line : lines) {
            for (int i = 0; i < size; i++) {
                int index = line.getLineIndex();
                if (line.isHorizontal()) {
                    matrix[index][i] = matrix[index][i] < 0 ? -3 : -1;
                } else {
                    matrix[i][index] = matrix[i][index] < 0 ? -3 : -2;
                }
            }
        }
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //System.out.printf("%d ", matrix[i][j]);
                if (matrix[i][j] < smallest && matrix[i][j] >= 0) {
                    smallest = matrix[i][j];
                }
            }
            System.out.println();
        }
        System.out.println("Pienin arvo Viivojen ulkopuolella= " + smallest);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", matrix[i][j]);
                if (matrix[i][j] >= 0) {
                    matrix[i][j] = matrix[i][j] - smallest;
                }
            }
            System.out.println();
        }
        System.out.println("After -smalles & +smallest to intersections of lines:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("Injecting altered values with help of original:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //System.out.printf("%d ", matrix[i][j]);
                if (matrix[i][j] == -3) {
                    matrix[i][j] = matrixOriginal[i][j] + smallest;
                    System.out.println("found -3 injecting= " + matrixOriginal[i][j]);
                } else if (matrix[i][j] == -2 || matrix[i][j] == -1) {
                    matrix[i][j] = matrixOriginal[i][j];
                    System.out.println("found -2 or -1 injecting= " + matrixOriginal[i][j]);
                } else {

                }
            }
            System.out.println();
        }

        System.out.println("After zeroing magic is done");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
        return matrix;
    }

    public static List<Viiva> findSmallestAmountOfLinesToCoverAllZeroes(Integer[][] matrix) {
        int size = matrix.length;

        int[] zeroCountInRows = new int[size];
        int[] zeroCountInColums = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    zeroCountInRows[i]++;
                    zeroCountInColums[j]++;
                }
            }
        }

        List<Viiva> viivat = new ArrayList<>(size);

        ViivaType lastInsertedLineType = ViivaType.NULL;
        // While there are 0's to count in either rows or colums...
        while (!isZero(zeroCountInRows) && !isZero(zeroCountInColums)) {
            // Search the largest count of 0's in both arrays
            int max = -1;
            Viiva lineWithMostZeros = null;
            for (int i = 0; i < size; i++) {
                // If exists another count of 0's equal to "max" but in this one has
                // the same direction as the last added line, then replace it with this
                if (zeroCountInRows[i] > max || (zeroCountInRows[i] == max && lastInsertedLineType == ViivaType.RIVI)) {
                    lineWithMostZeros = new Viiva(i, ViivaType.RIVI);
                    max = zeroCountInRows[i];
                }
            }

            for (int i = 0; i < size; i++) {
                // Same as above
                if (zeroCountInColums[i] > max || (zeroCountInColums[i] == max && lastInsertedLineType == ViivaType.SARAKE)) {
                    lineWithMostZeros = new Viiva(i, ViivaType.SARAKE);
                    max = zeroCountInColums[i];
                }
            }

            // Delete the 0 count from the line
            if (lineWithMostZeros.isHorizontal()) {
                zeroCountInRows[lineWithMostZeros.getLineIndex()] = 0;
            } else {
                zeroCountInColums[lineWithMostZeros.getLineIndex()] = 0;
            }

            int index = lineWithMostZeros.getLineIndex();
            if (lineWithMostZeros.isHorizontal()) {
                for (int j = 0; j < size; j++) {
                    if (matrix[index][j] == 0) {
                        zeroCountInColums[j]--;
                    }
                }
            } else {
                for (int j = 0; j < size; j++) {
                    if (matrix[j][index] == 0) {
                        zeroCountInRows[j]--;
                    }
                }
            }

            // Add the line to the list of lines
            viivat.add(lineWithMostZeros);
            lastInsertedLineType = lineWithMostZeros.getLineType();
        }
        return viivat;

    }

    /**
     * Find smallest in row and make it zero integer [ ] [ ].
     * <p>
     * Find the smallest values in row and substract that from all values in row
     * Doing previous conserves the relation between numbers while making smallest zero.
     *
     * @param matrix the matrix
     * @return the integer [ ] [ ]
     */
    public static Integer[][] findSmallestInRowAndMakeItZero(Integer[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            int smallest = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (smallest > matrix[i][j]) {
                    smallest = matrix[i][j];
                }
            }
            for (int j = 0; j < size; j++) {
                matrix[i][j] = matrix[i][j] - smallest;
            }
        }
        return matrix;
    }

    /**
     * Find smallest in column and make it zero integer [ ] [ ].
     * <p>
     * Find the smallest values in column and substract that from all values in column
     * Doing previous conserves the relation between numbers while making smallest zero.
     *
     * @param matrix the matrix
     * @return the integer [ ] [ ]
     */
    public static Integer[][] findSmallestInColumnAndMakeItZero(Integer[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            Integer smallest = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (smallest > matrix[j][i]) {
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
     * <p>
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

    public static boolean isZero(int[] array) {
        for (int e : array) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }
}
