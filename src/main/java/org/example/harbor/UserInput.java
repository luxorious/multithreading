package org.example.harbor;

import java.util.Random;
import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

//    incorrect work with Thread
//    public synchronized static String input(String message){
//        System.out.println(message);
//        return scanner.nextLine();
//    }
    public synchronized static String input(String message){
        System.out.println(message);
        int rnd = new Random().nextInt(100);
        System.out.println(rnd);
        return String.valueOf(rnd);
    }
    public static int inputInt(String message){
        while (true) {
            try {
                int number =  Integer.parseInt(input(message));
                if (number > 0){
                    return number;
                } else {
                    System.out.println("number must be > 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number try again");
            }
        }
    }
}
