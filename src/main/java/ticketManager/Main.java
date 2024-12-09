package ticketManager;

import java.util.Scanner;

import java.util.InputMismatchException;

public class Main {

    private static final String config_file = "config.json";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Real-Time Event Ticketing System");

        System.out.println("Please enter the total number of tickets(): ");
        int totalTickets = scan.nextInt();

        System.out.print("Please enter ticket release rate for vendor(): ");
        int ticketReleaseRate = scan.nextInt();

        System.out.println("Enter the customer retrieval rate: ");
        int customerRetrievalRate = scan.nextInt();

        System.out.print("Enter maximum ticket capacity ");
        int maximumTicketCapacity = scan.nextInt();

        //instantiate a configuration object
        Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
        configuration.loadFromFile(config_file);
        if (configuration == null) {
            configuration.saveToFile(config_file);
        }

        TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());

        Thread vendor1 = new Thread(new Vendor(ticketPool, configuration.getTicketReleaseRate()), "Vendor 1");
        vendor1.start();
        Thread vendor2 = new Thread (new Vendor(ticketPool, configuration.getTicketReleaseRate()), "Vendor 2");
        vendor2.start();

        Thread customer1 = new Thread(new Customer(ticketPool, configuration.getCustomerRetrievalRate()), "Customer 1");
        customer1.start();
        Thread customer2 = new Thread(new Customer(ticketPool, configuration.getCustomerRetrievalRate()), "Customer 2");
        customer2.start();

        System.out.println("System start with the following configuration:");
        System.out.println("Total number of tickets: " + configuration.getTotalTickets());
        System.out.println("Ticket release rate for vendor: " + configuration.getTicketReleaseRate());
        System.out.println("Customer retrieval rate: " + configuration.getCustomerRetrievalRate());
        System.out.println("Maximum ticket capacity: " + configuration.getMaxTicketCapacity());

        System.out.println("Click enter to stop the system");
        scan.nextLine();
        scan.nextLine();

        vendor1.interrupt();
        vendor2.interrupt();
        customer1.interrupt();
        customer2.interrupt();

        try {
            vendor1.join();
            vendor2.join();
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("System successfully end");
        scan.close();
    }
    private static int getIntInput(Scanner scan, String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scan.nextInt();
                if (value <= 0) {
                    System.out.println("Value must be a positive integer. Please try again.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scan.next(); // Clear invalid input
            }
        }
        return value;
    }

}
