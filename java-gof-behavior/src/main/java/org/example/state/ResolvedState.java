package org.example.state;

public class ResolvedState implements TicketState {
    @Override
    public void close(Ticket ticket) {
        System.out.println("QA approved the fix. Ticket closed.");
        ticket.setState(new ClosedState());
    }

    @Override
    public void startProgress(Ticket ticket) {
        System.out.println("QA rejected the fix. Reopening ticket.");
        // go back to previous state
        ticket.setState(new InProgressState());
    }

    @Override
    public String getStateName() {
        return "RESOLVED";
    }
}
