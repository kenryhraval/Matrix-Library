package matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner reader = new Scanner(System.in);
    private static List<Matrix> matrices = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        System.out.println("%=%=%=%=% MATRICU KOMANDRINDAS PALĪGS %=%=%=%=%");
        while (true) {
            System.out.println("\nIzvēlne:");
            System.out.println("1. Izveidot matricu");
            System.out.println("2. Pieejamās matricas");
            System.out.println("3. Apēķināt noteicēju");
            System.out.println("4. Saglabāt matricu");
            System.out.println("5. Ielādēt matricu");
            System.out.println("0. Pabeigt");
            System.out.print("# ");

            int choice = reader.nextInt();
            reader.nextLine();

            switch (choice) {
                case 1 -> createMatrix();
                case 2 -> viewMatrices();
                case 3 -> calculateDeterminant();
                case 4 -> System.out.println("Saving not implemented yet");
                case 5 -> System.out.println("Loading not implemented yet");
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
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
