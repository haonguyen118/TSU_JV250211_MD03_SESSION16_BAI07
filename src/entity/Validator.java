package entity;

import java.time.Year;
import java.util.Scanner;

public class Validator {
    public static String getString(Scanner scanner, String suggestion) {
        String input = "";
        do {
            System.out.println(suggestion);
            input = scanner.nextLine();
            if(input.isBlank()){
                System.err.println("Vui lòng không để trống");
            }else {
                return input;
            }
        }while (true);
    }

    public static int getInt(Scanner scanner, String suggestion) {
        String input = "";
        do {
            input = getString(scanner, suggestion);
            try {
                return Integer.parseInt(input);
            }catch (Exception e){
                System.out.println("Vui lòng nhập vào một số");
            }
        }while (true);
    }
    public static double getDouble(Scanner scanner, String suggestion) {
        String input = "";
        do {
            input = getString(scanner, suggestion);
            try {
                return Double.parseDouble(input);
            }catch (Exception e){
                System.out.println("Vui lòng nhập vào một số thực");
            }
        }while (true);
    }
    public static Year getYear(Scanner scanner, String suggestion) {
        String input = "";
        do {
            try {
                input = getString(scanner, suggestion);
                return Year.parse(input);

            }catch (Exception e){
                System.err.println("Vui lòng nhập vào năm đúng định dạng (yyyy) và <= 2025");
            }
        }while (true);
    }
}
