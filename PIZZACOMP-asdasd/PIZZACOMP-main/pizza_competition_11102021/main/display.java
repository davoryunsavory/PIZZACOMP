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
    public static List<Integer> amount = new ArrayList<>();

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
        crust(c, sc);
    }

    public static void gotopage(Console c, Scanner sc) {
        // print home
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        displayorder(c);
        c.writer().print("1: Select Crust\n");
        c.writer().print("2: Select Sauce\n");
        c.writer().print("3: Select Toppings\n");
        c.writer().print("4: Finish Order\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 4, "Not an option", "Select between 1 - 4: ");

        // director
        switch (option) {
        case 1:
            c.writer().print(ESC + "[2J");
            crust(c, sc);
            break;
        case 2:
            c.writer().print(ESC + "[2J");
            sauce(c, sc);
            break;
        case 3:
            c.writer().print(ESC + "[2J");
            toppings(c, sc);
            
            break;
        case 4:
            c.writer().print(ESC + "[2J");
            finalize(c, sc);
        }
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
        c.writer().print("4: Reset crust\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 4, "Not an option", "Select between 1 - 4: ");

        // director
        switch (option) {
        case 1:
            if (check.checkifavaliable("Regular Crust", sc, "crust", c) == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            sauce(c, sc);
            break;
        case 2:
            if (check.checkifavaliable("Gluten Free", sc, "crust", c) == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            sauce(c, sc);
            break;
        case 3:
            gotopage(c, sc);
            break;
        case 4:
            crustorder.clear();
            check.resetmap("crust");
            crust(c, sc);
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
        c.writer().print("3: No Sauce\t\n");
        c.writer().print("4: Go back to menu\n");
        c.writer().print("5: Reset Sauce\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 5, "Not an option", "Select between 1 - 5: ");

        // director
        switch (option) {
        case 1:
            if (check.checkifavaliable("Pizza Sauce", sc, "sauce", c) == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            toppings(c, sc);
            break;
        case 2:
            if (check.checkifavaliable("Garlic Sauce", sc, "sauce", c) == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            toppings(c, sc);
            break;
        case 3:
            if (check.checkifavaliable("No Sauce\t", sc, "sauce", c) == false) {
                c.writer().print("Item not available");
                c.flush();
            }
            toppings(c, sc);

            c.flush();
            break;
        case 4:
            gotopage(c, sc);
            break;
        case 5: 
            sauceorder.clear();
            check.resetmap("sauce");
            sauce(c, sc);
            break;
        }

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
                put(11, "Red Pepper");
                put(12, "Basil\t");
                put(13, "");
                put(14, "");
                put(15, "");
            }
        };

        // Prints menu toppings
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        showstatus(c, "toppings");
        displayorder(c);
        c.writer().print("1: Pizza Cheese\t2: Onion\t\t3: Green Onion\n");
        c.writer().print("4: Pepporoni\t5: Mushrooms\t\t6: Jalepenos\n");
        c.writer().print("7: Sardines\t8: Pinapple\t\t9: Tofu\n");
        c.writer().print("10: Ham\t\t11: Red Pepper\t\t12: Basil\n");
        c.writer().print("13: Go back to menu\t14: Finish order\t15: Rest Toppings\n");
        c.flush();

        // validation
        int option = validation.val_int(sc, 15, "Not an option", "Select between 1 - 15: ");

        // director
        if (itemmap.containsKey(option)) {
            if (option == 13) {
                gotopage(c, sc);
            } else if (option == 14) {
                
                finalize(c, sc);
            } else if (option == 15) {
                toppingsorder.clear();
                check.count = 0;
                check.resetmap("sauce");
                toppings(c, sc);
            }
            c.writer().print(ESC + "[2J");
            c.flush();
            check.checkifavaliable(itemmap.get(option), sc, "toppings", c);
            toppings(c, sc);
        }

    }

    public static void finalize(Console c, Scanner sc) {
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        c.flush();
        displayorder(c);
        c.writer().print("Are you done with your order?\n");
        c.writer().print("1: Yes\n");
        c.writer().print("2: No\n");
        c.flush();

        int option = validation.val_int(sc, 2, "Not an option", "Select between 1 - 2: ");

        switch(option) {
            case 1: 
            c.writer().println(ESC + "[2J");
            c.writer().print(ESC + "[1;1H");
            displayorder(c);
            c.writer().print("Order has been completed\n");
            c.writer().print("Cook until golden brown and toppings warm\n");
            c.flush();
            System.exit(0);
            break;
            case 2: gotopage(c, sc);
            break;
        }
    }
    public static void showstatus(Console c, String current) {
        c.writer().print(ESC + "[2J");
        c.writer().print(ESC + "[1;1H");
        c.flush();
        if (current.equals("crust")) {
            c.writer().print("(CRUST)━━━━━━━(sauce)━━━━━━━(toppings)\n");
            c.flush();
        } else if (current.equals("sauce")) {
            c.writer().print("(crust)━━━━━━━(SAUCE)━━━━━━━(toppings)\n");
            c.flush();
        } else if (current.equals("toppings")) {
            c.writer().print("(crust)━━━━━━━(sauce)━━━━━━━(TOPPINGS)\n");
            c.flush();
        }
    }

    public static void displayorder(Console c) {
        toppingsorder.sort(null);
        ;
        c.writer().print("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n");
        c.writer().print("┃\t  Current Order\t\t┃\n");
        c.writer().print("┃\t\t\t\t┃\n");
        for (int i = 0; i < crustorder.size(); i++) {
            c.writer().print("┃" + crustorder.get(i) + "\t\t\t┃\n");
        }
        for (int i = 0; i < sauceorder.size(); i++) {
            c.writer().print("┃  " + sauceorder.get(i) + "  \t\t┃\n");
        }
        for (int i = 0; i < toppingsorder.size(); i++) {
            String measure = "";
            measure = getmeasure(toppingsorder,i);
            c.writer().print("┃\t" + toppingsorder.get(i) + "" + measure +"┃\n");
        }
        c.writer().print("┃\t\t\t\t┃\n");
        c.writer().print("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }
    public static Map<String, String> measuremap = new HashMap<>()
    {
        {
            put("Crust", "\t1\t");
            put("Sauce", "\t1/4\t");
            put("Pizza Cheese", "\t1/4\t");
            put("Onion\t", "\t1/8\t");
            put("Green Onion", "\t1/8\t");
            put("Pepperoni", "\t2\t");
            put("Mushrooms", "\t1/8\t");
            put("Jalepenos", "\t1/8\t");
            put("Sardines", "\t1\t");
            put("Pineapple", "\t2\t");
            put("Tofu\t", "\t1/4\t");
            put("Ham\t", "\t4\t");
            put("Red Pepper", "  sprinkle\t");
            put("Basil\t", "    sprinkle\t");
        }
    };
    public static String getmeasure(List<String> order, int location) {
        String measure = "";
            if (measuremap.containsKey(order.get(location))) {
                measure = measuremap.get(order.get(location));
            }
        return measure;
    }

}