package main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class display {

    public static final char ESC = 27;
    public static List<String> crustorder = new ArrayList<>();
    public static List<String> sauceorder = new ArrayList<>();
    public static List<String> toppingsorder = new ArrayList<>();

    public static void main(String argv[]) {
        Console c = System.console();
        c.writer().print(ESC + "[2J");
        c.flush();

        for (int i = 0; i < 100; ++i) {
            // reposition the cursor to 1|1
            c.writer().print(ESC + "[1;1H");
            c.flush();

            c.writer().println("hello " + i);
            c.flush();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread2 thing didnt work");
                e.printStackTrace();
            }
        }

        Scanner sc = new Scanner(System.in);
        gotopage(c, sc);
    }

    public static void gotopage(Console c, Scanner sc) {
        // print home
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        c.writer().print("Options\n");
        c.writer().print("1: Select Crust\n");
        c.writer().print("2: Select Sauce\n");
        c.writer().print("3: Select Toppings\n");
        c.writer().print("4: See Current Order\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 4, "Not an option", "Select between 1 - 4: ");

        // director
        switch (option) {
        case 1:
            c.writer().print(ESC + "[2J");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print("Something f up");
                e.printStackTrace();
            }
            crust(c, sc);
            break;
        case 2:
            c.writer().print(ESC + "[2J");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print("Something f up");
                e.printStackTrace();
            }
            sauce(c, sc);
            break;
        case 3:
            c.writer().print(ESC + "[2J");
            toppings(c, sc);
            break;
        case 4:
            c.writer().print(ESC + "[2J");
            c.writer().println("Going to order");
            gotopage(c, sc);
        }
        c.flush();
    }

    public static void crust(Console c, Scanner sc) {
        // Print menu items
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        showstatus(c, "crust");
        displayorder(c);
        c.writer().print("1: Regular Crust\n");
        c.writer().print("2: Gluten Free Crust\n");
        c.writer().print("3: Go back to menu\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 3, "Not an option", "Select between 1 - 3: ");

        // director
        switch (option) {
        case 1:
            if (check.checkifavaliable("Regular Crust", sc, "crust") == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            crust(c, sc);
            break;
        case 2:
            if (check.checkifavaliable("Gluten Free", sc, "crust") == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            crust(c, sc);
            break;
        case 3:
            gotopage(c, sc);
            break;
        }
        c.flush();
    }

    public static void sauce(Console c, Scanner sc) {
        // Print menu items
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        showstatus(c, "sauce");
        displayorder(c);
        c.writer().print("1: Pizza Sauce\n");
        c.writer().print("2: Garlic Sauce\n");
        c.writer().print("3: Go back to menu\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 3, "Not an option", "Select between 1 - 3: ");

        // director
        switch (option) {
        case 1:
            if (check.checkifavaliable("Pizza Sauce", sc, "sauce") == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            sauce(c, sc);
            break;
        case 2:
            if (check.checkifavaliable("Garlic Sauce", sc, "sauce") == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            sauce(c, sc);
            break;
        case 3:
            gotopage(c, sc);
            break;
        }
        c.flush();
    }

    public static void toppings(Console c, Scanner sc) {
        // map for director
        Map<Integer, String> itemmap = new HashMap<Integer, String>() {
            {
                put(1, "Pizza Cheese");
                put(2, "Onion\t");
                put(3, "Green Onion");
                put(4, "Pepperoni");
                put(5, "Mushrooms");
                put(6, "Jalepenos");
                put(7, "Sardines");
                put(8, "Pineapple");
                put(9, "Tofu\t");
                put(10, "Ham\t");
                put(11, "back");
            }
        };

        // Prints menu toppings
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        showstatus(c, "toppings");
        displayorder(c);
        c.writer().print("1: Pizza Cheese\t2: Onion\t\t3: Green Onion\n");
        c.writer().print("4: Pepporoni\t5: Sliced Mushrooms\t6: Jalepenos\n");
        c.writer().print("7: Sardines\t8: Pinapple\t\t9: Tofu\n");
        c.writer().print("10: Ham\n");
        c.writer().print("11: Go back to menu\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 11, "Not an option", "Select between 1 - 11: ");

        // director
        if (itemmap.containsKey(option)) {
            c.writer().print(ESC + "[2J");
            c.writer().print(ESC + "[1;1H");
            c.flush();
            check.checkifavaliable(itemmap.get(option), sc, "toppings");
            toppings(c, sc);
        }

    }
/*
    public static int selectquantity(Console c, Scanner sc) {
        int amount = 0;
        String[] validchar = { "+", "-" };
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        c.writer().print("Please select amount\n");
        c.writer().print("To add press '+', to subtract press '-'\n");
        c.writer().print("Item selected: Ham Chunks\n");
        c.writer().print("Quantity:  \n");
        c.flush();
        String output = "";
        while (!output.equals("e")) {
            output = validation.val_plusminus(sc, validchar, "Your a dumb ass david", "");
            if (output.equals("+")) {
                amount += 1;
                c.writer().print(ESC + "[4;10H");
                c.writer().print(amount);
                c.writer().print(ESC + "[6;1H");
                c.flush();
            } else {
                amount -= 1;
                c.writer().print(ESC + "[4;10H");
                c.writer().print(amount);
                c.writer().print(ESC + "[6;1H");
                c.flush();
            }
        }

        toppings(c, sc);
        return amount;
    }
*/
    public static void showstatus(Console c, String current) {
        if (current.equals("crust")) {
            c.writer().print("(CRUST) -- (sauce) -- (toppings)\n");
            c.flush();
        } else if (current.equals("sauce")) {
            c.writer().print("(crust) -- (SAUCE) -- (toppings)\n");
            c.flush();
        } else if (current.equals("toppings")) {
            c.writer().print("(crust) -- (sauce) -- (TOPPINGS)\n");
            c.flush();
        }
    }

    public static void displayorder(Console c) {
        c.writer().print("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        c.writer().print("┃\t  Current Order\t\t┃\n");
        c.writer().print("┃\t\t\t\t┃\n");
        for (int i = 0; i < crustorder.size(); i++) {
            c.writer().print("┃" + crustorder.get(i) + "\t\t\t┃\n");
        }
        for (int i = 0; i < sauceorder.size(); i++) {
            c.writer().print("┃" + sauceorder.get(i) + "\t\t\t┃\n");
        }
        for (int i = 0; i < toppingsorder.size(); i++) {
            c.writer().print("┃" + toppingsorder.get(i) + "\t\t\t┃\n");
        }
        c.writer().print("┃\t\t\t\t┃\n");
        c.writer().print("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }
}