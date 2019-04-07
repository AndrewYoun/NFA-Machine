import java.util.HashMap;
import java.util.Scanner;

public class NFA {
    private int numStates;
    private HashMap<String, String> states;

    public NFA() {
        this.numStates = getNumberOfStates();
        this.createStates();
    }

    public int getNumStates() {
        return this.numStates;
    }

    public HashMap<String, String> getStates() {
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

    // Creates a hashmap of the form ((old-state, tape-symbol): new-state}
    private HashMap<String, String> createStates() {
        HashMap<String, String> states = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter transitions in the form: (old-state, tape-symbol, new-state)");
        System.out.println("Alphabet: {0, 1, -1}, where -1 represents ε\n");
        for (int i = 1; i <= this.numStates; i ++) {
            while (true) {
                System.out.print("Enter δ for (q" + i + "): ");
                String[] temp = scanner.nextLine()
                        .replace("(", "")
                        .replace(")", "")
                        .replace(" ", "")
                        .replace("q", "")
                        .split(",");
                if (temp.length == 3 && isKosherInput(temp))
                    break;
                System.out.println("Invalid input.");
            }
        }
        return states;
    }

    private int[] createAcceptingState() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a comma delimted list of accepting states.");
        System.out.println("Alphabet: {0, 1, -1}, where -1 represents ε\n");
    }
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
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void showNFATransitions() {
        for (String k : this.states.keySet()) {
            String v = this.states.get(k);
            // come back to this...

        }
    }
}
