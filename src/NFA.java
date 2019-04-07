import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

public class NFA {
    private int numStates;
    private HashMap<String, String> states;
    private String[] acceptingStates;

    public NFA() {
        this.numStates = getNumberOfStates();
        this.createStates();
        this.acceptingStates = createAcceptingStates();
    }

    public void validate(String s) {
        // Finish this...
    }

    public int getNumStates() {
        return this.numStates;
    }

    public String[] getAcceptingStates() {
        return this.acceptingStates;
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

    // Creates a hashmap of the form ("old-state, tape-symbol": "new-state"}
    private HashMap<String, String> createStates() {
        HashMap<String, String> states = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter transitions in the form: (old-state, tape-symbol, new-state)");
        System.out.println("Alphabet: {0, 1, -1}, where -1 represents ε\n");

        // Ensures there is the correct number of states.
        for (int i = 1; i <= this.numStates; i ++) {
            // Checks if the input was a 3-tuple.
            while (true) {
                System.out.print("Enter a 3-tuple δ for (q" + i + "): ");
                String[] temp = scanner.nextLine()
                        .replace("(", "")
                        .replace(")", "")
                        .replace(" ", "")
                        .replace("q", "")
                        .split(",");
                if (temp.length == 3 && isKosherInput(temp)) {
                    states.put(temp[0] + ", " + temp[1], temp[2]);
                    break;
                }
                System.out.println("Invalid input.");
            }
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
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    // This method just displays the transitions neatly.
    public String toString() {
        String s = "";
        for (String k : this.states.keySet()) {
            String[] ks = k.split(", ");
            String v = this.states.get(k);
            if (Arrays.asList(this.acceptingStates).contains(ks[0])) {
                s += "((q" + ks[0] + ")) --[";
            } else {
                s += " (q" + ks[0] + ")  --[";
            }
            s += ks[1] + "]--> ";
            if (Arrays.asList(this.acceptingStates).contains(v)) {
                s += "((q" + v + "))";
            } else {
                s += " (q" + v + ") ";
            }
            s += "\n";
        }
        return s;
    }
}
