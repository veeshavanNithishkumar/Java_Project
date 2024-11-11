package System;

import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TheaterSnackOrder snackOrder = new TheaterSnackOrder();

        // Initialize available movies, showtimes, and ticket prices
        String[] movies = {"Amaran", "Leo", "Jawan"};
        int[] prices = {160, 120, 150};
        String[][] showtimes = {
            {"10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM"},
            {"11:00 AM", "2:00 PM", "5:00 PM", "8:00 PM"},
            {"12:00 PM", "3:00 PM", "6:00 PM", "9:00 PM"}
        };

        System.out.println("Welcome to the Cinema Ticket Booking System!");

        // Prompt user for their name
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.println("Thank you for your booking, " + customerName + "!");

        // Display available movies
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i] + " - $" + prices[i] + " per ticket");
        }

        // Prompt user to select a movie
        System.out.print("Select a movie by entering the movie number: ");
        int movieChoice = scanner.nextInt();

        // Validate movie selection
        if (movieChoice < 1 || movieChoice > movies.length) {
            System.out.println("Invalid selection. Please restart the booking process.");
            scanner.close();
            return;
        }

        // Display available showtimes for the selected movie
        System.out.println("Available Showtimes for " + movies[movieChoice - 1] + ":");
        for (int j = 0; j < showtimes[movieChoice - 1].length; j++) {
            System.out.println((j + 1) + ". " + showtimes[movieChoice - 1][j]);
        }

        // Prompt user to select a showtime
        System.out.print("Select a showtime by entering the showtime number: ");
        int showtimeChoice = scanner.nextInt();

        // Validate showtime selection
        if (showtimeChoice < 1 || showtimeChoice > showtimes[movieChoice - 1].length) {
            System.out.println("Invalid selection. Please restart the booking process.");
            scanner.close();
            return;
        }

        // Store the selected showtime
        String selectedShowtime = showtimes[movieChoice - 1][showtimeChoice - 1];
        System.out.println("You have selected the showtime: " + selectedShowtime);

        // Prompt user to enter the number of tickets
        System.out.print("Enter the number of tickets: ");
        int numberOfTickets = scanner.nextInt();

        // Validate number of tickets
        if (numberOfTickets <= 0) {
            System.out.println("Invalid number of tickets. Please restart the booking process.");
            scanner.close();
            return;
        }

        int rows = 10;
        int columns = 10;

        // Create a boolean array to track booked seats
        boolean[][] bookedSeats = new boolean[rows][columns];

        // Main loop to keep asking if the user wants to select more seats
        while (true) {
            // Show the seating arrangement
            System.out.println("Available Seats Arrangement:");
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    // If the seat is booked, show it as X (booked), otherwise show the seat number
                    if (bookedSeats[row][col]) {
                        System.out.print("X ");  // Mark as booked
                    } else {
                        char seatLabel = (char) ('a' + col);
                        System.out.print((row + 1) + "" + seatLabel + " ");  // Show available seat
                    }
                }
                System.out.println();
            }

            // Ask the user to input a row and the range of seats they want to select
            System.out.print("Enter the row number (1-10): ");
            int selectedRow = scanner.nextInt();

            // Validate the row input
            if (selectedRow < 1 || selectedRow > 10) {
                System.out.println("Invalid row number. Please choose a row between 1 and 10.");
                continue;  // Continue to the next iteration if row input is invalid
            }

            System.out.print("Enter the start seat (a-j): ");
            char startSeat = scanner.next().charAt(0);

            System.out.print("Enter the end seat (a-j): ");
            char endSeat = scanner.next().charAt(0);

            // Validate the start and end seats
            if (startSeat < 'a' || startSeat > 'j' || endSeat < 'a' || endSeat > 'j') {
                System.out.println("Invalid seat letter. Please choose between 'a' and 'j'.");
                continue;  // Continue to the next iteration if seat input is invalid
            }

            // Check if the end seat comes after or is the same as the start seat
            if (startSeat > endSeat) {
                System.out.println("Invalid range. The start seat must be before or equal to the end seat.");
                continue;
            }

            // Convert selected row to index (0-based for the array)
            selectedRow -= 1;

            // Check if the selected seats are available
            boolean canBook = true;
            StringBuilder selectedSeats = new StringBuilder();
            int totalSelectedSeats = 0;

            for (char seat = startSeat; seat <= endSeat; seat++) {
                int seatIndex = seat - 'a';  // Convert seat letter to column index
                if (bookedSeats[selectedRow][seatIndex]) {
                    canBook = false;
                    break;
                }
                selectedSeats.append((selectedRow + 1)).append(seat).append(" ");
                totalSelectedSeats++;
            }

            if (!canBook) {
                System.out.println("Some of the selected seats are already booked. Please choose different seats.");
                continue;  // Continue if some selected seats are already booked
            }

            // Check if the number of selected seats exceeds the number of tickets requested
            if (totalSelectedSeats > numberOfTickets) {
                System.out.println("You cannot select more seats than the number of tickets requested.");
                continue;  // Continue if the selection exceeds the ticket count
            }

            // Mark the selected seats as booked
            for (char seat = startSeat; seat <= endSeat; seat++) {
                int seatIndex = seat - 'a';  // Convert seat letter to column index
                bookedSeats[selectedRow][seatIndex] = true;
            }

            // Display the selected seats
            System.out.println("You have selected: " + selectedSeats.toString().trim());

            // Ask if the user wants to select more seats
            System.out.print("Do you want to select more seats? (yes/no): ");
            String response = scanner.next().toLowerCase();

            // If the user types "no", exit the loop and proceed to snack ordering
            if (response.equals("no")) {
                System.out.println("Thank you for using the ticket booking system. Now, let's proceed to snack ordering.");

                // Proceed to snack ordering
                snackOrder.orderSnacks(scanner); // Pass the scanner to the orderSnacks method

                System.out.println("Thank you for your order! Enjoy your movie!");
                break;  // Exit the loop and terminate the program
            }
            // If the user types anything other than "no", the loop will continue to ask for more selections
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}