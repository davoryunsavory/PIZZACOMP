package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import financelib.configure;
import financelib.encode;

public class validation {
    public static int val_int(Scanner sc, int max, String errormessage, String promptquesiton) {
        int output = 0;
        do {
            System.out.println(promptquesiton);

            while (sc.hasNextInt() == false) {
                System.out.println(errormessage);
                sc.next();
            }
            output = sc.nextInt();
        } while (output > max);

        return output;
    }

    public static String val_pass(Scanner sc, String errormessage, String promptquestion) {
        String newpass = null;
        System.out.print("Enter a password --> ");
        String pass = sc.nextLine();
        do {
            System.out.println(promptquestion);
            while (sc.nextLine() != pass) {
                System.out.println(errormessage);
                sc.next();
            }
            newpass = sc.nextLine();
        } while (newpass != pass);
        return newpass;
    }

    public static boolean val_login(Scanner sc, String errormessage, String filepassword, String fileusername) {
        boolean active = true;
        boolean valid = false;
        Path filereason = Paths.get(filepassword);
        long length = 0;
        try {
            length = Files.lines(filereason).count();
        } catch (IOException e) {
            System.out.println("File length not found");
            e.printStackTrace();
        }
        int i = 0;

        while (active == true) {
            System.out.print("input username: ");
            String inputUser = sc.nextLine();
            System.out.print("input password: ");
            String inputPass = sc.nextLine();
            String inputHash = encode.enc_pass(inputPass);
            boolean matchingpass = false;
            boolean matchinguser = false;
            for (i = 0; i < length; i++) {
                if (configure.read(i, filepassword).equals(inputHash)) {
                    matchingpass = true;
                } else {
                    matchingpass = false;
                }
                if (configure.read(i, fileusername).equals(inputUser)) {
                    matchinguser = true;
                } else {
                    matchinguser = false;
                }
            }
            if (matchingpass == true && matchinguser == true) {
                active = false;
                valid = true;
            } else {
                System.out.print(errormessage);
            }
        }
        return valid;
    }

    public static String val_plusminus(Scanner sc, String[] validchar, String errormessage, String promptquesiton) {
        Map<String, Integer> validmap = new HashMap<>();
        String output = null;
        for (int i = 0; i < validchar.length; i++) {
            validmap.put(validchar[i], i);
        }
        do {
            System.out.println(promptquesiton);

            while (sc.hasNextLine() == false) {
                System.out.println(errormessage);
                sc.next();
            }
            output = sc.nextLine();
            if (output.equals("e")) {
                return "e";
            }
        } while (!validmap.containsKey(output));

        return output;
    }
}
