package org.example.state;

/**
 * Ticket like a jira ticket.
 */
public class Ticket {
    private TicketState state;
    private final String ticketId;

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
        this.state = new NewState();
    }

    public void setState(TicketState state) {
        this.state = state;
        System.out.println("[" + ticketId + "] State changed to: " + state.getStateName());
    }

    public String getStateName() {
        return state.getStateName();
    }

    // --- Delegate methods to the current state ---
    public void startProgress() {
        state.startProgress(this);
    }

    public void resolve() {
        state.resolve(this);
    }

    public void close() {
        state.close(this);
    }
}
