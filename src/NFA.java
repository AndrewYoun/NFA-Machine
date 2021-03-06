import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class NFA {
    private int numStates;
    private HashMap<String, ArrayList<String>> states;
    private String[] acceptingStates;
    private String qStart;

    public NFA(String qStart) {
        this.numStates = getNumberOfStates();
        this.states = createStates();
        this.acceptingStates = createAcceptingStates();
        this.qStart = qStart;
    }

    // Validates a string, taking a "shot in the dark" when
    // it comes to an NFA with many of the same transitions.
    public boolean isValid(String input) {
        String current = qStart;
        Random rand = new Random();
        for (int i = 0; i < input.length(); i ++) {
            String alpha = input.substring(i, i + 1);
            String key = current + ", " + alpha;
            ArrayList<String> possibleStates = states.get(key);
            int x = rand.nextInt(possibleStates.size());
            if (!states.containsKey(key)) {
                return false;
            }
        }
        if (Arrays.asList(this.acceptingStates).contains(current)) {
            return true;
        }
        return false;
    }

    public String getqStart() {
        return qStart;
    }

    public int getNumStates() {
        return this.numStates;
    }

    public String[] getAcceptingStates() {
        return this.acceptingStates;
    }

    public HashMap<String, ArrayList<String>>  getStates() {
        return this.states;
    }

    // Gets the number of states from the user.
    private int getNumberOfStates() {
        Scanner scanner = new Scanner(System.in);
        int numStates;
        while (true) {
            System.out.print("Enter the number of states for the NFA: ");
            numStates = Integer.parseInt(scanner.next());
            if ((numStates > 0) && (numStates <= 20)) {
                return numStates;
            }
            System.out.println("Supports between 1 and 20 nodes.\n");
        }
    }

    // Creates a hashmap of the form ("old-state, tape-symbol": "new-state"}
    private HashMap<String, ArrayList<String>>  createStates() {
        HashMap<String, ArrayList<String>>  states = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAlphabet: {0, 1, -1}, where -1 represents ε.");
        System.out.println("Type \"ok\" to stop entering transitions.\n");


        // Checks if the input was a 3-tuple.
        while (true) {
            System.out.print("Enter a 3-tuple δ (q-old, symbol, q-new): ");
            String input = scanner.nextLine();
            if (input.equals("ok")) {
                break;
            }
            String[] temp = input
                    .replace("(", "")
                    .replace(")", "")
                    .replace(" ", "")
                    .replace("q", "")
                    .split(",");
            if (temp.length == 3 && isKosherInput(temp)) {
                String focus = temp[0] + ", " + temp[1];
                if (!states.containsKey(focus)) {
                    states.put(focus, new ArrayList<String>());
                }
                    states.get(focus).add(temp[2]);
            }
            else
                System.out.println("Invalid input.");
        }
        return states;
    }

    // Prompts user for accepting states.
    private String[] createAcceptingStates() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a comma delimited list of accepting states: ");
        String temp = scanner.nextLine();
        return temp.replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("q", "")
                .split(",");
    }


    // Checks if the user entered integers.
    private boolean isKosherInput(String[] temp){
        for (String k : temp){
            if (!isInteger(k))
                return false;
        }
        return true;
    }

    // Checks if a string can be cast to an integer.
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
