/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package seriesmodel;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author melai
 */

// SeriesModel class
class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
    
    // Constructor
    public SeriesModel(String id, String name, String age, String episodes) {
        this.SeriesId = id;
        this.SeriesName = name;
        this.SeriesAge = age;
        this.SeriesNumberOfEpisodes = episodes;
    }
}

class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;
    
    // Constructor
    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Method to capture new series
    public void CaptureSeries() {
        System.out.println("\n**CAPTURE A NEW SERIES**");
        System.out.println("***********");
        
        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();
        
        // Check if the series ID already exists
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(id)) {
                System.out.println("Series with this ID already exists!");
                System.out.println("Enter (1) to launch menu or any other key to exit");
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    return;
                } else {
                    ExitSeriesApplication();
                }
            }
        }
        
        // Get series name
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        
        // Get age restriction with validation
        String age;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            age = scanner.nextLine();
            
            if (!isNumeric(age)) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            
            int ageValue = Integer.parseInt(age);
            if (ageValue < 2 || ageValue > 18) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            } else {
                break;
            }
        }
        
        // Number of episodes
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = scanner.nextLine();
        
        // Create new series and add it to list
        SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
        
        System.out.println("Series processed successfully!!!");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }
    }
    
    // Method to search for a series
    public void SearchSeries() {
        System.out.print("\nEnter the series id to search: ");
        String id = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(id)) {
                System.out.println("---");
                System.out.println("SERIES ID: " + series.SeriesId);
                System.out.println("SERIES NAME: " + series.SeriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
                System.out.println("SERIES NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
                System.out.println("---");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("---");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("---");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }
    }
    
    // Update a series
    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update: ");
        String id = scanner.nextLine();
        
        SeriesModel seriesToUpdate = null;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(id)) {
                seriesToUpdate = series;
                break;
            }
        }
        
        if (seriesToUpdate == null) {
            System.out.println("Series with ID " + id + " not found!");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                ExitSeriesApplication();
            }
            return;
        }
        
        // Get updated series name
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            seriesToUpdate.SeriesName = name;
        }
        
        // Get updated age restriction and validate
        String age;
        while (true) {
            System.out.print("Enter the age restriction: ");
            age = scanner.nextLine();
            
            if (age.isEmpty()) {
                break;
            }
            
            if (!isNumeric(age)) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            
            int ageValue = Integer.parseInt(age);
            if (ageValue < 2 || ageValue > 18) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            } else {
                seriesToUpdate.SeriesAge = age;
                break;
            }
        }
        
        // Get the updated number of episodes
        System.out.print("Enter the number of episodes: ");
        String episodes = scanner.nextLine();
        if (!episodes.isEmpty()) {
            seriesToUpdate.SeriesNumberOfEpisodes = episodes;
        }
        
        System.out.println("Series updated successfully!");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }
    }
    
    // Delete a series
    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        String id = scanner.nextLine();
        
        SeriesModel seriesToDelete = null;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(id)) {
                seriesToDelete = series;
                break;
            }
        }
        
        if (seriesToDelete == null) {
            System.out.println("Series with ID " + id + " not found!");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                ExitSeriesApplication();
            }
            return;
        }
        
        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            seriesList.remove(seriesToDelete);
            System.out.println("---");
            System.out.println("Series with Series Id: " + id + " WAS deleted!");
            System.out.println("---");
        } else {
            System.out.println("Deletion cancelled.");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }
    }
    
    // Method to generate series reports
    public void SeriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series data available to generate report.");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                ExitSeriesApplication();
            }
            return;
        }
        
        System.out.println();
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("--- SERIES ID: " + series.SeriesId);
            System.out.println("SERIES NAME: " + series.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
            System.out.println("---");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }
    }
    
    // Method to exit app
    public void ExitSeriesApplication() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }
    
    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Method to display menu and handle user input
    public void displayMenu() {
        while (true) {
            System.out.println("\nLATEST SERIES - 2025");
            System.out.println("***************");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                ExitSeriesApplication();
            }
            
            System.out.println("\nSelect one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    CaptureSeries();
                    break;
                case "2":
                    SearchSeries();
                    break;
                case "3":
                    UpdateSeries();
                    break;
                case "4":
                    DeleteSeries();
                    break;
                case "5":
                    SeriesReport();
                    break;
                case "6":
                    ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Main class to run the app
public class TVSeriesManagement {
    public static void main(String[] args) {
        Series seriesApp = new Series();
        seriesApp.displayMenu();
    }
}
