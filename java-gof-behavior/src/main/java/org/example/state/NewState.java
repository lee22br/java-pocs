package org.example.state;

public class NewState implements TicketState {
    @Override
    public void startProgress(Ticket ticket) {
        System.out.println("Developer assigned. Starting work on ticket.");
        ticket.setState(new InProgressState());
    }

    @Override
    public String getStateName() {
        return "NEW";
    }
}
