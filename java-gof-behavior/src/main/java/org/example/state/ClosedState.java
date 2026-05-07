package org.example.state;

public class ClosedState implements TicketState {

    // Terminal state. No transitions allowed.
    @Override
    public String getStateName() {
        return "CLOSED";
    }
}
