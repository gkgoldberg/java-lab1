public class ComplexNumber {
    // Мнимая и действительная части
    private final double real;
    private final double imaginary;

    // Конструктор
    public ComplexNumber (double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Сложение
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    // Умножение
    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(this.real * other.real - this.imaginary * other.imaginary, this.real * other.imaginary + this.imaginary * other.real);
    }

    // Инвертирование
    public ComplexNumber negate() {
        return new ComplexNumber(-this.real, -this.imaginary);
    }
}
