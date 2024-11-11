package System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to store individual customer reviews
class Review {
    private String type;  
    private String content;

    public Review(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return type + ": " + content;
    }
}

// Class to represent the details of a Movie
class Movie {
    private String title;
    private int duration;  // in minutes
    private String language;
    private String director;
    private List<String> cast;
    private String genre;
    private String plot;
    private List<Review> reviews;

    public Movie(String title, int duration, String language, String director, 
                 List<String> cast, String genre, String plot) {
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.director = director;
        this.cast = cast;
        this.genre = genre;
        this.plot = plot;
        this.reviews = new ArrayList<>();
    }

    // Method to add a review
    public void addReview(Review review) {
        reviews.add(review);
    }

    // Method to display the movie details
    public void displayMovieDetails() {
        System.out.println("\nMovie Title: " + title);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + String.join(", ", cast));
        System.out.println("Genre: " + genre);
        System.out.println("Plot: " + plot);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Language: " + language);
        System.out.println("Reviews:");
        if (reviews.isEmpty()) {
            System.out.println("No reviews yet.");
        } else {
            for (Review review : reviews) {
                System.out.println(review);
            }
        }
    }

    // Getter for title
    public String getTitle() {
        return title;
    }
}

// Cinema class to manage a list of movies
class Cinema {
    private List<Movie> movies;

    public Cinema() {
        this.movies = new ArrayList<>();
    }

    // Method to add a movie to the cinema
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    // Method to display all movies currently showing
    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies are currently playing.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie.getTitle());
            }
        }
    }

    // Method to find a movie by title
    public Movie findMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;  // Movie not found
    }
}

public class MovieDetails {

    public static void main(String[] args) {
        // Create a Cinema instance
        Cinema cinema = new Cinema();

        // Create Movies and Reviews
        Movie leo = new Movie("Leo", 150, "Tamil", "Lokesh Kanagaraj",
                List.of("Vijay", "Trisha", "Sanjay Dutt", "Arjun Sarja"),
                "Action, Thriller", "The film is set in the universe of Lokesh Kanagaraj’s LCU. Leo follows the story of a man whose peaceful life turns upside down due to violent forces.");
        leo.addReview(new Review("Positive", "Fans loved the high-octane action and the dark, gripping narrative."));
        leo.addReview(new Review("Criticisms", "Some viewers felt the film’s narrative was a bit predictable in places."));

        Movie jawan = new Movie("Jawan", 165, "Hindi", "Atlee",
                List.of("Shah Rukh Khan", "Nayanthara", "Vijay Sethupathi"),
                "Action, Drama, Thriller",                "A man on a quest for justice faces emotional and physical challenges while uncovering a larger conspiracy.");
                jawan.addReview(new Review("Positive", "A thrilling roller-coaster ride, Jawan has earned praise for its intense action and gripping storyline."));
                jawan.addReview(new Review("Criticisms", "Some felt that the screenplay was stretched at times."));
        
                Movie rajaRajaChola = new Movie("Raja Raja Chola", 180, "Tamil", "S. P. Jananathan",
                        List.of("Vikram", "Aishwarya Rai Bachchan", "Keerthy Suresh"),
                        "Historical, Drama", "Set during the Chola Empire, this period drama showcases the life of Raja Raja Chola.");
                rajaRajaChola.addReview(new Review("Positive", "The grandeur and scale of the film left a strong impression on audiences."));
                rajaRajaChola.addReview(new Review("Criticisms", "Some felt that the film looked magnificent, but the storyline could have been tighter."));
        
                Movie amaran = new Movie("Amaran", 145, "Tamil", "TBC",
                        List.of("TBC"),
                        "Action, Thriller, Drama", "The film follows a young man’s journey as he takes on an unjust system.");
                amaran.addReview(new Review("Positive", "Audiences have been intrigued by the fresh narrative and strong performances."));
                amaran.addReview(new Review("Criticisms", "Since the film's details are still coming in, critics have mostly shared early excitement."));
        
                Movie kanguva = new Movie("Kanguva", 160, "Tamil", "Siva",
                        List.of("Suriya", "Priyanka Arul Mohan"),
                        "Action, Fantasy", "Set in ancient times, Kanguva tells the story of a warrior who faces powerful foes and challenges.");
                kanguva.addReview(new Review("Positive", "The movie’s massive action sequences and Suriya’s intense performance have caught audiences' attention."));
                kanguva.addReview(new Review("Criticisms", "Some felt the film's reliance on CGI and larger-than-life sequences overshadowed character depth."));
        
                // Add movies to the cinema
                cinema.addMovie(leo);
                cinema.addMovie(jawan);
                cinema.addMovie(rajaRajaChola);
                cinema.addMovie(amaran);
                cinema.addMovie(kanguva);
        
                // Use try-with-resources for Scanner
                try (Scanner scanner = new Scanner(System.in)) {
                    // Display available movies
                    System.out.println("Welcome to the Cinema!\n");
                    System.out.println("Movies currently showing:");
                    cinema.displayMovies();
        
                    // Interactive loop
                    while (true) {
                        System.out.println("\nEnter the movie title to view details (or type 'exit' to quit): ");
                        String title = scanner.nextLine().trim();
        
                        // Exit condition
                        if (title.equalsIgnoreCase("exit")) {
                            System.out.println("Goodbye!");
                            break;
                        }
        
                        // Handle empty input
                        if (title.isEmpty()) {
                            System.out.println("Please enter a valid movie title.\n");
                            continue;
                        }
        
                        // Find the movie and display details
                        Movie movie = cinema.findMovie(title);
                        if (movie != null) {
                            movie.displayMovieDetails();
        
                            // Add a review
                            System.out.println("Do you want to add a review? (yes/no)");
                            String addReview = scanner.nextLine().trim();
                            if (addReview.equalsIgnoreCase("yes")) {
                                System.out.println("Enter review type (Positive or Criticisms): ");
                                String reviewType = scanner.nextLine().trim();
                                
                                // Validate review type
                                while (!reviewType.equalsIgnoreCase("Positive") && !reviewType.equalsIgnoreCase("Criticisms")) {
                                    System.out.println("Invalid review type. Please enter 'Positive' or 'Criticisms': ");
                                    reviewType = scanner.nextLine().trim();
                                }
        
                                System.out.println("Enter review content: ");
                                String reviewContent = scanner.nextLine().trim();
        
                                // Validate review content
                                while (reviewContent.isEmpty()) {
                                    System.out.println("Review content cannot be empty. Please enter review content: ");
                                    reviewContent = scanner.nextLine().trim();
                                }
        
                                movie.addReview(new Review(reviewType, reviewContent));
                                System.out.println("Review added successfully!\n");
                            }
                        } else {
                            System.out.println("Movie not found! Please try again.\n");
                        }
                    }
                }
            }
        }