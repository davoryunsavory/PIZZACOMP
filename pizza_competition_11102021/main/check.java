package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class check {
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
            put("Dry Red pepper", 4);
            put("Dry Basil", 2);
        }
    };

    public static boolean checkifavaliable(String input, Scanner sc, String lc) {
        String tempinput = input;
        if (input.equals("Regular Crust") || input.equals("Gluten Free")) {
            input = "Crust";
        } else if (input.equals("Pizza Sauce") || input.equals("Garlic sauce")) {
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
                    case "toppings": display.toppingsorder.add(tempinput);
                    break;
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}