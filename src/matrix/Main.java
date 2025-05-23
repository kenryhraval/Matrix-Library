package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filename = "data/matrices.txt";

    private static Scanner reader = new Scanner(System.in);
    private static List<Matrix> matrices = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("\n~~~~ MATRICU KOMANDRINDAS PALĪGS ~~~~");

        while (true) {
            System.out.println("\nIzvēlne:");
            System.out.println("(1) Izveidot matricu");
            System.out.println("(2) Pieejamās matricas");
            System.out.println("(3) Apēķināt noteicēju");
            System.out.println("(4) Saglabāt matricu");
            System.out.println("(5) Ielādēt matricu");
            System.out.println("(6) Matricu darbības");
            System.out.println("(0) Pabeigt");

            System.out.println();
            System.out.print("~> ");

            int choice = reader.nextInt();
            reader.nextLine();

            switch (choice) {
                case 1 -> createMatrix();
                case 2 -> viewMatrices();
                case 3 -> calculateDeterminant();
                case 4 -> saveMatrix();
                case 5 -> loadMatrix();
                case 6 -> operateMatrices();
                case 0 -> { System.out.println("Visu labu.."); return; }
                default -> System.out.println("Nederīga izvēle.");
            }
        }
    }
        
    private static void createMatrix() {
        System.out.print("Ievadiet matricas izmērus (m n): ");
        int m = reader.nextInt();
        int n = reader.nextInt();

        double[][] arr = new double[m][n];

        System.out.println("Ievadiet elementus pa rindiņai:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = reader.nextDouble();
            }
        }

        Matrix mat = new Matrix(arr);
        addMatrix(mat);
    }

    private static void addMatrix(Matrix m) {
        matrices.add(m);
        System.out.println("Matrica ievadīta kā #" + (matrices.size() - 1));
    }

    private static void viewMatrices() {
        if (matrices.isEmpty()) {
            System.out.println("Nav pieejama neviena matrica.");
            return;
        }

        for (int i = 0; i < matrices.size(); i++) {
            System.out.println("\nMatrica #" + i + ":");
            matrices.get(i).print();
        }
    }

    private static void saveMatrix() {
        System.out.print("Ievadiet matricas numuru: ");
        int idx = reader.nextInt();
        reader.nextLine();

        while (idx < 0 || idx >= matrices.size()) {
            System.out.print("Nederīgs numurs. Mēģiniet vēlreiz: ");
            idx = reader.nextInt();
            reader.nextLine();
        }

        createFile();

        try (FileWriter fw = new FileWriter(filename, true)) {
            Matrix mat = matrices.get(idx);
            double[][] data = mat.getData();
            int m = data.length;
            int n = data[0].length;
            fw.write(m + " " + n + "\n");
            for (double[] row : data) {
                for (double val : row) {
                    fw.write(val + " ");
                }
                fw.write("\n");
            }
            System.out.println("Matrica saglabāta.");
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot failu: " + e.getMessage());
        }
    }

    private static void loadMatrix() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                count++;

                String[] dims = line.trim().split(" ");
                if (dims.length != 2) {
                    System.out.println("Nederīgs ieraksts.");
                    continue;
                }

                int m = Integer.parseInt(dims[0]);
                int n = Integer.parseInt(dims[1]);

                double[][] arr = new double[m][n];

                for (int i = 0; i < m; i++) {
                    line = br.readLine();
                    if (line == null) {
                        System.out.println("Nederīgs ieraksts.");
                        break;
                    }

                    String[] values = line.trim().split(" ");
                    if (values.length != n) {
                        System.out.println("Nederīgs ieraksts.");
                        break;
                    }

                    for (int j = 0; j < n; j++) {
                        arr[i][j] = Double.parseDouble(values[j]);
                    }
                }

                matrices.add(new Matrix(arr));
                System.out.println("Matrica ielādēta kā #" + (matrices.size() - 1));
            }

            if (count == 0) {
                System.out.println("Datnē nav matricu.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Kļūda ielādējot matricas: " + e.getMessage());
        }
    }


    private static void createFile() {
        File file = new File(filename);
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("Kļūda datnes izveidē: " + e.getMessage());
        }
    }

    private static void calculateDeterminant() {
        System.out.print("Ievadiet matricas numuru: ");
        int idx = reader.nextInt();

        if (idx < 0 || idx >= matrices.size()) {
            System.out.println("Nederīgs numurs.");
            return;
        }

        try {
            double det = matrices.get(idx).Determinant();
            System.out.println("Noteicējs: " + det);
        } catch (Exception e) {
            System.out.println("Kļūda: " + e.getMessage());
        }
    }

    private static void operateMatrices() {
        if (matrices.isEmpty()) {
            System.out.println("Nav pieejama neviena matrica.");
            return;
        }

        System.out.print("Ievadiet darbību izteiksmi (neievadiet neko pamācībai): ");
        String line = reader.nextLine();

        if (line.isEmpty()) {
            printHelp();
            System.out.print("Ievadiet darbību izteiksmi: ");
            line = reader.nextLine();
        }

        int count = 0;
        Integer[] ops = new Integer[2];
        Character[] dar = new Character[1];

        for (int i = 0; i < line.length() && count < 2; i++) {
            char c = line.charAt(i);
            if (c == 'M') {
                int start = i + 1;
                // for multidigit numbers
                int end = start;
                while (end < line.length() && Character.isDigit(line.charAt(end))) end++;
                ops[count++] = Integer.parseInt(line.substring(start, end));
                i = end - 1;

            } else if (c == '*' || c == '+') {
                dar[0] = c;
            }
        }

        System.out.println();
        if (count == 2 && dar[0] != null) {
            if (ops[0] >= matrices.size() || ops[1] >= matrices.size() || ops[0] < 0 || ops[1] < 0) {
                System.out.println("Norādītā matrica neeksistē.");
                return;
            }

            // create a new matrix based on the first operand
            Matrix res = new Matrix(matrices.get(ops[0]));

             try {
                if (dar[0] == '*') res.Times(matrices.get(ops[1]));
                else if (dar[0] == '+') res.Plus(matrices.get(ops[1]));
            } catch (Exception e) {
                System.out.println("Kļūda: " + e.getMessage());
                return;
            }

            res.print();
            // add to the list
            addMatrix(res);

        } else {
            System.out.println("Nederīga izteiksme");
        }
    }

    private static void printHelp() {
        System.out.println("\n       .......................................................");
        System.out.println("      (                                                       )");
        System.out.println("      (   darbību izteiksme sastāv no bināriem operatoriem:   )");
        System.out.println(". o O (   [+] (saskaitīšana)                                  )");
        System.out.println("      (   [*] (reizināšana)                                   )");
        System.out.println("      (                                                       )");
        System.out.println("       ```````````````````````````````````````````````````````  ");

        System.out.println("\n       ..................................................");
        System.out.println("      (                                                  )");
        System.out.println("      (   ...izteiksme sastāv no unāriem operatoriem:    )");
        System.out.println(". o O (   [^T] (transponēšana)                           )");
        System.out.println("      (   [^I] (invertēšana)                             )");
        System.out.println("      (                                                  )");
        System.out.println("       `````````````````````````````````````````````````` ");

        System.out.println("\n       .........................................");
        System.out.println("      (                                         )");
        System.out.println("      (   un no operandiem:                     )");
        System.out.println(". o O (        [M0], [M2], ..., [Mk]            )");
        System.out.println("      (               kur k ir matricu skaits   )");
        System.out.println("      (                                         )");
        System.out.println("       ````````````````````````````````````````` ");

        System.out.println("\n       ...................................");
        System.out.println("      (                                   )");
        System.out.println(". o O (   piemēram, M1^T + (M0 * M3^I)    )");
        System.out.println("      (                                   )");
        System.out.println("       ```````````````````````````````````\n");
    }
}
