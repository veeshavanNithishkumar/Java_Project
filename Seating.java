package System;
import java.util.*;

public class Seating {

    public static void main(String[] args) {
        int rows = 10;
        int columns = 10;
    
        // Create a boolean array to track booked seats
        boolean[][] bookedSeats = new boolean[rows][columns];
    
        // Create a scanner object to get user input
        Scanner scanner = new Scanner(System.in);
    
        // Main loop to keep asking if the user wants to select more seats
        while (true) {
            // Show the seating arrangement, highlighting available and booked seats
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
            for (char seat = startSeat; seat <= endSeat; seat++) {
                int seatIndex = seat - 'a';  // Convert seat letter to column index
                if (bookedSeats[selectedRow][seatIndex]) {
                    canBook = false;
                    break;
                }
                selectedSeats.append((selectedRow + 1)).append(seat).append(" ");
            }
    
            if (!canBook) {
                System.out.println("Some of the selected seats are already booked. Please choose different seats.");
                continue;  // Continue if some selected seats are already booked
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
    
            // If the user types "no", exit the loop and end the program
            if (response.equals("no")) {
                System.out.println("Thank you for using the ticket booking system. Goodbye!");
                break;  // Exit the loop and terminate the program
            }
            // If the user types anything other than "no", the loop will continue to ask for more selections
        }
        
        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}
