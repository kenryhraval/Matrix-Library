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
    private static final String foldername = "data";
    private static final String filename = foldername + "/matrices.txt";

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
        matrices.add(mat);
        System.out.println("Matrica saglabāta kā #" + (matrices.size() - 1));
    }

    private static void viewMatrices() {
        if (matrices.isEmpty()) {
            System.out.println("Nav saglabātu matricu.");
            return;
        }

        for (int i = 0; i < matrices.size(); i++) {
            System.out.println("Matrica #" + i + ":");
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

        try (FileWriter fw = new FileWriter(filename)) {
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
            
            String[] dims = br.readLine().split(" ");
            int m = Integer.parseInt(dims[0]);
            int n = Integer.parseInt(dims[1]);

            double[][] arr = new double[m][n];
            for (int i = 0; i < m; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Double.parseDouble(line[j]);
                }
            }

            matrices.add(new Matrix(arr));
            System.out.println("Matrica ielādēta kā #" + (matrices.size() - 1));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Kļūda ielādējot matricu: " + e.getMessage());
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
}
