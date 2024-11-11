package System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ticket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        System.out.println("Available Movies:");
        
        // Display available movies
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

        String selectedMovie = movies[movieChoice - 1];
        int ticketPrice = prices[movieChoice - 1];

        // Display available showtimes for the selected movie
        System.out.println("Available Showtimes for " + selectedMovie + ":");
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

        String selectedShowtime = showtimes[movieChoice - 1][showtimeChoice - 1];

        // Prompt user to enter the number of tickets
        System.out.print("Enter the number of tickets: ");
        int numberOfTickets = scanner.nextInt();

        // Initialize 10x10 seating chart with seat labels "1A" to "10J"
        String[][] seats = new String[10][10];
        char seatLetter = 'A';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = (i + 1) + "" + (char)(seatLetter + j); // Row 1 = "1A", "1B"..., Row 2 = "2A", "2B"...
            }
        }

        // Display seating chart
        displaySeats(seats);

        // List to store selected seats
        List<String> selectedSeats = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            System.out.print("Enter seat (e.g., 1A, 2B) for ticket " + (i + 1) + ": ");
            String seatChoice = scanner.next();

            boolean validSeat = false;
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (seats[row][col].equalsIgnoreCase(seatChoice)) {
                        selectedSeats.add(seatChoice.toUpperCase());
                        seats[row][col] = "X"; // Mark seat as booked
                        validSeat = true;
                    }
                }
            }
            if (!validSeat) {
                System.out.println("Invalid or already booked seat. Please try again.");
                i--; // Retry current ticket selection
            } else {
                displaySeats(seats);
            }
        }

        // Calculate total cost
        int totalCost = ticketPrice * numberOfTickets;

        // Confirm booking
        System.out.println("\nBooking Summary:");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Movie: " + selectedMovie);
        System.out.println("Showtime: " + selectedShowtime);
        System.out.println("Seats: " + selectedSeats);
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Total Cost: $" + totalCost);

        System.out.print("Confirm booking? (yes/no): ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Booking confirmed for " + customerName + "! Enjoy the movie!");
        } else {
            System.out.println("Booking canceled.");
        }

        scanner.close();
    }

    // Display seating chart with booked seats marked as "X"
    public static void displaySeats(String[][] seats) {
        System.out.println("\nSeating Chart (X = Booked, Available Seats):");
        for (String[] row : seats) {
            for (String seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
        System.out.println();
}
}

