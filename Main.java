package org.example;
import java.util.*;
public class Main {
    public static boolean newLineSeparator(String input) {
        int n = input.length();
        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == '\n' && input.charAt(i + 1) == ',') {
                if (i > 0 && !Character.isDigit(input.charAt(i - 1))) {
                    System.out.println("Number expected but '\\n' found at position: " + i);
                    return false;
                }
            } else if (input.charAt(i) == ',' && input.charAt(i + 1) == '\n') {
                if (!Character.isDigit(input.charAt(i - 1))) {
                    System.out.println("Number expected but ',' found at position: " + i);
                    return false;
                }
            }
        }
        return true;
    }

    //rule 2:Missing number in last position
    public static boolean MissingLastPos(String input) {
        int len = input.length();
        if (len > 0 && input.charAt(len - 1) == ',') {
            System.out.println("Number expected but EOF found");
            return true;
        }
        return false;
    }

    //rule 3:custom operators

    public static boolean customOperator(String input) {
        String delimiter = "";
        int inrule = 0;
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\n");
            if (delimiterIndex != -1) {
                delimiter = input.substring(2, delimiterIndex);
            }
        }
        if (!delimiter.isEmpty()) {
            inrule = 1;
            System.out.println("In rule: Delimiter is '" + delimiter + "'");
        } else {
            System.out.println("Not in rule");
        }

        if (inrule == 1) {
            String[] parts = input.split("\n", 2);
            String numbers = parts[1];

            String[] nums;
            if (delimiter.equals("|")) {
                nums = numbers.split("\\|");
            } else {
                nums = numbers.split(delimiter);
            }

            int sum = 0;
            for (String num : nums) {
                try {
                    sum += Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    System.out.println("'" + delimiter + "' expected but '" + num + "' found.");
                    return false;
                }
            }

            System.out.println("Result: " + sum);
            return true;
        }

        return false;
    }


    //rule4:Negative numbers

    public static boolean isNegative(String input)
    {
        boolean isN=false;
        String[] negativeInt = input.split(",");
        for (int i = 0; i < negativeInt.length; i++) {
            int num1 = Integer.parseInt(negativeInt[i]);
            if (num1 < 0) {
                System.out.println("Negative not allowed: " + num1);
                isN=true;
            }
        }
        if(!isN) {
            return  true;
        }
        return false;

    }


     //rule 5:multi error
     public static boolean isMultiErro(String input) {
         String regex = "^[-+]?\\d+$";

         String[] multiErrorSplit = input.split(",");

         boolean isError = false;
         for (int i = 0; i < multiErrorSplit.length; i++) {
             if (multiErrorSplit[i].matches(regex) && Integer.parseInt(multiErrorSplit[i]) < 0) {
                 System.out.println("Negative not allowed here: " + Integer.parseInt(multiErrorSplit[i]));
                 isError = true;
             } else if (multiErrorSplit[i].isEmpty()) {
                 System.out.println("Number expected at position: " + i + " but found this: ,");
                 isError = true;
             } else {
                 System.out.println("Number expected at position: " + i + " but found this: " + multiErrorSplit[i]);
                 isError = true;
             }
         }

         return isError;
     }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input string:");
        String input = sc.nextLine(); // Read the input as a whole line

        if (input.isEmpty()) {
            System.out.println("No data found");
            return; // Exit the program if no input is provided
        }

        // Check all rules and conditions
        if (!newLineSeparator(input)) {
            return;
        }

        if (MissingLastPos(input)) {
            System.out.println("Missing number in last position");
            return;
        }

        if (customOperator(input)) {
            return;
        }

        if (isNegative(input)) {
            return;
        }

        if (isMultiErro(input)) {
            return;
        }

        // Extract integers and calculate their sum
        String[] numbers = input.split(",");
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        // Print the sum of integers
        System.out.println("Sum of integers: " + sum);
    }

}
