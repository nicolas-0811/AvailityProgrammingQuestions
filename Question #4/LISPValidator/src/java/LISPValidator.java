//Program that validates whether an input LISP code is valid and therefore would compile correctly

import java.io.*;

public class LISPValidator {

    public static void main(String[] args) {
        //Getting the input program from the user
        System.out.println("Please enter a LISP program to validate its parentheses.");
        System.out.println("When you are done typing the program, type the character ~ and then hit enter to finish execution.");

        char ch = '~';
        StringBuffer sb = new StringBuffer();

        try {
            while ((ch = (char) System.in.read()) != '~') {
                sb.append(ch);
            }
        } catch (IOException e) {
            System.out.println(e);
        }


        //Printing the result for the user
        boolean valid = validator(sb.toString());
        if (valid) {
            System.out.println("The program you entered is correct");
        } else {
            System.out.println("The program you entered is not correct");
        }

    }

    public static boolean validator(String input) {
        //Converting string to an array of characters
        char[] charactersList = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            charactersList[i] = input.charAt(i);
        }

        //Iterating thorugh the created array to count the number of opening and closing parentheses.
        //If the number of ( equals the number of ), then the program is correct, and true will be returned.

        int numOpening = 0;
        int numClosing = 0;

        for (int i = 0; i < charactersList.length; i++) {
            if (Character.compare(charactersList[i], '(') == 0) {
                numOpening++;
            }
            if (Character.compare(charactersList[i], ')') == 0) {
                numClosing++;
            }
        }

        if (numOpening == numClosing) {
            return true;
        } else {
            return false;
        }
    }
}