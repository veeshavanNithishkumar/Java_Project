package System;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheaterSnackOrder {
    private Map<String, Double> snackMenu;
    private Map<String, Integer> orderSummary;

    public TheaterSnackOrder() {
        // Initialize the snack menu with some snacks and their prices
        snackMenu = new HashMap<>();
        snackMenu.put("Popcorn (Small)", 5.00);
        snackMenu.put("Popcorn (Medium)", 7.00);
        snackMenu.put("Popcorn (Large)", 9.00);
        snackMenu.put("Soda (Small)", 3.00);
        snackMenu.put("Soda (Medium)", 4.00);
        snackMenu.put("Soda (Large)", 5.00);
        snackMenu.put("Candy", 2.50);
        snackMenu.put("Nachos", 4.50);
        
        // Initialize order summary
        orderSummary = new HashMap<>();
    }

    public void orderSnacks(Scanner scanner) {
        System.out.println("Available Snacks:");
        displaySnackMenu();

        double totalCost = 0;
        while (true) {
            System.out.print("Enter the snack you want to order (or type 'done' to finish): ");
            String snackChoice = scanner.nextLine().trim();

            if (snackChoice.equalsIgnoreCase("done")) {
                break; // Exit the loop if the user is done ordering
            }

            if (snackMenu.containsKey(snackChoice)) {
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (quantity > 0) {
                    double cost = snackMenu.get(snackChoice) * quantity;
                    totalCost += cost;
                    orderSummary.put(snackChoice, orderSummary.getOrDefault(snackChoice, 0) + quantity);
                    System.out.println("Successfully added " + quantity + " " + snackChoice + "(s) to your order.");
                } else {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                }
            } else {
                System.out.println("Sorry, we don't have that snack. Please check the available options.");
                displaySnackMenu(); // Display menu again for clarity
            }
        }

        // Print the order summary
        printOrderSummary(totalCost);
    }

    private void displaySnackMenu() {
        for (Map.Entry<String, Double> snack : snackMenu.entrySet()) {
            System.out.println(snack.getKey() + " - $" + snack.getValue());
        }
    }

    private void printOrderSummary(double totalCost) {
        System.out.println("\nYour Snack Order Summary:");
        if (orderSummary.isEmpty()) {
            System.out.println("No snacks ordered.");
        } else {
            for (Map.Entry<String, Integer> entry : orderSummary.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.printf("Your total snack order cost is: $%.2f\n", totalCost);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TheaterSnackOrder snackOrder = new TheaterSnackOrder();
        snackOrder.orderSnacks(scanner);
        scanner.close();
    }
}