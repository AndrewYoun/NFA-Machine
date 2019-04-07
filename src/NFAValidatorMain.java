import java.util.Scanner;

public class NFAValidatorMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NFA m = new NFA("1");
        while (true) {
            System.out.print("\nEnter a string to validate, or \"quit()\": ");
            String input = scanner.nextLine();
            if (input.equals("quit()"))
                break;
            if (m.isValid(input)) {
                System.out.println("--> M accepts \"" + input + "\".");
            } else {
                System.out.println("--> M rejects \"" + input + "\".");
            }
        }
    }
}
