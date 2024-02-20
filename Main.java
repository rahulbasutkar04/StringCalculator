package org.example;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class Main {

    public boolean empty(String input)
    {
        if(input.isEmpty()) return true;


        return false;
    }

    public int commasPresent(String input)
    {
        String[] data = input.split(",");
        int sum=0;
        for(int i=0;i<data.length;i++)
        {
            sum+=Integer.parseInt(data[i]);
        }

        return sum;
    }

    public double commasPresentWithDouble(String input)
    {
        String[] data = input.split(",");
        double sum = 0;

        for (int i = 0; i < data.length; i++) {

            DecimalFormat df = new DecimalFormat("#.#");
            double num = Double.parseDouble(df.format(Double.parseDouble(data[i])));
            sum += num;
        }

        return sum;
    }

    public int newlineAndcommasPresent(String input) {


        int position=0;
        for(int i=0;i<input.length();i++)
        {
            if(input.charAt(i)=='\n')
            {
                position=i;
            }
        }
        if (input.charAt(1) == '\n') {
            String[] data = input.split("[\\n,]"); // Splitting by "\n" or ","
            int sum = 0;
            for (String str : data) {
                if (!str.isEmpty()) { // Exclude empty strings
                    sum += Integer.parseInt(str);
                }
            }
            return sum;
        }


        else throw new IllegalArgumentException("Number expected but '\\n' found at position "+position);

    }

    public int missingLastNumber(String input)
    {
        if(input.charAt(input.length()-1)==',')
        {
            throw new IllegalArgumentException(" Number expected but EOF found.");
        }

        return commasPresent(input);
    }

    public void NegativeNumber(String input) {
        String[] data = input.split(",");

        for (int i = 0; i < data.length; i++) {
            if (Integer.parseInt(data[i]) < 0) {
                throw new IllegalArgumentException("Negative not allowed: " + data[i]);
            }
        }
    }


    public static String isMultiError(String input) {
        String regex = "^[-+]?\\d+$";

        String[] multiErrorSplit = input.split(",");

        StringBuilder errorMessage = new StringBuilder();
        boolean isError = false;
        for (int i = 0; i < multiErrorSplit.length; i++) {
            if (multiErrorSplit[i].matches(regex) && Integer.parseInt(multiErrorSplit[i]) < 0) {
                errorMessage.append("Negative not allowed: ").append(Integer.parseInt(multiErrorSplit[i])).append("\n");
                isError = true;
            } else if (multiErrorSplit[i].isEmpty()) {
                errorMessage.append("Number expected but ',' found at position ").append(i).append(".\n");
                isError = true;
            }
        }

        return isError ? errorMessage.toString() : null;
    }

    public static boolean customOperator(String input) {
        String delimiter = "";
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\n");
            if (delimiterIndex != -1) {
                delimiter = input.substring(2, delimiterIndex);
            }
        }

        if (!delimiter.isEmpty()) {
            System.out.println("Delimiter is '" + delimiter + "'");
            String[] parts = input.split("\n", 2);
            String numbers = parts[1];

            String[] nums = numbers.split(Pattern.quote(delimiter));

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
        } else {
            System.out.println("Not in rule");
            return false;
        }
    }



}




