package org.example.state;

public class InProgressState implements TicketState {
    @Override
    public void resolve(Ticket ticket) {
        System.out.println("Code committed and reviewed. Ticket resolved.");
        ticket.setState(new ResolvedState());
    }

    @Override
    public String getStateName() {
        return "IN_PROGRESS";
    }
}
