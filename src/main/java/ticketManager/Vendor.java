package ticketManager;

public class Vendor implements Runnable{

        //instance variables
        private TicketPool ticketPool;
        private int ticketReleaseRate;

        //constructor
        public Vendor(TicketPool ticketPool, int ticketReleaseRate){
            this.ticketPool = ticketPool;
            this.ticketReleaseRate = ticketReleaseRate;
        }

        @Override
        public void run() {
            int ticketCount = 1;
            while (true){
                try {
                    for (int i = 0; i < ticketReleaseRate; i++){
                        String ticketID = "T" + ticketCount++;
                        Ticket ticket = new Ticket(ticketID, 2000, "Consert");
                        ticketPool.addTicket(ticket); // add ticket to the pool
                    }
                    Thread.sleep(2000); //simulate delay before next ticket release
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    break; //exit loop on interruption
                }
            }
            System.out.println(Thread.currentThread().getName() + " is over");
        }

}
