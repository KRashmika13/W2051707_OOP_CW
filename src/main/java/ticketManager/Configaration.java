package ticketManager;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void saveToFile(String filename) {
        try(FileWriter writer = new FileWriter(filename)){
            Gson gson = new Gson();
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + filename);
        }catch(IOException e){
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadFromFile(String filename) {
        try(FileReader reader = new FileReader(filename)){
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        }catch (IOException e){
            System.err.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }
}