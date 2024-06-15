
import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the temperature value: ");
            String input = scanner.nextLine().trim();
            String unit = "";
            double temperature = 0;
            int i = input.length() - 1;
            if (Character.isLetter(input.charAt(i))) {
                while (i >= 0 && Character.isLetter(input.charAt(i))) {
                    i--;
                }
                unit = input.substring(i + 1).toUpperCase();
                input = input.substring(0, i + 1);
            } else {
                System.out.print("Enter the unit ('C' for Celsius, 'F' for Fahrenheit, 'K' for Kelvin): ");
                unit = scanner.next().toUpperCase();
            }
            try {
                temperature = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter a number followed by optional unit (e.g., 32, 100C, or 273K).");
                scanner.close();
                return;
            }
            switch (unit) {
                case "C" -> {
                    System.out.println("Fahrenheit: " + celsiusToFahrenheit(temperature));
                    System.out.println("Kelvin: " + celsiusToKelvin(temperature));
                }
                case "F" -> {
                    System.out.println("Celsius: " + fahrenheitToCelsius(temperature));
                    System.out.println("Kelvin: " + fahrenheitToKelvin(temperature));
                }
                case "K" -> {
                    System.out.println("Celsius: " + kelvinToCelsius(temperature));
                    System.out.println("Fahrenheit: " + kelvinToFahrenheit(temperature));
                }
                default -> System.out.println("Invalid unit. Please enter 'C', 'F', or 'K'.");
            }
        }
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }
}
