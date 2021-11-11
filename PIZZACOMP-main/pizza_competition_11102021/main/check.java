package main;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class check {
    public static int count = 0;
    public static final char ESC = 27;
    public static Map<String, Integer> othermap = new HashMap<>()
    {
        {
            put("Crust", 1);
            put("Sauce", 1);
            put("Pizza Cheese", 2);
            put("Onion\t", 2);
            put("Green Onion", 2);
            put("Pepperoni", 4);
            put("Mushrooms", 2);
            put("Jalepenos", 2);
            put("Sardines", 4);
            put("Pineapple", 4);
            put("Tofu\t", 2);
            put("Ham\t", 4);
            put("Red Pepper", 4);
            put("Basil\t", 2);
        }
    };

    public static boolean checkifavaliable(String input, Scanner sc, String lc, Console c) {
        String tempinput = input;
        if (input.equals("Regular Crust") || input.equals("Gluten Free")) {
            input = "Crust";
        } else if (input.equals("Pizza Sauce") || input.equals("Garlic Sauce") || input.equals("No Sauce\t")) {
            input = "Sauce";
        }
        if (othermap.containsKey(input)) {
            if (othermap.get(input) > 0) {
                int value = othermap.get(input);
                othermap.replace(input, value - 1);
                switch (lc) {
                    case "crust": display.crustorder.add(tempinput);
                    break;
                    case "sauce": display.sauceorder.add(tempinput);
                    break;
                    case "toppings":
                    count++;
                    if (count > 6) {
                            c.writer().print(ESC + "[1;1H");
                            c.writer().print("Maximum ingredients reached");
                            c.flush();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                    } else {
                        display.toppingsorder.add(tempinput);
                    }
                    
                    
                    break;
                }
                return true;
            }
        }
        return false;
    }

}