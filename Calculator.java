import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Simple Calculator");
            System.out.print("Enter the first number: ");
            double firstNumber = scanner.nextDouble();

            System.out.print("Enter the second number: ");
            double secondNumber = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            double result;

            switch (operator) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;
                case '-':
                    result = firstNumber - secondNumber;
                    break;
                case '*':
                    result = firstNumber * secondNumber;
                    break;
                case '/':
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                        return;
                    }
                    break;
                default:
                    System.out.println("Error: Invalid operator.");
                    return;
            }

            System.out.printf("Result: %.2f %c %.2f = %.2f\n", firstNumber, operator, secondNumber, result);
        }
    }
}
