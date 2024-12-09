package ticketManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    //instance variables
    private List<Ticket> tickets;
    private int maxCapacity;

    //constructor
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = Collections.synchronizedList(new LinkedList<>());
    }

    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxCapacity) {
            try{
                System.out.println("Waiting for space");
                wait(); //wait until there is space in the pool
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        System.out.println("Added ticket: ID - " + ticket.getTicketID() + " ,Price - " + ticket.getTicketPrice() + " ,Event - " + ticket.getEvent());
        notifyAll();
    }

    //method to remove a ticket from the pool
    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()){
            try {
                System.out.println("Waiting for tickets");
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.remove(0);
        System.out.println("Removed ticket: ID - " + ticket.getTicketID());

        notifyAll();
        return ticket;
    }
}
