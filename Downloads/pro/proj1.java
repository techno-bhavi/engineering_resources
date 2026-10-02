import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.?/";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("=================================");
        System.out.println("       PASSWORD GENERATOR");
        System.out.println("=================================");

        System.out.print("Enter password length: ");
        int length = scanner.nextInt();

        if (length < 4) {
            System.out.println("Password length must be at least 4.");
            scanner.close();
            return;
        }

        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUppercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLowercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include numbers? (y/n): ");
        boolean useNumbers = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include symbols? (y/n): ");
        boolean useSymbols = scanner.next().equalsIgnoreCase("y");

        StringBuilder characters = new StringBuilder();

        if (useUppercase) {
            characters.append(UPPERCASE);
        }

        if (useLowercase) {
            characters.append(LOWERCASE);
        }

        if (useNumbers) {
            characters.append(NUMBERS);
        }

        if (useSymbols) {
            characters.append(SYMBOLS);
        }

        if (characters.length() == 0) {
            System.out.println("Please select at least one character type.");
            scanner.close();
            return;
        }

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        System.out.println("\nGenerated Password:");
        System.out.println(password);

        scanner.close();
    }
}
