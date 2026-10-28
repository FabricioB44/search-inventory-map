package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

// Starter for Search Inventory Map (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2).
// main is done: it asks for a product id and looks it up in the map.
// Your job is loadInventory(): put products in the map, the product id as the key.
public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What item # are you interested in? ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f",
                        matchedProduct.getName(), matchedProduct.getPrice());
                System.out.println();
            }

            while (true) {
                System.out.println("Do you want to search again (y/n)? ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("n")) {
                    System.out.println("Have a good day!");
                    return;
                } else if (answer.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Invalid answer, please try again.");
                }
            }
        }
    }

    private static void loadInventory(){
        // Your code should be here
        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                Product newProduct = new Product(id, name, price);
                inventory.put(id, newProduct);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

