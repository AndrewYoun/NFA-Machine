# NFA-Machine
The goal of the project is to write a C++ or Java program that takes as input an NFA M and an input
string w, simulates M on w, and outputs ACCEPT if M accepts w, and REJECT if M does not accept w.
You will assume that the tape alphabet is Σ = {0, 1} (use −1 to represent ), and that the NFAA has
at most 20 states. The states are labeled 1, 2, . . . , 20. Suppose that the starting state is 1.
In order to input the NFA you will do the following:
You will ask the user to enter the transition function δ. The user will enter a list of 3-tuples, where
each 3-tuple has the form (old-state, tape-symbol or , new-state ).
Next, you will ask the user to enter the set F of accepting states.
Next you will ask the user to enter the input string w.
Test your program with the NFA from Example 1.35 (see Figure 1.36), page 52-54. Run the NFA on
3 strings that are accepted and 3 strings that are not accepted).