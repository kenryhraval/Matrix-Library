package matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner reader = new Scanner(System.in);
    private static List<Matrix> matrices = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        System.out.println("=== Matrix CLI Tool ===");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create matrix");
            System.out.println("2. View matrices");
            System.out.println("3. Calculate determinant");
            System.out.println("4. Save matrix to file");
            System.out.println("5. Load matrix from file");
            System.out.println("0. Exit");
            System.out.print("> ");

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
        System.out.print("Enter dimensions (m n): ");
        int m = reader.nextInt();
        int n = reader.nextInt();

        double[][] arr = new double[m][n];

        System.out.println("Enter elements row by row:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = reader.nextDouble();
            }
        }

        Matrix mat = new Matrix(arr);
        matrices.add(mat);
        System.out.println("Matrix saved as #" + (matrices.size() - 1));
    }

    private static void viewMatrices() {
        if (matrices.isEmpty()) {
            System.out.println("No matrices stored.");
            return;
        }

        for (int i = 0; i < matrices.size(); i++) {
            System.out.println("Matrix #" + i + ":");
            matrices.get(i).print(); // you can add a `print()` method in Matrix
        }
    }

    private static void calculateDeterminant() {
        System.out.print("Choose matrix index: ");
        int idx = reader.nextInt();

        if (idx < 0 || idx >= matrices.size()) {
            System.out.println("Invalid index.");
            return;
        }

        try {
            double det = matrices.get(idx).Determinant();
            System.out.println("Determinant: " + det);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
