public class ComplexMatrix {
    private ComplexNumber[][] grid;

    public ComplexMatrix(int rows, int cols) {
        this.grid = new ComplexNumber[rows][cols];
    }

    public ComplexNumber getElement(int row, int col) {
        return this.grid[row][col];
    }

    public int getRows() {
        return this.grid.length;
    }

    public int getCols() {
        return this.grid[0].length;
    }


    // СЛОЖЕНИЕ матриц
    public ComplexMatrix add(ComplexMatrix other) {
        // Проверка на null
        if (other == null) {
            throw new IllegalArgumentException("Матрица для сложения не может быть null");
        }

        // Проверка на несовместимые размеры матриц
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()){
            throw new IllegalArgumentException("Размеры матриц для сложения несовместимы");
        }

        // Алгоритм сложения
        ComplexMatrix result = new ComplexMatrix(other.getRows(), other.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.grid[i][j] = this.grid[i][j].add(other.getElement(i,j));
            }
        }

        return result;
    }


    // УМНОЖЕНИЕ матриц
    public ComplexMatrix multiply(ComplexMatrix other) {
        // Проверка на null
        if (other == null) {
            throw new IllegalArgumentException("Матрица для умножения не может быть null");
        }

        // Проверка на несовместимые размеры матриц
        if (this.getCols() != other.getRows()){
            throw new IllegalArgumentException("Размеры матриц для умножения несовместимы");
        }

        ComplexMatrix result = new ComplexMatrix(this.getRows(), other.getCols());

        // Алгоритм умножения
        for (int i = 0; i < this.getRows(); i++){
            for (int j = 0; j < other.getCols(); j++){
                ComplexNumber current = new ComplexNumber(0,0);
                for (int k = 0; k < this.getCols(); k++){
                    current = current.add(this.getElement(i,k).multiply(other.getElement(k,j)));
                }
                result.grid[i][j] = current;
            }
        }

        return result;
    }


    // ДЕЛЕНИЕ матриц (умножение одной на обратную другой)
    public ComplexMatrix division(ComplexMatrix other) {
        // Проверка на null
        if (other == null) {
            throw new IllegalArgumentException("Матрица для деления не может быть null");
        }

        return this.multiply(other.inverse());
    }

    // Нахождение ОБРАТНОЙ матрицы (вспомогательный метод)
    public ComplexMatrix inverse() {
        // Проверка на квадратную матрицу
        if (this.getCols() != this.getRows()) {
            throw new IllegalArgumentException("Матрица не квадратная, найти обратную нельзя");
        }

        int size = this.getRows();
        ComplexNumber det = this.determinant();

        // Проверка на вырожденность
        if (det.getReal() == 0 && det.getImaginary() == 0) {
            throw new IllegalArgumentException("Матрица вырожденная, найти обратную нельзя");
        }

        // Основной алгоритм
        ComplexMatrix result = new ComplexMatrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ComplexNumber currentElement = this.getMinor(i,j).determinant();
                if ((i + j) % 2 != 0) currentElement = currentElement.negate();
                result.grid[j][i] = currentElement.divide(det);
            }
        }

        return result;
    }


    // ТРАНСПОНИРОВАНИЕ матрицы
    public ComplexMatrix transpose(){
        ComplexMatrix result = new ComplexMatrix(this.getCols(), this.getRows());

        // Алгоритм транспонирования
        for (int i = 0; i < this.getRows(); i++){
            for (int j = 0; j < this.getCols(); j++){
                result.grid[j][i] = this.grid[i][j];
            }
        }

        return result;
    }


    // ОПРЕДЕЛИТЕЛЬ матрицы (по Лапласу)
    public ComplexNumber determinant() {
        // Проверка на квадратную матрицу
        if (this.getCols() != this.getRows()) {
            throw new IllegalArgumentException("Матрица не квадратная, найти определитель невозможно");
        }

        int size = this.getCols();

        // Базовый случай 1х1
        if (size == 1) {
            return this.getElement(0, 0);
        }

        // Базовый случай 2х2 (d1, d2 - диагонали)
        if (size == 2) {
            ComplexNumber d1 = this.getElement(0,0).multiply(this.getElement(1,1));
            ComplexNumber d2 = this.getElement(0,1).multiply(this.getElement(1,0));
            d2 = d2.negate();
            return d1.add(d2);
        }

        ComplexNumber det = new ComplexNumber(0,0);

        // Рекурсивный алгоритм
        for (int i = 0; i < size; i++) {
            // Получение минора
            ComplexMatrix minor = this.getMinor(0,i);

            // Определение знака
            ComplexNumber currentElement = this.getElement(0,i);
            if (i % 2 != 0) currentElement = currentElement.negate();

            // Рекурсия
            det = det.add(currentElement.multiply(minor.determinant()));
        }

        return det;
    }

    // Вспомогательный метод для получения минора
    public ComplexMatrix getMinor(int row, int col) {
        ComplexMatrix minor = new ComplexMatrix(this.getRows()-1, this.getCols()-1);

        int minorRow = 0;
        for (int i = 0; i < this.getRows(); i++) {
            if (i == row) continue;

            int minorCol = 0;
            for (int j = 0; j < this.getCols(); j++) {
                if (j == col) continue;

                minor.grid[minorRow][minorCol] = this.getElement(i,j);
                minorCol++;
            }
            minorRow++;
        }

        return minor;
    }
}
