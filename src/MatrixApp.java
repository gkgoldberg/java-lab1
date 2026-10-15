import java.util.Scanner;

public class MatrixApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("== Консольный интерфейс для работы с матрицами комплексных чисел ==");

        while (true) {
            System.out.println("\n------- ВЫБОР ОПЕРАЦИИ -------:");
            System.out.println("1. Сложить две матрицы");
            System.out.println("2. Перемножить две матрицы");
            System.out.println("3. Разделить одну матрицу на другую");
            System.out.println("4. Транспонировать матрицу");
            System.out.println("5. Вычислить определитель матрицы");
            System.out.println("0. Выход");
            System.out.print("Введите номер операции: ");

            int choice = scanner.nextInt();
            if (choice == 0) {
                System.out.println("Работа программы завершена.");
                break;
            }

            try {
                switch (choice) {
                    case 1 -> { // Сложение
                        System.out.println("\nВвод матрицы А:");
                        ComplexMatrix a = inputMatrix();
                        System.out.println("Ввод матрицы Б:");
                        ComplexMatrix b = inputMatrix();

                        ComplexMatrix result = a.add(b);
                        System.out.println("\nРезультат сложения:");
                        result.print();
                    }
                    case 2 -> { // Умножение
                        System.out.println("\nВвод матрицы А:");
                        ComplexMatrix a = inputMatrix();
                        System.out.println("Ввод матрицы Б:");
                        ComplexMatrix b = inputMatrix();

                        ComplexMatrix result = a.multiply(b);
                        System.out.println("\nРезультат умножения:");
                        result.print();
                    }
                    case 3 -> { // Деление
                        System.out.println("\nВвод матрицы А (делимое):");
                        ComplexMatrix a = inputMatrix();
                        System.out.println("Ввод матрицы Б (делитель):");
                        ComplexMatrix b = inputMatrix();

                        ComplexMatrix result = a.division(b);
                        System.out.println("\nРезультат деления:");
                        result.print();
                    }
                    case 4 -> { // Транспонирование
                        System.out.println("\nВвод матрицы:");
                        ComplexMatrix a = inputMatrix();

                        ComplexMatrix result = a.transpose();
                        System.out.println("\nТранспонированная матрица:");
                        result.print();
                    }
                    case 5 -> { // Определитель
                        System.out.println("\nВвод матрицы:");
                        ComplexMatrix a = inputMatrix();

                        ComplexNumber det = a.determinant();
                        System.out.println("\nОпределитель матрицы:");
                        if (det.getImaginary() >= 0) {
                            System.out.printf("%.2f + %.2fi\n", det.getReal(), det.getImaginary());
                        } else {
                            System.out.printf("%.2f - %.2fi\n", det.getReal(), Math.abs(det.getImaginary()));
                        }
                    }
                    default -> System.out.println("Неверный пункт меню. Введите одну цифру из предложенных ниже.");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("\nОШИБКА ОПЕРАЦИИ: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // Вспомогательный метод для ввода матрицы
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
