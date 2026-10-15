import java.util.Scanner;

public class MatrixApp {
    private static final Scanner scanner = new Scanner(System.in);

    private static ComplexMatrix inputMatrix() {
        System.out.print("Введите количество строк:    ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("\n--- Элемент [%d][%d] ---\n", i, j);
                System.out.print("Введите реальную часть: ");
                double real = scanner.nextDouble();
                System.out.print("Введите мнимую часть:   ");
                double imaginary = scanner.nextDouble();

                matrix.inputElement(new ComplexNumber(real, imaginary), i, j);
            }
        }
        return matrix;
    }
}
