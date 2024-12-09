package ticketManager;

public class Customer implements Runnable{
    //instance variables
    private TicketPool ticketPool;
    private int customerRetrievalRate; //number of tickets to retrieve customer

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            try{
                for(int i = 0; i < customerRetrievalRate; i++) {
                    Ticket ticket = ticketPool.removeTicket();
                }
                Thread.sleep(2000);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
