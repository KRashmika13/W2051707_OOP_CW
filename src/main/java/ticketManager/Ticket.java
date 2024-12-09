package ticketManager;

public class Ticket {

        //instance variables

        private String ticketID;
        private double ticketPrice;
        private String event;

        //constructor
        public Ticket(String ticketID, double ticketPrice, String event) {
            this.ticketID = ticketID;
            this.ticketPrice = ticketPrice;
            this.event = event;
        }

        public Ticket() {}

        //getters and setters
        public String getTicketID() {
            return ticketID;
        }

        public void setTicketID(String ticketID) {
            this.ticketID = ticketID;
        }

        public double getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
}
