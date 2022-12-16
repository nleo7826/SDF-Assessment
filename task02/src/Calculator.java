package task02.src;

import java.io.Console;

public class Calculator {

    public static void main(String[] args) {

        Console cons = System.console();
        Boolean stop = false;
        Integer result;
        Integer previousResult = 0;
        
        System.out.println("Welcome.");
        while (!stop) {
            String line = cons.readLine("> ");
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Bye bye");
                stop = true;
                break;
            }
            result = calculateResult(line, previousResult);
            previousResult = result;
            System.out.println(result);
        }
    }

    private static Integer calculateResult(String input, Integer previousResult) {

        Integer x = 0;
        Integer y = 0;
        String last = "$last";
        Integer result = 0;
        String[] tokens = input.split(" ");

        if (tokens[0].equals(last)) {
            x = previousResult;
        } else {
            x = Integer.parseInt(tokens[0]);
        }
        if (tokens[2].equals(last)) {
            y = previousResult;
        } else {
            y = Integer.parseInt(tokens[2]);
        }

        switch (tokens[1]) {
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "*":
                result = x * y;
                break;
            case "/":
                result = x / y;
        }
        return result;
    }
}